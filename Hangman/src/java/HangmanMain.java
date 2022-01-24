import java.util.*;
import java.io.*;
import java.util.stream.Collectors;

public class HangmanMain {
    public static void main (String args[]){
        String word="cat";
        //Using Set to remove duplicates
        HashSet<Character> wordToGuess=new HashSet<>(word.chars().mapToObj(i->(char)i).collect(Collectors.toList()));
        Scanner scan=new Scanner(System.in);

        System.out.println("Guess The Word");

        //checks if have enough guesses and if guessed
        int i=0;String missing="",lettersGuessed="";
        while(i<=7&&!wordToGuess.isEmpty()){
            String guess=scan.next();
            //get hanger, remove brackets and commas
            System.out.println(setHanger(i).toString().replace(",", "").replace("[", "").replace("]", "").trim());
            String letters=missing+"\nHas letters\n"+lettersGuessed+"\n";
            if(guess.equals(word)){
                wordToGuess.clear();
                break;
            }
            else if (wordToGuess.contains(guess.charAt(0))) { //get char
                wordToGuess.remove(guess.charAt(0));

                lettersGuessed = lettersGuessed+guess+" , ";
                System.out.println(missing+"\nHas letters\n"+lettersGuessed+"\n");
                System.out.println("You got a letter!");
            }
            else if(!wordToGuess.contains(guess.charAt(0))) {
                missing = missing + guess + " , ";
                i++;
                System.out.println(missing+"\nHas letters\n"+lettersGuessed+"\n");
                System.out.println("Sorry the word does not contain that letter!");
            }
        }
        if(wordToGuess.isEmpty()){
            System.out.println(missing+"\nHas letters\n"+lettersGuessed+"\n");
            System.out.println("You guessed the word! The word was: "+word);

        }
        else{
            System.out.println(missing+"\nHas letters\n"+lettersGuessed+"\n");
            System.out.println("Sorry you ran out of guesses! The word was: "+word);

        }
    }

    public static ArrayList<String> setHanger(Integer miss){
        HashMap<Integer,ArrayList<String>> hm=new HashMap<Integer,ArrayList<String>>();
        ArrayList<String> arrs= new ArrayList<String>();
        Collections.addAll(arrs,   "+---+",//0
                                            "\n    |" ,//1
                                            "\n    |" ,//2
                                            "\n    |" ,//3
                                            "\n    |" , //4
                                            "\n   ===" ,//5
                                            "\n" ,//6
                                            "\nMissing letters"); //7

        hm.put(0,new ArrayList<String>(arrs));
        arrs.set(1,"\n O  |"); hm.put(1, new ArrayList<String>(arrs));
        arrs.set(2,"\n |  |"); hm.put(2,new ArrayList<String>(arrs));
        arrs.set(2,"\n\\| |"); hm.put(3,new ArrayList<String>(arrs));
        arrs.set(2,"\n\\|/ |"); hm.put(4,new ArrayList<String>(arrs));
        arrs.set(3,"\n |  |"); hm.put(5,new ArrayList<String>(arrs));
        arrs.set(4,"\n  \\ |"); hm.put(6,new ArrayList<String>(arrs));
        arrs.set(4,"\n/ \\ |"); hm.put(7,new ArrayList<String>(arrs));

        return hm.get(miss);
    }
}
