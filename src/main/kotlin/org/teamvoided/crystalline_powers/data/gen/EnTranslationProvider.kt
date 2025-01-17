package org.teamvoided.crystalline_powers.data.gen

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider
import net.minecraft.block.Block
import net.minecraft.entity.effect.StatusEffect
import net.minecraft.item.Item
import net.minecraft.registry.HolderLookup
import net.minecraft.registry.Registries
import net.minecraft.util.Identifier
import org.teamvoided.crystalline_powers.init.CryEffects
import org.teamvoided.crystalline_powers.init.CryItems
import java.util.concurrent.CompletableFuture

class EnTranslationProvider(o: FabricDataOutput, r: CompletableFuture<HolderLookup.Provider>) :
    FabricLanguageProvider(o, r) {
    val items = listOf(
        CryItems.AMETHYST_PENDANT,
        CryItems.BLOODSTONE,
        CryItems.SOULSTONE,
        CryItems.LEAFSTONE,
        CryItems.STARSTONE,
        CryItems.GOLDINE,
    )
    val effects = CryEffects.soulEffects + listOf(
        CryEffects.NEW_BLOOD
    )
    override fun generateTranslations(lookup: HolderLookup.Provider, gen: TranslationBuilder) {
        items.forEach { gen.add(it, genLang(it.id)) }
        effects.forEach { gen.add(it.value(), genLang(it.value().id!!)) }

        gen.add("trinkets.slot.chest.copper_slot", "Copper Slot")
        gen.add("trinkets.slot.chest.iron_slot", "Iron Slot")
        gen.add("trinkets.slot.chest.gold_slot", "Gold Slot")
    }

    private fun genLang(identifier: Identifier): String =
        identifier.path.split("_").joinToString(" ") { it.replaceFirstChar(Char::uppercaseChar) }

    val Item.id get() = Registries.ITEM.getId(this)
    val Block.id get() = Registries.BLOCK.getId(this)
    val StatusEffect.id get() = Registries.STATUS_EFFECT.getId(this)
}