package io.github.rank.plugin.ability.util

import io.github.asr.mafp.utils.overWorld
import io.github.rank.plugin.ability.plugin
import io.github.rank.plugin.pvp.listener.coolDownItemList
import io.github.rank.plugin.util.removeEffects
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.entity.Player

val battleFieldLocations = listOf(
    Location(plugin.overWorld, -0.5, 101.0, -0.5),
    Location(plugin.overWorld, -0.5, 101.0, 20.5),
    Location(plugin.overWorld, 14.5, 101.0, 14.5),
    Location(plugin.overWorld, 20.5, 101.0, -0.5),
    Location(plugin.overWorld, 14.5, 101.0, -15.5),
    Location(plugin.overWorld, -0.5, 101.0, -21.5),
    Location(plugin.overWorld, -15.5, 101.0, -15.5),
    Location(plugin.overWorld, -21.5, 101.0, -0.5),
    Location(plugin.overWorld, -15.5, 101.0, 14.5)
)

/**
 * TELEPORT PLAYER TO ORIGINAL BATTLEFIELD
 * */
fun Player.battlefield() {
    this.teleport(battleFieldLocations.random())
}

/**
 * TELEPORT PLAYER TO LOBBY
 * */
fun Player.lobby() {
    this.teleport(Location(this.world, 0.0, 64.0, 0.0))
    coolDownItemList.forEach {
        setCooldown(it, 0)
    }
}