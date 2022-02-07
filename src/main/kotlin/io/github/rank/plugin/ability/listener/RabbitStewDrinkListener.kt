package io.github.rank.plugin.ability.listener

import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType

class RabbitStewDrinkListener: Listener {
    private val rabbitStewDrinkPotionEffect = listOf(PotionEffect(PotionEffectType.SPEED, 20 * 15, 2),
    PotionEffect(PotionEffectType.HEALTH_BOOST, (20 * 7.5).toInt(), 2),
    PotionEffect(PotionEffectType.ABSORPTION, 20 * 45, 4),
    PotionEffect(PotionEffectType.HEAL, (20 * 10).toInt(), 2))

    @EventHandler
    fun onEvent(event: PlayerInteractEvent) {
        if (event.action.isRightClick) {
            if (event.player.inventory.itemInMainHand.type == Material.RABBIT_STEW) {
                event.player.addPotionEffects(rabbitStewDrinkPotionEffect)
                event.player.inventory.setItemInMainHand(ItemStack(Material.AIR))
            }
        }
    }
}