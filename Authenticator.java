import java.util.ArrayList;

public class Authenticator
{
    private ArrayList<User> users = new ArrayList<User>();


    public void createUser(String firstname, String lastname, String username, String password, boolean admin)
    {
        users.add(new User());
    }

    public boolean checkUser()
    {

    }

    public boolean login()
    {

    }
}