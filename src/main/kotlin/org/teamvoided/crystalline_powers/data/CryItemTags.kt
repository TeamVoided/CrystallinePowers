package org.teamvoided.crystalline_powers.data

import net.minecraft.item.Item
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.tag.TagKey
import net.minecraft.util.Identifier
import org.teamvoided.crystalline_powers.CrystallinePowers.id

object CryItemTags {
    val SLOT_CAPABLE_ITEM = create("slot_capable_item")

    val COPPER_SLOT = createSlot("chest/copper_slot")
    val IRON_SLOT = createSlot("chest/iron_slot")
    val GOLD_SLOT = createSlot("chest/gold_slot")

    private fun create(id: String): TagKey<Item> = TagKey.of(RegistryKeys.ITEM, id(id))

    private fun createSlot(id: String): TagKey<Item> = TagKey.of(RegistryKeys.ITEM, Identifier("trinkets", id))
}