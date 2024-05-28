package org.teamvoided.crystalline_powers.client.item

import dev.emi.trinkets.api.SlotReference
import dev.emi.trinkets.api.client.TrinketRenderer
import net.minecraft.client.MinecraftClient
import net.minecraft.client.render.OverlayTexture
import net.minecraft.client.render.VertexConsumerProvider
import net.minecraft.client.render.entity.model.EntityModel
import net.minecraft.client.render.model.json.ModelTransformationMode
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.entity.LivingEntity
import net.minecraft.item.ItemStack
import net.minecraft.util.math.Axis
import org.teamvoided.crystalline_powers.item.PendantItem


class PendentRenderer : TrinketRenderer {
    override fun render(
        stack: ItemStack, slotReference: SlotReference, contextModel: EntityModel<out LivingEntity>,
        matrices: MatrixStack, vertexConsumers: VertexConsumerProvider, light: Int,
        entity: LivingEntity, limbAngle: Float, limbDistance: Float, tickDelta: Float,
        animationProgress: Float, headYaw: Float, headPitch: Float
    ) {
        if (stack.item !is PendantItem) return

        matrices.push()


        matrices.scale(0.6f, 0.6f, 0.6f)

        matrices.rotate(Axis.X_POSITIVE.rotationDegrees(180f))
        matrices.translate(0.0, -0.6, -0.25)

        MinecraftClient.getInstance().itemRenderer.renderItem(
            stack, ModelTransformationMode.FIXED, light, OverlayTexture.DEFAULT_UV,
            matrices, vertexConsumers, entity.world, 0
        )

        matrices.pop()
    }
}