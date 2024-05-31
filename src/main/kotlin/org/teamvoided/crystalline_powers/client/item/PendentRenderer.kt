package org.teamvoided.crystalline_powers.client.item

import dev.emi.trinkets.api.SlotReference
import dev.emi.trinkets.api.client.TrinketRenderer
import net.minecraft.client.MinecraftClient
import net.minecraft.client.render.OverlayTexture
import net.minecraft.client.render.VertexConsumerProvider
import net.minecraft.client.render.entity.model.EntityModel
import net.minecraft.client.render.item.ItemRenderer
import net.minecraft.client.render.model.json.ModelTransformationMode
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.entity.LivingEntity
import net.minecraft.item.ItemStack
import net.minecraft.util.math.Axis
import org.teamvoided.crystalline_powers.utils.PendentSlot
import org.teamvoided.crystalline_powers.utils.fixToHead
import org.teamvoided.crystalline_powers.utils.getSlot
import kotlin.math.cos
import kotlin.math.sin


class PendentRenderer : TrinketRenderer {
    private var itemRenderer: ItemRenderer? = null

    override fun render(
        stack: ItemStack, slot: SlotReference, contextModel: EntityModel<out LivingEntity>,
        matrices: MatrixStack, vertexConsumers: VertexConsumerProvider, light: Int,
        entity: LivingEntity, limbAngle: Float, limbDistance: Float, tickDelta: Float,
        animationProgress: Float, headYaw: Float, headPitch: Float
    ) {
        if (itemRenderer == null) itemRenderer = MinecraftClient.getInstance().itemRenderer

        val tick = animationProgress + (entity.uuid.toString().toByteArray().map { it.toFloat() }.sum()) +
                when (getSlot(slot)) {
                    PendentSlot.GOLD -> 240
                    PendentSlot.IRON -> 120
                    else -> 0
                }
        matrices.push()

        fixToHead(entity, matrices, headYaw, headPitch, tickDelta)
        // flipped the right way around
        matrices.rotate(Axis.X_POSITIVE.rotationDegrees(180f))


        val y = (sin(
            (tick + when (getSlot(slot)) {
                PendentSlot.GOLD -> 31;PendentSlot.IRON -> 13;else -> 1
            }) / 10
        ) + 1) / 10
        val radius = 0.5f
        val angle = tick * Math.PI.toFloat() / 180f

        val x = radius * cos(angle)
        val z = radius * sin(angle)
        matrices.translate(x, y, z)
        val rot = 90 + ((tick % 360) * -1)
        matrices.rotate(Axis.Y_POSITIVE.rotationDegrees(rot))

        itemRenderer!!.renderItem(
            stack, ModelTransformationMode.GROUND, light, OverlayTexture.DEFAULT_UV,
            matrices, vertexConsumers, entity.world, 0
        )

        matrices.pop()
    }
}