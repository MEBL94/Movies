import java.io.File;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.ArrayList;

public class FileHandler {

    private Library lib;
    private Authenticator auth;

    public FileHandler(Library lib, Authenticator auth){
        this.lib = lib;
        this.auth = auth;
    }

    public void loadFromFiles(){
        loadActors();
        // loadMovies();
        loadUsers();
        // linker();
    }

    private void loadActors(){
        try{
            File file = new File("Actors.xml");
            Scanner scan = new Scanner(file);
        
            while(scan.hasNext()){
                if(scan.next().equals("<actor>")){
                    createActorFromFile(scan);
                }
            }
        } catch (Exception e){
            System.out.println(">> Fejl i LoadActors " + e);
        }
    }

    private void loadMovies(){
        // File file = new File("database.db");
        // Scanner scan = new Scanner(file);
        // // lib.addMovie();
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
    }

    private void linker(){

    }

    public void saveToFiles(){

    }

    private void createActorFromFile(Scanner scan){
        scan.nextLine();
        String firstname = scan.nextLine();
        String lastname = scan.nextLine();
        int birthday = scan.nextInt();
        int birthmonth = scan.nextInt();
        int birthyear = scan.nextInt();
        

        // test statements:
        System.out.println("XXXXXXX");
        System.out.println(firstname + " " + lastname + " " + birthday + " " + birthmonth + " " + birthyear);
        lib.createActor(firstname, lastname, birthday, birthmonth, birthyear);
    }



    private void createUserFromFile(Scanner scan){
        scan.nextLine();
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

