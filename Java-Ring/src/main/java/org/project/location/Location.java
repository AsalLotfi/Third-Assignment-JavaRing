package org.project.location;

import org.project.entity.enemies.Enemy;
import java.util.ArrayList;

public class Location {
    private final String name; // Stores the name of the location
    private final Enemy enemy; // Each location has only one enemy
    private boolean cleared; // to check whether the location is clear or has an enemy

    public Location(String name, Enemy enemy) {
        this.name = name;
        this.enemy = enemy;
        this.cleared = false; // Initially, the location is not cleared
    }

    public String getName() {
        return name;
    }

    public Enemy getEnemy() {
        return enemy;
    }

    public boolean isCleared() { return cleared; }

    public void setCleared(boolean cleared) {
        this.cleared = cleared;
    }
}
