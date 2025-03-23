package org.project.object.armors;

import org.project.entity.players.Player;
import org.project.object.Object;

// TODO: UPDATE IMPLEMENTATION
public abstract class Armor implements Object {
    private int defense;
    private int maxDefense;
    private int durability;
    private final int maxDurability;
    private final int manaCost;
    private boolean isBroke;

    public Armor(int defense, int durability, int manaCost) {
        this.defense = defense;
        this.durability = durability;
        this.manaCost = manaCost;
        this.maxDurability = durability;
    }

    public void checkBreak() {
        if (durability <= 0) {
            isBroke = true;
            defense = 0;
        }
    }

    public int reduceDamage(int incomingDamage) {
        if (durability > 0) {
            durability -= 5;
            return Math.max(0, incomingDamage - defense); // Reduce damage
        } else {
            System.out.println("Your armor is broken! You take full damage.");
            return incomingDamage; // No reduction
        }
    }

    public void repair() {
        isBroke = false;
        defense = maxDefense;
        durability = maxDurability;
    }

    @Override
    public int getManaCost() { return manaCost; }

    @Override
    public boolean canUse(Player player) { return player.getMp() >= manaCost; }

    public int getDefense() {
        return defense;
    }

    public int getDurability() {
        return durability;
    }

    public boolean isBroken() {
        return isBroke;
    }
}
