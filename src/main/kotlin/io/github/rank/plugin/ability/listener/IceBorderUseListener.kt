package io.github.rank.plugin.ability.listener

import io.github.asr.mafp.utils.Task
import io.github.asr.mafp.utils.runLater
import io.github.rank.plugin.ability.plugin
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockPlaceEvent

class IceBorderUseListener: Listener {

    @EventHandler
    fun onEvent(event: BlockPlaceEvent) {
        val player = event.player
        if (event.block.type == Material.PACKED_ICE) {
            event.isCancelled = true
            if (player.hasCooldown(Material.PACKED_ICE)) return
            player.setCooldown(Material.PACKED_ICE, 25 * 20)

            val yaw = player.location.yaw.toDouble()
            val loc = event.block.location

            if ((0 - 45 < yaw && yaw < 0 + 45) || (180 - 45 < yaw || yaw < 45 - 180)) {
                for (y in loc.blockY until loc.blockY + 5) {
                    val task = Task {
                        for (x in loc.blockX - 2 until loc.blockX + 3) {
                            val iceLoc = loc.clone().set(x.toDouble(), y.toDouble(), loc.z)
                            val inTask = Task {
                                iceLoc.block.type = Material.AIR
                            }

                            if (!iceLoc.block.type.isSolid) {
                                iceLoc.block.type = Material.PACKED_ICE
                                plugin.runLater(inTask, 15.toDouble())
                            }
                        }
                    }

                    plugin.runLater(task, 10 * (y - loc.blockY + 1).toLong())
                }
            } else {
                for (y in loc.blockY until loc.blockY + 5) {
                    val task = Task {
                        for (z in loc.blockZ - 2 until loc.blockZ + 3) {
                            val iceLoc = loc.clone().set(loc.x, y.toDouble(), z.toDouble())
                            val inTask = Task {
                                iceLoc.block.type = Material.AIR
                            }

                            if (!iceLoc.block.type.isSolid) {
                                iceLoc.block.type = Material.PACKED_ICE
                                plugin.runLater(inTask, 15.toDouble())
                            }
                        }
                    }

                    plugin.runLater(task, 10 * (y - loc.blockY + 1).toLong())
                }
            }
        }
    }
}