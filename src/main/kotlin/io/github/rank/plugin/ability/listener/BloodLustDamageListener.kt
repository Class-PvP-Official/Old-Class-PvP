package io.github.rank.plugin.ability.listener

import io.github.rank.plugin.ability.getPlayerInfo
import io.github.rank.plugin.ability.util.PvpClass
import io.github.rank.plugin.util.serialPlayerKill
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent

class BloodLustDamageListener : Listener {
    @EventHandler
    fun onEvent(event: EntityDamageByEntityEvent) {
        if (event.damager is Player) {
            val attacker = event.damager as Player
            if (attacker.getPlayerInfo().pvpClass == PvpClass.BLOODLUST) {
                event.damage = event.damage * (1 + 0.1 * serialPlayerKill[attacker]!!)
            }
        }
    }
}