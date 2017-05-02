import java.util.*;
import java.io.*;

public class Menu {
    public void mainMenu() {
        int choice = 1;
        Scanner scan = new Scanner(System.in);
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
    
    public void showFavoritesMenu() {
        int choice = 1;
        Scanner scan = new Scanner(System.in);
        while (choice != 0) {
            System.out.println("Welcome to the favorites menu!");
            System.out.println("You now have the following choices:");
            System.out.println("1. Show favorites list.");
            System.out.println("2. Return to user menu.");
            System.out.println("3. Return to main menu.");
            choice = scan.nextInt();
            switch (choice) {
                case 0:
                break;
                case 1: 
                // supposed to show favorites list
                //System.out.println(createUserMenu().user.getFavorites().get(i));
                System.out.println("Favorites list: ");
                break;
                case 2:
                userMenu();
                break;
                case 3:
                mainMenu();
                break;
                default: System.out.println("Invalid input");
                break;
            }
        }
    }

    // implementation on its way...
    public void createUserMenu() {
        Scanner scanner = new Scanner(System.in);
        //Authenticator authenticator = new Authenticator();
        System.out.println("Welcome to the create user menu!");
        System.out.print("Enter your firstname: ");
        String firstname = scanner.next();
        System.out.print("\nEnter your lastname: ");
        String lastname = scanner.next();
        System.out.print("\nEnter your desired username: ");
        String username = scanner.next();
        System.out.print("\nEnter your desired password: ");
        String password = scanner.next();
        //User user = new User(firstname, lastname, username, password, false);
        //authenticator.createUser(user);
        System.out.println("You have successfully created a user!");
    }

    // implementation on its way
    public void logInUserMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the login menu!");
        System.out.print("Enter username: ");
        String username = scanner.next();
        //if (username.equals(createUserMenu().username)) {
        System.out.print("Enter password: ");
        String password = scanner.next();
        //    if (password.equals(createUserMenu().password)) {
        System.out.println("Login successful");
        userMenu();
        //    }
        //    else {
        //        System.out.println("Wrong password! Try again.");
        //        logInUserMenu();              
        //    }
        //else {
        //    System.out.print("Wrong username");
        //    logInUserMenu();
        //}
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
        while (choice != 0) {
            System.out.println("Welcome to the user menu! You now have the following choices:");
            System.out.println("1. Play movie");
            System.out.println("2. Display favorites");
            System.out.println("3. Display history");
            System.out.println("4. Return to main menu");
            choice = scanner.nextInt();
            switch (choice) {
                case 0:
                break;
                case 1:
                // call of play movie method
                System.out.println("Movie is played");
                break;
                case 2:
                System.out.println("Favorites are displayed"); 
                // call of favorites method
                break;
                case 3:
                System.out.println("History is displayed"); 
                // call of display history method
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

    // implementation on its way
    public void displayHistoryMenu() {
        System.out.println("Welcome to the history menu!");
        System.out.println("Call of getHistory() method");
    }

    // implementatio on its way
    public void searchMenu() {
        System.out.println("Welcome to the search menu!");
        System.out.println("Call of search() method");
    }

    // implementation on its way
    public void adminMenu() {
        int choice = 1;
        Scanner scanner = new Scanner(System.in);
        while (choice != 0) {
            System.out.println("1. Create movie");
            System.out.println("2. Delete movie");
            System.out.println("3. Edit movie");
            System.out.println("4. Create actor");
            System.out.println("5. Delete actor");
            System.out.println("6. Edit actor");
            choice = scanner.nextInt();
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
        System.out.println("Create movie option");
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