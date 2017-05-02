import java.util.Scanner;
public class UserInput {
    private Scanner scanner;

    public UserInput(String inputType){
        this.scanner = new Scanner(inputType);
    }

    public String getLine(){
        return scanner.nextLine();
    }

    public String getWord(){
        return scanner.next();
    }

    public Boolean hasNext(){
        return scanner.hasNext();
    }
    
    public int getInt(Scanner scan){
        try{
        int integer = Integer.parseInt(scan.nextLine());
        return integer;
        } catch (NumberFormatException e){
            System.out.println(">> ikke et tal: " + e);
        }
        return -1;
    }

}