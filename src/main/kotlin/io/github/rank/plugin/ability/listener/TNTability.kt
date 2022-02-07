package io.github.rank.plugin.ability.listener

import io.github.rank.plugin.ability.getPlayerInfo
import io.github.rank.plugin.ability.plugin
import io.github.rank.plugin.ability.util.PlayerInfo
import io.github.rank.plugin.ability.util.PvpClass
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.Particle
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.entity.ExplosionPrimeEvent
import org.bukkit.event.player.PlayerInteractEvent
import java.lang.RuntimeException
import kotlin.math.sqrt

class TNTability: Listener {
    private val damageOfTNT = 120.0

    @EventHandler
    fun onEvent(event: PlayerInteractEvent) {
        if (event.action == Action.RIGHT_CLICK_AIR || event.action == Action.RIGHT_CLICK_BLOCK) {
            if (event.isBlockInHand && event.player.inventory.itemInMainHand.type == Material.TNT && !event.player.hasCooldown(Material.TNT)) {
                event.player.world.spawnEntity(event.player.location, EntityType.PRIMED_TNT)
                event.isCancelled = true

                event.player.setCooldown(Material.TNT, 500)
            }
        }
    }

    @EventHandler
    fun onExplode(event: ExplosionPrimeEvent) {
        event.isCancelled = true
        val location = event.entity.location

        location.world.spawnParticle(Particle.EXPLOSION_LARGE, location, 30)

        val dx = listOf(0, 1, 0, -1, 0, 0)
        val dy = listOf(1, 0, 0, 0, 0, 1)
        val dz = listOf(0, 0, 1, 0, -1, 0)

        Bukkit.getScheduler().runTaskLater(plugin,
        Runnable {
            for (i in 0 until 6) location.world.spawnParticle(Particle.EXPLOSION_HUGE, location.clone().add(dx[i].toDouble(),
                dy[i].toDouble(), dz[i].toDouble()
            ), 30)
        }, 40L)

        Bukkit.getOnlinePlayers().forEach {
            if (it.getPlayerInfo().pvpClass != PvpClass.NULL && it.getPlayerInfo().pvpClass != PvpClass.TNT) {
                val damage = damageOfTNT / location.distance(it.location)

                it.damage(damage, event.entity)
            }
        }
    }
}