package org.project.entity.enemies;

import org.project.entity.Entity;


public abstract class Enemy implements Entity {
    private int hp;
    private final int maxHp;
    private int damage;

    public Enemy(int hp, int damage) {
        this.hp = hp;
        this.maxHp = hp; // Store initial values as max HP and MP
        this.damage = damage;
    }


    @Override
    public void attack(Entity target) {
        target.takeDamage(damage);
    }

    @Override
    public void defend() {

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
    public int getMaxHP() {
        return maxHp;
    }

    @Override
    public boolean isAlive() {
        return hp > 0;
    }

    public int getDamage() { return damage;}
}
