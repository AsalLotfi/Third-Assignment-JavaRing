package org.project.entity.players;

import org.project.object.armors.Armor;
import org.project.object.armors.KnightArmor;
import org.project.object.weapons.Sword;

// TODO: UPDATE IMPLEMENTATION
public class Knight extends Player {
    // TODO: DESIGN KNIGHT'S WEAPON AND ARMOR AND IMPLEMENT THE CONSTRUCTOR
    public Knight(String name) {
        super(name, 100, 50, new Sword(), new KnightArmor());
    }

    @Override
    public void useAbility(){

    }
}
