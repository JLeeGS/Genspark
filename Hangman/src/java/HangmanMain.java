import java.util.*;
import java.io.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class HangmanMain {
    public static void main (String args[]){
        String word="cat";
        //Using Set to remove duplicates
        HashSet<Character> wordToGuess=new HashSet<>(word.chars().mapToObj(i->(char)i).collect(Collectors.toList()));
        Scanner scan=new Scanner(System.in);

        System.out.println("Enter your name: ");
        String name=scan.next();

        System.out.println("Guess The Word, You have 7 Guesses");
        //checks if have enough guesses and if guessed
        int i=0;String missing="",lettersGuessed="";
        while(i<=7&&!wordToGuess.isEmpty()){
            int guesses=7-i;
            String guess=scan.next();
            //get hanger, remove brackets and commas
            //System.out.println(setHanger(i).toString().replace(",", "").replace("[", "").replace("]", "").trim());
            setHangerFile(i).forEach(l->System.out.println(l));
            String letters=missing+"\nHas letters\n"+lettersGuessed+"\nGuesses Left: "+guesses;
            if(guess.equals(word)){
                wordToGuess.clear();
                break;
            }
            else if (wordToGuess.contains(guess.charAt(0))) { //get char
                wordToGuess.remove(guess.charAt(0));

                lettersGuessed = lettersGuessed+guess+" , ";
                System.out.println(missing+"\nHas letters\n"+lettersGuessed+"\nGuesses Left: "+guesses);
                System.out.println("You got a letter!");
            }
            else if(!wordToGuess.contains(guess.charAt(0))) {
                missing = missing + guess + " , ";
                i++;
                System.out.println(missing+"\nHas letters\n"+lettersGuessed+"\nGuesses Left: "+guesses);
                System.out.println("Sorry the word does not contain that letter!");
            }
        }
        String result;
        if(wordToGuess.isEmpty()){
            result = missing + "\nHas letters\n" + lettersGuessed + "\nYou guessed the word! The word was: " + word;
        }
        else{
            result = missing + "\nHas letters\n" + lettersGuessed + "\nSorry you ran out of guesses! The word was: " + word;
        }
        System.out.println(result);
        resultsOutputToFile(result,name);
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
        arrs.set(1,"\n O  |"); hm.put(1,new ArrayList<String>(arrs));
        arrs.set(2,"\n |  |"); hm.put(2,new ArrayList<String>(arrs));
        arrs.set(2,"\n\\|  |"); hm.put(3,new ArrayList<String>(arrs));
        arrs.set(2,"\n\\|/ |"); hm.put(4,new ArrayList<String>(arrs));
        arrs.set(3,"\n |  |"); hm.put(5,new ArrayList<String>(arrs));
        arrs.set(4,"\n  \\ |"); hm.put(6,new ArrayList<String>(arrs));
        arrs.set(4,"\n/ \\ |"); hm.put(7,new ArrayList<String>(arrs));

        return hm.get(miss);
    }

    public static ArrayList<String> setHangerFile(Integer miss){
        HashMap<Integer,String> hm=new HashMap<Integer,String>();ArrayList arrs=new ArrayList<>();
        int i=0;
        try {
            BufferedReader br = new BufferedReader(new FileReader("resources/hanger.txt"));
            Stream s= br.lines();
            s.forEach(arrs::add);
            br.close();
        }
        catch(IOException ioe){
            ioe.printStackTrace();
        }
        int hang=miss; int x=0;

        if(miss==0) {
            arrs = new ArrayList(arrs.subList(hang * 10, hang * 10 + 8));
        }
        else{
            arrs = new ArrayList(arrs.subList(hang * 10-1, hang * 10 + 8));
        }

        return arrs;
    }
    public static void resultsOutputToFile(String result, String name){
        try{
            String writing= "Name: "+name+ "\nResult: "+result;
            BufferedWriter bw= new BufferedWriter(new FileWriter("resources/results.txt"));
            bw.write(writing);
            bw.close();
        }
        catch(IOException ioe){
            ioe.printStackTrace();
        }
    }

}
