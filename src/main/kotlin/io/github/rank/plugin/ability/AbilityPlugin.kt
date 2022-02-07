package io.github.rank.plugin.ability

import io.github.rank.plugin.ability.listener.*
import io.github.rank.plugin.ability.util.*
import io.github.rank.plugin.listener.*
import io.github.rank.plugin.pvp.listener.EntityDamageListener
import io.github.rank.plugin.pvp.listener.PlayerDeathListener
import io.github.rank.plugin.util.setFoodLevelMax
import net.kyori.adventure.text.Component
import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.event.Listener
import org.bukkit.plugin.Plugin
import org.bukkit.plugin.java.JavaPlugin
import java.lang.RuntimeException

lateinit var plugin: AbilityPlugin

val playerInfos = mutableListOf<PlayerInfo>()

private fun Plugin.registerListeners(vararg listeners: Listener) {
    listeners.forEach {
        server.pluginManager.registerEvents(it, this)
    }
}

fun Player.getPlayerInfo(): PlayerInfo {
    for (infoPlayer in playerInfos) {
        if (infoPlayer.player == this) return infoPlayer
    }

    throw RuntimeException("It's Invalid Player! Please Check Did You Reloaded!")
}

class AbilityPlugin: JavaPlugin() {
    init {
        plugin = this
    }

    private fun Player.setPlayerPvpClass(pvpClass: PvpClass) {
        for (playerInfo in playerInfos) {
            if (playerInfo.player == this) {
                val playerInfo2 = playerInfo
                playerInfo2.pvpClass = pvpClass
                playerInfos[playerInfos.indexOf(playerInfo)] = playerInfo2
                return
            }
        }
        throw RuntimeException("Not Invalid Player in InGame!")
    }

    override fun onEnable() {
        registerListeners(EntityDamageListener(), PlayerDeathListener()) // PvP Listeners

        registerListeners(ClassSelectListener(), ClockClassSelectorUseListener()) // Class Select Listeners

        registerListeners(
            ServerMotdListener(),
            PlayerJoinListener(),
            BlockBreakListener(),
            CraftListener(),
            CobbleStoneCreateListener(),
            PlayerItemListener()
         ) // Util Listeners

        registerListeners(TNTability(), TNTAttackListener(),  FlightAbility()) // TNT Class

        registerListeners(SkaterMoveListener(), IceBorderUseListener()) // Skater Class

        registerListeners(RabbitStewDrinkListener()) // Rabbit Class

        registerListeners(BlindArcherListener()) // Blind Archer Class

        registerListeners(TrollerSkillListener()) // Troller Class

        registerListeners(ElectricianAttackListener()) // Electrician Class

        registerListeners(PlanterSkillUseListener()) // Planter Class

        registerListeners(BlowFishAttackListener()) // BlowFish Class

        registerListeners(BloodLustDamageListener()) // BloodLust Class
    }

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        when (command.name) {
            "fly" -> {
                if (sender !is Player) return false
                if (!sender.isOp) {
                    sender.sendMessage(Component.text("${ChatColor.RED}You are not able to use this command!"))
                    return false
                }

                sender.allowFlight = !sender.allowFlight
            }

            "class" -> {
                if (sender !is Player) return false

                if (sender.getPlayerInfo().pvpClass != PvpClass.NULL) {
                    sender.sendMessage(Component.text("${ChatColor.RED}You already have PVP Class!"))
                    return false
                }

                sender.openInventory(ClassSelector.getSelectorGUI())
            }

            "lobby" -> {
                if (sender !is Player) return false

                sender.setPlayerPvpClass(PvpClass.NULL)

                sender.health = 20.0

                sender.lobby()
                sender.setBasicItem()
                sender.setFoodLevelMax()
            }

            "battlefield" -> {
                if (sender !is Player) return false

                sender.battlefield()
            }
        }
        return false
    }
}