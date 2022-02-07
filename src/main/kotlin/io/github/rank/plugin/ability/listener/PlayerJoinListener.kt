package io.github.rank.plugin.ability.listener

import io.github.asr.mafp.utils.AQUA
import io.github.asr.mafp.utils.RED
import io.github.asr.mafp.utils.YELLOW
import io.github.rank.plugin.ability.playerInfos
import io.github.rank.plugin.ability.util.PlayerInfo
import io.github.rank.plugin.ability.util.PvpClass
import io.github.rank.plugin.ability.util.lobby
import io.github.rank.plugin.ability.util.setBasicItem
import io.github.rank.plugin.util.serialPlayerKill
import io.github.rank.plugin.util.setFoodLevelMax
import net.kyori.adventure.text.Component
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent

class PlayerJoinListener: Listener {
    @EventHandler
    fun onEvent(event: PlayerJoinEvent) {
        val player = event.player
        val info = PlayerInfo(player)
        info.pvpClass = PvpClass.NULL
        playerInfos.add(info)

        serialPlayerKill[player] = 0

        player.lobby()
        player.setBasicItem()
        player.setFoodLevelMax()
        player.health = 20.0

        player.sendPlayerListHeader(Component.text("${YELLOW}Class$RED PvP$AQUA Official Server"))
        player.sendPlayerListFooter(Component.text("This Game's Kill : ${serialPlayerKill[player]}"))
    }
}