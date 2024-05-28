package org.teamvoided.crystalline_powers.init

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup
import net.minecraft.item.ItemGroup
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.text.Text
import org.teamvoided.crystalline_powers.CrystallinePowers.id

object CryTabs {
    fun init() {}
    val CRYSTALLINE_POWERS_TAB = register(
        "crystalline_powers_tab",
        FabricItemGroup.builder()
            .icon { CryItems.AMETHYST_PENDANT.defaultStack }
            .name(Text.translatable("Crystalline Powers"))
            .entries { _, entries ->
                entries.addStacks(
                    mutableSetOf(
                        CryItems.AMETHYST_PENDANT.defaultStack,
                    )
                )
            }
            .build()
    )

    fun register(id: String, tab: ItemGroup): ItemGroup {
        return Registry.register(Registries.ITEM_GROUP, id(id), tab)
    }
}
