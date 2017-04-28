import java.util.*;
import java.text.*;
public class User 
{
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private boolean admin;
    private ArrayList<Movie> favorites = new ArrayList<Movie>();
    private ArrayList<HistoryEvent> history = new ArrayList<HistoryEvent>();

    public User(String firstName, String lastName, String userName, String password, boolean admin)
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
        for (Movie m : favorites)
        {
            if(m.getTitle().equals(movie.getTitle()))
            {
                favorites.remove(m);
            }
            else
            {
                System.out.println("Can't find movie");
            }
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
}