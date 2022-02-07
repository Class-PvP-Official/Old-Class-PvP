package io.github.rank.plugin.ability.listener

import io.github.rank.plugin.ability.playerInfos
import io.github.rank.plugin.ability.plugin
import io.github.rank.plugin.ability.util.PvpClass
import io.github.rank.plugin.ability.util.battlefield
import io.github.rank.plugin.ability.util.giveItemEffect
import net.kyori.adventure.text.Component
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import java.lang.RuntimeException


class ClassSelectListener: Listener {

    private fun Player.setPlayerInfo(pvpClass: PvpClass) {
        for (playerInfo in playerInfos) {
            if (playerInfo.player == this) {
                val playerInfo2 = playerInfo
                playerInfo2.pvpClass = pvpClass
                playerInfos[playerInfos.indexOf(playerInfo)] = playerInfo2
                return
            }
        }
        throw RuntimeException("Invalid Player in InGame!")
    }

    @EventHandler
    fun onEvent(event: InventoryClickEvent) {
        val inventory = event.clickedInventory
        if (event.view.title() == Component.text("Class Selector")) {
            val player = event.whoClicked as Player
            val slot = event.slot

            if (inventory != event.whoClicked.inventory) {
                if (event.slot == 31) {
                    player.setPlayerInfo(PvpClass.values().random())

                    player.inventory.clear()

                    player.giveItemEffect()
                    player.battlefield()
                }
                if (event.slot >= 9 && event.slot < PvpClass.MAXIMUM.classNumber + 9) {
                    val number = slot - 9 + 1
                    if (number <= PvpClass.MAXIMUM.classNumber) {
                        val pvpClass = PvpClass.INSTANCE.getPvpClass(number)
                        player.setPlayerInfo(pvpClass)

                        player.inventory.clear()

                        player.giveItemEffect()
                        player.battlefield()
                    }
                }
                event.isCancelled = true
            }
        }
    }
}