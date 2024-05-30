package org.teamvoided.crystalline_powers.item

import dev.emi.trinkets.api.SlotReference
import net.minecraft.entity.LivingEntity
import net.minecraft.item.ItemStack
import org.teamvoided.crystalline_powers.init.CryComponents.setPendedCooldown
import org.teamvoided.crystalline_powers.utils.PendentSlot

class LeafstoneItem(settings: Settings) : AbstractPendantItem(settings) {
    override fun tickInSlot(stack: ItemStack, slot: PendentSlot, slotReference: SlotReference, entity: LivingEntity) {
        if(!entity.world.isClient) {
            val x = entity.getStatusEffects().filter { !it.effectType.value().isBeneficial }
            if (x.isNotEmpty()) {
                val y = x.random().effectType
                entity.removeStatusEffect(y)
                when (slot) {
                    PendentSlot.GOLD -> stack.setPendedCooldown(100)
                    PendentSlot.IRON -> stack.setPendedCooldown(200)
                    PendentSlot.COPPER -> stack.setPendedCooldown(300)
                }
            }
        }
        super.tickInSlot(stack, slot, slotReference, entity)
    }
}