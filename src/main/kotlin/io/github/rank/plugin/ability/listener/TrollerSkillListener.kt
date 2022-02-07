package io.github.rank.plugin.ability.listener

import io.github.asr.mafp.entity.forward
import io.github.asr.mafp.entity.to
import io.github.asr.mafp.loop.loop
import io.github.asr.mafp.utils.Task
import io.github.asr.mafp.utils.overWorld
import io.github.asr.mafp.utils.runLater
import io.github.rank.plugin.ability.plugin
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.entity.ArmorStand
import org.bukkit.entity.EntityType
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.ItemStack

fun Location.cobWeb() {
    if (block.type == Material.AIR) {
        block.type = Material.COBWEB

        plugin.runLater(
            Task {
                block.type = Material.AIR
            }, 15.0
        )
    }
}

class TrollerSkillListener: Listener {
    private val cobwebVelocity = 0.8 // block(meter) per second

    @EventHandler
    fun onUseCobWeb(event: PlayerInteractEvent) {
        val player = event.player

        if (event.action.isRightClick) {
            if (player.inventory.itemInMainHand.type == Material.COBWEB) {
                event.isCancelled = true
                if (player.hasCooldown(Material.COBWEB)) return
                player.setCooldown(Material.COBWEB, 10 * 20)

                val staticArmorStand = player.world.spawnEntity(player.location, EntityType.ARMOR_STAND) as ArmorStand
                val armorStand = player.world.spawnEntity(player.location, EntityType.ARMOR_STAND) as ArmorStand

                var overlaps = false

                staticArmorStand.isInvisible = true
                staticArmorStand.isInvulnerable = true

                armorStand.equipment.helmet = ItemStack(Material.COBWEB)
                armorStand.isInvisible = true
                armorStand.isInvulnerable = true

                plugin.loop(1L, 60) {
                    armorStand to staticArmorStand.forward(time().toDouble() * cobwebVelocity)
                    if (armorStand.location.clone().add(0.0, 1.0, 0.0).block.type.isSolid) {
                        overlaps = true
                        armorStand.remove()
                        staticArmorStand.remove()
                    }
                    plugin.server.onlinePlayers.forEach {
                        if (it != player && armorStand.boundingBox.overlaps(it.boundingBox)) {
                            armorStand.remove()
                            staticArmorStand.remove()

                            overlaps = true

                            val location = it.location
                            location.clone().add(1.0, 0.0, 0.0).cobWeb()
                            location.clone().add(-1.0, 0.0, 0.0).cobWeb()
                            location.clone().add(0.0, 1.0, 0.0).cobWeb()
                            location.clone().add(0.0, -1.0, 0.0).cobWeb()
                            location.clone().add(0.0, 0.0, 1.0).cobWeb()
                            location.clone().add(0.0, 0.0, -1.0).cobWeb()
                            location.clone().add(0.0, 0.0, 0.0).cobWeb()
                        }
                    }
                }

                if (!overlaps) plugin.runLater(
                    Task {
                        armorStand.remove()
                        staticArmorStand.remove()
                    },
                3.toDouble())
            }
        }
    }
}