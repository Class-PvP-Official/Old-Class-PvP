package io.github.rank.plugin.ability.listener

import io.github.rank.plugin.ability.playerInfos
import io.github.rank.plugin.ability.util.PlayerInfo
import io.github.rank.plugin.ability.util.PvpClass
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerMoveEvent
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import java.lang.RuntimeException

fun Player.addPotionEffect(potionEffectType: PotionEffectType, amplifier: Int) {
    this.addPotionEffect(PotionEffect(potionEffectType, Int.MAX_VALUE, amplifier, false, false, false))
}

class SkaterMoveListener: Listener {
    private fun Player.getPlayerInfo(): PlayerInfo {
        for (infoPlayer in playerInfos) {
            if (infoPlayer.player == this) return infoPlayer
        }

        throw RuntimeException("It's Invalid Player! Please Check Did You Reloaded!")
    }

    @EventHandler
    fun onEvent(event: PlayerMoveEvent) {
        val player = event.player

        if (player.getPlayerInfo().pvpClass == PvpClass.SKATER && player.isInWater) {
            player.removePotionEffect(PotionEffectType.SPEED)
            player.addPotionEffect(PotionEffectType.SLOW, 1)

            player.damage(1.2)
        }
    }
}