package org.project;

import org.project.location.Location;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        String name = in.nextLine();
        // ask the player which character does she wants to choose and then create an object from that class

        // Start the game
        GameManager gameManager = new GameManager(player);
        gameManager.startGame();
    }
}