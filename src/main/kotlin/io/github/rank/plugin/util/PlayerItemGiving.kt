package io.github.rank.plugin.util

import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.PlayerInventory

fun PlayerInventory.addItems(material: Material, amount: Int) {
    for (i in 0 until amount) this.addItem(ItemStack(material))
}