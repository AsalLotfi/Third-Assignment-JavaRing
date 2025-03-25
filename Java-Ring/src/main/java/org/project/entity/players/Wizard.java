package org.project.entity.players;

import org.project.entity.enemies.Enemy;
import org.project.object.armors.KnightArmor;
import org.project.object.weapons.Sword;

public class Wizard extends Player {

    public Wizard(String name) {
        super(name, 100, 50);
    }

    /*
     * Unique Ability: Casts a Special Spell
     * - Heals the Wizard by 15 HP.
     * - Damages the enemy by 25.
     * - Costs 10 Mana.
     */

    @Override
    public void useUniqueAbility(Enemy enemy){
        System.out.println("The Wizard Casts a Special Spell! Restoring health and damaging the enemy!");

        enemy.takeDamage(25); // Enemy takes 20 damage
        heal(15); // Wizard heals for 15 HP
        useMana(10); // Reduce mana by 10

        System.out.println("The enemy took 25 damage!");
        System.out.println("You healed for 15 HP! Current HP: " + getHp() + "/100");
        System.out.println("Remaining Mana: " + getMp());
    }

    @Override
    public boolean canUseUniqueAbility(){
        return getMp() >= 10;
    }
}
