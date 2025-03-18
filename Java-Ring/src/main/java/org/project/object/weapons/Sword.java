package org.project.object.weapons;

import org.project.entity.Entity;

import java.util.ArrayList;


public class Sword extends Weapon {

    private int abilityCharge;
    private static final int MAX_CHARGE = 10; // Charge needed to activate special attack

    public Sword() {
        super(25, 15);
        this.abilityCharge = 0;
    }

    // Increasing charge when attacking normally
    public void attack(Entity target){

        if (abilityCharge == MAX_CHARGE) {
            uniqueAbility(target);
        } else {
            target.takeDamage(getDamage());
            chargeUp(2); // Gain 2 charge per attack
        }
    }

    // Method to charge ability
    public void chargeUp(int amount){
        abilityCharge += amount;
    }

    // Special ability : Deals double damage when fully charged
    public void uniqueAbility(Entity target) {
        target.takeDamage(getDamage() * 2);
    }
}
