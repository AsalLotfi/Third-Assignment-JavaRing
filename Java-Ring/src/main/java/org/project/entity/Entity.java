package org.project.entity;

public interface Entity {

    void attack(Entity target); // Attack another entity
    void defend(); // Defend against an attack
    void takeDamage(int damage); // Take damage

    int getHp(); // get current HP
    int getMaxHP(); // get maximum HP

    boolean isAlive(); // Check if entity is still alive
}
