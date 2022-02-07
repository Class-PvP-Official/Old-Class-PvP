package io.github.rank.plugin.util

import org.bukkit.entity.Player

fun Player.removeEffects() {
    this.activePotionEffects.forEach {
        this.removePotionEffect(it.type)
    }

    this.fireTicks = 0
}

fun Player.setFoodLevelMax() {
    this.foodLevel = 20
}