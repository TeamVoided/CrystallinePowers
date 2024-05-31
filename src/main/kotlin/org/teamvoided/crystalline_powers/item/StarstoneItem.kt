package org.teamvoided.crystalline_powers.item

import dev.emi.trinkets.api.SlotReference
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.effect.StatusEffects
import net.minecraft.item.ItemStack
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
                StatusEffects.WIND_CHARGED,
                StatusEffects.INSTANT_HEALTH,
            )
            val posEffect =
                Registries.STATUS_EFFECT
                    .holders()
                    .toList()
                    .filter { it.value().isBeneficial }
                    .filter { !blacklist.contains(it) }
                    .random()
            val negEffect =
                Registries.STATUS_EFFECT
                    .holders()
                    .toList()
                    .filter { !it.value().isBeneficial }
                    .filter { !blacklist.contains(it) }
                    .random()
            var amplifier = 0
            var cooldown = 0
            when (slot) {
                PendentSlot.GOLD -> {
                    amplifier = 2
                    cooldown = 100
                }

                PendentSlot.IRON -> {
                    amplifier = 1
                    cooldown = 200
                }

                PendentSlot.COPPER -> {
                    amplifier = 0
                    cooldown = 400
                }
            }
            entity.addStatusEffect(
                StatusEffectInstance(
                    posEffect,
                    400, amplifier,
                    false, false, true
                )
            )
            entity.addStatusEffect(
                StatusEffectInstance(
                    negEffect,
                    400, amplifier,
                    false, false, true
                )
            )
            stack.setPendedCooldown(cooldown)
        }
    }
}