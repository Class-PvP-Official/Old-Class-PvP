package io.github.rank.plugin.listener

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent

class BlockBreakListener : Listener {
    @EventHandler
    fun onEvent(event: BlockBreakEvent) {
        if (event.player.isOp) return
        event.isCancelled = true
    }
}