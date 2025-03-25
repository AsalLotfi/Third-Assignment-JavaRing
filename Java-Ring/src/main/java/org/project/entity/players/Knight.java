package org.project.entity.players;

import org.project.entity.enemies.Enemy;
import org.project.object.armors.Armor;
import org.project.object.armors.KnightArmor;
import org.project.object.weapons.Sword;


public class Knight extends Player {
    private final int uniqueAbilityCooldown; // Cooldown in turns
    private int turnsSinceLastUse; // Track turns since last use
    private final int kickDamage; // Fixed damage for the strong kick

    public Knight(String name) {
        super(name, 100, 50);
        this.uniqueAbilityCooldown = 3;
        this.turnsSinceLastUse = uniqueAbilityCooldown;
        this.kickDamage = 30;
    }

    /*
     * Unique Ability: Strong Kick
     * - Deals 30 damage.
     * - Costs 10 Mana.
     */

    @Override
    public void useUniqueAbility(Enemy enemy){
        System.out.println("Knight delivers a **powerful kick**, dealing " + kickDamage + " damage!");
        enemy.takeDamage(kickDamage);
        useMana(10);
        System.out.println(enemy.getClass().getSimpleName() + " took " + kickDamage + " damage! (HP: " + enemy.getHp() + "/100)");
        System.out.println("Remaining Mana: " + getMp());
        turnsSinceLastUse = 0; // Reset cooldown tracker
    }

    @Override
    public boolean canUseUniqueAbility(){
        return getMp() >= 10 && turnsSinceLastUse >= uniqueAbilityCooldown;
    }

    public void updateCooldown() {
        turnsSinceLastUse++; // Increment cooldown tracker after each turn
    }
}
