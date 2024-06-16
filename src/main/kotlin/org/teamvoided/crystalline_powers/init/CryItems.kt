package org.teamvoided.crystalline_powers.init

import net.minecraft.entity.effect.StatusEffects
import net.minecraft.item.Item
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import org.teamvoided.crystalline_powers.CrystallinePowers.id
import org.teamvoided.crystalline_powers.item.*

object CryItems {
    fun init() {}
    val AMETHYST_PENDANT = register("amethyst_pendant", StarstoneItem(Item.Settings().maxCount(1)))
    val BLOODSTONE = register("bloodstone", StatusEffectPendantItem(Item.Settings().maxCount(1), StatusEffects.OOZING))
    val SOULSTONE = register("soulstone", SoulstoneItem(Item.Settings().maxCount(1)))
    val LEAFSTONE = register("leafstone", LeafstoneItem(Item.Settings().maxCount(1)))
    val STARSTONE = register("starstone", StarstoneItem(Item.Settings().maxCount(1)))
    val GOLDINE = register("goldine", StatusEffectPendantItem(Item.Settings().maxCount(1), StatusEffects.HASTE))
    fun register(id: String, item: Item): Item {
        return Registry.register(Registries.ITEM, id(id), item)
    }
}