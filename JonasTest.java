public class JonasTest {
    public static void main(String[] args) {
        //Testkode
        Library lib = new Library();
        Authenticator auth = new Authenticator();
        FileHandler fh = new FileHandler(lib, auth);
        //Test Load Users
        System.out.println("Loading files:");
        fh.loadFromFiles();
        System.out.println("Check for users:");
        System.out.println(auth.checkUser("duch22"));
        System.out.println(auth.checkUser("duch21"));
        System.out.println(auth.checkUser("d"));
        //Test Load Actors
        System.out.println("Check for Actors:");
        System.out.println("Monster index: " + lib.findActor("Monster the beast"));
        System.out.println("Belle index: " + lib.findActor("Belle the beauty"));
        System.out.println("noone index: " + lib.findActor("blurp"));
        for(Movie movie : lib.getActor(lib.findActor("Monster the beast")).getMovies()){
            System.out.println("Monster the beast has: " + movie.getTitle());
        }
        for(Movie movie : lib.getActor(lib.findActor("Belle the beauty")).getMovies()){
            System.out.println("Belle has: " + movie.getTitle());
        }

        // for(Actor actor : lib.getMovie(lib.findMovie("Beauty and the beast")).getActors()){
        //     System.out.println("Budibeast 1 har: " + actor);
        // }

        // for(Actor actor : lib.getMovie(lib.findMovie("Beauty and the beast 2")).getActors()){
        //     System.out.println("Budibeast 2 har: " + actor);
        // }

        for(User user : auth.getUsers()){
            System.out.println(user.getUsername() + " has " + user.getFavorites());
        }

        fh.saveToFiles();
        
        //Test


        
    }
}