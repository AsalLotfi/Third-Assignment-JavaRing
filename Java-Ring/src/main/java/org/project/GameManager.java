package org.project;

import org.project.entity.enemies.Dragon;
import org.project.entity.enemies.Goblin;
import org.project.entity.enemies.Skeleton;
import org.project.entity.enemies.Enemy;
import org.project.entity.players.Assassin;
import org.project.entity.players.Knight;
import org.project.entity.players.Player;
import org.project.entity.players.Wizard;
import org.project.location.Location;
import org.project.object.armors.KnightArmor;
import org.project.object.weapons.Sword;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class GameManager {
    private List<Location> locations;
    private Player player;

    public GameManager(Player player) {
        this.player = player;

        // Initialize locations with enemies
        locations = new ArrayList<>();
        locations.add(new Location("Ruined Castle", new Skeleton(100, 25)));
        locations.add(new Location("Dark Forest", new Goblin(100, 20)));
        locations.add(new Location("Dragon's Lair", new Dragon(100, 30)));

        // Shuffle locations so the order is randomized
        Collections.shuffle(locations);
    }

    public void startGame() {

        // Automatically equip Sword and Knight Armor at game start
        System.out.println("Equipping your starting gear...");

        player.setWeapon(new Sword());
        player.useMana(20);
        System.out.println("You have equipped a Sword (-20 Mana).");

        player.setArmor(new KnightArmor());
        player.useMana(15);
        System.out.println("You have equipped Knight Armor (-15 Mana).");
        player.getArmor().use(player); // Armor is now worn

        System.out.println("Remaining Mana: " + player.getMp());

        System.out.print("Traveling to the first location");
        loadingEffect();

        while(!allLocationsCleared()) {
            Location nextLocation = getNextLocation();
            if (nextLocation != null) {
                System.out.println("\nYou have entered " + nextLocation.getName());
                System.out.println("A " + nextLocation.getEnemy().getClass().getSimpleName() + " appeared!");
                combat(player, nextLocation.getEnemy());

                if (!player.isAlive()) {
                    System.out.println("You have been defeated! Game Over.");
                    return;
                }

                nextLocation.setCleared(true);
            }
        }
        System.out.println("Congratulations! You won!");
        System.out.println("You have defeated all the enemies!");
        return;
    }

    private boolean allLocationsCleared() {
        for (Location loc : locations) {
            if(!loc.isCleared()) {
                return false;
            }
        }
        return true;
    }

    private Location getNextLocation() {
        for (Location loc : locations) {
            if(!loc.isCleared()) {
                return loc;
            }
        }
        return null;
    }

    private void combat(Player player, Enemy enemy) {
        Scanner scanner = new Scanner(System.in);

        while(player.isAlive() && enemy.isAlive()) {
            System.out.println("\n=== Your Turn ===\n");

            int choice;
            while(true) { // Keep asking until a valid input is received
                System.out.println("[1] Attack");
                System.out.println("[2] Use Flask (Restores HP)");
                System.out.println("[3] Use Unique Ability (Costs 10 Mana)");
                System.out.print("Choose an action: ");

                if (scanner.hasNextInt()) {
                    choice = scanner.nextInt();

                    if (choice >= 1 && choice <= 3) {
                        break; // Exit input loop if valid choice
                    }
                } else {
                    scanner.next(); // Discard invalid input
                }
                System.out.println("Invalid choice. Please enter 1, 2, or 3.");
            }

            boolean validAction = false; // Flag to check if action is performed

            while(!validAction) {

                switch (choice) {
                    case 1: // Attack
                        player.attack(enemy);
                        // Check if weapon is broken
                        if (player.getWeapon().isBroken()) {
                            System.out.println("Warning: Your weapon is broken! Consider repairing it at Safe Haven.");
                        }
                        validAction = true;
                        break;

                    case 2: // Use flask
                        if (player.getFlasksCount() > 0) {
                            player.useFlask();
                            System.out.println("You used a flask and restored 10 HP!");
                            validAction = true;
                        }  else {
                            System.out.println("No flasks left!");
                            System.out.println("Please choose another action.");
                            choice = scanner.nextInt(); // Let player pick another action
                        }
                        break;

                    case 3: // Use Unique Ability
                        if (player instanceof Knight) { // Knight's Unique Ability
                            Knight knight = (Knight) player;
                            if (knight.canUseUniqueAbility()) {
                                knight.useUniqueAbility(enemy);
                                validAction = true;
                            } else {
                                System.out.println("Your unique ability is on cooldown or you lack mana!");
                            }
                        }
                        else if (player instanceof Wizard) { // Wizard's Unique Ability
                            Wizard wizard = (Wizard) player;
                            if (wizard.canUseUniqueAbility()) {
                                wizard.useUniqueAbility(enemy);
                                validAction = true;
                            } else {
                                System.out.println("Not enough mana for the Wizard's ability!");
                            }
                        }
                        else if (player instanceof Assassin) { // Assassin's unique ability
                            Assassin assassin = (Assassin) player;
                            if (assassin.canUseUniqueAbility()) {
                                assassin.useUniqueAbility(enemy);
                                validAction = true;
                            } else {
                                System.out.println("Not enough mana for the Assassin's ability or Still invisible!");
                            }
                        }

                        // If the unique ability was NOT used, ask the player to choose again
                        if (!validAction) {
                            System.out.println("Please choose another action.");
                            choice = scanner.nextInt();
                        }
                        break;
                }
            }

            if (player instanceof Knight) {
                ((Knight) player).updateCooldown(); // CALL updateCooldown() here to track cooldowns
            }

            if (player instanceof Assassin) {
                ((Assassin) player).updateInvisibility(); // Call updateInvisibility() here to decrement invisibility turns
            }

            // Enemy's turn if still alive
            if (enemy.isAlive()) {
                System.out.println("\n=== Enemy's Turn ===");
                System.out.print(enemy.getClass().getSimpleName() + " attacked you back!");
                enemy.attack(player);
                System.out.println(" (HP: " + player.getHp() + "/100)");

                // Check if armor is broken
                if (player.getArmor().isBroken()) {
                    System.out.println("Warning: Your armor is broken! You are taking full damage.");
                }
            }
        }

        // Player wins
        if (player.isAlive()) {
            System.out.println("You have defeated " + enemy.getClass().getSimpleName() + "!");

            if (allLocationsCleared()) {
                return; // player wins the game
            }

            player.fillMana(30);
            System.out.println("You have gained 30 Mana! remaining mana: " + player.getMp());

            System.out.println("\nDo you want to:");
            System.out.println("[1] Move to the next location");
            System.out.println("[2] Go to Safe Haven to heal");

            int move;
            while(true) { // Validate input for next move
                if (scanner.hasNextInt()) {
                    move = scanner.nextInt();
                    if (move == 1 || move == 2) {
                        break;
                    }
                } else {
                    scanner.next(); // Discard invalid input
                }
                System.out.println("Invalid choice. Please enter 1 or 2.");
            }

            if (move == 1) {

                System.out.print("Moving to the next location");
                loadingEffect();

            } else {
                System.out.print("Moving to the Safe Haven");
                loadingEffect();
                safeHaven(player);
            }
        }
    }

    private void safeHaven(Player player) {
        Scanner scanner = new Scanner(System.in);

        while (true) {

            System.out.println("\n=== SAFE HAVEN ===");
            System.out.println("You have arrived at the Safe Haven. Choose an action:");
            System.out.println("[1] Use Potion to Heal (+30 HP, costs 10 MP)");
            System.out.println("[2] Refill a Flask (+10 HP each, costs 5 MP each, Max: " + (3 - player.getFlasksCount()) + ")");
            System.out.println("[3] Repair Weapon (if broken, costs 10 MP)");
            System.out.println("[4] Repair Armor (if broken, costs 10 MP)");
            System.out.println("[5] Continue to the Next Location");
            System.out.println("Current flasks: " + player.getFlasksCount() + ", " + "Current mana: " + player.getMp());

            int choice = scanner.nextInt();

            switch(choice) {
                case 1: // Use potion to heal
                    if (player.getMp() >= 10) {
                        player.heal(30);
                        player.useMana(10);
                        System.out.println("You used a potion and restored 30 HP!");
                    } else {
                        System.out.println("Not enough mana! You need at least 10 MP.");
                        continue; // Stay in the Safe Haven menu
                    }
                    break;

                case 2: // Refill ONE flask at a time
                    if (player.getFlasksCount() < 3) {
                        if (player.getMp() >= 5) {
                            player.refillFlasks();
                            player.useMana(5);
                            System.out.println("You refilled 1 flask.");
                        } else {
                            System.out.println("Not enough mana! You need at least 5 MP.");
                            continue; // Stay in the Safe Haven menu
                        }
                    } else {
                        System.out.println("Your flasks are already full!");
                    }
                    break;

                case 3: // Repair Weapon
                    if (player.getWeapon() != null && player.getWeapon().isBroken()) {

                       if (player.getMp() >= 10) {
                           player.getWeapon().repair();
                           player.useMana(10);
                           System.out.println("Your weapon has been fully repaired!");
                       } else {
                           System.out.println("Not enough mana! You need at least 10 MP.");
                       }
                    } else {
                        System.out.println("Your weapon is not broken.");
                    }
                    break;


                case 4: // Repair Armor
                    if (player.getArmor() != null && player.getArmor().isBroken()) {
                        if (player.getMp() >= 10) {
                            player.getArmor().repair();
                            player.useMana(10);
                            System.out.println("Your armor has been fully repaired!");
                        } else {
                            System.out.println("Not enough mana! You need at least 10 MP.");
                        }
                    } else {
                        System.out.println("Your armor is not broken.");
                    }
                    break;


                case 5: // Continue to Next Location
                    System.out.println("Leaving Safe Haven...");
                    return; // Exit Safe Haven

                default:
                    System.out.println("Invalid choice! Please enter 1, 2, 3, 4, or 5.");
            }
        }
    }

    private void loadingEffect() {
        for (int i = 0; i < 5; i++) {

            try {
                Thread.sleep(1000); // Pause for 1 second

            } catch(InterruptedException e) {
                System.out.println("\nUnexpected interruption occurred! Continuing...");
                Thread.currentThread().interrupt();
            }
            System.out.print("."); // Print a dot every second
        }
    }
}
