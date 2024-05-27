package org.teamvoided.crystalline_powers.init

import net.minecraft.item.Item
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import org.teamvoided.crystalline_powers.CrystallinePowers.id

object CryItems {
    fun init() {}
    val AMETHYST_PENDANT = register("amethyst_pendant", Item(Item.Settings()))
    fun register(id: String, item: Item): Item {
        return Registry.register(Registries.ITEM, id(id), item)
    }
}