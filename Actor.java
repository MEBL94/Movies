import java.util.ArrayList;
import java.util.Date;

public class Actor
{
     private String firstname;
     private String lastname;
     private Date birthday;
     private ArrayList<Movie> movies = new ArrayList<Movie>();

     public Actor(String firstname, String lastname, Date birthday)
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
     
     public void setBirthday(Date birthday)
     {
         this.birthday = birthday;
     }
   
     
     public String getname()
     {
         return this.firstname + this.lastname;
     }

     public ArrayList<Movie> getmovies()
     {
        return this.movies;
     }
    
     public void addMovies(Movie movie)
     {
        movies.add(movie);
        
     }
    
     @Override
     public String toString()
     {
        return this.firstname + " " + this.lastname + " " + this.birthday;
     }
}