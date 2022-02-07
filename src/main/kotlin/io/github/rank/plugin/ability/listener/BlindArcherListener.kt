package io.github.rank.plugin.ability.listener

import io.github.rank.plugin.ability.getPlayerInfo
import io.github.rank.plugin.ability.util.PvpClass
import io.github.rank.plugin.ability.util.getPotionEffect
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.ProjectileHitEvent
import org.bukkit.potion.PotionEffectType

class BlindArcherListener : Listener {
    private val blindArcherPotionEffect = listOf(
        getPotionEffect(PotionEffectType.BLINDNESS, 255),
        getPotionEffect(PotionEffectType.SLOW, 4)
    )

    @EventHandler
    fun onEvent(event: ProjectileHitEvent) {
        if (event.hitEntity == null || event.hitEntity !is Player) return
        if (event.entity.shooter == null || event.entity.shooter !is Player) return
        val victim = event.hitEntity!! as Player
        val attacker = event.entity.shooter as Player

        if (attacker.getPlayerInfo().pvpClass == PvpClass.BLIND_ARCHER) {
            victim.addPotionEffects(blindArcherPotionEffect)
        }
    }
}