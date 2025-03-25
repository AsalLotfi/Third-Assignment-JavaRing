package org.project.entity.enemies;

import org.project.entity.Entity;
import org.project.entity.players.Assassin;
import org.project.entity.players.Player;

import java.util.Random;

public class Dragon extends Enemy {
    private final Random random = new Random();

    public Dragon(int hp, int damage) {
        super(hp, damage);
    }

    /*
     * Unique Ability: bypassing shields
     * - Ignores the player's armor.
     */

    @Override
    public void attack(Entity target) {

        if (!(target instanceof Player)) {
            return; // Safety check to ensure target is a Player
        }

        Player player = (Player) target; // Cast target to Player


        if (random.nextInt(100) < 30) { // 30% chance to use unique ability

            System.out.println("\nThe Dragon unleashes its fiery breath, bypassing your armor!");
            target.takeDamage(getDamage()); // Ignores armor completely

            if (player instanceof Assassin && ((Assassin)player).isInvisible()) {
                System.out.print("You took 0 damage!");
                return; // Exit the method cause Assassin is invisible so no need to Check if the player has armor
            }

            System.out.print("You took " + getDamage() + " damage!");
        } else {
            int damageTaken = getDamage(); // Default full damage

            if (player instanceof Assassin && ((Assassin)player).isInvisible()) {
                System.out.print("You took 0 damage!");
                return; // Exit the method cause Assassin is invisible so no need to Check if the player has armor
            }

            // Check if the player has armor and it's NOT broken
            if (player.getArmor() != null && !player.getArmor().isBroken()) {
                damageTaken = player.getArmor().reduceDamage(getDamage());
            }

            player.takeDamage(damageTaken);
            System.out.print("You took " + damageTaken + " damage!");
        }
    }
}
