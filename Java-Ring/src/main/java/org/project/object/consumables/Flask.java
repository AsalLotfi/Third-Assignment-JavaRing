package org.project.object.consumables;

import org.project.entity.players.Player;


public class Flask extends Consumable {
    private final int healAmount; // HP restored when used

    public Flask(int healAmount) {
        super(0); // No mana cost during combat
        this.healAmount = healAmount;
    }

    @Override
    public void use(Player target) {
        target.heal(healAmount);
        System.out.println("You used a Flask! Restored " + healAmount + " HP.");
    }

    public int getHealAmount() {
        return healAmount;
    }
}
