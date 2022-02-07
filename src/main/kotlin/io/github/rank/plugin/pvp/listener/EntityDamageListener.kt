package io.github.rank.plugin.pvp.listener

import org.bukkit.Material
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageEvent
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType

class EntityDamageListener: Listener {

    @EventHandler
    fun onEvent(event: EntityDamageEvent) {
        if (event.cause == EntityDamageEvent.DamageCause.FALL) {
            event.isCancelled = true
            return
        }

        if (event.entityType == EntityType.PLAYER) {
            if (event.entityType == EntityType.PLAYER) {
                val player = (event.entity as Player)

                player.addPotionEffect(PotionEffect(PotionEffectType.GLOWING, 60, 0, true, false))
            }
        }
    }
}