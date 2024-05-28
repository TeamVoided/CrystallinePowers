package org.teamvoided.crystalline_powers.item

import dev.emi.trinkets.api.SlotReference
import dev.emi.trinkets.api.TrinketItem
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.effect.StatusEffect
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.effect.StatusEffects
import net.minecraft.item.ItemStack
import net.minecraft.registry.Holder
import org.teamvoided.crystalline_powers.CrystallinePowers.log

class PendantItem(settings: Settings, val effect: Holder<StatusEffect>) : TrinketItem(settings) {
    override fun tick(stack: ItemStack, slot: SlotReference, entity: LivingEntity) {
        when(slot.inventory.slotType.name) {
            "gold_slot" -> entity.addStatusEffect(StatusEffectInstance(effect, 40, 2))
            "iron_slot" -> entity.addStatusEffect(StatusEffectInstance(effect, 40, 1))
            "copper_slot" -> entity.addStatusEffect(StatusEffectInstance(effect, 40, 0))
            else -> log.error("Pendant found in slot its not meant to be in, you did a bad!")
        }
    }
}