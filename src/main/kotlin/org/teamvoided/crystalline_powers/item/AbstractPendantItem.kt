package org.teamvoided.crystalline_powers.item

import dev.emi.trinkets.api.SlotReference
import dev.emi.trinkets.api.TrinketItem
import dev.emi.trinkets.api.TrinketsApi
import net.minecraft.entity.LivingEntity
import net.minecraft.item.ItemStack
import net.minecraft.server.network.ServerPlayerEntity
import org.teamvoided.crystalline_powers.init.CryComponents.getPendedCooldown
import org.teamvoided.crystalline_powers.init.CryComponents.setPendedCooldown
import org.teamvoided.crystalline_powers.utils.PendentSlot
import org.teamvoided.crystalline_powers.utils.getSlot

abstract class AbstractPendantItem(settings: Settings) : TrinketItem(settings) {

    override fun tick(stack: ItemStack, slot: SlotReference, entity: LivingEntity) {
        stack.getPendedCooldown()?.let { cooldown ->
            if (cooldown > 0) stack.setPendedCooldown(cooldown - 1)
        }
        getSlot(slot)?.let {
            val cooldown = stack.getPendedCooldown()
            if (cooldown == null || cooldown <= 0) tickInSlot(stack, it, slot, entity)
        }
    }

    open fun tickInSlot(stack: ItemStack, slot: PendentSlot, slotReference: SlotReference, entity: LivingEntity) {}

    override fun postHit(stack: ItemStack, target: LivingEntity, attacker: LivingEntity): Boolean {
        if (attacker is ServerPlayerEntity && attacker.isCreative) {
            val component = TrinketsApi.getTrinketComponent(target)
            if (component.isPresent) {
                val chest = component.get().inventory["chest"]!!
                listOf("copper_slot", "iron_slot", "gold_slot").forEach { name ->
                    chest[name]?.let {
                        if (it.isEmpty) {
                            it.setStack(0, stack.copyWithCount(1))
                            return false
                        }
                    }
                }
            }
        }
        return false
    }

    override fun getDefaultStack(): ItemStack {
        return super.getDefaultStack().setPendedCooldown(0)
    }
}