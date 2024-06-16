package org.teamvoided.crystalline_powers.item

import dev.emi.trinkets.api.SlotReference
import net.fabricmc.fabric.api.event.player.AttackEntityCallback
import net.minecraft.entity.Entity
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import net.minecraft.util.ActionResult
import net.minecraft.util.Hand
import net.minecraft.util.hit.EntityHitResult
import net.minecraft.world.World
import org.teamvoided.crystalline_powers.init.CryEffects
import org.teamvoided.crystalline_powers.utils.PendentSlot

class SoulstoneItem(settings: Settings) : AbstractPendantItem(settings){
    override fun onUnequip(stack: ItemStack, slot: SlotReference, entity: LivingEntity) {
        super.onUnequip(stack, slot, entity)
        CryEffects.soulEffects.forEach{
            entity.removeStatusEffect(it)
        }
    }
}