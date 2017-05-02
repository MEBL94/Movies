import java.util.*;
import java.text.*;
public class User 
{
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private boolean admin;
    private int userID;
    
    private ArrayList<Movie> favorites = new ArrayList<Movie>();
    private ArrayList<HistoryEvent> history = new ArrayList<HistoryEvent>();

    public User(String firstName, String lastName, String userName, String password, int userID, boolean admin)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.admin = admin;
    }

    public void addToFavorites(Movie movie)
    {
        favorites.add(movie);
    }

    public void deleteFromFavorites(Movie movie)
    {
        int deletedMovieCount = 0;
        for (Movie m : favorites)
        {
            if(m.getTitle().equals(movie.getTitle()))
            {
                favorites.remove(m);
                deletedMovieCount += 1;
            }
        }
        if(deletedMovieCount == 0){
            System.out.println("No movies deleted from favorites");
        } else {
            System.out.println("Deleted " + deletedMovieCount + " movies from favorites.");
        }
    }

    public ArrayList<Movie> getFavorites()
    {
        return favorites;
    }

    public ArrayList<HistoryEvent> getHistory()
    {
        return history;
    }

    public String getUsername()
    {
        return this.userName;
    }
    
    public String getPassword()
    {
        return this.password;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }
    public int getUserID()
    {
        return userID;
    }
    
    
    
    
}