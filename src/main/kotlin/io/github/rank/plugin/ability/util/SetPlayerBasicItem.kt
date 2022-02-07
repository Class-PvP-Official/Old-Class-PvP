package io.github.rank.plugin.ability.util

import io.github.rank.plugin.util.removeEffects
import net.kyori.adventure.text.Component
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

fun Player.setBasicItem() {
    this.inventory.clear()

    val stack = ItemStack(Material.CLOCK)
    stack.editMeta {
        it.displayName(Component.text("${ChatColor.AQUA}클래스 선택기"))
    }

    this.inventory.setItem(8, stack)

    this.removeEffects()
}