public class Classic{
    private int i;
    public Classic(int i){
        this.i = i;
    }
    // public void addOne(){
    //     i += 1;
    // }

    public int getI(){
        return this.i;
    }

    public void printI(){
        System.out.println("this is the old i; "+this.i);
    }
}