package org.project.object;

import org.project.entity.Entity;
import org.project.entity.players.Player;

public interface Object {

    void use(Player target);
    int getManaCost();
    boolean canUse(Player player);
}
