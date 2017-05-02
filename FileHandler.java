import java.io.File;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.ArrayList;

public class FileHandler {

    Library lib;
    Authenticator auth;

    public FileHandler(Library lib, Authenticator auth){
        this.lib = lib;
        this.auth = auth;
    }

    private void loadMovies(){
        // File file = new File("database.db");
        // Scanner scan = new Scanner(file);
        // // lib.addMovie();
    }

    private void loadActors(){
        String hej;
    }

    private void loadUsers(){
        try{
            File file = new File("Users.xml");
            Scanner scan = new Scanner(file);
        
            while(scan.hasNext()){
                if(scan.next().equals("<user>")){
                    createUserFromFile(scan);
                }
            }
        } catch (Exception e){
            System.out.println(e);
        }
        
        
        // auth.createUser(firstname, lastname, username, password, admin);
    }

    private void linker(){

    }

    public void loadFromFiles(){
        // loadActors();
        // loadMovies();
        loadUsers();
        // linker();
    }

    public void saveToFiles(){

    }




    public void readFromFile(){
    // try {
    //         File file = new File("database.db");
    //         Scanner scan = new Scanner(file);
    //         movies.add(new Movie());
    //     }
    //     catch (Exception e) {
    //         System.out.println(e);
    //     }
    } 
    
    public void writeToFile(){
        // try {
        //     File dbFile = new File("database.db");
        //     dbFile.createNewFile();
        //     PrintStream database = new PrintStream(dbFile);
        //     for(Movie movie : movies){
        //         database.println(movie);
        //     }
        //     database.println("$$$");      
        //     for(Actor actor : actors){
        //         database.println(actor);
        //     }
        // }
        // catch (Exception e) {
        //     System.out.println(e);
        // }        
    }

    private void createUserFromFile(Scanner scan){
        System.out.println(scan.nextLine());
        String firstname = scan.nextLine();
        String lastname = scan.nextLine();
        String username = scan.nextLine();
        String password = scan.nextLine();
        boolean admin = false;
        System.out.println(firstname + lastname);
        if(scan.next().equals("true")){
            admin = true;
        }
        
        auth.createUser(firstname,lastname,username,password,admin);
        
    }
}