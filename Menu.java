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
            fileHandler.saveToFiles();
            break;
            case 1:
            createUserMenu();
            break;
            case 2:
            userID = logInUserMenu();
            userMenu(userID);
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
            choice = scan.getInt();
            switch (choice) {
                case 0:
                break;
                case 1:
                // call of play movie method
                System.out.println("Choose a movie that you would like to play.");
                System.out.println("0 for search");
                int number = 0;
                for (Movie movie : lib.getMovies()) {
                    number++;
                    System.out.println(number +": "+ movie.getTitle());
                }
                number = scan.getInt();
                if (number == 0){
                    int searchResult = this.searchForMovie();
                    if (searchResult > -1){
                        playMovie(lib.getMovie(searchResult), userID);
                    }
                } else if (number > 0){
                    playMovie(lib.getMovie(number - 1), userID);
                }

                //createHistoryEvent(lib.getMovie(searchForMovie()));
                break;
                case 2: 
                showFavoritesMenu();
                break;
                case 3:
                displayHistoryMenu();
                break;
                default: System.out.println("Invalid input");
                userMenu(userID);
                break;
            }
        }
    }

    public void displayHistoryMenu() {
        System.out.println("Welcome to the history menu!");
        au.getUser(userID).printHistory();
        System.out.println("PRESS ANY KEY TO CONTINUE");
        pause();
    }

    public void playMovie(Movie movie, int userID){
        System.out.println("#################");
        System.out.println("You are now playing a movie:");
        System.out.println(movie);
        au.getUser(userID).createHistoryEvent(movie);
        System.out.println("#################");

        pause();
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
            System.out.println("1. Create movie");
            System.out.println("2. Delete movie");
            System.out.println("3. Edit movie");
            System.out.println("4. Create actor");
            System.out.println("5. Delete actor");
            System.out.println("6. Users");
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
                editMovieMenu(chooseMovieMenu("edit"));
                break;
                case 4:
                createActorMenu();
                break;
                case 5:
                deleteActorMenu();
                break;
                case 6:
                System.out.println("List of all users:");
                for (User user : au.getUsers()) {
                    System.out.println(user);
                }
                System.out.println("Do you wish to create a new user or delete an existing user? Write create/delete.");
                String answer = scan.getLine();
                if (answer.equalsIgnoreCase("create")) {
                    createUserMenu();
                }
                else if (answer.equalsIgnoreCase("delete")) {
                   System.out.print("Which user do you wish to remove? Enter the specific username : ");
                   String username = scan.getLine(); 
                   au.removeUser(username); 
                }
                else {
                    System.out.println("Invalid input");
                }
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
        System.out.println("Do you wish to add an actor? Yes or no?");
        String answer = scan.getLine();
        if (answer.equals("yes")) {
            while(answer.equals("yes")){
                System.out.print("Actorname: ");
                String actor = scan.getLine();
                if(lib.findActor(actor) > -1)
                {
                    lib.getActor(lib.findActor(actor));
                    System.out.println("Want to add another actor? ");
                } 
                else 
                {
                    System.out.println("Actor not found");
                    System.out.println("Want to try again?");
                }
                answer = scan.getLine();
            }
        }
    }
    public void deleteMovieMenu() {
        System.out.println("So you wish to delete a movie");
        System.out.print("Title: ");
        String title = scan.getLine();
        System.out.print("Release year: ");
        int year = scan.getInt();
        lib.deleteMovie(title);
    }
    public Movie chooseMovieMenu(String operation) {
        System.out.println("What movie would you like to " + operation +"?");
        System.out.println("0 for search");
        int number = 0;
        for (Movie movie : lib.getMovies()) {
            number++;
            System.out.println(number +": "+ movie.getTitle());
        }
        number = scan.getInt();
        if (number == 0){
            int searchResult = this.searchForMovie();
            if (searchResult > -1){
                return lib.getMovie(searchResult);
            }
        }
        return lib.getMovie(number - 1);
    }
    public Actor chooseActorMenu(String operation) {
        System.out.println("What Actor would you like to " + operation +"?");
        System.out.println("0 for search");
        int number = 0;
        for (Actor actor : lib.getActors()) {
            number++;
            System.out.println(number +": "+ actor.getName());
        }
        number = scan.getInt();
        if (number == 0){
            int searchResult = this.searchForActor();
            if (searchResult > -1){
                return lib.getActor(searchResult);
            }
        }
        return lib.getActor(number - 1);
    }

    public Actor chooseActorMenu(String operation, ArrayList<Actor> actors) {
        System.out.println("What Actor would you like to " + operation +"?");
        boolean wrongChoice = true;
        int number = 0;
        while(wrongChoice){
            for (Actor actor : actors) {
                number++;
                System.out.println(number +": "+ actor.getName());
            }
            number = scan.getInt() - 1;
            if(number < actors.size() && number >= 0){
                wrongChoice = false;
            }
        }
        return lib.getActor(number);
    }

    public void editMovieMenu(Movie movie){
        boolean running = true;
        while(running){
            System.out.println("Edit " + movie.getTitle());
            System.out.println("1. Change title");
            System.out.println("2. Change release year");
            System.out.println("3. Add actor");
            System.out.println("4. Remove actor");
            switch (scan.getInt()){
                case 1:
                    System.out.print("New title: ");
                    String newTitle = scan.getLine();
                    movie.setTitle(newTitle);
                break;
                case 2:
                    System.out.print("New release year: ");
                    int newYear = scan.getInt();
                    movie.setReleaseYear(newYear);
                break;
                case 3:
                    movie.addActor(chooseActorMenu("add"));
                break;
                case 4:
                    // if(movie.)
                    movie.removeActor(chooseActorMenu("remove", movie.getActors()));
                break;
            }
        }
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
        String birthday = day + "/" + month + "-" + year;
        lib.createActor(firstname, lastname, birthday);
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
        // cls();        
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