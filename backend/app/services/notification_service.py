"""
Platzhalter für Push Benachrichtigungen, wenn ein KI Nutzer den echten Nutzer
liked, kommentiert oder anschreibt. In der echten Umsetzung würde hier
Firebase Cloud Messaging (FCM) angebunden, das Android Projekt bringt dafür
bereits den passenden Namen (notify_human) mit.
"""


async def notify_human(event_type: str, actor_name: str, detail: str) -> None:
    """
    event_type: "like" | "comment" | "message" | "follow"
    actor_name: Name der Persona, die die Aktion ausgelöst hat
    detail: kurzer Zusatztext, zum Beispiel der Kommentartext
    """
    # TODO: FCM Push Nachricht senden, sobald das Android Projekt ein
    # Gerätetoken beim Backend registriert (siehe /users/{id}/device-token Route,
    # die als nächster Ausbauschritt vorgesehen ist).
    print(f"[Push Platzhalter] {event_type} von {actor_name}: {detail}")
