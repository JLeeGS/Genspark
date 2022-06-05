import org.junit.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

public class ProjectTests {
    int[]arr1= {1,5,6,7,9,2,10}, arr2= {10,7,4,13,24,7,8};
    List<Integer> li=new ArrayList<>(Arrays.asList(101,30,21,9,2,10,12,4,7,34,57)),
            li2=new ArrayList<>(Arrays.asList(105,205,3,4,12,84,12,37,70,23));

    @BeforeEach
    void setup(){
        System.out.println("Begin Tests");
    }
    @Test
    void mergeTwoSorted(){
        Arrays.sort(arr1); Arrays.sort(arr2);
        int[]narr=new int[arr1.length+arr2.length];
        //narr= IntStream.concat(Arrays.stream(arr1),Arrays.stream(arr2)).toArray();
        List<Integer> mergedLi= li; mergedLi.addAll(li2);
        List<Integer> mergedLi2=Arrays.asList(li,li2).stream().flatMap(List::stream).collect(Collectors.toList());
        Collections.sort(mergedLi2);
        System.out.println(mergedLi2);
    }
    @Test
    void subArrGivenSum(){
        int currSum = 0; int sum=12; int i=0, p=0;
        for(i=0; i<arr1.length; i++){
            for(int j=0; j<=arr1.length; j++) {
                //sub array
                currSum = arr1[i];
                if (currSum == sum) {
                    p=j-1;
                    System.out.println("Sum from: "+p+":"+i+" indices");
                    System.exit(0);
                }
                if (currSum > sum || j== arr1.length){
                    break;
                }
                currSum=currSum +arr1[j];
            }
        }

    }
    @Test
    void zigzagArr(){
        int size=arr1.length,side=size/3, mid=side/2;
        for(int i=0; i<size;i++){
            for (int x = 0; x < side; x++) {
                System.out.print(arr1[i] + " ");
                for (int y = 0; y < mid; y++) {
                    System.out.println(arr1[i]);
                }
            }
        }
    }

    @Test
    void romanNumeralsToInteger() {
        String s = "XLIV";
        HashMap<Integer,String> map= new HashMap<Integer,String>(Map.of(0,"",1,"I"));
        String first = s.substring(0), sub="";
        int index = 0;
        String diff = "";
        for (int i = 0; i < s.length(); i++) {
            if (first != s.substring(i)) {
                diff = s.substring(i);
                index = i;
            }
            else{
                diff=first;
                index=i;
            }
            sub = s.substring(index);
        }
        int tens=0, ones=0;
        for(Map.Entry m: map.entrySet()){
            if(first.equals(m)){
               tens=(int)m.getKey()*index;
            }
            if(sub.equals(m)){
               ones=(int)m.getKey();
            }
        }
        int total=tens-ones;
    }

    @Test
    void reverseString(){
        String s="madam",s2="testing";
        String ns="";
        for(int i=0;i<s2.length(); i++){
            ns+=s2.charAt(s2.length()-i-1);
        }
        System.out.println(ns);
    }

    @Test
    void anagram(){
        String s1="spring", s2="gripsn";
        char[]c1= s1.toCharArray(), c2=s2.toCharArray();
        Arrays.sort(c1); Arrays.sort(c2);
        System.out.println(Arrays.equals(c1,c2));
        //use treemap
    }

    @Test
    void minStackEle(){
        Stack s=new Stack<>(); int e=0,currMin=0;
    }

    @Test
    void rotate(){
        List<Integer> li =new ArrayList<>(Arrays.asList(1,2,3,4,5,6));
        int x=3; Integer eleTop=0;
            for(int i=0; i<x; i++) {
              eleTop=li.remove(li.size()-1);
              li.add(0,eleTop);
           }
        System.out.println(li);
    }

    @Test
    void combineArrsAsList(){
        List <Integer> li = new ArrayList<>();
        li.addAll(Arrays.stream(arr1).boxed().collect(Collectors.toList())); li.addAll(Arrays.stream(arr2).boxed().collect(Collectors.toList())); //add to list
        TreeSet<Integer> set = li.stream().collect(Collectors.toCollection(TreeSet::new)); //remove dups and sort
        System.out.println(set);
    }

    @AfterEach
    void teardown(){
        System.out.println("Tests Complete");
    }
}
