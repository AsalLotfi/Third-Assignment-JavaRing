package org.project.object;

import org.project.entity.Entity;
import org.project.entity.players.Player;

public interface Object {

    void use(Entity target);
    int getManaCost();
    boolean canUse(Player player);
}
