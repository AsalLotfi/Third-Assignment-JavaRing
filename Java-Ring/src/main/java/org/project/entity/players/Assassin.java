package org.project.entity.players;

import org.project.object.armors.KnightArmor;
import org.project.object.weapons.Sword;

public class Assassin extends Player {

    public Assassin(String name) {
        super(name, 100, 50, new Sword(), new KnightArmor());
    }

    @Override
    public void useAbility(){

    }
}
