package org.project.entity;

public interface Entity {

    void attack(Entity target); // Attack another entity
    void defend(); // Defend against an attack
    void fillMana(int mana); // Restore mana points
    void takeDamage(int damage); // Take damage

    int getHp(); // get current HP
    int getMp(); // get current MP
    int getMaxHP(); // get maximum HP
    int getMaxMP(); // get maximum MP

    boolean isAlive(); // Check if entity is still alive

}
