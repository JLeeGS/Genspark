import java.util.*;
public class GuessTheNumberMain {
    GuessTheNumberMain(){
        super();
    }
    private String name;
    private int randomNum, guessedNum;
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name=name;
    }
    public int getRandom(){
        return randomNum;
    }
    public void setRandom(int random){
        this.randomNum=random;
    }
    public int getGuess(){
        return guessedNum;
    }
    public void setGuess(int guess){
        this.guessedNum=guess;
    }

    public static void main(String[] args) {
        GuessTheNumberMain gn=new GuessTheNumberMain();
        Scanner sc = new Scanner(System.in);

        //Temporarily set as Random Number
        Random ran= new Random();
        gn.setRandom(ran.nextInt(19));
        gn.guess(gn.getRandom());

        //Repeat?
        String again=sc.nextLine();

        while(again.equals("y")){
            gn.guess(ran.nextInt(19));
            again=sc.nextLine();
        }

        //End
        System.out.println("Thanks for playing!");
        sc.close();
        System.exit(0);
    }

    public void playerName(){
        //Player Input Name
        Scanner scan = new Scanner(System.in);
        System.out.println("Hello! What is your name?");
        String name = scan.nextLine();
        System.out.println("Well," + name + " I am thinking of a number between 1 and 20" +
                "\nTake a guess.");
        setName(name);
    }

    public void guess(int num) {
        playerName();
        Scanner scan = new Scanner(System.in);
        int count=0;

        //Guess Numbers
        boolean repeat=true;
        try {
            int input = scan.nextInt();
            setGuess(input);

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


