"""
Lädt die statischen Persona Definitionen aus data/personas.json.
Wird beim Start der App genutzt, um fehlende Personas als User Einträge
in der Datenbank anzulegen (Seeding), und vom Scheduler, um an die
Verhaltensparameter (engagementRate, activeHours, ...) heranzukommen.
"""
import json
from pathlib import Path
from functools import lru_cache

PERSONAS_FILE = Path(__file__).parent / "data" / "personas.json"


@lru_cache(maxsize=1)
def load_personas() -> list[dict]:
    with open(PERSONAS_FILE, "r", encoding="utf-8") as f:
        data = json.load(f)
    return data["personas"]


def get_persona_by_id(persona_id: str) -> dict | None:
    for p in load_personas():
        if p["id"] == persona_id:
            return p
    return None
