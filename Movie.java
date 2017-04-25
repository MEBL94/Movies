import java.util.ArrayList;

public class Movie
{
    private String title;
    private int releaseYear;
    private ArrayList<Actor> actors = new ArrayList<Actor>();

    public Movie(String title, int releaseYear, Arraylist<Actor> actors )
    {
        this.title = title;
        this.releaseYear = releaseYear;
        this.actors = actors;
    }

   public ArrayList<Actor> getactors()
   {
       return this.actors;
   }

    @Override
    public String toString()
    {

    }

}