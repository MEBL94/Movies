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
        return movie + ", watched at " + date + "/n";
    }

    public String getData(){
        return date + " " + movie.getTitle();
    }
}