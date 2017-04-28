import java.util.ArrayList;

public class Authenticator
{
    private ArrayList<User> users = new ArrayList<User>();

    public void createUser(String firstname, String lastname, String username, String password, boolean admin)
    {
        users.add(new User(firstname, lastname, username, password, admin));
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

    public boolean login(String username, String password)
    {
        for (User user : users)
        {
            if(user.getUsername().equals(username) && user.getPassword().equals(password))
            {
                return true;
            }
        }
        return false;
    }
}