package org.teamvoided.crystalline_powers.item

import dev.emi.trinkets.api.SlotReference
import dev.emi.trinkets.api.TrinketItem
import dev.emi.trinkets.api.TrinketsApi
import net.minecraft.client.item.TooltipConfig
import net.minecraft.entity.LivingEntity
import net.minecraft.item.ItemStack
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.text.Text
import net.minecraft.util.Formatting
import org.teamvoided.crystalline_powers.init.CryComponents.COOLDOWN
import org.teamvoided.crystalline_powers.init.CryComponents.addComponent
import org.teamvoided.crystalline_powers.init.CryComponents.getPendedCooldown
import org.teamvoided.crystalline_powers.init.CryComponents.setPendedCooldown
import org.teamvoided.crystalline_powers.utils.PendentSlot
import org.teamvoided.crystalline_powers.utils.getSlot
import kotlin.math.ceil

abstract class AbstractPendantItem(settings: Settings) : TrinketItem(settings) {

    override fun tick(stack: ItemStack, slot: SlotReference, entity: LivingEntity) {
        if (stack.item !is AbstractPendantItem) return
        getSlot(slot)?.let {
            val cooldown = stack.getPendedCooldown()!!
            if (cooldown > 0) stack.setPendedCooldown(cooldown - 1)
            else tickInSlot(stack, it, slot, entity)
        }
    }

    open fun tickInSlot(stack: ItemStack, slot: PendentSlot, slotReference: SlotReference, entity: LivingEntity) {}

    override fun appendTooltip(
        stack: ItemStack, context: TooltipContext, tooltip: MutableList<Text>, config: TooltipConfig
    ) {
        stack.getPendedCooldown()?.let {
            if (it > 0)
                tooltip.add(Text.literal("Pended Cooldown: ${ceil(it / 20.0).toInt()}").formatted(Formatting.GRAY))
        }
    }

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
        return super.getDefaultStack().addComponent(COOLDOWN, 0)
    }
}