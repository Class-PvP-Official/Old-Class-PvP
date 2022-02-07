package io.github.rank.plugin.listener

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.CraftItemEvent

class CraftListener: Listener {
    @EventHandler
    fun onEvent(event: CraftItemEvent) { event.isCancelled = true }
}