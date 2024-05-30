package org.teamvoided.crystalline_powers.client

import dev.emi.trinkets.api.client.TrinketRendererRegistry
import org.teamvoided.crystalline_powers.CrystallinePowers.log
import org.teamvoided.crystalline_powers.client.item.PendentRenderer
import org.teamvoided.crystalline_powers.init.CryItems

@Suppress("unused")
object CrystallinePowersClient {
    val items = listOf(
        CryItems.AMETHYST_PENDANT,
        CryItems.SOULSTONE,
        CryItems.BLOODSTONE,
    )

    fun clientInit() {
        log.info("Hello from Client")
        items.forEach() {
            TrinketRendererRegistry.registerRenderer(it, PendentRenderer())
        }
    }
}