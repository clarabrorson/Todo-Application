package org.campusmolndal;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    /**
     * Den här klassen hanterar menyn för applikationen.
     * Den skapar en instans av Application och anropar metoder i den klassen.
     * Den här klassen hanterar inmatning från användaren.
     */

    private Application application;
    private Scanner scanner;
    private Scanner lineScanner;
    private Scanner intScanner;

    public Menu() {

        application = new Application();
        scanner = new Scanner(System.in);
        lineScanner = new Scanner(System.in);
        intScanner = new Scanner(System.in);
    }

    public void showMenu() {

        boolean exit = false;

        while (!exit) {
            System.out.println("Välkommen till Todo-appen!");
            System.out.println("1. Lägg till en todo");
            System.out.println("2. Visa en todo");
            System.out.println("3. Visa alla todos");
            System.out.println("4. Uppdatera en todo");
            System.out.println("5. Ta bort en todo");
            System.out.println("6. Avsluta");
            System.out.println();
            int choice = getIntInput("Ange ditt val: ");

            switch (choice) {
                case 1 -> application.addToDo();
                case 2 -> application.showToDo();
                case 3 -> application.showAllToDos();
                case 4 -> application.updateToDo();
                case 5 -> application.deleteToDo();
                case 6 -> exit = true;
                default -> System.out.println("Ogiltigt val. Försök igen.");
            }
            System.out.println();
        }
        scanner.close();
        lineScanner.close();
        intScanner.close();

    }

    private int getIntInput(String message) {
        while (true) {
            try {
                System.out.print(message);
                return scanner.nextInt(); // Returnerar det användaren matar in
            } catch (InputMismatchException e) { // Fångar upp om användaren matar in något annat än en siffra
                System.out.println("Var god ange en siffra.");
                scanner.next();
            }
        }
    }
}





