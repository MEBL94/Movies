import java.io.File;
import java.io.PrintStream;
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
        loadMovies();
        loadUsers();
        // linker();
    }

    private void loadActors(){
        try{
            File file = new File("Actors.xml");
            // Scanner scan = new Scanner(file);
            UserInput scan = new UserInput(file);
        
            while(scan.hasNext()){
                if(scan.getWord().equals("<actor>")){
                    createActorFromFile(scan);
                }
            }
        } catch (Exception e){
            System.out.println(">> Fejl i Load Actors " + e);
        }
    }

    private void loadMovies(){
        try{
            File file = new File("Movies.xml");
            UserInput scan = new UserInput(file);
        
            while(scan.hasNext()){
                if(scan.getWord().equals("<movie>")){
                    createMovieFromFile(scan);
                }
            }
        } catch (Exception e){
            System.out.println(">> Fejl i load Movies: " + e);
        }
    }

    private void loadUsers(){
        try{
            File file = new File("Users.xml");
            UserInput scan = new UserInput(file);
        
            while(scan.hasNext()){
                if(scan.getWord().equals("<user>")){
                    createUserFromFile(scan);
                }
            }
        } catch (Exception e){
            System.out.println(">> Fejl i load Users: " + e);
        }
    }

    private void createActorFromFile(UserInput scan){
        scan.getLine();
        String firstname = scan.getLine();
        String lastname = scan.getLine();
        int birthday = scan.getInt();
        int birthmonth = scan.getInt();
        int birthyear = scan.getInt();

        lib.createActor(firstname, lastname, birthday, birthmonth, birthyear);
    }

    private void createMovieFromFile(UserInput scan){
        scan.getLine();
        String movieTitle = scan.getLine();
        int productionYear = scan.getInt();

        lib.createMovie(movieTitle, productionYear);
    }

    private void createUserFromFile(UserInput scan){
        scan.getLine();
        String firstname = scan.getLine();
        String lastname = scan.getLine();
        String username = scan.getLine();
        String password = scan.getLine();
        boolean admin = false;
        if(scan.getWord().equals("true")){ admin = true; }

        auth.createUser(firstname,lastname,username,password,admin);
    }

    private void linker(){
        linkActors();
        linkMovies();
        linkUsers();
    }

    private void linkActors(){
        try{
            File file = new File("Actors.xml");
            UserInput scan = new UserInput(file);
        
            Actor placeholder;
            while(scan.hasNext()){
                String tagFinder = scan.getLine();
                if(tagFinder.equals("<actor>")){
                    placeholder = lib.getActor(lib.findActor(scan.getLine() + " " + scan.getLine()));
                } else if (tagFinder.equals("<movies>")){
                    String movietag = "";
                    while(movietag != "</movies>"){
                        // movietag 
                        // placeholder.addMovies(lib.getMovie(lib.findMovie(scan.getL)))
                    }
                }
            }
        } catch (Exception e){
            System.out.println(">> Fejl i load Users: " + e);
        }

    }

    private void linkMovies(){

    }

    private void linkUsers(){

    }

    public void saveToFiles(){
        saveActors();
        saveMovies();
        saveUsers();
    }

    private void saveActors(){
        try{
            File movieFile = new File("ActorsTest.xml");
            PrintStream fileStream = new PrintStream(movieFile);
            for(Actor actor : lib.getActors()){
                //StartTag
                fileStream.println("<actor>");
                //FirstName
                UserInput nameScan = new UserInput(actor.getName());
                fileStream.println(nameScan.getWord());
                //lastname
                String lastname = nameScan.getWord();
                while(nameScan.hasNext()){
                    lastname += " ";
                    lastname += (nameScan.getWord());
                }
                fileStream.println(lastname);
                //birthday
                //code here?? 
                fileStream.println("</actor>");
            }
        }
        catch(Exception e){
            System.out.println(">> Exception in saveActors: " + e);
        }
    }

    private void saveMovies(){

    }

    private void saveUsers(){

    }


}