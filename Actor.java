import java.util.ArrayList;
import java.util.Calendar;

public class Actor
{
     private String firstname;
     private String lastname;
     private String birthday;
     private ArrayList<Movie> movies = new ArrayList<Movie>();

     public Actor(String firstname, String lastname, String birthday)
     {
        this.firstname = firstname;
        this.lastname = lastname;

        this.birthday = birthday;
     }

     public void setFirstname(String firstname)
     {
        this.firstname = firstname;
     }
     
     public void setLastname(String lastname)
     {
         this.lastname = lastname;
     }

     public String getBirthday(){
         return this.birthday;
     }

     public void setBirthday(String birthday)
     {
         this.birthday = birthday;
     }

     public String getName()
     {
         return this.firstname + " " + this.lastname;
     }

     public ArrayList<Movie> getMovies()
     {
        return this.movies;
     }
    
     public void addMovie(Movie movie)
     {
        movies.add(movie);
     }
     
     public void removeMovie(Movie movieToRemove){
         int movieIndexToRemove = 0;
         boolean moreMovies = true;
         while(moreMovies){
            moreMovies = false;
            for(Movie movie : movies){
                if(movie.getTitle().equals(movieToRemove.getTitle())){
                   movieIndexToRemove = movies.indexOf(movie);
                   moreMovies = true;
                }
            }
            if(moreMovies){
                movies.remove(movieIndexToRemove);
            }
         }
     }
    
     @Override
     public String toString()
     {
        return this.firstname + " " + this.lastname + " " + this.birthday;
     }
}