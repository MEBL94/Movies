public class JonasTest {
    public static void main(String[] args) {
        Library lib = new Library();
        Authenticator auth = new Authenticator();
        FileHandler fh = new FileHandler(lib, auth);
        fh.loadFromFiles();
        System.out.println(auth.checkUser("duch22"));
        System.out.println(auth.checkUser("duch21"));
    }
}