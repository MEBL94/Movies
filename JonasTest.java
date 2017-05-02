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
        //Test Load Actors
        System.out.println("Check for Actors:");
        

    }
}