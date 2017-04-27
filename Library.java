import java.util.ArrayList;
import java.io.File;
import java.io.PrintStream;
import java.util.Scanner;

public class Library {
    private ArrayList<Movie> movies = new ArrayList<Movie>();
    private ArrayList<Actor> actors = new ArrayList<Actor>();
    
    public int findMovie(String movieTitle){
        // for (Movie movie : movies){
        //     if(movie.getTitle().equals(movieTitle)){
        //         return movies.indexOf(movie);
        //     }
        // }
        return -1; // -1 error signal: no movie found
    }
              
    public int findActor(String actorName){
        // for (Actor actor : actors){
        //     if(actor.getName().equals(actorName)){
        //         return actors.indexOf(actor);
        //     }
        // }
        return -1; // -1 error signal: no actor found
    }
    
    public Actor getActor(int index){
        return actors.get(index);
    }

    public Movie getMovie(int index){
        return movies.get(index);
    }

    public void createMovie(){
        // movies.add(new Movie());
    } 
    
    public void createActor(){
        // actors.add(new Actor());
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
    
}