package org.teamvoided.crystalline_powers.item

import dev.emi.trinkets.api.SlotReference
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.item.ItemStack
import net.minecraft.registry.Holder
import net.minecraft.registry.Registries
import org.teamvoided.crystalline_powers.init.CryComponents.setPendedCooldown
import org.teamvoided.crystalline_powers.utils.PendentSlot

class StarstoneItem(settings: Settings) : AbstractPendantItem(settings) {
    override fun tickInSlot(stack: ItemStack, slot: PendentSlot, slotReference: SlotReference, entity: LivingEntity) {
        if (!entity.world.isClient) {
            val poseffect = Registries.STATUS_EFFECT.filter { it.isBeneficial }.random()
            val negeffect = Registries.STATUS_EFFECT.filter { !it.isBeneficial }.random()
            var x = 0
            var y = 0
            when (slot) {
                PendentSlot.GOLD -> {
                    x = 0
                    y = 100
                }

                PendentSlot.IRON -> {
                    x = 1
                    y = 200
                }

                PendentSlot.COPPER -> {
                    x = 2
                    y = 400
                }
            }
            entity.addStatusEffect(StatusEffectInstance(Holder.createDirect(poseffect), 400, x, false, false, true))
            entity.addStatusEffect(StatusEffectInstance(Holder.createDirect(negeffect), 400, x, false, false, true))
            stack.setPendedCooldown(y)
        }
    }
}