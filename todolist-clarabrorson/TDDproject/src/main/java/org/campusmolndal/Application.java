package org.campusmolndal;

import java.util.List;
import java.util.Scanner;

public class Application {
    /**
     * Den här klassen hanterar logiken för applikationen.
     * Den här klassen anropar metoder i MongoDbFacade.
     * Den här klassen hanterar inmatning från användaren.
     */

    private MongoDbFacade dbFacade;
    private Scanner lineScanner;
    private Scanner intScanner;


    public Application() {
        dbFacade = new MongoDbFacade();
        lineScanner = new Scanner(System.in);
        intScanner = new Scanner(System.in);
    }

    public void addToDo() {
        System.out.println("Skriv in en todo: ");
        String id = getStringInput("Ange ID: ");

        // Kontrollera om id redan finns i databasen
        Todo existingTodo = dbFacade.getTodoById(id);
        if (existingTodo != null) {
            System.out.println("En todo med samma ID finns redan i databasen!");
            return;
        }

        String text = getStringInput("Ange text: ");
        boolean done = getBooleanInput("Är den klar? (ja/nej): ");

        Todo todo = new Todo(id, text, done);
        dbFacade.addTodo(todo); // Lägg till todo i databasen

        System.out.println("Todo'n har lagts till!");
    }

    public void showToDo() {
        System.out.println("Visa en todo");
        String id = getStringInput("Ange ID: ");
        Todo todo = dbFacade.getTodoById(id); // Hämta todo från databasen

        if (todo != null) {
            System.out.println(todo);
        } else {
            System.out.println("Todo'n finns inte!");
        }

    }

    public void showAllToDos() {
        System.out.println("Visa alla todos");
        List<Todo> todos = dbFacade.getAllTodos(); // Hämta alla todos från databasen
        for (Todo todo : todos) {
            System.out.println(todo);
        }

    }
    // Hjälpt av chatGPT
    public void updateToDo() {
        System.out.println("Uppdatera en todo");
        String id = getStringInput("Ange ID: ");
        String newText = getStringInput("Ange ny text: ");
        boolean newDone = getBooleanInput("Är den klar? (ja/nej): ");

        Todo updatedTodo = new Todo (id, newText, newDone);
        dbFacade.updateTodoById(id, updatedTodo); // Uppdatera todo i databasen

        System.out.println("Todo'n har uppdaterats!");
    }


    public void deleteToDo() {
        System.out.println("Ta bort en todo");
        String id = getStringInput("Ange ID: ");

        dbFacade.deleteTodoById(id); // Ta bort todo från databasen

        System.out.println("Todo'n har tagits bort!");

    }

    // Metod för att läsa in en sträng från användaren
    // Hjälpt av chatGPT
    private String getStringInput(String message) {
        System.out.print(message);
        return lineScanner.nextLine();
    }
    // Metod för att läsa in en boolean från användaren
    // Hjälpt av chatGPT
    private boolean getBooleanInput(String message) {
        System.out.print(message);
        String input = lineScanner.nextLine();
        return input.equalsIgnoreCase("ja");
    }

}