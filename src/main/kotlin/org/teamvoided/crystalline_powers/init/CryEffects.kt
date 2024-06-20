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
            0.1,
            EntityAttributeModifier.Operation.ADD_VALUE
        )
    );
    val SOUL_HEALTH = register(
        "soul_health",
        StatusEffect(StatusEffectType.BENEFICIAL, 16515918).addAttributeModifier(
            EntityAttributes.GENERIC_MAX_HEALTH,
            "e5f6df15-a32a-49c1-b01b-204bc0c0787f",
            0.5,
            EntityAttributeModifier.Operation.ADD_VALUE
        )
    );
    val SOUL_SPEED = register(
        "soul_speed", StatusEffect(StatusEffectType.BENEFICIAL, 5637116).addAttributeModifier(
            EntityAttributes.GENERIC_MOVEMENT_SPEED,
            "8c2a3c2c-8bac-4825-9f11-a54f17c32286",
            0.001,
            EntityAttributeModifier.Operation.ADD_VALUE
        )
    )
    val SOUL_ARMOUR = register(
        "soul_armour", StatusEffect(StatusEffectType.BENEFICIAL, 261123).addAttributeModifier(
            EntityAttributes.GENERIC_ARMOR,
            "fe47b018-bd6c-4ccb-a408-c502cd3b1d52",
            0.5,
            EntityAttributeModifier.Operation.ADD_VALUE
        )
    )
    val SOUL_TOUGHNESS = register(
        "soul_toughness", StatusEffect(StatusEffectType.BENEFICIAL, 16548867).addAttributeModifier(
            EntityAttributes.GENERIC_ARMOR_TOUGHNESS,
            "caaa40eb-d129-45fd-b21a-2ea2d871cb18",
            0.1,
            EntityAttributeModifier.Operation.ADD_VALUE
        )
    )
    val NEW_BLOOD = register(
        "new_blood",
        StatusEffect(StatusEffectType.BENEFICIAL, 16515918).addAttributeModifier(
            EntityAttributes.GENERIC_MAX_HEALTH,
            "36353495-8431-4232-80ec-98a9d64c9b41",
            10.0,
            EntityAttributeModifier.Operation.ADD_VALUE
        )
    );

    private fun register(id: String, entry: StatusEffect): Holder<StatusEffect> {
        return Registry.registerHolder(Registries.STATUS_EFFECT, Identifier(id), entry)
    }

    var soulEffects = listOf(
        CryEffects.SOUL_SPEED,
        CryEffects.SOUL_TOUGHNESS,
        CryEffects.SOUL_ARMOUR,
        CryEffects.SOUL_DAMAGE,
        CryEffects.SOUL_HEALTH
    )
}