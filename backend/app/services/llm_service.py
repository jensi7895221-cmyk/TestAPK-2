"""
Kapselt alle Aufrufe an das Sprachmodell (Anthropic Messages API). Ohne
gesetzten LLM_API_KEY liefert jede Funktion weiterhin einen Platzhaltertext,
damit das restliche System auch ohne API Key startet und lauffähig bleibt.

Caching Hinweis: Für häufig wiederkehrende Situationen (zum Beispiel Kommentare
zu ähnlichen Bildthemen) lohnt sich ein einfacher Cache auf Basis von
(persona_id, prompt_hash), um Kosten zu sparen. Ein Platzhalter dafür ist unten
als TODO markiert.
"""
import os
from typing import List, Dict

import httpx

LLM_API_KEY = os.getenv("LLM_API_KEY", "")
LLM_MODEL = os.getenv("LLM_MODEL", "claude-sonnet-5")
ANTHROPIC_API_URL = "https://api.anthropic.com/v1/messages"
ANTHROPIC_VERSION = "2023-06-01"


async def _call_llm(prompt: str, max_tokens: int, fallback: str) -> str:
    """Ruft das Sprachmodell auf. Bei fehlendem API Key oder einem Fehler wird
    der uebergebene Platzhaltertext zurueckgegeben, damit ein Ausfall des LLM
    Providers nie den Scheduler oder eine Anfrage des Nutzers blockiert."""
    if not LLM_API_KEY:
        return fallback

    try:
        async with httpx.AsyncClient(timeout=20.0) as client:
            response = await client.post(
                ANTHROPIC_API_URL,
                headers={
                    "x-api-key": LLM_API_KEY,
                    "anthropic-version": ANTHROPIC_VERSION,
                    "content-type": "application/json",
                },
                json={
                    "model": LLM_MODEL,
                    "max_tokens": max_tokens,
                    "messages": [{"role": "user", "content": prompt}],
                },
            )
            response.raise_for_status()
            data = response.json()
            text = data["content"][0]["text"].strip()
            return text or fallback
    except (httpx.HTTPError, KeyError, IndexError, ValueError):
        # TODO: Fehler geeignet loggen, sobald ein Logging Setup vorhanden ist.
        return fallback


async def generate_caption(persona: dict, topic: str) -> str:
    """
    Erzeugt eine Caption für einen neuen Post einer Persona.
    persona: dict mit Feldern aus personas.json (name, personalityPrompt, toneHints, emojiUsage, ...)
    """
    prompt = _build_caption_prompt(persona, topic)
    fallback = f"[Platzhalter Caption von {persona['name']} zum Thema {topic}]"
    return await _call_llm(prompt, max_tokens=150, fallback=fallback)


async def generate_comment(persona: dict, post_author_name: str, image_topic: str, caption: str) -> str:
    """Erzeugt einen Kommentar einer Persona zu einem fremden oder eigenen Beitrag."""
    prompt = _build_comment_prompt(persona, post_author_name, image_topic, caption)
    fallback = persona.get("commentStyleExample", f"[Platzhalter Kommentar von {persona['name']}]")
    return await _call_llm(prompt, max_tokens=100, fallback=fallback)


async def generate_dm_reply(persona: dict, chat_history: List[Dict[str, str]]) -> str:
    """Erzeugt eine Antwort einer Persona in einem Direktnachrichten Chat."""
    prompt = _build_dm_prompt(persona, chat_history)
    fallback = f"[Platzhalter Antwort von {persona['name']} basierend auf {len(chat_history)} bisherigen Nachrichten]"
    return await _call_llm(prompt, max_tokens=150, fallback=fallback)


async def generate_story_idea(persona: dict) -> dict:
    """Erzeugt eine kurze Story Idee (Bildthema plus Text) für eine Persona."""
    fallback_topic = f"Alltag von {persona['name']}"
    fallback_text = f"[Platzhalter Story Text im Stil von {persona['name']}]"

    prompt = (
        f"Du bist {persona['name']}. {persona.get('personalityPrompt', '')} "
        f"Erzeuge eine kurze Story Idee fuer heute: ein Bildthema (2 bis 4 Woerter) "
        f"und einen kurzen Begleittext (maximal 1 Satz) in deinem Stil. "
        f"Antworte GENAU in diesem Format, ohne weitere Erklaerung:\n"
        f"Thema: <thema>\nText: <text>"
    )
    raw = await _call_llm(prompt, max_tokens=100, fallback="")
    if not raw:
        return {"topic": fallback_topic, "text": fallback_text}

    topic, text = fallback_topic, fallback_text
    for line in raw.splitlines():
        if line.lower().startswith("thema:"):
            topic = line.split(":", 1)[1].strip() or topic
        elif line.lower().startswith("text:"):
            text = line.split(":", 1)[1].strip() or text
    return {"topic": topic, "text": text}


def _build_caption_prompt(persona: dict, topic: str) -> str:
    return (
        f"Du bist {persona['name']}. {persona.get('personalityPrompt', '')} "
        f"Du postest gerade ein Foto zum Thema {topic}. "
        f"Schreibe eine kurze, charaktertypische Caption (1 bis 3 Sätze), "
        f"passend zu deinem Stil: {persona.get('toneHints', '')}. "
        f"Nutze Emojis nur wenn es zu deinem Charakter passt: {persona.get('emojiUsage', '')}."
    )


def _build_comment_prompt(persona: dict, post_author_name: str, image_topic: str, caption: str) -> str:
    return (
        f"Du bist {persona['name']}. {persona.get('personalityPrompt', '')} "
        f"Du siehst folgenden Beitrag von {post_author_name}: "
        f"Bild Thema \"{image_topic}\", Caption: \"{caption}\". "
        f"Schreibe einen kurzen, ehrlichen Kommentar aus deiner Perspektive, maximal 2 Sätze."
    )


def _build_dm_prompt(persona: dict, chat_history: List[Dict[str, str]]) -> str:
    history_text = "\n".join(f"{m['sender']}: {m['text']}" for m in chat_history)
    return (
        f"Du bist {persona['name']}. {persona.get('personalityPrompt', '')} "
        f"Bisheriger Chatverlauf mit dem Nutzer:\n{history_text}\n"
        f"Antworte auf die letzte Nachricht des Nutzers in deinem Stil."
    )
