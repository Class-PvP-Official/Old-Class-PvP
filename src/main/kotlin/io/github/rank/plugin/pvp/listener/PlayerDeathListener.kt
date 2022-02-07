package io.github.rank.plugin.pvp.listener

import io.github.asr.mafp.inventory.clearItem
import io.github.rank.plugin.ability.getPlayerInfo
import io.github.rank.plugin.ability.playerInfos
import io.github.rank.plugin.ability.plugin
import io.github.rank.plugin.ability.util.PvpClass
import io.github.rank.plugin.ability.util.lobby
import io.github.rank.plugin.ability.util.setBasicItem
import io.github.rank.plugin.util.serialPlayerKill
import io.github.rank.plugin.util.removeEffects
import io.github.rank.plugin.util.setFoodLevelMax
import net.kyori.adventure.text.Component
import net.kyori.adventure.title.Title
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.PlayerDeathEvent
import org.bukkit.inventory.ItemStack
import java.time.Duration

val coolDownItemList = listOf(
    Material.ELYTRA,
    Material.TNT,
    Material.PACKED_ICE,
    Material.COBWEB,
    Material.GOLDEN_AXE,
    Material.DANDELION
)

class PlayerDeathListener : Listener {
    @EventHandler
    fun onEvent(event: PlayerDeathEvent) {
        event.apply {
            isCancelled = true
            player.health = 20.0
            player.lobby()
            player.showTitle(
                Title.title(
                    Component.text("${ChatColor.RED}당신은 죽었습니다!"),
                    Component.text(""), Title.Times.of(
                        Duration.ofMillis(250),
                        Duration.ofMillis(2500), Duration.ofMillis(250)
                    )
                )
            )

            for (i in 0 until playerInfos.size) {
                if (playerInfos[i].player == player) {
                    playerInfos[i].pvpClass = PvpClass.NULL
                    break
                }
            }

            for (item in coolDownItemList) player.setCooldown(item, 0)

            player.setBasicItem()
            player.removeEffects()
            player.setFoodLevelMax()
            serialPlayerKill[player] = 0

            if (player.getPlayerInfo().pvpClass == PvpClass.RABBIT) player.killer?.inventory?.addItem(ItemStack(Material.RABBIT_STEW))

            if (player.killer == null) plugin.server.broadcast(Component.text("${player.name} 님이 사망했습니다!"))
            else {
                plugin.server.broadcast(Component.text("${player.name} 님이 ${player.killer!!.name} 님에게 살해당했습니다!"))

                serialPlayerKill[player.killer!!] = serialPlayerKill[player.killer]!! + 1
            }

            player.sendPlayerListFooter(Component.text("This Game's Kill : ${serialPlayerKill[player]}"))
        }
    }
}