import java.util.*;

public class GuessTheNumberMain {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random ran= new Random();
        guess(ran.nextInt(19));
        String again=sc.nextLine();

        while(again.equals("y")){
            guess(ran.nextInt(19));
            again=sc.nextLine();
        }
        System.out.println("Thanks for playing!");
    }

        public static void guess(int num) {
            Scanner scan = new Scanner(System.in);
            System.out.println("Hello! What is your name?");
            String name = scan.nextLine();
            System.out.println("Well," + name + " I am thinking of a number between 1 and 20" +
                    "\nTake a guess.");
            int count=0;

            boolean repeat=true;
            try {
                int input = scan.nextInt();
                while (repeat&&count<6) {
                    count++;
                    if (count==6){
                        System.out.print("You ran out of guesses!");
                    }
                    else if(input < num) {
                        System.out.println("Your guess is too low." +
                                "\nTake a guess");
                        input = scan.nextInt();
                    } else if (input > num) {
                        System.out.println("Your guess is too high." +
                                "\nTake a guess");
                        input = scan.nextInt();
                    }
                    else if(input==num) {
                        System.out.println("Good job," + name + "! You guessed my number in " + count + " guesses!");
                        System.out.println("Would you like to play again? (y or n)");
                        break;
                    }
                    else {
                        System.out.println("Wrong Input");
                    }
                }
            }
            catch(Error err){
                System.out.println("Error!");
            }
    }
}


