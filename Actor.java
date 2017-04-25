import java.util.ArrayList;
import java.util.Date;


public class Actor
{
     private String firstname;
     private String lastname;
     private ArrayList<Movie> movies = new ArrayList<Movie>();
     private Date birthday;

     public Actor(String firstname, String lastname, ArrayList<Movie> movies , Date birthday)
     {
         this.firstname = firstname;
         this.lastname = lastname;
         this.birthday = birthday;
         this.movies = movies;
     }
     
     
    public ArrayList<Movie> getmovies()
    {
        return this.movies;
    }
    

    public void addMovie(String title, int releaseYear)
    {
        movies.add(title, releaseYear));
        return movies;
    }
    
    
    @Override
    public String toString()
    {

    }


}