package org.project;

import org.project.entity.enemies.Dragon;
import org.project.entity.enemies.Goblin;
import org.project.entity.enemies.Skeleton;
import org.project.entity.enemies.Enemy;
import org.project.entity.players.Player;
import org.project.location.Location;
import org.project.object.weapons.Sword;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameManager {
    private List<Location> locations;
    private Player player;

    public GameManager(Player player) {
        this.player = player;

        // Initialize locations with enemies
        locations = new ArrayList<>();
        locations.add(new Location("Ruined Castle", new Skeleton(100, 0, new Sword()))));
        locations.add(new Location("Dark Forest", new Goblin(80, 0, new Sword())));
        locations.add(new Location("Dragon's Lair", new Dragon(200, 50, new Sword())));

        // Shuffle locations so the order is randomized
        Collections.shuffle(locations);
    }

    public void startGame() {

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

    private void Combat(Player player, Enemy enemy) {
        System.out.println("A " + enemy.getClass().getSimpleName() + " appeared!");

        while(player.isAlive() && enemy.isAlive()) {
            player.attack(enemy);
            if (enemy.isAlive()) {
                enemy.attack(player);
            }
        }

        if (player.isAlive()) {
            System.out.println("You have defeated " + enemy.getClass().getSimpleName() + "!");
        }
    }
}
