package org.project.entity.enemies;

import org.project.entity.Entity;
import org.project.object.weapons.Weapon;

// TODO: UPDATE IMPLEMENTATION
public abstract class Enemy implements Entity {
    Weapon weapon;
    private int hp;
    private int mp;
    private final int maxHp;
    private final int maxMp;

    public Enemy(int hp, int mp, Weapon weapon) {
        this.hp = hp;
        this.mp = mp;
        this.weapon = weapon;
        this.maxHp = hp; // Store initial values as max HP and MP
        this.maxMp = mp;
    }


    @Override
    public void attack(Entity target) {

    }

    @Override
    public void defend() {

    }

    @Override
    public void fillMana(int mana) {

    }

    @Override
    public void takeDamage(int damage) {
        hp -= damage;
    }



    @Override
    public int getHp() {
        return hp;
    }

    @Override
    public int getMp() {
        return mp;
    }

    @Override
    public int getMaxHP() {
        return maxHp;
    }

    @Override
    public int getMaxMP() {
        return maxMp;
    }

    @Override
    public boolean isAlive() {
        return hp > 0;
    }

    public Weapon getWeapon() {
        return weapon;
    }
}
