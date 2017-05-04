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
        //done
        loadActors();
        //done
        loadMovies();
        //done
        loadUsers();
        //mangler
        linker();
    }
    
    private void loadActors(){
        try{
            File file = new File("Actors.xml");
            UserInput scan = new UserInput(file);
            while(scan.hasNext()){
                if(scan.getLine().equals("<actor>")){
                    createActorFromFile(scan);
                }
            }
        } catch (Exception e){
            System.out.println(">> Fejl i LoadActors " + e);
        }
    }

    private void loadMovies(){
        try{
            File file = new File("Movies.xml");
            UserInput scan = new UserInput(file);
        
            while(scan.hasNext()){
                if(scan.getLine().equals("<movie>")){
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
            System.out.println(">> Fejl i loadUsers: " + e);
        }
    }

    private void createActorFromFile(UserInput scan){
        String firstname = scan.getLine();
        String lastname = scan.getLine();
        int birthday = scan.getInt();
        int birthmonth = scan.getInt();
        int birthyear = scan.getInt();
        lib.createActor(firstname, lastname, birthday, birthmonth, birthyear);
    }

    private void createMovieFromFile(UserInput scan){
        String movieTitle = scan.getLine();
        int productionYear = scan.getInt();
        lib.createMovie(movieTitle, productionYear);
    }

    private void createUserFromFile(UserInput scan){
        String firstname = scan.getLine();
        String lastname = scan.getLine();
        //skip credentials tag
        scan.getLine();
        String username = scan.getLine();
        String password = scan.getLine();
        boolean admin = false;
        if(scan.getLine().equals("true")){ admin = true; }

        auth.createUser(firstname,lastname,username,password,admin);
    }

    private void linker(){
        //done
        linkActors();
        //done
        linkMovies();
        //mangler
        linkUsers();
    }

    private void linkActors(){
        try{
            File file = new File("Actors.xml");
            UserInput scan = new UserInput(file);
        
            String tag;
            while(scan.hasNext()){
                //Look for actor tag
                tag = scan.getLine();
                if(tag.equals("<actor>")){
                    //fill in name blanks to find user
                    String firstName = scan.getLine();
                    String lastName = scan.getLine();
                    Actor thisActor = lib.getActor(lib.findActor(firstName + " " + lastName));
                    while(!tag.equals("</actor>")){
                        //go look for movies
                        tag = scan.getLine();
                        if (tag.equals("<movies>")){
                            tag = scan.getLine();
                            while(!tag.equals("</movies>")){
                                thisActor.addMovies(lib.getMovie(lib.findMovie(tag)));
                                tag = scan.getLine();
                            }
                        }
                    }
                }
            }
        } catch (Exception e){
            System.out.println(">> Fejl i link actors: " + e);
        }

    }

    private void linkMovies(){
        try{
            File file = new File("Movies.xml");
            UserInput scan = new UserInput(file);
        
            String tag;
            while(scan.hasNext()){
                //Look for movie tag
                tag = scan.getLine();
                if(tag.equals("<movie>")){
                    //find movietitle, to get the movie object from library.
                    String movieTitle = scan.getLine();
                    Movie thisMovie = lib.getMovie(lib.findMovie(movieTitle));
                    while(!tag.equals("</movie>")){
                        //go look for actors
                        tag = scan.getLine();
                        if (tag.equals("<actors>")){
                            tag = scan.getLine();
                            while(!tag.equals("</actors>")){
                                thisMovie.addActor(lib.getActor(lib.findActor(tag)));
                                tag = scan.getLine();
                            }
                        }
                    }
                }
            }
        } catch (Exception e){
            System.out.println(">> Fejl i link movies: " + e);
        }
    }

    private void linkUsers(){
        try{
            File file = new File("Users.xml");
            UserInput scan = new UserInput(file);
        
            String tag;
            while(scan.hasNext()){
                //Look for user tag
                tag = scan.getLine();
                if(tag.equals("<user>")){
                    System.out.println("Ysssssssssssssssshej");
                    //go to credentials tag
                    while(!tag.equals("<credentials>")){
                        tag = scan.getLine();
                    }
                    //find username and password, to get the user object from autheticator.
                    String username = scan.getLine();
                    String password = scan.getLine();
                    User thisUser = auth.getUser(auth.login(username, password));
                    while(!tag.equals("</user>")){
                        //go look for favorites
                        tag = scan.getLine();
                        if (tag.equals("<favorites>")){
                            tag = scan.getLine();
                            while(!tag.equals("</favorites>")){
                                thisUser.addToFavorites(lib.getMovie(lib.findMovie(tag)));
                                tag = scan.getLine();
                            }
                        }
                    }
                }
            }
        } catch (Exception e){
            System.out.println(">> Fejl i link users: " + e);
        }
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