import java.util.*;
import java.io.*;

public class Menu {
    Scanner scan = new Scanner(System.in);
    Authenticator au = new Authenticator();
    Library lib = new Library();
    public void mainMenu() {

        int choice = 1;
        while (choice != 0) {
        System.out.println("You now have the following choices:");
        System.out.println("1. Create user");
        System.out.println("2. Login");
        System.out.println("3. Admin login");
        choice = scan.nextInt();
        switch (choice) {
            case 0:
            break;
            case 1:
            createUserMenu();
            break;
            case 2:
            logInUserMenu();
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
            choice = scan.nextInt();
            pause();
            switch (choice) {
                case 0:
                break;
                case 1: 
                System.out.println("Favorites list: ");
                System.out.println(createUserMenu().getFavorites());
                break;
                case 2:
                System.out.print("Title: ");
                String title = scan.nextLine();
                System.out.print("Release year: ");
                int year = scan.nextInt();
                Movie movie = new Movie(title, year);
                createUserMenu().addToFavorites(movie);
                break;
                case 3:
                System.out.print("Title: ");
                String title2 = scan.nextLine();
                System.out.print("Release year: ");
                int year2 = scan.nextInt();
                Movie movie2 = new Movie(title2, year2);
                createUserMenu().deleteFromFavorites(movie2);
                break;
                case 4:
                userMenu();
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
        String firstname = scan.next();
        System.out.print("\nEnter your lastname: ");
        String lastname = scan.next();
        System.out.print("\nEnter your desired username: ");
        String username = scan.next();
        System.out.print("\nEnter your desired password: ");
        String password = scan.next();
        if (au.checkUser(username)) {
        au.createUser(firstname, lastname, username, password, false);
        }
        else {
            System.out.println("Go away.");
        }
        int userid = au.login(username, password); 
        User user = au.getUser(userid);
        return user;
        //System.out.println("You have successfully created an account!");
    }

    public void logInUserMenu() {
        System.out.println("Welcome to the login menu!");
        System.out.print("Enter username: ");
        String username = scan.nextLine();
        System.out.print("Enter password: ");
        String password = scan.nextLine();
        if (au.login(username, password) >= 0) {
            System.out.println("Login successful");
            au.getUser(au.login(username, password));
            userMenu();
        }
        else {
            System.out.println("Go away.");
            mainMenu();
        }
    }

    public void userMenu() {
        int choice = 1;
        while (choice != 0) {
            System.out.println("Welcome to the user menu! You now have the following choices:");
            System.out.println("1. Play movie");
            System.out.println("2. Go to favorites menu");
            System.out.println("3. Go to history menu");
            System.out.println("4. Return to main menu");
            choice = scan.nextInt();
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
                userMenu();
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
        choice = scan.nextInt();
        switch (choice) {
            case 0:
            break;
            case 1:
            System.out.print("Title");
            String title = scan.nextLine();
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
            choice = scan.nextInt();
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
        String title = scan.nextLine();
        System.out.print("Release year: ");
        int year = scan.nextInt();
        lib.createMovie(title, year);    
    }
    public void deleteMovieMenu() {
        System.out.println("So you wish to delete a movie");
        System.out.print("Title: ");
        String title = scan.nextLine();
        System.out.print("Release year: ");
        int year = scan.nextInt();
        lib.deleteMovie(title);
    }
    public void editMovieMenu() {
        System.out.println("So you wish to edit a movie");
        System.out.print("Title: ");
        String title = scan.nextLine();
        System.out.print("Release year: ");
        int year = scan.nextInt();
        //lib.editMovie(title, year);
    }
    public void createActorMenu() {
        System.out.println("So you wish to create an actor");
        System.out.print("Firstname: ");
        String firstname = scan.nextLine();
        System.out.print("Lastname: ");
        String lastname = scan.nextLine();
        System.out.print("Day: ");
        int day = scan.nextInt();
        System.out.print("Month: ");
        int month = scan.nextInt();
        System.out.print("Year: ");
        int year = scan.nextInt();
        lib.createActor(firstname, lastname, day, month, year);
    }
    
    public void deleteActorMenu() {
        System.out.println("So you wish to delete an actor");
        System.out.print("Name: ");
        String name = scan.nextLine();        
        lib.deleteActor(name);
    }

    // public void editActorMenu() {
    //     System.out.println("So you wish to edit an actor");
    //     System.out.print("Name: ");
    //     String name = scan.nextLine();        
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