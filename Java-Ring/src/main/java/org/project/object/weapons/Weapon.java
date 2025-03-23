package org.project.object.weapons;

import org.project.entity.Entity;
import org.project.entity.players.Player;
import org.project.object.Object;


public abstract class Weapon implements Object {
    private final int damage;
    private final int manaCost;
    private int durability;
    private final int maxDurability;

    public Weapon(int damage, int manaCost,  int durability) {
        this.damage = damage;
        this.manaCost = manaCost;
        this.durability = durability;
        this.maxDurability = durability;
    }

    @Override
    public void use(Player target) {
        target.takeDamage(damage);
        durability -= 5;

        if (durability <= 0) {
            durability = 0;
        }
    }

    public void repair() {
        durability = maxDurability;
    }

    public boolean isBroken() {
        return durability <= 0;
    }

    public int getDamage() {
        return damage;
    }

    public int getDurability() {
        return durability;
    }

    @Override
    public int getManaCost() {
        return manaCost;
    }

    @Override
    public boolean canUse(Player player) {
        return player.getMp() >= manaCost;
    }
}
