package org.project.entity.players;

import org.project.entity.Entity;
import org.project.entity.enemies.Enemy;
import org.project.object.armors.KnightArmor;
import org.project.object.weapons.Sword;

public class Assassin extends Player {
    private boolean invisible;
    private int invisibleTurnsLeft;

    public Assassin(String name) {
        super(name, 100, 50);
        invisible = false;
        invisibleTurnsLeft = 0;
    }

    /*
     * Unique Ability: Shadow Veil
     * - Becomes invisible for 3 turns (immune to attacks).
     * - Deals 25 damage while invisible.
     * - Costs 10 Mana.
     */

    @Override
    public void useUniqueAbility(Enemy enemy){
        System.out.println("The Assassin activates Shadow Veil! Now invisible for 3 turns!");
        invisible = true;
        invisibleTurnsLeft = 3;
        useMana(10); // Reduce mana by 10
        System.out.println("Remaining Mana: " + getMp());
    }

    @Override
    public boolean canUseUniqueAbility(){
        return getMp() >= 10 && !isInvisible(); // Can only activate when NOT already invisible
    }

    @Override
    public void attack(Entity enemy) {
        if (isInvisible()) {
            System.out.println("You strike from the shadows, dealing 25 damage!");
            enemy.takeDamage(25);
            System.out.println(enemy.getClass().getSimpleName() + " took 25 damage! (HP: " + enemy.getHp() + "/100)");
        } else {
            super.attack(enemy); // Normal attack
        }
    }

    @Override
    public void takeDamage(int damage) {
        if (invisible) {
            System.out.println("\nThe enemy attacks, but you are invisible! You take NO DAMAGE!");
        } else {
            super.takeDamage(damage); // Normal damage if visible
        }
    }

    public void updateInvisibility() {
        if (invisible) {
            invisibleTurnsLeft--;
            if (invisibleTurnsLeft == 0) {
                invisible = false;
                System.out.println("\nYour invisibility fades. You are now VISIBLE again!");
            }
        }
    }

    public boolean isInvisible() {
        return invisible;
    }
}
