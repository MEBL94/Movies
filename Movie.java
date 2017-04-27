import java.util.ArrayList;

public class Movie
{
    private String title;
    private int releaseYear;
    private ArrayList<Actor> actors = new ArrayList<Actor>();

    public Movie(String title, int releaseYear)
    {
        this.title = title;
        this.releaseYear = releaseYear;
        
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public void setReleaseYear(int releaseYear)
    {
        this.releaseYear = releaseYear;
    }
 
    public String getTitle(String title)
    {
        return this.title;
    }

    public ArrayList<Actor> getactors()
    {
       return this.actors;
    }

    public void addActor(Actor actors)
    {
        actors.add(new Actor());
    }

    @Override
    public String toString()
    {
        return this.title + " " + this.releaseYear;
    }

}