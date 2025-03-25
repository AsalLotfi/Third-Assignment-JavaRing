package org.project.entity.enemies;

import org.project.entity.Entity;
import org.project.entity.players.Assassin;
import org.project.entity.players.Player;


public abstract class Enemy implements Entity {
    private int hp;
    private final int maxHp;
    private int damage;

    // Constructor
    public Enemy(int hp, int damage) {
        this.hp = hp;
        this.maxHp = hp; // Store initial values as max HP and MP
        this.damage = damage;
    }


    @Override
    public void attack(Entity target) {
        int finalDamage = getDamage(); // Default full damage

        // Check if the target is a Player (since only Players have armor)
        if (target instanceof Player) {
            Player player = (Player) target; // Cast Entity to Player

            if (player instanceof Assassin && ((Assassin)player).isInvisible()) {
                player.takeDamage(finalDamage);
                System.out.print("You took 0 damage!");
                return; // Exit the method cause Assassin is invisible so no need to Check if the player has armor
            }

            if (player.getArmor() != null && !player.getArmor().isBroken()) {
                finalDamage = player.getArmor().reduceDamage(getDamage()); // Reduce damage if armor exists
            }
        }

        target.takeDamage(finalDamage); // Apply final damage
        System.out.print("You took " + finalDamage + " damage!");
    }

    @Override
    public void takeDamage(int damage) {
        hp -= damage;
    }

    @Override
    public int getHp() {
        return hp;
    }

    public void setHp(int hp) { this.hp = hp; }

    @Override
    public int getMaxHP() {
        return maxHp;
    }

    @Override
    public boolean isAlive() {
        return hp > 0;
    }

    public int getDamage() { return damage;}
}
