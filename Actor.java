import java.util.ArrayList;
import java.util.Calendar;

public class Actor
{
     private String firstname;
     private String lastname;
     private Calendar birthday;
     private ArrayList<Movie> movies = new ArrayList<Movie>();

     public Actor(String firstname, String lastname, int day, int month, int year)
     {
        this.firstname = firstname;
        this.lastname = lastname;

        Calendar birthday = Calendar.getInstance();
        birthday.set(Calendar.YEAR, year);
        birthday.set(Calendar.MONTH, month+1);
        birthday.set(Calendar.DAY_OF_MONTH, day);



         //Virker ikke, giver mig NULL POINTER EXCEPTION
         // private Calendar birthday = new Calendar().set(year, month, day);
         // this.birthday = new Calendar();
         // birthday.set(year, month, day);
     }

     public void setFirstname(String firstname)
     {
        this.firstname = firstname;
     }
     
     public void setLastname(String lastname)
     {
         this.lastname = lastname;
     }
     
     public void setBirthday(int day, int month, int year)
     {
         this.birthday.set(year, month, day);
     }

   
     
     public String getName()
     {
         return this.firstname + " " + this.lastname;
     }

     public ArrayList<Movie> getMovies()
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