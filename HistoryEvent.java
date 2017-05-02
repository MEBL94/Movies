import java.util.*;
public class HistoryEvent
{
    private Date date;
    private Movie movie;
    
    public HistoryEvent(Date date, Movie movie)
    {
        this.date = date;
        this.movie = movie;
    }
    public String toString()
    {
        return movie + ", watched at " + date;
    }
}