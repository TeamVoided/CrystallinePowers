package org.teamvoided.crystalline_powers.data.gen

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider
import net.minecraft.data.client.ItemModelGenerator
import net.minecraft.data.client.model.BlockStateModelGenerator
import net.minecraft.data.client.model.Models
import org.teamvoided.crystalline_powers.init.CryItems

class ModelCreator(o: FabricDataOutput) : FabricModelProvider(o) {
    override fun generateBlockStateModels(gen: BlockStateModelGenerator) {

    }

    val items = listOf(
        CryItems.AMETHYST_PENDANT
    )

    override fun generateItemModels(gen: ItemModelGenerator) {
        items.forEach{
            gen.register(it, Models.SINGLE_LAYER_ITEM)
        }
    }
}