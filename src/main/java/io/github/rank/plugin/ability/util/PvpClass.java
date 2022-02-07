package io.github.rank.plugin.ability.util;

import java.util.List;

public enum PvpClass {
    INSTANCE(0),
    NULL(0),
    WARRIOR(1),
    ARCHER(2),
    SPEEDER(3),
    HOT(4),
    ASSASSIN(5),
    FISHER(6),
    BERSERKER(7),
    TNT(8),
    TROLLER(9),
    BLIND_ARCHER(10),
    RABBIT(11),
    SKATER(12),
    ELECTRICIAN(13),
    PLANTER(14),
    SNIPER(15),
    BLOWFISH(16),
    BLOODLUST(17),
    MAXIMUM(17),
    BUG(-1);

    private final int classNumber;

    PvpClass(int classNumber) { this.classNumber = classNumber; }

    public int getClassNumber() { return classNumber; }

    public PvpClass getPvpClass(int classNumber) {
        List<PvpClass> except = List.of(PvpClass.INSTANCE, PvpClass.MAXIMUM);
        for (PvpClass pvpClass : values()) {
            if (pvpClass.classNumber == classNumber && !except.contains(pvpClass)) return pvpClass;
        }
        return PvpClass.BUG;
    }
}