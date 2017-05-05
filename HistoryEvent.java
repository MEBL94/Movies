import java.util.*;
import java.text.*;
public class HistoryEvent
{
    private String date;
    private Movie movie;
    
    public HistoryEvent(String date, Movie movie)
    {
        this.date = date;
        this.movie = movie;
    }
    public String toString()
    {
        return "##################\n" + movie.getTitle() + "\n" + "Watched at " + date;
    }

    public String getData(){
        return date + " " + movie.getTitle();
    }
}