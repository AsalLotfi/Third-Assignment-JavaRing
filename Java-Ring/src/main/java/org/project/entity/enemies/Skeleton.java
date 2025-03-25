package org.project.entity.enemies;

import org.project.entity.Entity;
import org.project.entity.players.Player;
import java.util.Random;

public class Skeleton extends Enemy {
    private final Random random = new Random();
    private boolean hasResurrected = false; // Track resurrection

    public Skeleton(int hp, int damage) {
        super(hp, damage);
    }

    /*
     * Unique Ability: resurrect once
     * - returning to battle with a 50% of its health restored.
     */

    @Override
    public void takeDamage(int damage) {
        super.takeDamage(damage);

        // Check if Skeleton "dies"
        if (!isAlive() && !hasResurrected) {
            resurrect();
        }
    }

    private void resurrect() {
        hasResurrected = true; // Mark that it has resurrected
        int resurrectHP = getMaxHP() / 2; // Restore 50% HP
        setHp(resurrectHP);
        System.out.println("The Skeleton reforms its bones and rises again with " + resurrectHP + " HP!");
    }
}
