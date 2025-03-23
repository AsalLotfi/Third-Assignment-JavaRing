package org.project.object.consumables;

import org.project.entity.players.Player;

public class Potion extends Consumable {
    private final int healAmount;

    public Potion() {
        super(10);
        this.healAmount = 30;
    }

    @Override
    public void use(Player target) {
        if (target.getMp() > getManaCost()) {
            target.heal(healAmount);
            target.useMana(getManaCost());
            System.out.println("You used a Potion! Restored " + healAmount + " HP.");
            System.out.println("Remaining Mana: " + target.getMp());
        } else {
            System.out.println("Not enough mana! You need at least " + getManaCost() + " MP to use a potion.");
        }
    }
}
