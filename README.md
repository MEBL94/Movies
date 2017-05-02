Andreas laver movie og actor.
Tobias laver user.
Jonas laver library.
Mathias laver menu.

Class diagram changes:
    Library:
        getMovies -> getMovie(int index)
        getActors -> getActor(int index)
        ++ findActor(String actorName) : int index (-1 if not found)
        ++ findMovie(String movieTitle) : int index (-1 if not found)
        deleteMovie() -> deleteMovie(String movieTitle)
        deleteActor() -> deleteActor(String actorName)

    Movie:
        ++ getTitle() : String
        ++ setMethoder generelt
    
    Actor:
        ++ getName() : String
        ++ setMethoder generelt

    