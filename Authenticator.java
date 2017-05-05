import java.util.ArrayList;

public class Authenticator
{
    private ArrayList<User> users = new ArrayList<User>();

    public void createUser(String firstname, String lastname, String username, String password, boolean admin)
    {
        if(!checkUser(username))
        {
            int userID = users.size() +1;
            users.add(new User(firstname, lastname, username, password, userID, admin));
        }
    }

    public boolean checkUser(String username)
    {
       for (User user : users)
       {
        if(user.getUsername().equals(username))
        {
            return true; // the user exist
        }
       }   
        return false; // the user doesn't exist
    }

    public int login(String username, String password)
    {
        for (User user : users)
        {
            if(user.getUsername().equals(username) && user.getPassword().equals(password))
            {
                return user.getUserID();
            }
        }
        return 0;
    }
    
    public User getUser(int userID){
        for (User user : users){
            if(user.getUserID() == userID){
                return user;
            }    
        }
        return null;
    }
    
    public ArrayList<User> getUsers(){
        return users;
    }

    public void removeUser(String username){
        int userIndex;
        for(User user : users){
            if(username.equals(user.getUsername())){
                users.remove(user);
                System.out.println("User: " + username + " removed.");
            }
        }
    }
}