package org.teamvoided.crystalline_powers.client

import dev.emi.trinkets.api.client.TrinketRendererRegistry
import org.teamvoided.crystalline_powers.CrystallinePowers.log
import org.teamvoided.crystalline_powers.client.item.PendentRenderer
import org.teamvoided.crystalline_powers.init.CryItems

@Suppress("unused")
object CrystallinePowersClient {

    fun clientInit() {
        log.info("Hello from Client")
        TrinketRendererRegistry.registerRenderer(CryItems.AMETHYST_PENDANT, PendentRenderer())
    }
}