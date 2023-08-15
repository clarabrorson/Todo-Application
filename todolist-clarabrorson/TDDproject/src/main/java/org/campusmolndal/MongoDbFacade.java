package org.campusmolndal;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.IndexOptions;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class MongoDbFacade {
    /**
     * Klass för att hantera anslutningen till MongoDB.
     * Klassen innehåller metoder för att utföra CRUD operationer.
     * MongoDB-klienten skapas med hjälp av en ConnectionString.
     * Denna ConnectionString innehåller information om hur vi ska ansluta till databasen.
     */
    MongoClient client;
    MongoDatabase db;
    MongoCollection<Document> collection;
    KeyReader keyReader = new KeyReader("todoKey");
    String connString = keyReader.getKey();
    String collectionName = "todo";
    String databaseName = "todoDB";

    public MongoDbFacade(String connString, String databaseName, String collectionName) {
        this.connString = connString;
        this.collectionName = collectionName;
        this.databaseName = databaseName;
        Connect();
    }

    public MongoDbFacade() {

        Connect();
    }
    // Metod för att skapa en anslutning till databasen.
    private void Connect() {
        ServerApi serverApi = ServerApi.builder()
                .version(ServerApiVersion.V1)
                .build(); // Skapar en instans av MongoDB Server API.

        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(connString))
                .serverApi(serverApi)
                .build(); // Skapar inställningarna för MongoDB-klienten.

        try {
            client = MongoClients.create(settings); // Skapar en instans av MongoDB-klienten.
            db = client.getDatabase(databaseName); // Hämtar instansen av databasen.
            collection = db.getCollection(collectionName); // Hämtar instansen av samlingen.


        } catch (Exception ex) {

            System.out.println("Misslyckad anslutning!");
            System.out.println(ex.getMessage());
        }
    }

    // Metod för att skapa ett index på todo-fältet i samlingen.
    public void createIndex() {
        collection.createIndex(new Document("todo", 1),
                new IndexOptions().unique(true));
    }

    public void addTodo(Todo todo) {
        Document doc = todo.toDoc(); // Skapar ett dokument av todo-objektet.
        collection.insertOne(doc); // Lägger till
    }

    public void updateTodoById(String id, Todo updatedTodo) {
        Document filter = new Document("_id", id);
        Document update = new Document("$set", updatedTodo.toDoc()); // $set är en operator som används för att uppdatera ett dokument.
        collection.updateOne(filter, update); // Uppdaterar
    }

    public void deleteTodoById(String id) {
        Document filter = new Document("_id", id); // Skapar ett filter som matchar dokumentet med det angivna id:t.
        collection.deleteOne(filter); // Tar bort
    }

    public Todo getTodoById(String id) {
        Document filter = new Document("_id", id); // Skapar ett filter som matchar dokumentet med det angivna id:t.
        Document doc = collection.find(filter).first(); // Hämtar det första dokumentet som matchar filtret.
        if (doc != null) { // Om dokumentet inte är null så skapas en instans av Todo med hjälp av dokumentet.
            return Todo.fromDoc(doc);
        }
        return null;
    }

    public List<Todo> getAllTodos() {
        List<Todo> todos = new ArrayList<>(); // Skapar en lista för att lagra alla todos.
        for (Document doc : collection.find()) {
            Todo todo = Todo.fromDoc(doc);
            todos.add(todo);
        }
        return todos;
    }
    // Metod för att lägga till några exempel-todos i databasen.
    public void addSampleTodos() {
        Todo todo1 = new Todo("1", "Köpa kaffe", false);
        Todo todo2 = new Todo("2", "Hämta paket", true);
        Todo todo3 = new Todo("3", "Föräldramöte kl 15", false);
        try {
            addTodo(todo1);
            addTodo(todo2);
            addTodo(todo3);

            System.out.println("Todos tillagda i databasen!");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }


    }
}



