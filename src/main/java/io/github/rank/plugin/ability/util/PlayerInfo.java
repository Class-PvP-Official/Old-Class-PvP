package io.github.rank.plugin.ability.util;

import org.bukkit.entity.Player;

public class PlayerInfo {
    private final Player player;
    private PvpClass pvpClass;
    private boolean isInvincibility = true;

    public PlayerInfo(Player player) {
        this.player = player;
    }

    public Player getPlayer() { return player; }

    public PvpClass getPvpClass() { return pvpClass; }

    public void setPvpClass(PvpClass pvpClass) { this.pvpClass = pvpClass; }

    public boolean isInvincibility() { return isInvincibility; }

    public void setInvincibility(boolean isInvincibility) { this.isInvincibility = isInvincibility; }
}
