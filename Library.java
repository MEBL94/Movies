import java.util.ArrayList;
import java.io.File;
import java.io.PrintStream;
import java.util.Scanner;

public class Library {
    private ArrayList<Movie> movies = new ArrayList<Movie>();
    private ArrayList<Actor> actors = new ArrayList<Actor>();
    
    public int findMovie(String movieTitle){
        for (Movie movie : movies){
            if(movie.getTitle().equals(movieTitle)){
                return movies.indexOf(movie);
            }
        }
        return -1; // -1 error signal: no movie found
    }
              
    public int findActor(String actorName){
        for (Actor actor : actors){
            if(actor.getName().equals(actorName)){
                return actors.indexOf(actor);
            }
        }
        return -1; // -1 error signal: no actor found
    }
    
    public Actor getActor(int index){
        return actors.get(index);
    }

    public Movie getMovie(int index){
        return movies.get(index);
    }

    public void createMovie(){
        movies.add(new Movie());
    } 
    
    public void createActor(){
        actors.add(new Actor());
    } 
     
    public void deleteMovie(String movieTitle){
        if(findMovie(movieTitle) > -1){
            movies.remove(findMovie(movieTitle));
            System.out.println("Movie deleted.");
        } else {
            System.out.println("Movie not found.");
        }
    } 
    
    public void deleteActor(String actorName){
        if ( findActor(actorName) > -1) {
            movies.remove(findActor(actorName));
            System.out.println("Actor deleted.");
        } else {
            System.out.println("Actor not found.");
        }
    } 
    
    public void editMovie(){
        // Skal nok over i Movie class faktisk
        // er i virkeligheden nok SET metoder
    } 
    
    public void editActor(){
        // Skal nok over i Actor class faktisk
        // er i virkeligheden nok SET metoder
    } 
    
    public void readFromFile(){
    try {
            File file = new File("database.db");
            Scanner scan = new Scanner(file);
            //hvordan vi lige læser filen, skal jeg snakke med Movie og Actor guy om.
            movies.add(new Movie());
        }
        catch (Exception e) {
            System.out.println("Error dude!!");
        }
    } 
    
    public void writeToFile(){
        try {
            File dbFile = new File("database.db");
            dbFile.createNewFile();
            PrintStream database = new PrintStream(dbFile);
            for(Movie movie : movies){
                database.println(movie); // skal også rettes for at passe ind med 
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