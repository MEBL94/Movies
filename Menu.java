import java.util.*;
import java.io.*;

public class Menu {
    Scanner scan = new Scanner(System.in);
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
            adminMenu();
            break;
            default: System.out.println("Invalid input");
            break;        
            }
    }
    }    
    
    public void showFavoritesMenu(User user) {
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
            switch (choice) {
                case 0:
                break;
                case 1: 
                // supposed to show favorites list
                System.out.println("Favorites list: ");
                System.out.println(user.getFavorites());
                break;
                case 2:
                System.out.print("Title: ");
                String title = scan.nextLine();
                System.out.print("Release year: ");
                int year = scan.nextInt();
                user.addToFavorites(title, year);
                break;
                case 3:
                System.out.print("Title: ");
                String title = scan.nextLine();
                System.out.print("Release year: ");
                int year = scan.nextInt();
                user.deleteFromFavorites(title, year);
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

    public void createUserMenu() {
        Authenticator authenticator = new Authenticator();
        System.out.println("Welcome to the create user menu!");
        System.out.print("Enter your firstname: ");
        String firstname = scan.nextLine();
        System.out.print("Enter your lastname: ");
        String lastname = scan.nextLine();
        System.out.print("Enter your desired username: ");
        String username = scan.nextLine();
        authenticator.checkUser(username);
        System.out.print("Enter your desired password: ");
        String password = scan.next();
        authenticator.createUser(firstname, lastname, username, password, false);
        User user = new User(firstname, lastname, username, password, false);
        
        //System.out.println("You have successfully created an account!");
    }

    public void logInUserMenu() {
        Authenticator authenticator = new Authenticator();
        System.out.println("Welcome to the login menu!");
        System.out.print("Enter username: ");
        String username = scan.nextLine();
        System.out.print("Enter password: ");
        String password = scan.nextLine();
        if (authenticator.login(username, password)) {
            System.out.println("Login successful");
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
        user.getHistory();
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

    public void adminMenu() {
        int choice = 1;
        while (choice != 0) {
            System.out.println("1. Create movie menu");
            System.out.println("2. Delete movie menu");
            System.out.println("3. Edit movie menu");
            System.out.println("4. Create actor menu");
            System.out.println("5. Delete actor menu");
            System.out.println("6. Edit actor menu");
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
                case 6:
                editActorMenu();
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
        
    }
    public void deleteMovieMenu() {
        System.out.println("Delete movie option");
    }
    public void editMovieMenu() {
        System.out.println("Edit movie option");
    }
    public void createActorMenu() {
        System.out.println("Create actor option");
    }
    public void deleteActorMenu() {
        System.out.println("Delete actor option");
    }
    public void editActorMenu() {
        System.out.println("Edit actor option");
    }
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