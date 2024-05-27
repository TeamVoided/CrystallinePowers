package org.teamvoided.crystalline_powers.data

import net.minecraft.item.Item
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.tag.TagKey
import net.minecraft.util.Identifier
import org.teamvoided.crystalline_powers.CrystallinePowers.id

object CryItemTags {
    val COPPER_SLOT = createSlot("chest/copper_slot")

    fun create(id: String): TagKey<Item> = TagKey.of(RegistryKeys.ITEM, id(id))


    fun createSlot(id: String): TagKey<Item> = TagKey.of(RegistryKeys.ITEM, Identifier("trinkets", id))
}