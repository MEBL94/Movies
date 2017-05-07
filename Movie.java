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

    public int getReleaseYear(){
        return this.releaseYear;
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
    
    public void removeActor(Actor actor)
    {
        int actorIndexToRemove = 0;
        boolean moreActors = true;
        while(moreActors){
            moreActors = false;
            for(Actor actorToCheck : actors){
                if(actorToCheck.getName().equals(actor.getName())){
                    actorIndexToRemove = actors.indexOf(actorToCheck);
                    moreActors = true;
                }
            }
            if(moreActors){
                actors.remove(actorIndexToRemove);
            }
        }

        actors.remove(actor);
    }

    public boolean hasActors(){
        if(actors.isEmpty()){
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        String actorsString = "";
        for(Actor actor : actors){
            actorsString += actor + "\n";
        }
        actorsString += "         The End";
        if(actors.isEmpty()){
            return this.title + " " + this.releaseYear + "\n         The End";
        }
        return this.title + " " + this.releaseYear + "\n" + actorsString;
    }
}