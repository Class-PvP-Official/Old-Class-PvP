package io.github.rank.plugin.ability.listener

import io.github.rank.plugin.ability.getPlayerInfo
import io.github.rank.plugin.ability.util.PvpClass
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType

class BlowFishAttackListener : Listener {
    private val blowfishPoison = PotionEffect(PotionEffectType.POISON, 100, 1)

    @EventHandler
    fun onEvent(event: EntityDamageByEntityEvent) {
        if (event.entity is Player && event.damager is Player) {
            val victim = event.entity as Player
            val attacker = event.damager as Player

            if (attacker.getPlayerInfo().pvpClass == PvpClass.BLOWFISH) {
                victim.addPotionEffect(blowfishPoison)
            }
        }
    }
}