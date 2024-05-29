package org.teamvoided.crystalline_powers.item

import dev.emi.trinkets.api.SlotReference
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.effect.StatusEffect
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.item.ItemStack
import net.minecraft.registry.Holder
import net.minecraft.server.network.ServerPlayerEntity
import org.teamvoided.crystalline_powers.utils.PendentSlot

class StatusEffectPendantItem(settings: Settings, private val effect: Holder<StatusEffect>) : AbstractPendantItem(settings) {

    override fun tickInSlot(stack: ItemStack, slot: PendentSlot, slotReference: SlotReference, entity: LivingEntity) {
        val shouldParticle = entity !is ServerPlayerEntity
        when (slot) {
            PendentSlot.GOLD -> entity.addStatusEffect(
                StatusEffectInstance(effect, 40, 2, false, shouldParticle, false)
            )

            PendentSlot.IRON -> entity.addStatusEffect(
                StatusEffectInstance(effect, 40, 1, false, shouldParticle, false)
            )

            PendentSlot.COPPER -> entity.addStatusEffect(
                StatusEffectInstance(effect, 40, 0, false, shouldParticle, false)
            )
        }
    }
}