package io.github.rank.plugin.ability.listener

import org.bukkit.Material
import org.bukkit.Particle
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent

class TNTAttackListener : Listener {
    @EventHandler
    fun onTNTAttacked(event: EntityDamageByEntityEvent) {
        if (event.entity is Player && event.damager is Player) {
            val attacker = event.damager as Player
            val victim = event.entity as Player

            if (attacker.inventory.itemInMainHand.type == Material.TNT && !attacker.hasCooldown(Material.TNT)) {
                attacker.setCooldown(Material.TNT, 100)
                victim.world.spawnParticle(Particle.EXPLOSION_HUGE, victim.location, 1)
                event.damage = 5.0
            }
        }
    }
}