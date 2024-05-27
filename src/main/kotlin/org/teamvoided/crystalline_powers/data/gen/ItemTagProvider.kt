package org.teamvoided.crystalline_powers.data.gen

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider
import net.minecraft.registry.HolderLookup
import java.util.concurrent.CompletableFuture

class ItemTagProvider(o: FabricDataOutput, c: CompletableFuture<HolderLookup.Provider>) : FabricTagProvider.ItemTagProvider(o, c) {
    override fun configure(provider: HolderLookup.Provider?) {
    }

}