public class Main
{
    public static void main(String[] args)
    {
        Authenticator au = new Authenticator();; 
        au.createUser("Andreas", "Nissen", "Nissemand", "123456", true);
        au.createUser("Andreas", "Nissen", "Tissemand", "123456", true);

        au.printUsers();

        if(au.login("Tissemand", "123456")){
            System.out.println("Go away");
        }
    }
}