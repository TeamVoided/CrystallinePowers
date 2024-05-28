package org.teamvoided.crystalline_powers.item

import dev.emi.trinkets.api.SlotReference
import dev.emi.trinkets.api.TrinketItem
import dev.emi.trinkets.api.TrinketsApi
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.effect.StatusEffect
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.item.ItemStack
import net.minecraft.registry.Holder
import net.minecraft.server.network.ServerPlayerEntity
import org.teamvoided.crystalline_powers.CrystallinePowers.log

class PendantItem(settings: Settings, val effect: Holder<StatusEffect>) : TrinketItem(settings) {
    override fun tick(stack: ItemStack, slot: SlotReference, entity: LivingEntity) {
        val shouldParticle = entity !is ServerPlayerEntity
        when (slot.inventory.slotType.name) {
            "gold_slot" -> entity.addStatusEffect(StatusEffectInstance(effect, 40, 2, false, shouldParticle, false))
            "iron_slot" -> entity.addStatusEffect(StatusEffectInstance(effect, 40, 1, false, shouldParticle, false))
            "copper_slot" -> entity.addStatusEffect(StatusEffectInstance(effect, 40, 0, false, shouldParticle, false))
            else -> log.error("Pendant found in slot its not meant to be in, you did a bad!")
        }
    }

    override fun postHit(stack: ItemStack, target: LivingEntity, attacker: LivingEntity): Boolean {
        if (attacker is ServerPlayerEntity) {
            val component = TrinketsApi.getTrinketComponent(target)
            if (component.isPresent) {
                if (!component.get().isEquipped(stack.item)) {
                    component.get().inventory["chest"]?.get("copper_slot")?.setStack(0, stack)
                }
            }
        }
        return false
    }
}