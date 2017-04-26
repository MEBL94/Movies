import java.util.*;
import java.text.*;
public class User 
{
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private boolean admin;
    private ArrayList<Movie> favorites;
    private ArrayList<HE> history = new ArrayList<HE>();

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
        // todo: find index of movie
        favorites.remove(favorites.get(movie));
    }
    public ArrayList<Movie> getFavorites()
    {
        return favorites;
    }

        public ArrayList<HE> getHistory()
    {
        return history;
    }
}