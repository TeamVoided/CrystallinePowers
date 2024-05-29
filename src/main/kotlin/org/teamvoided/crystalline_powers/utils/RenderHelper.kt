package org.teamvoided.crystalline_powers.utils

import net.minecraft.client.util.math.MatrixStack
import net.minecraft.entity.LivingEntity
import net.minecraft.util.math.Axis

fun fixToHead(entity: LivingEntity, matrices: MatrixStack, headYaw: Float, headPitch: Float, tickDelta: Float) {
    matrices.rotate(Axis.Y_POSITIVE.rotationDegrees(headYaw))
    if (entity.isInSwimmingPose) {
        matrices.rotate(Axis.X_POSITIVE.rotationDegrees(-45.0f * entity.getLeaningPitch(tickDelta)))
    } else if (entity.isFallFlying) {
        matrices.rotate(Axis.X_POSITIVE.rotationDegrees(if (entity.roll > 4) -45f else headPitch))
    } else {
        if (entity.isInSneakingPose) matrices.translate(0f, 0.265f, 0f)
        matrices.rotate(Axis.X_POSITIVE.rotationDegrees(headPitch))
    }
}