import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class InterviewQuestion1 {
    public static void main (String args[]) throws IOException {
        InterviewQuestion1 iq=new InterviewQuestion1();
        ArrayList<Call> callRec=iq.readACall("src/resources/calls.csv");
        Set<String> allNumbers= new HashSet<>(); callRec.stream().forEach(call -> {allNumbers.add(call.getCalling());});
        Set<String> match=allNumbers.stream().filter(c->(c.startsWith("(0")&c.contains(")")
                        |(c.startsWith("140")&(!c.contains("(")&!c.contains(")")))
                        |((c.startsWith("7")|c.startsWith("8")|c.startsWith("9"))&(!c.contains("(")&!c.contains(")"))&(c.contains(" ")))))
                .collect(Collectors.toCollection(TreeSet::new));
        //StringBuilder sb=new StringBuilder();
        //match.forEach(l->sb.append(l.substring(0,4)+" , "));
        //if contains () get numbers, else split from space
        Set<String> unique=new TreeSet<>();
        for(String s:match){
                if(s.startsWith("(0")&s.contains(")")){
                    int start = s.indexOf("(") + 1, end = s.indexOf(")");
                    unique.add(s.substring(start, end));
                }
                else if ((s.startsWith("7")|s.startsWith("8")|s.startsWith("9"))&(!s.contains("(")&!s.contains(")"))&(s.contains(" "))) {
                    unique.add(s.substring(0, 4));
                }
        }
        unique.add("140");
        //Set<Integer> dictionary=new TreeSet<>(); unique.forEach(x->dictionary.add(Integer.valueOf(x)));
        String[] lex= new String[unique.size()];
        int i=0;
        for(String s:unique){
            lex[i++]=s;
        }
        Arrays.sort(lex, String.CASE_INSENSITIVE_ORDER);
        String result = "";
        for(int j = 0; j < lex.length; j++){
            if(j != lex.length - 1){
                result += lex[j] + " ";
            }
            else {
                result += lex[j];
            }
        }
        System.out.println(iq.solution());
    }


    public ArrayList<Call> readAFile(String path){
        ArrayList<Call> arrs=new ArrayList<>();
        try{
            File file=new File(path);
            Scanner scan= new Scanner(file);
            while(scan.hasNextLine()){
                String[] attr= scan.nextLine().split(",");
                 String calling= attr[0];
                 String receiving=attr[1];
                 String start=attr[2];
                 String duration="0";
                 Call c=new Call(calling, receiving, start, duration);
                 arrs.add(c);
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
        return arrs;
    }

    public ArrayList<Call> readACall(String path){
        ArrayList<Call> arrs=new ArrayList<>();
        try{
            File file=new File(path);
            Scanner scan= new Scanner(file);
            while(scan.hasNextLine()){
                String[] attr= scan.nextLine().split(",");
                String calling= attr[0];
                String receiving=attr[1];
                String start=attr[2];
                String duration=attr[3];
                Call c=new Call(calling, receiving, start, duration);
                arrs.add(c);
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
        return arrs;
    }
    public ArrayList<Call> readAText(String path){
        ArrayList<Call> arrs=new ArrayList<>();
        try{
            File file=new File(path);
            Scanner scan= new Scanner(file);
            while(scan.hasNextLine()){
                String[] attr= scan.nextLine().split(",");
                String calling= attr[0];
                String receiving=attr[1];
                String start=attr[2];
                String duration="0";
                Call c=new Call(calling, receiving, start, duration);
                arrs.add(c);
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
        return arrs;
    }

    public Set<String> duplicates(String path){
        Set<String> set=new HashSet<>();
        try{
            File file=new File(path);
            Scanner scan= new Scanner(file);
            while(scan.hasNextLine()){
                String [] ln= scan.nextLine().split(",");
                String calling=ln[0];
                String receiving=ln[1];
                set.add(calling);set.add(receiving);
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }

        return set;
    }

    public String solution() throws IOException {
        // ↓↓↓↓ your code goes here ↓↓↓↓
        Set<String> bangaloreCalls = new HashSet<>();
        File callFile = new File("src/resources/calls.csv");
        FileReader cfr = new FileReader(callFile);
        BufferedReader cbr = new BufferedReader(cfr);
        String callLine;
        while((callLine = cbr.readLine()) != null){
            String[] callLineArr = callLine.split(",");
            String number = callLineArr[0];
            if(number.contains("(0")){
                String[] numArr = number.split("[()]");
                bangaloreCalls.add(numArr[1]);
            }
            else if((number.charAt(0) == '7' || number.charAt(0) == '8' || number.charAt(0) == '9') && number.contains(" ")){
                String prefixString = "";
                for(int i = 0; i < 4; i++){
                    prefixString += number.charAt(i);
                }
                bangaloreCalls.add(prefixString);
            }
            else if(number.charAt(0) == '1' && number.charAt(1) == '4' && number.charAt(2) == '0'){
                bangaloreCalls.add("140");
            }
        }
        String[] bangArr = new String[bangaloreCalls.size()];
        int i = 0;
        for(String number: bangaloreCalls){
            bangArr[i++] = number;
        }
        Arrays.sort(bangArr, String.CASE_INSENSITIVE_ORDER);
        String result = "";
        for(int j = 0; j < bangArr.length; j++){
            if(j != bangArr.length - 1){
                result += bangArr[j] + " ";
            }
            else {
                result += bangArr[j];
            }
        }
        return result;
    }
}
