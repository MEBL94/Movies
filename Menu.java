import java.util.*;
import java.io.*;

public class Menu {
    UserInput scan = new UserInput();
    Authenticator au = new Authenticator();
    Library lib = new Library();
    private int userID = 0;

    public void mainMenu() {
        int choice = 1;
        while (choice != 0) {
        System.out.println("You now have the following choices:");
        System.out.println("1. Create user");
        System.out.println("2. Login");
        System.out.println("3. Admin login");
        choice = scan.getInt();
        switch (choice) {
            case 0:
            break;
            case 1:
            createUserMenu();
            break;
            case 2:
            userID = logInUserMenu();
            userMenu(userID);
            break;
            case 3:
            cls();
            adminMenu();
            break;
            default: System.out.println("Invalid input");
            break;        
            }
    }
    }    
    
    public void showFavoritesMenu() {
        int choice = 1;
        while (choice != 0) {
            System.out.println("Welcome to the favorites menu!");
            System.out.println("You now have the following choices:");
            System.out.println("1. Show favorites list.");
            System.out.println("2. Add to favorites");
            System.out.println("3. Delete from favorites");
            System.out.println("4. Return to user menu.");
            System.out.println("5. Return to main menu.");
            choice = scan.getInt();
            switch (choice) {
                case 0:
                break;
                case 1: 
                System.out.println("Favorites list: ");
                System.out.println(au.getUser(userID).getFavorites());
                break;
                case 2:
                System.out.print("Title: ");
                String title = scan.getLine();
                System.out.print("Release year: ");
                int year = scan.getInt();
                Movie movie = new Movie(title, year);
                au.getUser(userID).addToFavorites(movie);
                break;
                case 3:
                System.out.print("Title: ");
                String title2 = scan.getLine();
                System.out.print("Release year: ");
                int year2 = scan.getInt();
                Movie movie2 = new Movie(title2, year2);
                createUserMenu().deleteFromFavorites(movie2);
                break;
                case 4:
                userMenu(userID);
                break;
                case 5:
                mainMenu();
                break;
                default: System.out.println("Invalid input");
                break;
            }
        }
    }

    public User createUserMenu() {
        System.out.println("Welcome to the create user menu!");
        System.out.print("Enter your firstname: ");
        String firstname = scan.getLine();
        System.out.print("Enter your lastname: ");
        String lastname = scan.getLine();
        System.out.print("Enter your desired username: ");
        String username = scan.getLine();
        System.out.print("Enter your desired password: ");
        String password = scan.getLine();
        if (au.checkUser(username) == false) {
        au.createUser(firstname, lastname, username, password, false);
        System.out.println("User created successfully.");
        }
        else {
            System.out.println("Go away.");
        }
        userID = au.login(username, password); 
        User user = au.getUser(userID);
        return user;
        //System.out.println("You have successfully created an account!");
    }

    public int logInUserMenu() {
        System.out.println("Welcome to the login menu!");
        System.out.print("Enter username: ");
        String username = scan.getLine();
        System.out.print("Enter password: ");
        String password = scan.getLine();
        int userid = au.login(username, password);
        if (userid > 0) {
            System.out.println("Login successful");
            return userid;
        }
        else {
            System.out.println("Go awallly.");
            mainMenu();
            return 0;
        }
    }

    public void userMenu(int userid) {
        int choice = 1;
        while (choice != 0) {
            if (userid == 0) {
                break;
            }
            System.out.println("Welcome to the user menu! You now have the following choices:");
            System.out.println("1. Play movie");
            System.out.println("2. Go to favorites menu");
            System.out.println("3. Go to history menu");
            System.out.println("4. Return to main menu");
            choice = scan.getInt();
            switch (choice) {
                case 0:
                break;
                case 1:
                // call of play movie method
                System.out.println("Movie is played");
                break;
                case 2: 
                showFavoritesMenu();
                break;
                case 3:
                displayHistoryMenu();
                break;
                case 4:
                mainMenu();
                break;
                default: System.out.println("Invalid input");
                userMenu(userID);
                break;
            }
        }
    }

    public void displayHistoryMenu() {
        System.out.println("Welcome to the history menu!");
        createUserMenu().getHistory();
    }

    public void searchMenu() {
        System.out.println("Welcome to the search menu!");
        int choice = 1;
        while (choice != 0) {
        System.out.println("1. Search for movie");
        System.out.println("2. Search for actor");
        choice = scan.getInt();
        switch (choice) {
            case 0:
            break;
            case 1:
            System.out.print("Title");
            String title = scan.getLine();
            System.out.println(lib.findMovie(title));
            break;
            case 2:
            break;
            default:
            System.out.println("Invalid input");
            break;
        }
    }
    }

    public void adminMenu() {
        int choice = 1;
        while (choice != 0) {
            System.out.println("1. Create movie menu");
            System.out.println("2. Delete movie menu");
            System.out.println("3. Edit movie menu");
            System.out.println("4. Create actor menu");
            System.out.println("5. Delete actor menu");
            choice = scan.getInt();
            switch (choice) {
                case 0:
                break;
                case 1:
                createMovieMenu();
                break;
                case 2:
                deleteMovieMenu();
                break;
                case 3:
                editMovieMenu();
                break;
                case 4:
                createActorMenu();
                break;
                case 5:
                deleteActorMenu();
                break;
                default: System.out.println("Invalid input");
                break;
            }
        }
    }

    public void createMovieMenu() {
        System.out.println("So you wish to create a movie");
        System.out.print("Title: ");
        String title = scan.getLine();
        System.out.print("Release year: ");
        int year = scan.getInt();
        lib.createMovie(title, year);    
    }
    public void deleteMovieMenu() {
        System.out.println("So you wish to delete a movie");
        System.out.print("Title: ");
        String title = scan.getLine();
        System.out.print("Release year: ");
        int year = scan.getInt();
        lib.deleteMovie(title);
    }
    public void editMovieMenu() {
        System.out.println("So you wish to edit a movie");
        System.out.print("Title: ");
        String title = scan.getLine();
        System.out.print("Release year: ");
        int year = scan.getInt();
        //lib.editMovie(title, year);
    }
    public void createActorMenu() {
        System.out.println("So you wish to create an actor");
        System.out.print("Firstname: ");
        scan.getLine();
        String firstname = scan.getLine();
        System.out.print("Lastname: ");
        String lastname = scan.getLine();
        System.out.print("Day: ");
        int day = scan.getInt();
        System.out.print("Month: ");
        int month = scan.getInt();
        System.out.print("Year: ");
        int year = scan.getInt();
        lib.createActor(firstname, lastname, day, month, year);
    }
    
    public void deleteActorMenu() {
        System.out.println("So you wish to delete an actor");
        System.out.println("Name: ");
        String name = scan.getLine();        
        lib.deleteActor(name);
    }

    // public void editActorMenu() {
    //     System.out.println("So you wish to edit an actor");
    //     System.out.print("Name: ");
    //     String name = scan.getLine();        
    //     //lib.editActor(name);
    //}

    public void pause() {
        Scanner input = new Scanner(System.in);                     
        input.nextLine(); //fanger den nye linie der kommer ved et entertryk
        cls();        
    }
    public void cls() {
         try { 
            Process p = Runtime.getRuntime().exec("clear"); 
            BufferedReader in = new BufferedReader( 
                                new InputStreamReader(p.getInputStream())); 
            String line = null; 
            while ((line = in.readLine()) != null) { 
                System.out.println(line); 
            } 
        } catch (IOException e) { 
            e.printStackTrace(); 
        } 
    }

}