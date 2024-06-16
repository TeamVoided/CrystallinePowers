package org.teamvoided.crystalline_powers

import dev.emi.trinkets.api.TrinketsApi
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents
import net.fabricmc.fabric.api.event.player.AttackEntityCallback
import net.minecraft.entity.Entity
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.damage.DamageSource
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.particle.ParticleType
import net.minecraft.particle.ParticleTypes
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.server.world.ServerWorld
import net.minecraft.sound.SoundCategory
import net.minecraft.sound.SoundEvents
import net.minecraft.util.ActionResult
import net.minecraft.util.Hand
import net.minecraft.util.Identifier
import net.minecraft.util.hit.EntityHitResult
import net.minecraft.world.World
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.teamvoided.crystalline_powers.init.CryComponents
import org.teamvoided.crystalline_powers.init.CryEffects
import org.teamvoided.crystalline_powers.init.CryItems
import org.teamvoided.crystalline_powers.init.CryTabs
import org.teamvoided.crystalline_powers.item.SoulstoneItem

@Suppress("unused")
object CrystallinePowers {
    const val MODID = "crystalline_powers"

    @JvmField
    val log: Logger = LoggerFactory.getLogger(CrystallinePowers::class.simpleName)

    fun commonInit() {
        log.info("Hello from Common")
        CryItems.init()
        CryComponents.init()
        CryTabs.init()
        CryEffects.init()
        ServerLivingEntityEvents.AFTER_DEATH.register { target: LivingEntity, source: DamageSource ->
            val world = target.world as ServerWorld
            val killer = source.source
            if (killer is ServerPlayerEntity) {
                val player = killer as ServerPlayerEntity
                if (!world.isClient) {
                    val trinket = TrinketsApi.getTrinketComponent(player)
                    if (trinket.isPresent) {
                        val chest = trinket.get().inventory["chest"]!!
                        val copperSlot = chest["copper_slot"]
                        val ironSlot = chest["iron_slot"]
                        val goldSlot = chest["gold_slot"]
                        if (copperSlot != null && !copperSlot.isEmpty) {
                            val stack = copperSlot.getStack(0)
                            if (stack.item is SoulstoneItem) {
                                val effect = CryEffects.soulEffects.random()
                                var amplifier = -1
                                if (player.getStatusEffect(effect) != null) {
                                    amplifier = player.getStatusEffect(effect)!!.amplifier
                                }
                                amplifier += 1
                                world.spawnParticles(
                                    ParticleTypes.SOUL,
                                    target.x,
                                    target.y,
                                    target.z,
                                    100,
                                    0.2,
                                    0.2,
                                    0.2,
                                    0.1
                                )
                                world.playSound(
                                    null,
                                    target.x,
                                    target.y,
                                    target.z,
                                    SoundEvents.PARTICLE_SOUL_ESCAPE,
                                    SoundCategory.PLAYERS,
                                    5.0F,
                                    1.0F
                                )
                                player.addStatusEffect(
                                    StatusEffectInstance(
                                        effect,
                                        24000, amplifier,
                                        false, false, true
                                    )
                                )
                            }
                        }
                        if (ironSlot != null && !ironSlot.isEmpty) {
                            val stack = ironSlot.getStack(0)
                            if (stack.item is SoulstoneItem) {
                                for (i in 1..2) {
                                    val effect = CryEffects.soulEffects.random()
                                    var amplifier = -1
                                    world.spawnParticles(
                                        ParticleTypes.SOUL,
                                        target.x,
                                        target.y,
                                        target.z,
                                        100,
                                        0.2,
                                        0.2,
                                        0.2,
                                        0.1
                                    )
                                    world.playSound(
                                        null,
                                        target.x,
                                        target.y,
                                        target.z,
                                        SoundEvents.PARTICLE_SOUL_ESCAPE,
                                        SoundCategory.PLAYERS,
                                        5.0F,
                                        1.0F
                                    )
                                    if (player.getStatusEffect(effect) != null) {
                                        amplifier = player.getStatusEffect(effect)!!.amplifier
                                    }
                                    amplifier += 1
                                    player.addStatusEffect(
                                        StatusEffectInstance(
                                            effect,
                                            24000, amplifier,
                                            false, false, true
                                        )
                                    )
                                }
                            }
                        }
                        if (goldSlot != null && !goldSlot.isEmpty) {
                            val stack = goldSlot.getStack(0)
                            if (stack.item is SoulstoneItem) {
                                for (i in 1..3) {
                                    val effect = CryEffects.soulEffects.random()
                                    var amplifier = -1
                                    world.spawnParticles(
                                        ParticleTypes.SOUL,
                                        target.x,
                                        target.y,
                                        target.z,
                                        100,
                                        0.2,
                                        0.2,
                                        0.2,
                                        0.1
                                    )
                                    world.playSound(
                                        null,
                                        target.x,
                                        target.y,
                                        target.z,
                                        SoundEvents.PARTICLE_SOUL_ESCAPE,
                                        SoundCategory.PLAYERS,
                                        5.0F,
                                        1.0F
                                    )
                                    if (player.getStatusEffect(effect) != null) {
                                        amplifier = player.getStatusEffect(effect)!!.amplifier
                                    }
                                    amplifier += 1
                                    player.addStatusEffect(
                                        StatusEffectInstance(
                                            effect,
                                            24000, amplifier,
                                            false, false, true
                                        )
                                    )
                                }
                            }
                        }
                    }
                }
                ActionResult.PASS

            }

        }
    }

    fun id(path: String) = Identifier(MODID, path)
}
