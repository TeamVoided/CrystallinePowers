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
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import net.minecraft.text.Text
import net.minecraft.util.math.Axis
import org.teamvoided.crystalline_powers.item.PendantItem
import org.teamvoided.crystalline_powers.utils.SlotTier
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
        if (stack.item !is PendantItem) return
        if (itemRenderer == null) itemRenderer = MinecraftClient.getInstance().itemRenderer

        val tick = animationProgress + (entity.uuid.toString().toByteArray().map { it.toFloat() }.sum()) +
                when (getSlot(slot)) {
                    SlotTier.GOLD -> 240
                    SlotTier.IRON -> 120
                    else -> 0
                }



        matrices.push()



        fixToHead(entity, matrices, headYaw, headPitch, tickDelta)
        // flipped the right way around
        matrices.rotate(Axis.X_POSITIVE.rotationDegrees(180f))


        val yOffset = (sin(tick / 10) + 1) / 10
        val radius = 0.5f
        val angle = tick * Math.PI.toFloat() / 180f

        val x = radius * cos(angle)
        val y = radius * sin(angle)
        matrices.translate(x, 0.45f + yOffset, y)
        val rot = 90 + ((tick % 360) * -1)
        matrices.rotate(Axis.Y_POSITIVE.rotationDegrees(rot))

        val txt = ":)"
        if (entity is PlayerEntity) {
            entity.sendMessage(Text.literal(txt), true)
        }

        itemRenderer!!.renderItem(
            stack, ModelTransformationMode.GROUND, light, OverlayTexture.DEFAULT_UV,
            matrices, vertexConsumers, entity.world, 0
        )

        matrices.pop()
    }


    companion object {
        fun fixToHead(entity: LivingEntity, matrices: MatrixStack, headYaw: Float, headPitch: Float, tickDelta: Float) {
            if (entity.isInSwimmingPose) {
                matrices.rotate(Axis.Y_POSITIVE.rotationDegrees(headYaw))
                matrices.rotate(Axis.X_POSITIVE.rotationDegrees(-45.0f * entity.getLeaningPitch(tickDelta)))
            } else if (entity.isFallFlying) {
                matrices.rotate(Axis.Y_POSITIVE.rotationDegrees(headYaw))
                matrices.rotate(Axis.X_POSITIVE.rotationDegrees(if (entity.roll > 4) -45f else headPitch))

            } else {
                if (entity.isInSneakingPose) matrices.translate(0f, 0.265f, 0f)

                matrices.rotate(Axis.Y_POSITIVE.rotationDegrees(headYaw))
                matrices.rotate(Axis.X_POSITIVE.rotationDegrees(headPitch))
            }

        }
    }
}