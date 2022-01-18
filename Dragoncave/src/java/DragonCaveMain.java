import java.util.Scanner;
public class DragonCaveMain {

    DragonCaveMain(){
        super();
    }
    private String start,end1,end2;
    public String getStart(){
        return start;
    }
    public void setStart(String start){
        this.start=start;
    }
    public String getEnd1(){
        return end1;
    }
    public void setEnd1(String end1){
        this.end1=end1;
    }
    public String getEnd2(){
        return end2;
    }
    public void setEnd2(String end2) {
        this.end2=end2;
    }

    public static void main(String[] args){
        DragonCaveMain dragonCave= new DragonCaveMain();

        String s="You are in a land full of dragons. In front of you, " +
                "\nyou see two caves. In one cave, the dragon is friendly" +
                "\nand will share his treasure with you. The other dragon" +
                "\nis greedy and hungry and will eat your on sight." +
                "\nWhich cave will you go into? (1 or 2)";
        String e1="You approach the cave..." +
                "\nIt is dark and spooky..." +
                "\nA large dragon jumps out in front of you! He opens his jaws and..." +
                "\nGobbles you down in one bite!";
        String e2 = "You approach the cave..." +
                "\nYou got the treasure!";
        dragonCave.setStart(s);
        dragonCave.setEnd1(e1);
        dragonCave.setEnd2(e2);

        Scanner scan= new Scanner(System.in);
        try {
                System.out.println(dragonCave.getStart());
                int input=scan.nextInt();
                if (input == 1) {
                    System.out.println(dragonCave.getEnd1());
                } else if (input == 2) {
                    System.out.println(dragonCave.getEnd2());
                }
                else{
                    System.out.println("Wrong Input");
                }
        }
        catch(Exception err){
            System.out.println("Something went wrong "+ err);
        }
    }
}
