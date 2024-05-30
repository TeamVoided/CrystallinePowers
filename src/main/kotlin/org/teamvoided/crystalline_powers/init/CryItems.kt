package org.teamvoided.crystalline_powers.init

import net.minecraft.entity.effect.StatusEffects
import net.minecraft.item.Item
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import org.teamvoided.crystalline_powers.CrystallinePowers.id
import org.teamvoided.crystalline_powers.item.AbstractPendantItem
import org.teamvoided.crystalline_powers.item.LeafstoneItem
import org.teamvoided.crystalline_powers.item.StatusEffectPendantItem

object CryItems {
    fun init() {}
    val AMETHYST_PENDANT = register("amethyst_pendant", LeafstoneItem(Item.Settings().maxCount(1)))
    val BLOODSTONE = register("bloodstone", StatusEffectPendantItem(Item.Settings().maxCount(1), StatusEffects.OOZING))
    val SOULSTONE = register("soulstone", StatusEffectPendantItem(Item.Settings().maxCount(1), StatusEffects.INSTANT_DAMAGE))
    fun register(id: String, item: Item): Item {
        return Registry.register(Registries.ITEM, id(id), item)
    }
}