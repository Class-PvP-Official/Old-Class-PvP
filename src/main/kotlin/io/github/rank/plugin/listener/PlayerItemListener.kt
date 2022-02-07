package io.github.rank.plugin.listener

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerAttemptPickupItemEvent
import org.bukkit.event.player.PlayerDropItemEvent

class PlayerItemListener : Listener {
    @EventHandler
    fun onDropEvent(event: PlayerDropItemEvent) {
        event.isCancelled = true
    }

    @EventHandler
    fun onPickUpEvent(event: PlayerAttemptPickupItemEvent) {
        event.isCancelled = true
    }
}