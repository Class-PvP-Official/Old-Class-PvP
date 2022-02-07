package io.github.rank.plugin.ability.util

import io.github.asr.mafp.utils.RED
import io.github.rank.plugin.ability.getPlayerInfo
import io.github.rank.plugin.util.addItems
import io.github.rank.plugin.util.setFoodLevelMax
import net.kyori.adventure.text.Component
import org.bukkit.ChatColor
import org.bukkit.Color
import org.bukkit.FireworkEffect
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.CrossbowMeta
import org.bukkit.inventory.meta.FireworkMeta
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType

fun ItemStack.setUnbreakable(): ItemStack {
    val meta = this.itemMeta
    meta.isUnbreakable = true
    meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE)
    this.itemMeta = meta
    return this
}

fun getPotionEffect(potionEffectType: PotionEffectType, amplifier: Int, particles: Boolean = false): PotionEffect {
    return PotionEffect(potionEffectType, Int.MAX_VALUE, amplifier, false, particles, false)
}

fun Player.giveItemEffect() {
    this.setFoodLevelMax()

    when(this.getPlayerInfo().pvpClass) {
        PvpClass.WARRIOR -> {
            this.inventory.addItem(ItemStack(Material.STONE_SWORD).setUnbreakable())

            this.inventory.chestplate = ItemStack(Material.IRON_CHESTPLATE).setUnbreakable()
            this.inventory.leggings = ItemStack(Material.IRON_LEGGINGS).setUnbreakable()
            this.inventory.boots = ItemStack(Material.IRON_BOOTS).setUnbreakable()
        }

        PvpClass.ARCHER -> {
            val archerHelmet = ItemStack(Material.CHAINMAIL_HELMET)
            archerHelmet.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3)
            this.inventory.helmet = archerHelmet.setUnbreakable()

            val archerChest = ItemStack(Material.CHAINMAIL_CHESTPLATE)
            archerChest.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3)
            this.inventory.chestplate = archerChest.setUnbreakable()

            val archerBow = ItemStack(Material.BOW)
            archerBow.addUnsafeEnchantment(Enchantment.ARROW_INFINITE, 1)
            this.inventory.addItem(archerBow.setUnbreakable())

            this.inventory.addItem(ItemStack(Material.ARROW).setUnbreakable())
        }

        PvpClass.SPEEDER -> {
            val speederPotionEffect = listOf(getPotionEffect(PotionEffectType.SPEED, 0),
                getPotionEffect(PotionEffectType.INCREASE_DAMAGE, 1))

            this.inventory.boots = ItemStack(Material.GOLDEN_BOOTS).setUnbreakable()
            this.addPotionEffects(speederPotionEffect)
        }

        PvpClass.HOT -> {
            val hotPotionEffect = listOf(getPotionEffect(PotionEffectType.FIRE_RESISTANCE, 0))

            val pickaxe = ItemStack(Material.STONE_PICKAXE)
            pickaxe.addUnsafeEnchantment(Enchantment.LUCK, 10)
            this.inventory.addItem(pickaxe.setUnbreakable())

            this.inventory.addItems(Material.LAVA_BUCKET, 1)

            this.addPotionEffects(hotPotionEffect)
        }

        PvpClass.ASSASSIN -> {
            val assassinPotionEffect = listOf(getPotionEffect(PotionEffectType.INVISIBILITY, 0, true))

            val shovel = ItemStack(Material.IRON_SHOVEL)
            shovel.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 2)
            this.inventory.addItem(shovel.setUnbreakable())

            this.addPotionEffects(assassinPotionEffect)
        }

        PvpClass.FISHER -> {
            val fisherPotionEffect = listOf(getPotionEffect(PotionEffectType.DOLPHINS_GRACE, 2))

            val attackRod = ItemStack(Material.FISHING_ROD)
            attackRod.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 5)
            var meta = attackRod.itemMeta
            meta.displayName(Component.text("${ChatColor.RED}날카로운 낚시대"))
            attackRod.itemMeta = meta

            val knockBackRod = ItemStack(Material.FISHING_ROD)
            knockBackRod.addUnsafeEnchantment(Enchantment.KNOCKBACK, 2)
            meta = knockBackRod.itemMeta
            meta.displayName(Component.text("${ChatColor.LIGHT_PURPLE}밀치기 낚시대"))
            knockBackRod.itemMeta = meta

            this.inventory.addItem(attackRod.setUnbreakable())
            this.inventory.addItem(knockBackRod.setUnbreakable())
            this.inventory.addItems(Material.WATER_BUCKET, 1)

            this.addPotionEffects(fisherPotionEffect)
        }

        PvpClass.BERSERKER -> {
            val bulldozerPotionEffect = listOf(getPotionEffect(PotionEffectType.SLOW, 5),
                getPotionEffect(PotionEffectType.SLOW_FALLING, 0))

            this.inventory.addItem(ItemStack(Material.NETHERITE_AXE).setUnbreakable())

            val chestPlate = ItemStack(Material.NETHERITE_CHESTPLATE)
            chestPlate.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 10)
            this.inventory.chestplate = chestPlate.setUnbreakable()

            this.addPotionEffects(bulldozerPotionEffect)
        }

        PvpClass.TNT -> {
            this.inventory.addItem(ItemStack(Material.TNT))
            this.inventory.addItem(ItemStack(Material.ELYTRA))
        }

        PvpClass.BLIND_ARCHER -> {
            val blindArcherPotionEffect = listOf(getPotionEffect(PotionEffectType.BLINDNESS, 255),
                getPotionEffect(PotionEffectType.SLOW, 4))

            val blindArcherBowEnchantments = mutableMapOf<Enchantment, Int>()
            blindArcherBowEnchantments[Enchantment.ARROW_KNOCKBACK] = 10
            blindArcherBowEnchantments[Enchantment.ARROW_INFINITE] = 1
            blindArcherBowEnchantments[Enchantment.ARROW_DAMAGE] = 4
            val bow = ItemStack(Material.BOW)
            bow.addUnsafeEnchantments(blindArcherBowEnchantments)
            this.inventory.addItem(bow.setUnbreakable())
            this.inventory.addItem(ItemStack(Material.ARROW))

            this.addPotionEffects(blindArcherPotionEffect)
        }

        PvpClass.RABBIT -> {
            val rabbitPotionEffect = listOf(getPotionEffect(PotionEffectType.JUMP, 2))

            val hotFoot = ItemStack(Material.RABBIT_FOOT)

            hotFoot.editMeta {
                it.displayName(Component.text("${ChatColor.RED}HOT FOOT"))
            }

            hotFoot.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 3)
            hotFoot.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 2)

            this.inventory.addItem(hotFoot)
            this.inventory.addItem(ItemStack(Material.RABBIT_STEW))

            this.inventory.boots = ItemStack(Material.LEATHER_BOOTS).setUnbreakable()

            this.addPotionEffects(rabbitPotionEffect)
        }

        PvpClass.TROLLER -> {
            this.inventory.addItem(ItemStack(Material.COBWEB))

            val leather = ItemStack(Material.LEATHER)
            leather.addUnsafeEnchantment(Enchantment.KNOCKBACK, 10)
            this.inventory.addItem(leather)

            val helmet = ItemStack(Material.LEATHER_HELMET)
            helmet.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 10)
            this.inventory.helmet = helmet.setUnbreakable()

            val egg = ItemStack(Material.EGG)
            egg.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 1)
            this.inventory.addItem(egg)
        }

        PvpClass.SKATER -> {
            val skaterPotionEffect = listOf(getPotionEffect(PotionEffectType.SPEED, 4),
                getPotionEffect(PotionEffectType.INCREASE_DAMAGE, 0))

            val iceWall = ItemStack(Material.PACKED_ICE)
            iceWall.editMeta { it.displayName(Component.text("${ChatColor.AQUA}얼음 장벽")) }
            this.inventory.addItem(iceWall)

            val skate = ItemStack(Material.DIAMOND_BOOTS)
            skate.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 5)
            this.inventory.boots = skate.setUnbreakable()

            this.addPotionEffects(skaterPotionEffect)
        }

        PvpClass.ELECTRICIAN -> {
            val electricTool = ItemStack(Material.GOLDEN_AXE)
            electricTool.editMeta {
                it.displayName(Component.text("${ChatColor.YELLOW}전기공의 도구"))
            }
            this.inventory.addItem(electricTool.setUnbreakable())

            this.inventory.chestplate = ItemStack(Material.DIAMOND_CHESTPLATE).setUnbreakable()
            this.inventory.leggings = ItemStack(Material.LEATHER_LEGGINGS).setUnbreakable()
            this.inventory.boots = ItemStack(Material.LEATHER_BOOTS).setUnbreakable()
        }

        PvpClass.PLANTER -> {
            this.inventory.addItem(ItemStack(Material.DANDELION))

            val planterChest = ItemStack(Material.LEATHER_CHESTPLATE)
            planterChest.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 5)

            this.inventory.chestplate = planterChest.setUnbreakable()

            val planterBoots = ItemStack(Material.IRON_BOOTS)
            planterBoots.addUnsafeEnchantment(Enchantment.PROTECTION_FIRE, 10)

            this.inventory.boots = planterBoots
        }

        PvpClass.SNIPER -> {
            val ammo = ItemStack(Material.FIREWORK_ROCKET).apply {
                val meta = this.itemMeta as FireworkMeta
                val fireworkEffect = FireworkEffect.builder()
                    .with(FireworkEffect.Type.BALL)
                    .withColor(Color.AQUA)
                    .withColor(Color.AQUA)
                    .withColor(Color.AQUA)
                    .withColor(Color.AQUA)
                    .withColor(Color.AQUA)
                    .withColor(Color.AQUA)
                    .withColor(Color.AQUA)
                    .withColor(Color.AQUA)
                    .withColor(Color.AQUA)
                    .withColor(Color.AQUA)
                    .withColor(Color.AQUA)
                    .withColor(Color.AQUA)
                    .build()
                meta.addEffect(fireworkEffect)
                meta.displayName(Component.text("${ChatColor.GRAY}탄"))

                this.itemMeta = meta
            }

            val rifle = ItemStack(Material.CROSSBOW).apply {
                val meta = this.itemMeta as CrossbowMeta
                meta.addChargedProjectile(ammo)
                meta.displayName(Component.text("${ChatColor.GRAY}라이플"))

                this.itemMeta = meta
            }.setUnbreakable()

            this.inventory.addItem(rifle)
            ammo.amount = 3
            this.inventory.addItem(ammo)

            this.inventory.chestplate = ItemStack(Material.LEATHER_CHESTPLATE).apply {
                addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 10)
            }.setUnbreakable()
        }

        PvpClass.BLOWFISH -> {
            val blowfishPotionEffect = listOf(getPotionEffect(PotionEffectType.WATER_BREATHING, 0, false))

            this.addPotionEffects(blowfishPotionEffect)

            this.inventory.chestplate = ItemStack(Material.GOLDEN_CHESTPLATE).apply {
                this.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4)
                this.addUnsafeEnchantment(Enchantment.THORNS, 10)
            }.setUnbreakable()
        }

        PvpClass.BLOODLUST -> {
            val bloodSword = ItemStack(Material.IRON_SWORD)

            bloodSword.editMeta {
                it.displayName(Component.text("${RED}피의 칼날"))
            }

            bloodSword.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 1)

            this.inventory.addItem(bloodSword.setUnbreakable())

            val bloodLustChest = ItemStack(Material.IRON_CHESTPLATE)

            bloodLustChest.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2)

            this.inventory.chestplate = bloodLustChest.setUnbreakable()
        }

        else -> {
            throw RuntimeException("Invalid PvpClass in InGame!")
        }
    }
}