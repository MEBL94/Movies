import java.util.Scanner;
public class Menu {
    public void mainMenu() {
        int choice = 1;
        Scanner scan = new Scanner(System.in);
        // System.out.println("You now have two choices:");
        // System.out.println("1. Create user");
        // System.out.println("2. Login");
        switch (choice) {
            case 1: choice = 1;
            createUserMenu();
            scan.nextInt();
            break;
            case 2: choice = 2;
            logInUserMenu();
            scan.nextInt();
            break;
            default: System.out.println("Invalid input");
        }
    }

    public void showFavoritesMenu() {
        System.out.println("Favorites: ");
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

    }

}