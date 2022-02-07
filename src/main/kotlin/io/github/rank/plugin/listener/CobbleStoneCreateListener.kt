package io.github.rank.plugin.listener

import io.github.asr.mafp.utils.Task
import io.github.asr.mafp.utils.runLater
import io.github.rank.plugin.ability.plugin
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockFormEvent

class CobbleStoneCreateListener : Listener {
    @EventHandler
    fun onEvent(event: BlockFormEvent) {
        plugin.runLater(
            Task {
                event.newState.location.block.type = Material.AIR
            },
            5.0
        )
    }
}