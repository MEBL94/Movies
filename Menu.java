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
            int index = 0;

            switch (choice) {

                case 0:

                    break;

                case 1:
                    index = chooseMovieMenu("add to favorites");
                    if(index > -1){
                        au.getUser(userID).addToFavorites(lib.getMovie(index));
                    }
                    break;

                case 2:
                    index = chooseMovieMenu("add to favorites", au.getUser(userID).getFavorites());
                    if(index > -1){
                        au.getUser(userID).deleteFromFavorites(index);
                    }

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



    // public void searchMenu()

    // {


    //     System.out.println("Welcome to the search menu!");

    //     int choice = 1;

    //    while(choice != 0) {

    //         cls();
            
    //         System.out.println("1. Search for movie");

    //         System.out.println("2. Search for actor");

    //         choice = scan.getInt();

    //         switch (choice) {

    //             case 0:

    //             break;

    //             case 1:

    //             searchForMovie();

    //             break;

    //             case 2:

    //             searchForActor();

    //             break;

    //             default:

    //             System.out.println("Invalid input");

    //             break;

    //         }

    //     }

    // }



    private int searchForMovie()

    {


        String search = "";

        boolean foundMovie = false;

        while(foundMovie == false) {
            
            cls();   

            System.out.print("Search term (only title): "); 

            search = scan.getLine();
            ArrayList<Movie> listOfFoundMovies = new ArrayList<Movie>();
            int preciseResult = lib.findMovie(search);
            int listNumber = 0;
            System.out.println("Found these movies, which one did you mean?");
            
            if (preciseResult > -1){
                listNumber++;                
                listOfFoundMovies.add(lib.getMovie(preciseResult));
                System.out.println("Exact result:");
                System.out.println(listNumber + ". " + lib.getMovie(preciseResult).getTitle());
            }
            
            for(Movie movie : lib.getMovies()){
                if(movie.getTitle().toLowerCase().contains(search.toLowerCase())){
                    listOfFoundMovies.add(movie);
                }
            }

            System.out.println("Partial results:");
            for(Movie movie : listOfFoundMovies){
                listNumber++;
                System.out.println(listNumber + ". " + movie.getTitle());
            }
            

            System.out.println("Which one did you mean? (0 to go back)");
            
            listNumber = scan.getInt();
            //go back please
            if(listNumber == 0){
                return -1;
            // if in range SUCCESS
            } else if ((listNumber) > 0 && (listNumber -1) < listOfFoundMovies.size()){
                foundMovie = true;
                return lib.getMovies().indexOf(listOfFoundMovies.get(listNumber - 1));
            } else {
                System.out.println("Wrong input");
                pause();
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

            System.out.print("Search term (only Name): "); 

            search = scan.getLine();
            ArrayList<Actor> listOfFoundActors = new ArrayList<Actor>();
            int preciseResult = lib.findActor(search);
            int listNumber = 0;
            System.out.println("Found these Actors, which one did you mean?");
            
            if (preciseResult > -1){
                listNumber++;                
                listOfFoundActors.add(lib.getActor(preciseResult));
                System.out.println("Exact result:");
                System.out.println(listNumber + ". " + lib.getActor(preciseResult).getName());
            }
            
            for(Actor actor : lib.getActors()){
                if(actor.getName().toLowerCase().contains(search.toLowerCase())){
                    listOfFoundActors.add(actor);
                }
            }

            System.out.println("Partial results:");
            for(Actor actor : listOfFoundActors){
                listNumber++;
                System.out.println(listNumber + ". " + actor.getName());
            }
            

            System.out.println("Which one did you mean? (0 to go back)");
            
            listNumber = scan.getInt();
            //go back please
            if(listNumber == 0){
                return -1;
            // if in range SUCCESS
            } else if ((listNumber) > 0 && (listNumber -1) < listOfFoundActors.size()){
                foundActor = true;
                return lib.getActors().indexOf(listOfFoundActors.get(listNumber - 1));
            } else {
                System.out.println("Wrong input");
                pause();
            }
            
        }

        return lib.findActor(search); 

    }


    // private int searchForActor()

    // {

    //     String search = "";

    //     boolean foundActor = false;

    //   while(foundActor == false) {

    //         cls();

    //         System.out.print("Search term (only name): "); 

    //         search = scan.getLine();

    //         if (lib.findActor(search) >= 0) {

    //             foundActor = true;

    //         }

    //         else {

    //             System.out.println("Could not find the actor. Try Again! Want to try again? Yes or no?");

    //             if (scan.getLine().equalsIgnoreCase("no")) {

    //                 return -1;

    //             } 

    //         }

    //     }

    //     return lib.findActor(search); 

    // }



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
                    int index = chooseMovieMenu("edit");
                    if(index > -1){
                        editMovieMenu(lib.getMovie(index));
                    }
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
            System.out.println("3. Show actor details.");

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

                case 3:
                    showActorDetails();
                    break;

                default:

                    System.out.println("Not an option try again.");

                    pause();

                break;                

            }

        }

    }

    public void showActorDetails(){
        cls();
        int index = chooseActorMenu("see");
        
        if(index > -1){
            Actor thisActor = lib.getActor(index);
            cls();
            System.out.println(thisActor.getName()); 

            for(Movie movie : thisActor.getMovies()){
                System.out.println(movie.getTitle());
            }
            pause();
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

                    int index = chooseUserMenu("delete");
                    if(index > -1){
                        au.removeUser(au.getUser(index).getUsername());
                    }

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
                

                int index = chooseActorMenu("add");
                if(index > -1){
                    createdMovie.addActor(lib.getActor(index));
                    lib.getActor(index).addMovie(createdMovie);
                }
                
                

                System.out.println("Want to add another?");

                answer = scan.getLine();

            }

        }

    }



    public void deleteMovieMenu() {
        
        int index = chooseMovieMenu("delete");
        if(index > -1){
            lib.deleteMovie(lib.getMovie(index).getTitle());
        }

        

    }



    public int chooseMovieMenu(String operation) 
    {
        cls();
        int number = -1;
        boolean wrongChoice = true;
        String reason = " ";
        while(wrongChoice){

            System.out.println("What movie would you like to " + operation +"?");


            number = 0;

            for (Movie movie : lib.getMovies()) {

                number++;

                System.out.println(number +": "+ movie.getTitle());

            }
            System.out.println("Press 0 to return.");
            System.out.println("S for search");

            number = scan.getIntOrLetter();
            
            if (number == 888){

                number = this.searchForMovie();

                if (number > -1){

                    return number;

                } else {
                    cls();
                    reason = "Couldn't find that movie";
                }

            } else if (number > 0 && (number - 1) < lib.getMovies().size()){
                wrongChoice = false;
            } else if (number == 0) {
                //RETURN ved 0
                return -1;
            } else {
                reason = "wrong choice";
            }
            System.out.println(reason + " Try again.");

        }

        return (number - 1);

    }



    public int chooseMovieMenu(String operation, ArrayList<Movie> movies) {

        System.out.println("What Movie would you like to " + operation +"?");
        System.out.println("Press 0 to return.");

        boolean wrongChoice = true;

        int number = 0;

        while(wrongChoice){

            for (Movie movie : movies) {

                number++;

                System.out.println(number +": "+ movie.getTitle());

            }

            number = scan.getInt();

            if((number -1) < movies.size() && number > 0){

                wrongChoice = false;

            } else if (number == 0) {
                return -1;
            } else {
                System.out.println("Wrong choice try again.");
            }

        }

        return number - 1;

    }



    public int chooseUserMenu(String operation) {

        System.out.println("What User would you like to " + operation +"?");
        System.out.println("Press 0 to return.");
        

        boolean wrongChoice = true;

        int number = 0;

      while(wrongChoice){

            number = 0;

            for (User user : au.getUsers()) {

                number++;

                System.out.println(number +": "+ user.getUsername());

            }

            number = scan.getInt();

            if((number - 1) < au.getUsers().size() && number > 0){

                wrongChoice = false;

            } else if (number == 0) {
                return -1;
            } else {
                System.out.println("Wrong choice try again.");
            }

        }

        return (number - 1);

    }

    public int chooseActorMenu(String operation)
    {
        cls();
        boolean wrongChoice = true;
        int number = 0;
        String reason = " ";
        
        while(wrongChoice){
            System.out.println("What Actor would you like to " + operation +"?");
            number = 0;
            for (Actor actor : lib.getActors()) {
                number++;
                System.out.println(number +": "+ actor.getName());
            }
            System.out.println("S for search");
            System.out.println("Press 0 to return.");
            number = scan.getIntOrLetter();
            //if S
            if (number == 888){
                number = this.searchForActor();
                if (number > -1){
                    return number;
                } else {
                    reason = "Couldn't find that actor";
                }
            } else if (number > 0 && (number - 1) < lib.getActors().size()){
                wrongChoice = false;
            //if C
            } else if(number == 999){
                createActorMenu();
            } else if(number == 0) {
                return -1;
            } else {
                reason = "Wrong choice";
                System.out.println(reason + " try again.");
            }
        }
        return (number - 1);
    }



    public int chooseActorMenu(String operation, ArrayList<Actor> actors)

    {

        cls();

        boolean wrongChoice = true;

        int number = 0;

      while(wrongChoice){
          

            System.out.println("What Actor would you like to " + operation +"?");
            for (Actor actor : actors) {

                number++;

                System.out.println(number +": "+ actor.getName());

            }
            System.out.println("Press 0 to return.");

            number = scan.getInt();

            if((number - 1) < actors.size() && number > 0){
                wrongChoice = false;
            } else if(number == 0) {
                return -1;
            } else {
                System.out.println("Wrong choice try again.");
            }

        }

        return number - 1;

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
            int index = 0;

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
                    index = chooseActorMenu("add");
                    if(index > -1){
                        movie.addActor(lib.getActor(index));
                    }
                    

                break;

                case 4:

                    if(movie.hasActors()){
                        index = chooseActorMenu("remove", movie.getActors());
                        if(index > -1){
                            movie.removeActor(lib.getActor(index));
                        }

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

        int index = chooseActorMenu("delete");
        if(index > -1){
            lib.deleteActor(lib.getActor(index).getName());
        }
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