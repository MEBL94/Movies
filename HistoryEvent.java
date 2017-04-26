
public class HistoryEvent
{
    public Date date;
    public Movie movie;
    
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