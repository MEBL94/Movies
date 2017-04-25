import java.util.*;
import java.io.*;
public class Menu {
    public void mainMenu() {
        Scanner scan = new Scanner(System.in);
        int choice = 1;
        while (choice >= 1) {
        System.out.println("You now have two choices:");
        System.out.println("1. Create user");
        System.out.println("2. Login");
        choice = scan.nextInt();
        switch (choice) {
            case 1: choice = 1;
            createUserMenu();
            break;
            case 2: choice = 2;
            logInUserMenu();
            break;
            default: System.out.println("Invalid input");
            mainMenu();
        }
    }
    }

    public void userMenu() {
        Scanner scan = new Scanner(System.in);
        int choice = 1;
        while (choice >= 1) {
        System.out.println("You now have two choices:");
        System.out.println("1. Favorites");
        System.out.println("2. History");
        choice = scan.nextInt();
        switch (choice) {
            case 1: choice = 1;
            showFavoritesMenu();
            break;
            case 2: choice = 2;
            displayHistoryMenu();
            break;
            default: System.out.println("Invalid input");
            userMenu();
        }
    }
    }

    public void showFavoritesMenu() {
        System.out.println("Welcome to the favorites menu:");

    }

    public void createUserMenu() {
        System.out.println("So you would like to create a new user.");
        System.out.print("Enter your desired username: ");
        Scanner usernameScanner = new Scanner(System.in);
        String username = usernameScanner.next();
        System.out.print("\nEnter your desired password: ");
        Scanner passwordScanner = new Scanner(System.in);
        String password = passwordScanner.next();
        System.out.print("\nConfirm password: ");
        Scanner confirmPasswordScanner = new Scanner(System.in);
        String confirmedPassword = confirmPasswordScanner.next();
        if (confirmedPassword.equals(password)) {
            System.out.println("User created successfully.");
        }
        else {
            System.out.print("Try again: ");
            confirmedPassword = confirmPasswordScanner.next();
        }
    }

    public void logInUserMenu() {
        System.out.println("Welcome to the login menu!");
        System.out.print("Enter username: ");
        Scanner usernameScanner = new Scanner(System.in);
        String username = usernameScanner.next();
        System.out.println("Enter password: ");
        Scanner pwScanner = new Scanner(System.in);
        String password = pwScanner.next();

    }

    public void displayHistoryMenu() {
        System.out.println("History");
    }

    public void searchMenu() {
        System.out.println("Search");
    }
    public void adminMenu() {
        createMovieMenu();
        deleteMovieMenu();
        editMovieMenu();
    }
    public void createMovieMenu() {
        System.out.println("Create movie");
    }
    public void deleteMovieMenu() {
        System.out.println("Delete movie");
    }
    public void editMovieMenu() {
        System.out.println("Edit movie");
    }
    public void createActorMenu() {
        System.out.println("Create actor");
    }
    public void deleteActorMenu() {
        System.out.println("Delete actor");
    }
    public void editActorMenu() {
        System.out.println("Edit actor");
    }
    public void pause() {
        
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