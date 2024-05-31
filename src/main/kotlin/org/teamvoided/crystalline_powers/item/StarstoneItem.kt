package org.teamvoided.crystalline_powers.item

import dev.emi.trinkets.api.SlotReference
import net.minecraft.entity.LivingEntity
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
            ).map { it.value() }
            val posEffect = Registries.STATUS_EFFECT.toList().filter { it.isBeneficial }
                .filter { !blacklist.contains(it) }.random()
            println("pos effect:" + posEffect.name.string)
            val negEffect = Registries.STATUS_EFFECT.toList().filter { !it.isBeneficial }
                .filter { !blacklist.contains(it) }.random()
            println("neg effect:" + negEffect.name.string)
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
            entity.addStatusEffect(StatusEffectInstance(Holder.createDirect(posEffect), 400, x, false, false, true))
            entity.addStatusEffect(StatusEffectInstance(Holder.createDirect(negEffect), 400, x, false, false, true))
            stack.setPendedCooldown(y)
        }
    }
}