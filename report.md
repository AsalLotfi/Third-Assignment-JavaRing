# **Project Report: Java-Ring - A Turn-Based RPG Game**

## **1. Introduction**
### **Game Overview**
**Java-Ring** is a turn-based RPG inspired by Souls-like games, designed as a console-based game in Java. The player engages in battles against enemies, each possessing unique abilities. The goal is to defeat all enemies while strategically managing health, mana, and equipment. The player can attack, use consumables, or activate their class-specific unique ability.

### **Purpose of the Project**
This project was developed as part of a university programming assignment to demonstrate an understanding of **Object-Oriented Programming (OOP)** principles, including **inheritance, polymorphism, and interfaces**. Additionally, the project implements game logic such as **combat mechanics, item usage, and unique character abilities**, reinforcing Java programming skills.

## **2. Game Features**
### **Combat System**
The game follows a **turn-based combat system** where the player and the enemy take turns performing actions. The player can:
- Attack using a weapon.
- Use a **Flask** to restore health.
- Use a **unique ability** (if conditions allow).

Each **enemy** also has distinct behaviors and abilities, affecting how combat plays out.

### **Player Classes and Unique Abilities**
The player can choose one of the following three classes, each with a special ability:

1. **Knight** - Can perform a **strong kick** that deals extra damage but can only be used after a certain number of rounds.
2. **Wizard** - Can cast a **special spell** that both heals themselves and damages the enemy.
3. **Assassin** - Can become **invisible for three turns**, making them immune to attacks while dealing increased damage.

Each class starts with specific health (HP), mana (MP), and equipment.

### **Enemies and Their Unique Abilities**
Each enemy has its own combat style and ability:

- **Goblin** - A standard enemy with no special abilities.
- **Skeleton** - Can **resurrect once** after being defeated, returning with partial health.
- **Dragon** - Can **bypass shields**, damaging players even if they are defending.

### **Consumables**
The game includes items that help the player survive combat:
- Flask - Restores a set amount of HP in the combat.
- Potion - Allowing the player to heal and restore more HP at Safe Haven.

### **Armor and Weapons**
- **Armor** provides damage reduction but can break over time.
- **Weapons** influence attack power and can also degrade after repeated use.
- Players can repair their equipment at the **Safe Haven**, a resting area in the game.

### **Game World & Locations**
- **Ruined Castle** - Home to a single **Skeleton**.
- **Dark Forest** - Home to a single **Goblin**.
- **Dragon’s Lair** - Home to a single **Dragon**.
- **Safe Haven** - A location where the player can rest and repair their equipment.

## **3. Object-Oriented Programming Implementation**
### **Classes and Inheritance**
The game utilizes OOP principles effectively:
- `Player` is an abstract superclass, extended by `Knight`, `Wizard`, and `Assassin`.
- `Enemy` is a superclass, extended by `Goblin`, `Skeleton`, and `Dragon`.
- `Armor` and `Weapon` are base classes that provide functionality for all armor and weapon types.
- The `GameManager` class controls the main game logic, including combat interactions and turn management.

### **Polymorphism**
Each subclass **overrides** methods from the parent class to define unique behaviors:
- `useUniqueAbility()` is overridden in each player subclass to implement the class-specific ability.
- `attack()` is overridden for the **Dragon**, as it has a unique attack style.
- `takeDamage()` is customized for **Skeleton**, allowing it to revive once.

### **Interfaces**
- A generic `Entity` interface is used for both `Player` and `Enemy` classes.
- `Object` interface is implemented by consumables, weapons, and armor to standardize their behavior.

## **4. Technical Details**
### **Key Methods and Logic**
#### **Combat System Flow**
1. The game starts, and the player is placed in a location with an enemy.
2. On each turn, the player chooses an action: Attack, Use Flask, or Use Unique Ability.
3. The enemy retaliates if still alive.
4. If the player wins, they move to a new location until all enemies are defeated.

#### **Code Example: Dragon’s Attack Method**
```java
public void attack(Entity target) {
    if (random.nextInt(100) < 30) { // 30% chance to use unique ability
        System.out.println("🔥 The Dragon unleashes its fiery breath, bypassing your armor!");
        target.takeDamage(getDamage()); // Ignores armor
    } else {
        Player player = (Player) target;
        if (!player.getArmor().isBroken()) {
            int damageTaken = player.getArmor().reduceDamage(getDamage());
            player.takeDamage(damageTaken);
        } else {
            player.takeDamage(getDamage());
        }
    }
}
```

### **Error Handling and Input Validation**
- Ensures only valid inputs (e.g., numbers for menu choices) are accepted.
- Prevents players from using abilities without enough mana.
- Checks if armor or weapons are broken before applying their effects.

## **5. Challenges & Solutions**
### **Challenges Faced**
- Implementing polymorphism correctly for player and enemy classes.
- Managing player actions dynamically based on **armor state**, **mana**, and **unique abilities**.
- Ensuring that the Assassin remains immune for three turns while still allowing the player to attack.

### **Solutions**
- Used **casting** (e.g., `(Player) target`) to differentiate between entity types in combat.
- Added **turn counters** for time-based abilities (e.g., Assassin’s invisibility).
- Implemented **durability checks** for weapons and armor to ensure proper functionality.

## **6. Future Enhancements**
### **Possible Features to Add**
- Implementing **a graphical user interface (GUI)** using **JavaFX**.
- Expanding the **game world** with more locations and enemies.
- Introducing a **leveling system** for player progression.
- Adding **multiplayer functionality**.

### **Improvements**
- **Enhanced AI**: Enemies could have smarter decision-making instead of random attacks.
- **More Weapons & Armors**: Providing different weapons with special abilities.

## **7. Conclusion**
This project successfully implements a turn-based RPG game using Java and OOP principles. The game includes various player classes, enemies, consumables, and a structured combat system. While the project is complete, there is potential for future improvements, such as a GUI, AI enhancements, and expanded game mechanics.

Through this project, valuable lessons were learned about **game development, object-oriented programming, and designing interactive systems in Java**.


