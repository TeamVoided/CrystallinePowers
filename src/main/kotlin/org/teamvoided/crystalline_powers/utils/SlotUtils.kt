package org.teamvoided.crystalline_powers.utils

import dev.emi.trinkets.api.SlotReference
import org.teamvoided.crystalline_powers.CrystallinePowers.log

fun getSlot(slot: SlotReference): SlotTier? {
    return when (slot.inventory.slotType.name) {
        "gold_slot" -> SlotTier.GOLD
        "iron_slot" -> SlotTier.IRON
        "copper_slot" -> SlotTier.COPPER
        else -> {
            log.error("Pendant found in slot its not meant to be in, you did a bad!")
            null
        }
    }
}

enum class SlotTier(val id: String) {
    GOLD("gold_slot"),
    IRON("iron_slot"),
    COPPER("copper_slot")
}