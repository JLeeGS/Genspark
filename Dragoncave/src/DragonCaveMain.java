import java.util.Scanner;
public class DragonCaveMain {
    public static void main(String[] args){
        String start="You are in a land full of dragons. In front of you, " +
                "\nyou see two caves. In one cave, the dragon is friendly" +
                "\nand will share his treasure with you. The other dragon" +
                "\nis greedy and hungry and will eat your on sight." +
                "\nWhich cave will you go into? (1 or 2)";
        String end1="You approach the cave..." +
                "\nIt is dark and spooky..." +
                "\nA large dragon jumps out in front of you! He opens his jaws and..." +
                "\nGobbles you down in one bite!";
        String end2="You approach the cave..." +
                "\nYou got the treasure!";

        Scanner scan= new Scanner(System.in);


        try {
                System.out.println(start);
                int input=scan.nextInt();
                if (input == 1) {
                    System.out.println(end1);
                } else if (input == 2) {
                    System.out.println(end2);
                }
                else{
                    System.out.println("Wrong Input");
                }
        }
        catch(Error err){
            System.out.println("Something went wrong "+ err);
        }
    }
}
