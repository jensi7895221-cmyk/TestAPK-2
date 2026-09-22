from fastapi import APIRouter, Depends, HTTPException
from sqlalchemy.orm import Session

from app import models, schemas
from app.database import get_db
from app.services import llm_service, notification_service
from app.personas_loader import get_persona_by_id

router = APIRouter(prefix="/messages", tags=["messages"])


@router.post("", response_model=schemas.MessageOut)
async def send_message(payload: schemas.MessageCreate, db: Session = Depends(get_db)):
    """
    Sendet eine Nachricht. Wenn der Empfänger eine Persona ist, wird direkt im
    Anschluss eine Antwort generiert und ebenfalls gespeichert, damit der Chat
    sich für den Nutzer wie ein echtes Gespräch anfühlt. In der Produktivversion
    sollte diese Antwort mit einer kleinen, zufälligen Verzögerung im Hintergrund
    erzeugt werden, statt synchron im selben Request.
    """
    sender = db.query(models.User).filter(models.User.id == payload.sender_id).first()
    receiver = db.query(models.User).filter(models.User.id == payload.receiver_id).first()
    if not sender or not receiver:
        raise HTTPException(status_code=404, detail="Sender oder Empfänger nicht gefunden")

    message = models.Message(
        sender_id=payload.sender_id,
        receiver_id=payload.receiver_id,
        text=payload.text,
    )
    db.add(message)
    db.commit()
    db.refresh(message)

    if not receiver.is_human:
        await _trigger_persona_reply(db, persona_user=receiver, human_user=sender)

    return message


async def _trigger_persona_reply(db: Session, persona_user: models.User, human_user: models.User) -> None:
    persona = get_persona_by_id(persona_user.id)
    if not persona:
        return

    history = (
        db.query(models.Message)
        .filter(
            (
                (models.Message.sender_id == human_user.id)
                & (models.Message.receiver_id == persona_user.id)
            )
            | (
                (models.Message.sender_id == persona_user.id)
                & (models.Message.receiver_id == human_user.id)
            )
        )
        .order_by(models.Message.created_at.asc())
        .all()
    )
    chat_history = [
        {"sender": human_user.name if m.sender_id == human_user.id else persona_user.name, "text": m.text}
        for m in history
    ]

    reply_text = await llm_service.generate_dm_reply(persona, chat_history)

    reply = models.Message(sender_id=persona_user.id, receiver_id=human_user.id, text=reply_text)
    db.add(reply)
    db.commit()

    await notification_service.notify_human("message", persona_user.name, reply_text)


@router.get("/chat/{user_a_id}/{user_b_id}", response_model=list[schemas.MessageOut])
def get_chat_history(user_a_id: str, user_b_id: str, db: Session = Depends(get_db)):
    messages = (
        db.query(models.Message)
        .filter(
            ((models.Message.sender_id == user_a_id) & (models.Message.receiver_id == user_b_id))
            | ((models.Message.sender_id == user_b_id) & (models.Message.receiver_id == user_a_id))
        )
        .order_by(models.Message.created_at.asc())
        .all()
    )
    return messages
