package io.github.rank.plugin.ability.util

import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack

class ClassSelector {
    companion object {

        fun getSelectorGUI(): Inventory {
            val number = PvpClass.MAXIMUM.classNumber
            val line = (number - 1) / 9 + 1 + 2
            val inventory = Bukkit.createInventory(null, line * 9, Component.text("Class Selector"))

            val blank = ItemStack(Material.GRAY_STAINED_GLASS_PANE)
            val meta = blank.itemMeta
            meta.displayName(Component.text(" "))
            blank.itemMeta = meta

            for (i in 0 until 9) {
                inventory.setItem(i, blank)
                inventory.setItem((line - 1) * 9 + i, blank)
            }

            val warrior = ItemStack(Material.IRON_SWORD)
            warrior.editMeta {
                it.displayName(Component.text("${ChatColor.YELLOW}워리어 클래스"))
                it.lore(listOf(Component.text("${ChatColor.WHITE}가장 기본적인 형태의 클래스."),
                    Component.text("${ChatColor.WHITE}능력 : 없음"),
                    Component.text("${ChatColor.WHITE}버프 : 없음")))
                it.addItemFlags(ItemFlag.HIDE_ATTRIBUTES)
            }
            inventory.setItem(9, warrior)

            val archer = ItemStack(Material.BOW)
            archer.editMeta {
                it.displayName(Component.text("${ChatColor.YELLOW}궁수 클래스"))
                it.lore(listOf(Component.text("${ChatColor.WHITE}궁수 형태의 클래스."),
                    Component.text("${ChatColor.WHITE}능력 : 없음"),
                    Component.text("${ChatColor.WHITE}버프 : 없음")))
                it.addItemFlags(ItemFlag.HIDE_ATTRIBUTES)
            }
            inventory.setItem(10, archer)


            val speeder = ItemStack(Material.GOLDEN_BOOTS)
            speeder.editMeta {
                it.displayName(Component.text("${ChatColor.YELLOW}스피더 클래스"))
                it.lore(listOf(Component.text("${ChatColor.WHITE}날렵한 클래스."),
                    Component.text("${ChatColor.WHITE}능력 : 없음"),
                    Component.text("${ChatColor.WHITE}버프 : 이동 속도 증가, 공격력 증가")))
                it.addItemFlags(ItemFlag.HIDE_ATTRIBUTES)
            }
            inventory.setItem(11, speeder)

            val hot = ItemStack(Material.LAVA_BUCKET)
            hot.editMeta {
                it.displayName(Component.text("${ChatColor.YELLOW}핫 클래스"))
                it.lore(listOf(Component.text("${ChatColor.WHITE}최초의 테러형 클래스."),
                    Component.text("${ChatColor.WHITE}능력 : 없음"),
                    Component.text("${ChatColor.WHITE}버프 : 화염 저항")))
                it.addItemFlags(ItemFlag.HIDE_ATTRIBUTES)
            }
            inventory.setItem(12, hot)

            val assassin = ItemStack(Material.BARRIER)
            assassin.editMeta {
                it.displayName(Component.text("${ChatColor.YELLOW}어쎄신 클래스"))
                it.lore(listOf(Component.text("${ChatColor.WHITE}은신 클래스."),
                    Component.text("${ChatColor.WHITE}능력 : 없음"),
                    Component.text("${ChatColor.WHITE}버프 : 투명")))
                it.addItemFlags(ItemFlag.HIDE_ATTRIBUTES)
            }
            inventory.setItem(13, assassin)

            val fisher = ItemStack(Material.FISHING_ROD)
            fisher.editMeta {
                it.displayName(Component.text("${ChatColor.YELLOW}낚시꾼 클래스"))
                it.lore(listOf(Component.text("${ChatColor.WHITE}친수성 클래스."),
                    Component.text("${ChatColor.WHITE}능력 : 없음"),
                    Component.text("${ChatColor.WHITE}버프 : 돌고래의 가호")))
                it.addItemFlags(ItemFlag.HIDE_ATTRIBUTES)
            }
            inventory.setItem(14, fisher)

            val bulldozer = ItemStack(Material.NETHERITE_CHESTPLATE)
            bulldozer.editMeta {
                it.displayName(Component.text("${ChatColor.YELLOW}버서커 클래스"))
                it.lore(listOf(Component.text("${ChatColor.WHITE}무거운 클래스."),
                    Component.text("${ChatColor.WHITE}능력 : 없음"),
                    Component.text("${ChatColor.WHITE}버프 : 이동 속도 저하, 느린 낙하")))
                it.addItemFlags(ItemFlag.HIDE_ATTRIBUTES)
            }
            inventory.setItem(15, bulldozer)

            val tnt = ItemStack(Material.TNT)
            tnt.editMeta {
                it.displayName(Component.text("${ChatColor.YELLOW}TNT 클래스"))
                it.lore(listOf(Component.text("${ChatColor.WHITE}최초의 능력형 클래스."),
                    Component.text("${ChatColor.WHITE}능력 : TNT 를 들고 우클릭 시 폭격이 가능하다."),
                    Component.text("${ChatColor.WHITE}TNT 를 들고 플레이어 공격 시 폭발을 일으킨다."),
                    Component.text("${ChatColor.WHITE}겉날개를 들고 우클릭 시 3초 동안 날수 있다."),
                    Component.text("${ChatColor.WHITE}버프 : 없음")))
                it.addItemFlags(ItemFlag.HIDE_ATTRIBUTES)
            }
            inventory.setItem(16, tnt)

            val troller = ItemStack(Material.COBWEB)
            troller.editMeta {
                it.displayName(Component.text("${ChatColor.YELLOW}트롤러 클래스"))
                it.lore(listOf(Component.text("${ChatColor.WHITE}트롤 클래스."),
                    Component.text("${ChatColor.WHITE}능력 : 거미줄을 들고 발사 할수 있다."),
                    Component.text("${ChatColor.WHITE}버프 : 없음")))
                it.addItemFlags(ItemFlag.HIDE_ATTRIBUTES)
            }
            inventory.setItem(17, troller)

            val blindArcher = ItemStack(Material.BONE)
            blindArcher.editMeta {
                it.displayName(Component.text("${ChatColor.YELLOW}블라인드 궁수 클래스"))
                it.lore(listOf(Component.text("${ChatColor.WHITE}눈이 먼 궁수 클래스."),
                    Component.text("${ChatColor.WHITE}능력 : 적을 맞추면 실명시킨다."),
                    Component.text("${ChatColor.WHITE}버프 : 실명, 구속")))
                it.addItemFlags(ItemFlag.HIDE_ATTRIBUTES)
            }
            inventory.setItem(18, blindArcher)

            val rabbit = ItemStack(Material.RABBIT_FOOT)
            rabbit.editMeta {
                it.displayName(Component.text("${ChatColor.YELLOW}토끼 클래스"))
                it.lore(listOf(Component.text("${ChatColor.WHITE}신체 능력 강화 클래스."),
                    Component.text("${ChatColor.WHITE}능력 : 비상 식량을 먹으면 신체 강화."),
                    Component.text("${ChatColor.WHITE}버프 : 점프 강화")))
                it.addItemFlags(ItemFlag.HIDE_ATTRIBUTES)
            }
            inventory.setItem(19, rabbit)

            val skater = ItemStack(Material.DIAMOND_BOOTS)
            skater.editMeta {
                it.displayName(Component.text("${ChatColor.YELLOW}스케이터 클래스"))
                it.lore(listOf(Component.text("${ChatColor.WHITE}스피더의 상위 클래스(치명적인 단점이 존재)."),
                    Component.text("${ChatColor.WHITE}능력 : 얼음 장벽 설치 시 얼음 장벽 생성."),
                    Component.text("${ChatColor.WHITE}버프 : 이동 속도 대폭 증가, 공격력 증가")))
                it.addItemFlags(ItemFlag.HIDE_ATTRIBUTES)
            }
            inventory.setItem(20, skater)

            val electrician = ItemStack(Material.GOLDEN_AXE)
            electrician.editMeta {
                it.displayName(Component.text("${ChatColor.YELLOW}전기공 클래스"))
                it.lore(listOf(Component.text("${ChatColor.WHITE}전기 클래스."),
                    Component.text("${ChatColor.WHITE}능력 : 상대에게 전기 공격을 가함."),
                    Component.text("${ChatColor.WHITE}버프 : 없음")))
                it.addItemFlags(ItemFlag.HIDE_ATTRIBUTES)
            }
            inventory.setItem(21, electrician)

            val planter = ItemStack(Material.DANDELION)
            planter.editMeta {
                it.displayName(Component.text("${ChatColor.YELLOW}환경 미화원 클래스"))
                it.lore(listOf(Component.text("${ChatColor.WHITE}환경 미화 클래스."),
                    Component.text("${ChatColor.WHITE}능력 : 꽃을 든 상태로 우클릭 시 범위 내 적을 공격."),
                    Component.text("${ChatColor.WHITE}스킬로 적을 공격할 시 자신의 체력도 회복."),
                    Component.text("${ChatColor.WHITE}버프 : 없음")))
                it.addItemFlags(ItemFlag.HIDE_ATTRIBUTES)
            }
            inventory.setItem(22, planter)

            val sniper = ItemStack(Material.CROSSBOW)
            sniper.editMeta {
                it.displayName(Component.text("${ChatColor.YELLOW}스나이퍼 클래스"))
                it.lore(listOf(Component.text("${ChatColor.WHITE}저격 클래스."),
                    Component.text("${ChatColor.WHITE}능력 : 없음"),
                    Component.text("${ChatColor.WHITE}버프 : 없음")))
                it.addItemFlags(ItemFlag.HIDE_ATTRIBUTES)
            }
            inventory.setItem(23, sniper)

            val blowfish = ItemStack(Material.PUFFERFISH)
            blowfish.editMeta {
                it.displayName(Component.text("${ChatColor.YELLOW}복어 클래스"))
                it.lore(listOf(Component.text("${ChatColor.WHITE}독 클래스."),
                    Component.text("${ChatColor.WHITE}능력 : 플레이어를 공격 시 독을 줌."),
                    Component.text("${ChatColor.WHITE}버프 : 호흡")))
                it.addItemFlags(ItemFlag.HIDE_ATTRIBUTES)
            }
            inventory.setItem(24, blowfish)

            val bloodlust = ItemStack(Material.REDSTONE)
            bloodlust.editMeta {
                it.displayName(Component.text("${ChatColor.YELLOW}블러드 러스트 클래스"))
                it.lore(listOf(Component.text("${ChatColor.WHITE}최초의 각성 클래스."),
                    Component.text("${ChatColor.WHITE}능력 : 플레이어를 죽이는 만큼 각성."),
                    Component.text("${ChatColor.WHITE}버프 : 없음")))
                it.addItemFlags(ItemFlag.HIDE_ATTRIBUTES)
            }
            inventory.setItem(25, bloodlust)

            val random = ItemStack(Material.NETHER_STAR)
            random.editMeta {
                it.displayName(Component.text("${ChatColor.YELLOW}랜덤"))
                it.addItemFlags(ItemFlag.HIDE_ATTRIBUTES)
            }
            inventory.setItem(31, random)

            return inventory
        }
    }
}