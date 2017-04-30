public class NewOne{
    private int i;
    public NewOne(int i){
        this.i = i;
    }
    public void addOne(){
        i += 1;
    }
    public void printI(){
        System.out.println("this is the new i; "+this.i);
    }
}