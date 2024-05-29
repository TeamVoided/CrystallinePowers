package org.teamvoided.crystalline_powers.utils

import dev.emi.trinkets.api.SlotReference
import org.teamvoided.crystalline_powers.CrystallinePowers.log

fun getSlot(slot: SlotReference): PendentSlot? {
    return when (slot.inventory.slotType.name) {
        "gold_slot" -> PendentSlot.GOLD
        "iron_slot" -> PendentSlot.IRON
        "copper_slot" -> PendentSlot.COPPER
        else -> {
            log.error("Pendant found in slot its not meant to be in, you did a bad!")
            null
        }
    }
}

enum class PendentSlot(val id: String) {
    GOLD("gold_slot"),
    IRON("iron_slot"),
    COPPER("copper_slot")
}