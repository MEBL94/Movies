import java.util.ArrayList;
import java.io.File;
import java.io.PrintStream;
import java.util.Scanner;

public class Library {
    private ArrayList<Movie> movies = new ArrayList<Movie>();
    private ArrayList<Actor> actors = new ArrayList<Actor>();

    public ArrayList<Movie> getMovies()
    {
        return movies;
    }

    public ArrayList<Actor> getActors()
    {
        return actors;
    }
    
    public int findMovie(String searchTerm)
    {
        for (Movie movie : movies){
            if(movie.getTitle().equals(searchTerm)){
                return movies.indexOf(movie);
            }
        }
        return -1; // -1 error signal: no movie found
    }
              
    public int findActor(String searchTerm)
    {
        for (Actor actor : actors){
            if(actor.getName().equals(searchTerm)){
                return actors.indexOf(actor);
            }
        }
        return -1; // -1 error signal: no actor found
    }
    
    public Actor getActor(int index)
    {
        return actors.get(index);
    }

    public Movie getMovie(int index)
    {
        return movies.get(index);
    }

    public Movie createMovie(String title, int releaseYear)
    {
        movies.add(new Movie(title, releaseYear));
        return movies.get(movies.size()-1);
    } 
    
    public void createActor(String firstname, String lastname, String birthday){
            Actor tempActor = new Actor(firstname, lastname, birthday);
            actors.add(tempActor);
    } 
     
    public void deleteMovie(String movieTitle)
    {
        int movieIndex = findMovie(movieTitle);
        if(movieIndex > -1){
            Movie movie = getMovie(movieIndex);
            for(Actor actor : movie.getActors()){
                actor.removeMovie(movie);
            }
            movies.remove(findMovie(movieTitle));
            System.out.println("Movie deleted.");
        } else {
            System.out.println("Movie not found.");
        }
    } 
    
    public void deleteActor(String actorName)
    {
        int actorIndex = findActor(actorName);
        if (actorIndex > -1) {
            Actor actor = getActor(actorIndex);
            for(Movie movie : actor.getMovies()){
                movie.removeActor(actor);
            }
            actors.remove(actorIndex);
            System.out.println("Actor deleted.");
        } else {
            System.out.println("Actor not found.");
        }
    } 
    
}