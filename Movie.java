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
 
    public String getTitle()
    {
        return this.title;
    }

    public ArrayList<Actor> getActors()
    {
       return this.actors;
    }

    public void addActor(Actor actor)
    {
        actors.add(actor);
    }

    @Override
    public String toString()
    {
        return this.title + " " + this.releaseYear;
    }

}