import java.util.*;
import java.io.*;

public class Menu {
    UserInput scan = new UserInput();
    Authenticator au = new Authenticator();
    Library lib = new Library();
    FileHandler fileHandler = new FileHandler(lib, au);
    private int userID = 0;

    public void mainMenu() {
        fileHandler.loadFromFiles();
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
            System.out.println("Favorites list: ");
            for (Movie movie : au.getUser(userID).getFavorites()) {
                 System.out.println(movie);
            }
            System.out.println("You now have the following choices:");
            System.out.println("1. Add to favorites");
            System.out.println("2. Delete from favorites");
            System.out.println("3. Return to user menu.");
            System.out.println("4. Return to main menu.");
            choice = scan.getInt();
            switch (choice) {
                case 0:
                    break;
                case 1:
                    int index = searchForMovie();
                    if (index == -1) {
                        break;
                    }
                    Movie movie = lib.getMovie(index);
                    au.getUser(userID).addToFavorites(movie);
                    break;
                case 2:
                    int index2 = searchForMovie();
                    if (index2 == -1) {
                        break;
                    }
                    Movie movie2 = lib.getMovie(index2);
                    try
                    {
                        au.getUser(userID).deleteFromFavorites(movie2);
                    }
                    catch (Exception e)
                    {
                        
                    }
                    break;
                case 3:
                    userMenu(userID);
                    break;
                case 4:
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
            System.out.println("Creating a user failed.");
        }
        this.userID = au.login(username, password); 
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
        int userID = au.login(username, password);
        if (userID > 0) {
            System.out.println("Login successful");
            return userID;
        }
        else {
            System.out.println("Login failed. Wrong username or password.");
            mainMenu();
            return 0;
        }
    }

    public void userMenu(int userID) {
        int choice = 1;
        while (choice != 0) {
            if (userID == 0) {
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
                System.out.println("Choose a movie that you would like to play.");
                searchForMovie();
                au.getUser(this.searchForMovie());
                //createHistoryEvent(lib.getMovie(searchForMovie()));
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
        System.out.print("Do you wish to display history? Yes or no? : ");
        String answer = scan.getLine();
        if (answer.equalsIgnoreCase("no")) {
            userMenu(userID);
        } 
        else { 
        System.out.println(au.getUser(userID).getHistory());
        }
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
            searchForMovie();
            // System.out.println(movie);
            break;
            case 2:
            searchForActor();
            break;
            default:
            System.out.println("Invalid input");
            break;
        }
    }
    }

    private int searchForMovie() {
            String search = "";
            boolean foundMovie = false;
            while(foundMovie == false) {
                System.out.print("Search term (only title): "); 
                search = scan.getLine();
                if (lib.findMovie(search) >= 0) {
                    foundMovie = true;
                }
                else {
                    System.out.println("Could not find the movie. Try Again! Want to try again? Yes or no?");
                    if (scan.getLine().equalsIgnoreCase("no")) {
                        return -1;
                    } 
                }
            }
        return lib.findMovie(search); 
    }

    private int searchForActor() {
            String search = "";
            boolean foundActor = false;
            while(foundActor == false) {
                System.out.print("Search term (only name): "); 
                search = scan.getLine();
                if (lib.findActor(search) >= 0) {
                    foundActor = true;
                }
                else {
                    System.out.println("Could not find the actor. Try Again! Want to try again? Yes or no?");
                    if (scan.getLine().equalsIgnoreCase("no")) {
                        return -1;
                    } 
                }
            }
        return lib.findActor(search); 
    }

    public void adminMenu() {
        int choice = 1;
        while (choice != 0) {
            System.out.println("1. Create movie.");
            System.out.println("2. Delete movie.");
            System.out.println("3. Edit movie.");
            System.out.println("4. Create actor.");
            System.out.println("5. Delete actor.");
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
        lib.findMovie(title);
        System.out.print("New title: ");
        String newTitle = scan.getLine();
        System.out.print("New release year: ");
        int newYear = scan.getInt();
        lib.createMovie(newTitle, newYear);
        lib.deleteMovie(title);
        
    }
    public void createActorMenu() {
        System.out.println("So you wish to create an actor");
        System.out.print("Firstname: ");
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
        System.out.print("Name: ");
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