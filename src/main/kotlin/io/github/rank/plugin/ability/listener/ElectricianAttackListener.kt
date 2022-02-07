package io.github.rank.plugin.ability.listener

import io.github.asr.mafp.entity.forward
import io.github.asr.mafp.entity.to
import io.github.asr.mafp.utils.Task
import io.github.rank.plugin.ability.getPlayerInfo
import io.github.rank.plugin.ability.plugin
import io.github.rank.plugin.ability.util.PvpClass
import org.bukkit.Material
import org.bukkit.Particle
import org.bukkit.entity.ArmorStand
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType

private fun Player.stun() {
    val stunPotionEffects = listOf(
        PotionEffect(PotionEffectType.SLOW, 20, 5, false, false, true),
        PotionEffect(PotionEffectType.SLOW_FALLING, 20, 5, false, false, true),
        PotionEffect(PotionEffectType.WEAKNESS, 20, 2, false, false, true)
    )

    addPotionEffects(stunPotionEffects)
}

class ElectricianAttackListener : Listener {
    @EventHandler
    fun onRightClick(event: PlayerInteractEvent) {
        if (event.item?.type == Material.GOLDEN_AXE && !event.player.hasCooldown(Material.GOLDEN_AXE)) {
            if (event.action.isRightClick) {
                val player = event.player
                player.swingMainHand()
                player.setCooldown(Material.GOLDEN_AXE, 300)

                val staticArmorStand = player.world.spawnEntity(player.location, EntityType.ARMOR_STAND) as ArmorStand
                val armorStand = player.world.spawnEntity(player.location, EntityType.ARMOR_STAND) as ArmorStand

                var overlaps = false

                staticArmorStand.isInvisible = true
                staticArmorStand.isInvulnerable = true

                armorStand.isInvisible = true
                armorStand.isInvulnerable = true

                for (time in 0..15) {
                    armorStand to staticArmorStand.forward(time.toDouble() * 0.5)
                    if (armorStand.location.clone().add(0.0, 1.0, 0.0).block.type.isSolid) {
                        overlaps = true
                        armorStand.remove()
                        staticArmorStand.remove()
                    }
                    armorStand.world.spawnParticle(Particle.FIREWORKS_SPARK, armorStand.location, 5)
                    plugin.server.onlinePlayers.forEach {
                        if (it != player && armorStand.boundingBox.overlaps(it.boundingBox)) {
                            armorStand.remove()
                            staticArmorStand.remove()

                            overlaps = true

                            it.stun()
                            it.damage(1.0, player)
                            it.addPotionEffect(
                                PotionEffect(
                                    PotionEffectType.WITHER, 100, 0, false, false, false
                                )
                            )
                        }
                    }
                }

                if (!overlaps) Task {
                    staticArmorStand.remove()
                    armorStand.remove()
                }.run()
            }
        }
    }

    @EventHandler
    fun onAttack(event: EntityDamageByEntityEvent) {
        if (event.damager is Player && event.entity is Player) {
            val attacker = event.damager as Player
            val victim = event.entity as Player
            if (attacker.getPlayerInfo().pvpClass == PvpClass.ELECTRICIAN && !attacker.hasCooldown(Material.GOLDEN_AXE)) {
                attacker.setCooldown(Material.GOLDEN_AXE, 100)
                victim.stun()
            }
        }
    }
}