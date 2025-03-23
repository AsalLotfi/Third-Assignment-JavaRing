package org.project.entity.players;

import org.project.entity.Entity;
import org.project.object.armors.Armor;
import org.project.object.consumables.Flask;
import org.project.object.weapons.Weapon;


public abstract class Player implements Entity {
    protected String name;
    Weapon weapon;
    Armor armor;
    private int hp;
    private int maxHP;
    private int mp;
    private int maxMP;
    private int flasksCount;
    private final int MAX_FLASKS;
    private int manaCharge;
    private Flask flask;

    public Player(String name, int hp, int mp) {
        this.name = name;
        this.hp = hp;
        this.mp = mp;
        this.maxMP = 100;
        this.maxHP = 100;
        this.flasksCount = 0; // Starts at 0
        MAX_FLASKS = 3; // Max limit

        this.weapon = null; // No weapon equipped by default
        this.armor = null; // No armor equipped by default
        this.flask = new Flask(10); // Create one flask object
    }

    // each subclass should implement this method
    public abstract void useAbility();

    @Override
    public void attack(Entity target) {
        if (weapon != null && !weapon.isBroken()) {
            weapon.use(target); // Calls the use method, reducing durability
        } else {
            System.out.println("Your Weapon is broken! You deal minimal damage.");
            target.takeDamage(5); // Weak unarmed attack
        }
        manaCharge(5); // Increase mana at each attack
    }

    // Method to charge mana
    private void manaCharge(int amount) {
        manaCharge += amount;
    }

    @Override
    public void defend() {
        // TODO: (BONUS) IMPLEMENT A DEFENSE METHOD FOR SHIELDS
    }

    // TODO: (BONUS) UPDATE THE FORMULA OF TAKING DAMAGE
    @Override
    public void takeDamage(int damage) {
        hp -= damage - armor.getDefense();
    }

    public void heal(int health) {
        hp += health;
        if (hp > maxHP) {
            hp = maxHP;
        }
    }

    public void fillMana(int mana) {
        mp += mana;
        if (mp > maxMP) {
            mp = maxMP;
        }
    }

    public void useMana(int mana) {
        mp -= mana;
        if (mp <= 0) {
            mp = 0;
        }
    }

    public void refillFlasks() {
        if (flasksCount < MAX_FLASKS) {
            flasksCount++;
        }
    }

    public void useFlask() {
        if (flasksCount > 0) {
            flask.use(this);  // Calls Flask's use() method to heal
            flasksCount--;     // Reduce flask count
            System.out.println("Remaining Flasks: " + flasksCount);
        } else {
            System.out.println("You have no flasks left!");
        }
    }


    public String getName() {
        return name;
    }

    @Override
    public int getHp() {
        return hp;
    }

    @Override
    public int getMaxHP() {
        return maxHP;
    }

    @Override
    public boolean isAlive() { return hp > 0; }

    public int getMp() { return mp; }

    public int getMaxMP() { return maxMP; }

    public Weapon getWeapon() {
        return weapon;
    }

    public Armor getArmor() {
        return armor;
    }

    public void setArmor(Armor armor) { this.armor = armor; }

    public void setWeapon(Weapon weapon) { this.weapon = weapon; }

    public int getFlasksCount() { return flasksCount; }

}
