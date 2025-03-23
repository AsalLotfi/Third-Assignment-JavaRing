package org.project.object.consumables;

import org.project.entity.Entity;
import org.project.entity.players.Player;
import org.project.object.Object;

// TODO: UPDATE IMPLEMENTATION
public abstract class Consumable implements Object {
    private final int manaCost;

    public Consumable(int manaCost) {
        this.manaCost = manaCost;
    }

    // Abstract method to be implemented by subclasses (Flask, Potion, etc.)
    @Override
    public abstract void use(Player target);

    // Get mana cost of the consumable
    public int getManaCost() {
        return manaCost;
    }

    // Check if the consumable can be used => enough mana available
    public boolean canUse(Player player) {
        return player.getMp() >= manaCost;
    }
}
