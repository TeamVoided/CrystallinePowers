package org.teamvoided.crystalline_powers.data

import net.minecraft.item.Item
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.tag.TagKey
import org.teamvoided.crystalline_powers.CrystallinePowers.id

object CryItemTags {
    val COPPER_TIER = create("copper_tier")

     fun create(id: String): TagKey<Item> = TagKey.of(RegistryKeys.ITEM, id(id))
}