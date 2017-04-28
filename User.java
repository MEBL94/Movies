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

    public User(String firstName, String lastName, String userName, boolean admin)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.admin = admin;
    }

    public void addToFavorites(Movie movie)
    {
        favorites.add(movie);
    }

    public void deleteFromFavorites(Movie movie)
    {
        while (favorites.hasNext())
        {
            Movie movie = favorites.next();
            if (movie.getTitle().equals("Titanic")) 
            {
                favorites.remove(movie);
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
}