import java.util.*;
import java.io.*;

public class Menu {
    public void mainMenu() {
        int choice = 1;
        Scanner scan = new Scanner(System.in);
        while (choice >= 1) {
        System.out.println("You now have the following choices:");
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
            break;        
            }
    }
    }    
    
    public void showFavoritesMenu() {
        int choice = 1;
        Scanner scan = new Scanner(System.in);
        while (choice >= 1) {
            System.out.println("Welcome to the favorites menu!");
            System.out.println("You now have the following choices:");
            System.out.println("1. Show favorites list.");
            System.out.println("2. Return to user menu.");
            System.out.println("3. Return to main menu.");
            choice = scan.nextInt();
            switch (choice) {
                case 1: choice = 1;
                // supposed to show favorites list
                break;
                case 2: choice = 2;
                userMenu();
                break;
                case 3: choice = 3;
                mainMenu();
                break;
                default: System.out.println("Invalid input");
                showFavoritesMenu();
                break;
            }

        }
    }

    // implementation on its way...
    public void createUserMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the create user menu!");
        System.out.println("You must choose a username and a password");

        // System.out.println("So you would like to create a new user.");
        // System.out.print("Enter your desired username: ");
        // Scanner usernameScanner = new Scanner(System.in);
        // String username = usernameScanner.next();
        // System.out.print("\nEnter your desired password: ");
        // Scanner passwordScanner = new Scanner(System.in);
        // String password = passwordScanner.next();
        // System.out.print("\nConfirm password: ");
        // Scanner confirmPasswordScanner = new Scanner(System.in);
        // String confirmedPassword = confirmPasswordScanner.next();
        // if (confirmedPassword.equals(password)) {
        //     System.out.println("User created successfully.");
        // }
        // else {
        //     System.out.print("Try again: ");
        //     confirmedPassword = confirmPasswordScanner.next();
        // }
    }

    // implementation on its way
    public void logInUserMenu() {
        System.out.println("Welcome to the login menu!");
        System.out.println("Enter username and password to proceed");
        // System.out.println("Welcome to the login menu!");
        // System.out.print("Enter username: ");
        // Scanner usernameScanner = new Scanner(System.in);
        // String username = usernameScanner.next();
        // System.out.println("Enter password: ");
        // Scanner pwScanner = new Scanner(System.in);
        // String password = pwScanner.next();
    }

    public void userMenu() {
        int choice = 1;
        Scanner scanner = new Scanner(System.in);
        while (choice >= 1) {
            System.out.println("Welcome to the user menu! You now have the following choices:");
            System.out.println("1. Play movie");
            System.out.println("2. Display favorites");
            System.out.println("3. Display history");
            System.out.println("4. Return to main menu");
            choice = scanner.nextInt();
            switch (choice) {
                case 1: choice = 1;
                // call of play movie method
                break;
                case 2: choice = 2;
                // call of favorites method
                break;
                case 3: choice = 3;
                // call of display history method
                break;
                case 4: choice = 4;
                mainMenu();
                break;
                default: System.out.println("Invalid input");
                userMenu();
                break;
            }
        }
    }

    // implementation on its way
    public void displayHistoryMenu() {
        System.out.println("Welcome to the history menu!");
    }

    // implementatio on its way
    public void searchMenu() {
        System.out.println("Welcome to the search menu!");
    }

    // implementation on its way
    public void adminMenu() {
        createMovieMenu();
        deleteMovieMenu();
        editMovieMenu();
        createActorMenu();
        deleteActorMenu();
        editActorMenu();
    }

    public void createMovieMenu() {
        System.out.println("1. Create movie");
    }
    public void deleteMovieMenu() {
        System.out.println("2. Delete movie");
    }
    public void editMovieMenu() {
        System.out.println("3. Edit movie");
    }
    public void createActorMenu() {
        System.out.println("4. Create actor");
    }
    public void deleteActorMenu() {
        System.out.println("5. Delete actor");
    }
    public void editActorMenu() {
        System.out.println("6. Edit actor");
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