package org.teamvoided.crystalline_powers.item

import dev.emi.trinkets.api.SlotReference
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.effect.StatusEffect
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.item.ItemStack
import net.minecraft.registry.Holder
import net.minecraft.server.network.ServerPlayerEntity
import org.teamvoided.crystalline_powers.utils.PendentSlot

class StatusEffectPendantItem(settings: Settings, private val effect: Holder<StatusEffect>) :
    AbstractPendantItem(settings) {
    override fun tickInSlot(stack: ItemStack, slot: PendentSlot, slotReference: SlotReference, entity: LivingEntity) {
        if (!entity.world.isClient) {
            val shouldParticle = entity !is ServerPlayerEntity
            val amplifier = when (slot) {
                PendentSlot.GOLD -> 2
                PendentSlot.IRON -> 1
                PendentSlot.COPPER -> 0
            }
            entity.addStatusEffect(
                StatusEffectInstance(effect, 40, amplifier, false, shouldParticle, false)
            )
        }
    }
}