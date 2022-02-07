package io.github.rank.plugin.ability.listener

import io.github.rank.plugin.ability.plugin
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.entity.EntityDamageEvent
import org.bukkit.event.player.PlayerInteractEvent

class FlightAbility: Listener {
    @EventHandler
    fun onEvent(event: PlayerInteractEvent) {
        val player = event.player
        if (event.action != Action.RIGHT_CLICK_BLOCK && event.action != Action.RIGHT_CLICK_AIR) return
        if (player.inventory.itemInMainHand.type == Material.ELYTRA && !player.hasCooldown(Material.ELYTRA)) {
            if (player.allowFlight) return
            player.allowFlight = true
            event.isCancelled = true
            player.setCooldown(Material.ELYTRA, 1000)
            Bukkit.getScheduler().runTaskLater(plugin, Runnable {
                player.allowFlight = false
            }, 60L)
        }
    }

    @EventHandler
    fun onFall(event: EntityDamageEvent) {
        if (event.cause == EntityDamageEvent.DamageCause.FALL) event.isCancelled = true
    }
}