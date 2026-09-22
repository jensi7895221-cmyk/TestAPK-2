"""
Beschafft Bilder für neue Persona Posts und Stories.
Zwei Modi als Platzhalter vorgesehen: Bildgenerierung über ein externes Modell,
oder Ziehen aus einem kuratierten Bildpool je Kategorie. Der Modus wird über
IMAGE_SOURCE_MODE gesteuert, Standard ist der Bildpool, da er ohne zusätzliche
API Kosten läuft.
"""
import os
import random

IMAGE_SOURCE_MODE = os.getenv("IMAGE_SOURCE_MODE", "pool")  # "pool" oder "generate"

# Platzhalter Bildpool je Kategorie. In der echten Umsetzung durch eine
# kuratierte, lizenzsichere Bildsammlung oder einen Cloud Storage Bucket ersetzen.
IMAGE_POOL_BY_CATEGORY = {
    "Reisen": ["https://picsum.photos/seed/travel1/800", "https://picsum.photos/seed/travel2/800"],
    "Kochen": ["https://picsum.photos/seed/food1/800", "https://picsum.photos/seed/food2/800"],
    "Fitness": ["https://picsum.photos/seed/fitness1/800"],
    "Kunst": ["https://picsum.photos/seed/art1/800"],
    "Technik": ["https://picsum.photos/seed/tech1/800"],
    "default": ["https://picsum.photos/seed/default1/800"],
}


async def get_image_for_post(category: str, topic: str) -> str:
    """Liefert eine Bild URL für einen neuen Post, abhängig vom konfigurierten Modus."""
    if IMAGE_SOURCE_MODE == "generate":
        return await _generate_image(category, topic)
    return _pick_from_pool(category)


async def _generate_image(category: str, topic: str) -> str:
    # TODO: Anbindung an ein Bildgenerierungsmodell einbauen, sobald feststeht,
    # welcher Anbieter genutzt wird (Kosten und Lizenzfragen beachten).
    return _pick_from_pool(category)


def _pick_from_pool(category: str) -> str:
    pool = IMAGE_POOL_BY_CATEGORY.get(category, IMAGE_POOL_BY_CATEGORY["default"])
    return random.choice(pool)
