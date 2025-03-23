package org.project;

import org.project.entity.enemies.Enemy;
import org.project.entity.players.Assassin;
import org.project.entity.players.Knight;
import org.project.entity.players.Wizard;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        System.out.println("Welcome to Java-Ring!");
        System.out.println("Your adventure begins...");
        System.out.println("Please enter your name: ");

        Scanner in = new Scanner(System.in);
        String name = in.nextLine();

        System.out.println("Choose your character(1 to 3): ");
        System.out.println("1. Wizard");
        System.out.println("2. Knight");
        System.out.println("3. Assassin");

        Scanner in2 = new Scanner(System.in);
        int choice = in2.nextInt();

        while (choice <= 0 || choice > 3 ) {
            System.out.println("Please choose a valid option!");
            choice = in2.nextInt();
        }
        createPlayer(choice, name);
    }

    private static void createPlayer(int choice, String name) {
        switch (choice) {
            case 1:
                Wizard wizard = new Wizard(name);
                // start the game
                GameManager gameManager_0 = new GameManager(wizard);
                gameManager_0.startGame();
                break;
            case 2:
                Knight knight = new Knight(name);
                // start the game
                GameManager gameManager_1 = new GameManager(knight);
                gameManager_1.startGame();
                break;
            case 3:
                Assassin assassin = new Assassin(name);
                // start the game
                GameManager gameManager_2 = new GameManager(assassin);
                gameManager_2.startGame();
                break;
        }
    }
}