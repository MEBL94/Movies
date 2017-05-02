import java.util.Scanner;
import java.io.File;
public class UserInput {
    private Scanner scanner;

    public UserInput(){
        this.scanner = new Scanner(System.in);
    }

    public UserInput(File file){
        try{this.scanner = new Scanner(file);}
        catch(Exception e){
            System.out.println(">> File not found: " + e);
        }
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