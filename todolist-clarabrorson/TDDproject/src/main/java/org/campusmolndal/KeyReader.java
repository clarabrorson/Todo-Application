package org.campusmolndal;
import java.io.File;
import java.util.Properties;

public class KeyReader {
    /**
     * Den här klassen läser in en fil som innehåller en connectionString till en databas.
     * Filen ska ligga i användarens hemkatalog och heta todoKey.txt
     * Om ingen fil hittas så skapas en ny fil med en connectionString till en lokal databas.
     */
    Properties prop;

    public KeyReader(String file) {

        prop = new Properties();

        String userHome = System.getProperty("user.home");

        String filename= "/Documents/todoKey/" + file + ".txt";
        File newfile = new File("/Documents/todoKey/" + file + ".txt");
        if (!newfile.exists()) { // Om filen inte finns så skapa en ny fil
            try { // Skapa en ny fil med en connectionString till en lokal databas
                prop.setProperty("connectionString", "mongodb://localhost:27017");
                prop.setProperty("database", "todoDB");
                prop.setProperty("collection", "todo");
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return;
            }
        }
    }
    public String getKey() {

        return prop.getProperty("connectionString");
    }
}
