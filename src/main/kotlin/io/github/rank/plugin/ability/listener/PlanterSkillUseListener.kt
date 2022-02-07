package io.github.rank.plugin.ability.listener

import io.github.asr.mafp.loop.loop
import io.github.asr.mafp.utils.spawnParticle
import io.github.rank.plugin.ability.plugin
import org.bukkit.Material
import org.bukkit.Particle
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEvent
import kotlin.math.min

class PlanterSkillUseListener : Listener {
    @EventHandler
    fun onEvent(event: PlayerInteractEvent) {
        if (event.action.isRightClick) {
            if (event.player.inventory.itemInMainHand.type == Material.DANDELION
                && !event.player.hasCooldown(Material.DANDELION)) {
                event.player.setCooldown(Material.DANDELION, 500)
                plugin.loop(1L, 200) {
                    event.player.location.spawnParticle(Particle.END_ROD, 5)

                    plugin.server.onlinePlayers.forEach {
                        if (event.player.location.distance(it.location) <= 5 && event.player != it) {
                            it.damage(0.2, event.player)
                            event.player.health = min(event.player.health + 0.075, event.player.healthScale)
                        }
                    }
                }
            }
        }
    }
}