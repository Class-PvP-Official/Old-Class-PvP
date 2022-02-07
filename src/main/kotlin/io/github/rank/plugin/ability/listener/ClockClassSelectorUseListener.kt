package io.github.rank.plugin.ability.listener

import io.github.rank.plugin.ability.util.ClassSelector
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEvent

class ClockClassSelectorUseListener: Listener {
    @EventHandler
    fun onEvent(event: PlayerInteractEvent) {
        if (event.action.isRightClick) {
            if (event.player.inventory.itemInMainHand.type == Material.CLOCK) {
                event.player.openInventory(ClassSelector.getSelectorGUI())
            }
        }
    }
}