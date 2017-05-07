import java.util.*;

import java.io.*;



public class Menu {

    UserInput scan = new UserInput();

    Authenticator au = new Authenticator();

    Library lib = new Library();

    FileHandler fileHandler = new FileHandler(lib, au);

    private int userID = 0;



    public void mainMenu()

    {

        cls();

        fileHandler.loadFromFiles();

        int choice = 1;

       while(choice != 0) {

            cls();

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

                    adminMenu();

                break;

                default: 
                    System.out.println("Invalid input");

                break;        

                }

        }      
        fileHandler.saveToFiles();

    }    



    public void showFavoritesMenu()

    {

        int choice = 1;

       while(choice != 0) {

            cls();

            System.out.println("Welcome to the favorites menu!\n");

            System.out.println("Favorites for " + au.getUser(userID).getName());

            System.out.println("----------------------------------");

            for (Movie movie : au.getUser(userID).getFavorites()) {

                 System.out.println(movie.getTitle());

            }

            System.out.println("----------------------------------");

            System.out.println("You now have the following choices:");

            System.out.println("1. Add to favorites");

            System.out.println("2. Delete from favorites");

            choice = scan.getInt();

            switch (choice) {

                case 0:

                    break;

                case 1:

                    au.getUser(userID).addToFavorites(chooseMovieMenu("add to favorites"));

                    break;

                case 2:

                    au.getUser(userID).deleteFromFavorites(chooseMovieMenu("add to favorites", au.getUser(userID).getFavorites()));

                    break;

                default: System.out.println("Invalid input");

                break;

            }

        }

    }



    public User createUserMenu()

    {   

        cls();

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

    }



    public int logInUserMenu()

    {

        cls();

        System.out.println("Welcome to the login menu!");

        System.out.print("Enter username: ");

        String username = scan.getLine();

        System.out.print("Enter password: ");

        String password = scan.getLine();

        int userID = au.login(username, password);

        if (userID > 0) {

            cls();

            System.out.println("Login successful\nWelcome " + au.getUser(userID).getName());

            pause();

            return userID;
        }

        else {

            cls();

            System.out.println("Login failed. Wrong username or password.");
            
            pause();


            return 0;

        }

    }



    public void userMenu(int userID)

    {

        int choice = 1;

       while(choice != 0) {

            cls();

            if (userID == 0) {

                break;

            }

            User thisUser = au.getUser(userID);

            System.out.println("Current user: " + thisUser.getName() + " \\\\ " + thisUser.getUsername() + "\nYou now have the following choices:");

            System.out.println("1. Play movie");

            System.out.println("2. Go to favorites menu");

            System.out.println("3. Go to history menu");

            choice = scan.getInt();

            switch (choice) {

                case 0:

                break;

                case 1:

                    // call of play movie method

                    cls();

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



    public void displayHistoryMenu()

    {

        cls();

        System.out.println("Welcome to the history menu!\n");

        au.getUser(userID).printHistory();

        pause();

    }



    public void playMovie(Movie movie, int userID){

        cls();

        System.out.println("----------------------------");

        System.out.println("You are now playing a movie:");

        System.out.println(movie);

        au.getUser(userID).createHistoryEvent(movie);

        System.out.println("----------------------------");

        pause();

    }



    public void searchMenu()

    {


        System.out.println("Welcome to the search menu!");

        int choice = 1;

       while(choice != 0) {

            cls();
            
            System.out.println("1. Search for movie");

            System.out.println("2. Search for actor");

            choice = scan.getInt();

            switch (choice) {

                case 0:

                break;

                case 1:

                searchForMovie();

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



    private int searchForMovie()

    {


        String search = "";

        boolean foundMovie = false;

      while(foundMovie == false) {
            
            cls();   

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



    private int searchForActor()

    {

        String search = "";

        boolean foundActor = false;

      while(foundActor == false) {

            cls();

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



    public void adminMenu()

    {


        int choice = 1;

       while(choice != 0) {
            cls();

            System.out.println("---Admin Menu---");

            System.out.println("1. Manage Movies");

            System.out.println("2. Manage Actors");

            System.out.println("3. Manage Users");

            choice = scan.getInt();

            switch (choice) {

                case 0:
                    break;

                case 1:

                    manageMoviesMenu();

                    break;

                case 2:

                    manageActorsMenu();

                    break;

                case 3:

                    manageUsersMenu();

                    break;

                default: 

                    System.out.println("Invalid input");

                    break;

            }

        }

    }



    public void manageMoviesMenu() {

        int answer = -1;

       while(answer != 0){
            cls();

            System.out.println("Movies in system:");
            System.out.println("-----------------");

            for (Movie movie : lib.getMovies()) {

                System.out.println("  " + movie.getTitle());

            }
            System.out.println("-----------------");

            System.out.println("Options:");

            System.out.println("1. Create new movie.");

            System.out.println("2. Edit movie.");

            System.out.println("3. Delete movie.");

            answer = scan.getInt();

            switch (answer){

                case 0:

                    break;

                case 1:

                    createMovieMenu();

                    break;

                case 2:

                    editMovieMenu(chooseMovieMenu("edit"));

                    break;

                case 3:

                    deleteMovieMenu();

                    break;

                default:

                    System.out.println("Not an option try again.");

                    pause();

                break;                

            }

        }

    }



    public void manageActorsMenu() {

        int answer = -1;

        while(answer != 0){

            cls();

            System.out.println("Actors in system:");
            System.out.println("-----------------");

            for (Actor actor : lib.getActors()) {

                System.out.println("  " + actor.getName());

            }
            System.out.println("-----------------");

            System.out.println("Options:");

            System.out.println("1. Create an actor.");

            System.out.println("2. Delete actor.");

            answer = scan.getInt();

            switch (answer){

                case 0:

                    break;

                case 1:

                    createActorMenu();

                    break;

                case 2:

                    deleteActorMenu();

                    break;

                default:

                    System.out.println("Not an option try again.");

                    pause();

                break;                

            }

        }

    }



    public void manageUsersMenu() {

        int answer = -1;

         while(answer != 0){
            
            cls();

            System.out.println("Users in system:");
            System.out.println("-----------------");

            for (User user : au.getUsers()) {

                System.out.println("  " + user);

            }
            System.out.println("-----------------");

            System.out.println("Options:");

            System.out.println("1. Create new user.");

            System.out.println("2. Delete user.");

            answer = scan.getInt();

            switch (answer){

                case 0:

                    break;

                case 1:
                    
                    createUserMenu();

                    break;

                case 2:
                    au.removeUser(chooseUserMenu("delete").getUsername());

                    break;

                default:

                    System.out.println("Not an option try again.");

                    pause();

                    break;                

            }

        }

    }



    public void createMovieMenu() {

        cls();

        System.out.println("CREATE MOVIE");

        System.out.print("Title: ");

        String title = scan.getLine();

        System.out.print("Release year: ");

        int year = scan.getInt();

        while(year == -1){

            cls();

            System.out.println("Wrong input try again");

            year = scan.getInt();

        } 

        Movie createdMovie = lib.createMovie(title, year);

        System.out.println("Do you wish to add an actor? Yes or no?");

        String answer = scan.getLine();

        if (answer.equalsIgnoreCase("Yes") || answer.equalsIgnoreCase("y")) {

            while(answer.equalsIgnoreCase("Yes") || answer.equalsIgnoreCase("y")){

                cls();
                
                Actor tempactor;
                tempactor = chooseActorMenu("add");

                createdMovie.addActor(tempactor);

                System.out.println("Want to add another?");

                answer = scan.getLine();

            }

        }

    }



    public void deleteMovieMenu() {

        lib.deleteMovie(chooseMovieMenu("delete").getTitle());

    }



    public Movie chooseMovieMenu(String operation) 
    {
        cls();
        int number = -1;
        boolean wrongChoice = true;
        String reason = " ";
        while(wrongChoice){

            System.out.println("What movie would you like to " + operation +"?");

            System.out.println("0 for search");

            number = 0;

            for (Movie movie : lib.getMovies()) {

                number++;

                System.out.println(number +": "+ movie.getTitle());

            }

            number = scan.getInt();
            
            if (number == 0){

                number = this.searchForMovie();

                if (number > -1){

                    return lib.getMovie(number);

                } else {
                    reason = "Couldn't find that movie";
                }

            } else if (number > 0 && (number - 1) < lib.getMovies().size()){
                wrongChoice = false;
            } else {
                reason = "wrong choice";
            }
            System.out.println(reason + " Try again.");

        }

        return lib.getMovie(number - 1);

    }



    public Movie chooseMovieMenu(String operation, ArrayList<Movie> movies) {

        System.out.println("What Movie would you like to " + operation +"?");

        boolean wrongChoice = true;

        int number = 0;

        while(wrongChoice){

            for (Movie movie : movies) {

                number++;

                System.out.println(number +": "+ movie.getTitle());

            }

            number = scan.getInt() - 1;

            if(number < movies.size() && number >= 0){

                wrongChoice = false;

            }

        }

        return movies.get(number);

    }



    public User chooseUserMenu(String operation) {

        System.out.println("What User would you like to " + operation +"?");

        boolean wrongChoice = true;

        int number = 0;

      while(wrongChoice){

            number = 0;

            for (User user : au.getUsers()) {

                number++;

                System.out.println(number +": "+ user.getUsername());

            }

            number = scan.getInt() - 1;

            if(number < au.getUsers().size() && number >= 0){

                wrongChoice = false;

            } 

        }

        return au.getUsers().get(number);

    }

    public Actor chooseActorMenu(String operation)
    {
        cls();
        boolean wrongChoice = true;
        int number = 0;
        String reason = " ";
        
        while(wrongChoice){
            System.out.println("What Actor would you like to " + operation +"?");
            System.out.println("0 for search");
            number = 0;
            for (Actor actor : lib.getActors()) {
                number++;
                System.out.println(number +": "+ actor.getName());
            }
            number = scan.getIntOrC();
            if (number == 0){
                number = this.searchForActor();
                if (number > -1){
                    return lib.getActor(number);
                } else {
                    reason = "Couldn't find that actor";
                }
            } else if (number > 0 && (number - 1) < lib.getActors().size()){
                wrongChoice = false;
            } else if(number == 999){
                createActorMenu();
            } else {
                reason = "wrong choice";
                System.out.println(reason + " Try again.");
            }
        }
        return lib.getActor(number - 1);
    }



    public Actor chooseActorMenu(String operation, ArrayList<Actor> actors)

    {

        cls();

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

        return actors.get(number);

    }



    public void editMovieMenu(Movie movie)

    {

        cls();

        boolean running = true;

      while(running){

            System.out.println("Edit " + movie.getTitle());

            System.out.println("1. Change title");

            System.out.println("2. Change release year");

            System.out.println("3. Add actor");

            System.out.println("4. Remove actor");

            System.out.println("5. return to admin menu");

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

                    if(movie.hasActors()){

                        movie.removeActor(chooseActorMenu("remove", movie.getActors()));

                    } else {

                        System.out.println("No actors to delete.");

                    }

                break;

                case 5:

                    running = false;

                break;

            }

        }

    }

    public void createActorMenu()

    {

        cls();

        System.out.println("So you wish to create an actor");

        System.out.print("Firstname: ");

        String firstname = scan.getLine();

        System.out.print("Lastname: ");

        String lastname = scan.getLine();

        System.out.print("Day: ");

        int day = scan.getInt();

      while(day == -1){

            System.out.println("Wrong input try again");

            day = scan.getInt();

        } 

        System.out.print("Month: ");

        int month = scan.getInt();

      while(month == -1){

            System.out.println("Wrong input try again");

            month = scan.getInt();

        } 

        System.out.print("Year: ");

        int year = scan.getInt();

      while(year == -1){

            System.out.println("Wrong input try again");

            year = scan.getInt();

        } 

        String birthday = day + "/" + month + "-" + year;

        lib.createActor(firstname, lastname, birthday);

    }

    

    public void deleteActorMenu() {

        lib.deleteActor(chooseActorMenu("delete").getName());

    }


    public void pause()
    {
        System.out.println("Press enter to continue..");
        UserInput input = new UserInput();                     
        input.getLine(); 

    }

    

    public void cls() {

         try { 

            Process p = Runtime.getRuntime().exec("clear"); 

            BufferedReader in = new BufferedReader( 

                                new InputStreamReader(p.getInputStream())); 

            String line = null; 

           while((line = in.readLine()) != null) { 

                System.out.println(line); 

            } 

        } catch (IOException e) { 
            System.out.println("Der var en fejl med clearscreen, vi pynter lige på det hele her.");
            for(int i = 0; i < 45; i++){
                System.out.println();
            }
        } 

    }

}