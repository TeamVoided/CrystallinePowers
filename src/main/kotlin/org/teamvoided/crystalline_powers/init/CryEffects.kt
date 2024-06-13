package org.teamvoided.crystalline_powers.init

import net.minecraft.entity.attribute.EntityAttributeModifier
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.effect.StatusEffect
import net.minecraft.entity.effect.StatusEffectType
import net.minecraft.entity.effect.StatusEffects
import net.minecraft.registry.Holder
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.Identifier

object CryEffects {
    fun init() {}
    val SOUL_DAMAGE = register(
        "soul_damage",
        StatusEffect(StatusEffectType.BENEFICIAL, 6684672).addAttributeModifier(
            EntityAttributes.GENERIC_ATTACK_DAMAGE,
            "40d91d12-4552-4941-84e0-fe47c7e8db72",
            1.0,
            EntityAttributeModifier.Operation.ADD_VALUE
        )
    );
    private fun register(id: String, entry: StatusEffect): Holder<StatusEffect> {
        return Registry.registerHolder(Registries.STATUS_EFFECT, Identifier(id), entry)
    }
}