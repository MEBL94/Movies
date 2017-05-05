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
        this.userID = userID;
        this.admin = admin;
    }

    public void addToFavorites(Movie movie)
    {
        favorites.add(movie);
    }

    public void deleteFromFavorites(Movie movie)
    {
        // int deletedMovieCount = 0;
        // for (Movie m : favorites)
        // {
        //     if(m.getTitle().equals(movie.getTitle()))
        //     {
        //         favorites.remove(m);
        //         deletedMovieCount += 1;
        //     }
        // }
        // if(deletedMovieCount == 0){
        //     System.out.println("No movies deleted from favorites");
        // } else {
        //     System.out.println("Deleted " + deletedMovieCount + " movies from favorites.");
        // }
        int favoriteIndex;
        for(Movie favorite : favorites)
        {
            if (favorite.getTitle().equals(movie.getTitle()))
            {
                favoriteIndex = favorite.indexOf(movie);
                
            }
        }
        favorites.remove(favoriteIndex);
        
    }

    public ArrayList<Movie> getFavorites()
    {
        return favorites;
    }

    public void createHistoryEvent(Movie movie)
    {
        SimpleDateFormat df = new SimpleDateFormat("HH:mm dd/MM-yyyy");
        Calendar calendar = new GregorianCalendar();
        String startDateString = df.format(calendar.getTime());
        Date startDate;
        try
        {        
            startDate = df.parse(startDateString);
            String newDateString = df.format(startDate);
            HistoryEvent event = new HistoryEvent(newDateString, movie);
            history.add(event);
        }
        catch (Exception e)
        {
            System.out.println("Doh!");
            
        }
    }
    public void createHistoryEvent(String date, Movie movie)
    {
        HistoryEvent event = new HistoryEvent(date, movie);
        history.add(event);
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

    public int getUserID()
    {
        return userID;
    }

    public String getName(){
        return firstName + " " + lastName;
    }

    public boolean getAdminStatus(){
        return admin;
    }

    public String toString() {
        return firstName + " " + lastName + ": " + userName;
    }
}