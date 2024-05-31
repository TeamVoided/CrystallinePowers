package org.teamvoided.crystalline_powers.item

import dev.emi.trinkets.api.SlotReference
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.effect.StatusEffect
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.effect.StatusEffects
import net.minecraft.item.ItemStack
import net.minecraft.registry.Holder
import net.minecraft.registry.Registries
import org.teamvoided.crystalline_powers.init.CryComponents.setPendedCooldown
import org.teamvoided.crystalline_powers.utils.PendentSlot

class StarstoneItem(settings: Settings) : AbstractPendantItem(settings) {
    override fun tickInSlot(stack: ItemStack, slot: PendentSlot, slotReference: SlotReference, entity: LivingEntity) {
        if (!entity.world.isClient) {
            val blacklist = listOf(
                StatusEffects.INSTANT_DAMAGE,
                StatusEffects.OOZING,
                StatusEffects.INFESTED,
                StatusEffects.WEAVING,
                StatusEffects.BAD_OMEN,
                StatusEffects.TRIAL_OMEN,
                StatusEffects.RAID_OMEN,
                StatusEffects.WIND_CHARGED
            )
            val poseffect = Registries.STATUS_EFFECT.filter { it.isBeneficial }
                .filter { !blacklist.contains(Holder.createDirect(it)) }.random()
            println("pos effect:" + poseffect.name.string)
            val negeffect = Registries.STATUS_EFFECT.filter { !it.isBeneficial }
                .filter { !blacklist.contains(Holder.createDirect(it)) }.random()
            println("neg effect:" + negeffect.name.string)
            var x = 0
            var y = 0
            when (slot) {
                PendentSlot.GOLD -> {
                    x = 2
                    y = 100
                }

                PendentSlot.IRON -> {
                    x = 1
                    y = 200
                }

                PendentSlot.COPPER -> {
                    x = 0
                    y = 400
                }
            }
            entity.addStatusEffect(StatusEffectInstance(Holder.createDirect(poseffect), 400, x, false, false, true))
            entity.addStatusEffect(StatusEffectInstance(Holder.createDirect(negeffect), 400, x, false, false, true))
            stack.setPendedCooldown(y)
        }
    }
}