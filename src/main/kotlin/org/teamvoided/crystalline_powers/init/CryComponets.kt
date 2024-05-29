package org.teamvoided.crystalline_powers.init

import net.minecraft.component.DataComponentMap
import net.minecraft.component.DataComponentType
import net.minecraft.item.ItemStack
import net.minecraft.network.codec.PacketCodecs
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.dynamic.Codecs
import org.teamvoided.crystalline_powers.CrystallinePowers.id
import org.teamvoided.crystalline_powers.item.AbstractPendantItem
import java.util.function.UnaryOperator

object CryComponents {
    fun init() {}

    val COOLDOWN: DataComponentType<Int> =
        register("cooldown") { it.codec(Codecs.NONNEGATIVE_INT).packetCodec(PacketCodecs.VAR_INT) }


    private fun <T> register(id: String, operator: UnaryOperator<DataComponentType.Builder<T>>): DataComponentType<T> {
        return Registry.register(
            Registries.DATA_COMPONENT_TYPE, id(id),
            operator.apply(DataComponentType.builder()).build()
        )
    }


    fun ItemStack.getPendedCooldown(): Int? {
        val cooldown = this.components[COOLDOWN]
        if (this.item is AbstractPendantItem && cooldown == null) {
            this.cooldown = 0
            return 0
        }
        return cooldown
    }

    fun ItemStack.setPendedCooldown(cooldown: Int): ItemStack = this.addComponent(COOLDOWN, cooldown)

    fun <T> ItemStack.addComponent(type: DataComponentType<T>, value: T?): ItemStack {
        this.applyComponents(DataComponentMap.builder().put(type, value).build())
        return this

    }
}