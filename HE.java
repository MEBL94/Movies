
public class HE
{
    public Date date;
    public Movie movie;
    
    public HE(Date date, Movie movie)
    {
        this.date = date;
        this.movie = movie;
    }
    public String toString()
    {
        return movie + ", watched at " + date;
    }
}