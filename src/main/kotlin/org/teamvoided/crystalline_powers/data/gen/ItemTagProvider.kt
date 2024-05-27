package org.teamvoided.crystalline_powers.data.gen

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider
import net.minecraft.item.Item
import net.minecraft.item.Items
import net.minecraft.registry.HolderLookup
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.tag.TagKey
import net.minecraft.util.Identifier
import org.teamvoided.crystalline_powers.data.CryItemTags
import org.teamvoided.crystalline_powers.init.CryItems
import java.util.concurrent.CompletableFuture

class ItemTagProvider(o: FabricDataOutput, c: CompletableFuture<HolderLookup.Provider>) :
    FabricTagProvider.ItemTagProvider(o, c) {

    val CHEST_NECKLACE = itemTag("trinkets","chest/necklace")

    override fun configure(provider: HolderLookup.Provider) {
        getOrCreateTagBuilder(CHEST_NECKLACE)
            .addTag(CryItemTags.COPPER_TIER)
            .add(Items.AMETHYST_SHARD)

        getOrCreateTagBuilder(CryItemTags.COPPER_TIER)
            .add(CryItems.AMETHYST_PENDANT)

    }
    fun itemTag(path:String,id: String): TagKey<Item> = TagKey.of(RegistryKeys.ITEM, Identifier(path, id))
}