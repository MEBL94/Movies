import java.io.File;
import java.io.PrintStream;
import java.util.Scanner;

public class FileHandler {
    public void readFromFile(){
    try {
            File file = new File("database.db");
            Scanner scan = new Scanner(file);
            movies.add(new Movie());
        }
        catch (Exception e) {
            System.out.println(e);
        }
    } 
    
    public void writeToFile(){
        try {
            File dbFile = new File("database.db");
            dbFile.createNewFile();
            PrintStream database = new PrintStream(dbFile);
            for(Movie movie : movies){
                database.println(movie);
            }
            database.println("$$$");      
            for(Actor actor : actors){
                database.println(actor);
            }
        }
        catch (Exception e) {
            System.out.println(e);
        }        
    }
}