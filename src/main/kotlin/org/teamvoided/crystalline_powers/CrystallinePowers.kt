package org.teamvoided.crystalline_powers

import net.minecraft.util.Identifier
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.teamvoided.crystalline_powers.init.CryItems

@Suppress("unused")
object CrystallinePowers {
    const val MODID = "crystalline_powers"

    @JvmField
    val log: Logger = LoggerFactory.getLogger(CrystallinePowers::class.simpleName)

    fun commonInit() {
        log.info("Hello from Common")
        CryItems.init()
    }

    fun clientInit() {
        log.info("Hello from Client")
    }

    fun id(path: String) = Identifier(MODID, path)
}
