package org.teamvoided.crystalline_powers.init

import net.minecraft.entity.effect.StatusEffects
import net.minecraft.item.Item
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import org.teamvoided.crystalline_powers.CrystallinePowers.id
import org.teamvoided.crystalline_powers.item.StatusEffectPendantItem

object CryItems {
    fun init() {}
    val AMETHYST_PENDANT = register("amethyst_pendant", StatusEffectPendantItem(Item.Settings(), StatusEffects.HASTE))
    fun register(id: String, item: Item): Item {
        return Registry.register(Registries.ITEM, id(id), item)
    }
}