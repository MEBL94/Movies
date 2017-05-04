import java.util.*;
import java.text.*;
public class HistoryEvent
{
    private Calendar date;
    private Movie movie;
    
    public HistoryEvent(Calendar date, Movie movie)
    {
        this.date = date;
        this.movie = movie;
    }
    public String toString()
    {
        return movie + ", watched at " + date;
    }
}