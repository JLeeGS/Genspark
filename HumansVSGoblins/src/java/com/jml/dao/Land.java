package com.jml.dao;

import java.util.*;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.regex.*;

public class Land {
    private HashMap<Object,TreeMap<Integer,Integer>> grid;
    public Land land;
    public Land(){
        super();
    }

    public TreeMap<Integer,Integer> setCoords(int x, int y){
        TreeMap<Integer,Integer> coords= new TreeMap<Integer,Integer>();
        coords.put(x,y);
        return coords;
    }
    public TreeMap<Integer,Integer> getCoords(Object obj){
        return getGrid().get(obj);
    }

    public boolean isOccupied(int xaxis, int yaxis){
        TreeMap<Integer,Integer> coords=new TreeMap<Integer,Integer>();
        coords.put(xaxis,yaxis);
        return getGrid().values().contains(coords);
    }

    public int getX(Object obj){
        return getCoords(obj).keySet().iterator().next();
    }

    public int getY(Object obj){
        return getCoords(obj).values().iterator().next();
    }

    public void initGrid(int xaxis, int yaxis){
        //Y     initialize grid
        //1     O   O   O   O   O   O   O   O   O   0

        //2     O   O   O   O   O   O   O   O   O   0

        //3     O   O   O   O   O   O   O   O   O   0

        //4     O   O   O   O   O   O   O   O   O   0

        //5     O   O   O   O   O   O   O   O   O   0

        //6     O   O   O   O   O   O   O   O   O   0

        //7     O   O   O   O   O   O   O   O   O   0

        //8     O   O   O   O   O   O   O   O   O   0

        //9     O   O   O   O   O   O   O   O   O   0

        //10    O   O   O   O   O   O   O   O   O   0
        //------------------------------------------------------
        //X     1   2   3   4   5   6   7   8   9   10

        HashMap<Object,TreeMap<Integer,Integer>> grid= new HashMap<Object,TreeMap<Integer,Integer>>();
            //10x10 grid for testing
//            for(int i=1; i<=yaxis;i++){
//                for(int e=1; e<=xaxis; e++){
//                    grid.put(null,setCoords(e,i));
//                }
//            }
//        //null -nothing there
//        //Obstacle -obstacle like a wall --impl later
//        //Object - human or goblin
        setGrid(grid);
    }

    public void initGrid(HashMap<Object,TreeMap<Integer,Integer>> newMap, int xaxis, int yaxis){
        for(int i=1; i<=yaxis;i++){
            for(int e=1; e<=xaxis; e++){
                newMap.put(null, setCoords(e,i));
            }
        }
        setGrid(newMap);
    }

    public void initGrid(){
        HashMap<Object,TreeMap<Integer,Integer>> grid= new HashMap<Object,TreeMap<Integer,Integer>>();
        setGrid(grid);
    }

    public void setGrid(HashMap<Object,TreeMap<Integer,Integer>> grid){
        this.grid=grid;
    }
    public void setGrid(Object obj, TreeMap<Integer,Integer> coords){getGrid().put(obj, coords);}
    public HashMap<Object,TreeMap<Integer,Integer>> getGrid(){
        return grid;
    }

    public void moveGrid(Object obj, int x, int y){
        //d=root of x-x^2-y-y^2
        HashMap<Object,TreeMap<Integer,Integer>> willMove = getGrid();
        Human human=(Human)obj;

        //each grid=5speed
        int movement= Math.abs((int)Math.sqrt(getX(obj)^2- x^2) -(getY(obj)^2- y^2));
        if(((Humanoid) obj).getSpeed()-movement>=0){
           setGrid(obj, setCoords(x,y));
        }
        else{
            //cannot move, illegal speed
        }
    }

    public void printGrid() {
        HashMap<Object,TreeMap<Integer,Integer>> grid = getGrid();
        Set<Object> obj= grid.keySet();
        Collection<TreeMap<Integer,Integer>> coords = grid.values();
        //print -|-, and every object, replace with G or H or ?

        int length=0;
        for(Map.Entry entry: getGrid().entrySet()) {
            //System.out.println(entry.getKey());
            if (entry.getKey().getClass().equals(Goblin.class)) {
                System.out.print(" -G- "+"("+getX(entry.getKey())+" , "+getY(entry.getKey())+")");
            } else if (entry.getKey().getClass().equals(Human.class)) {
                System.out.print(" -H- "+"("+getX(entry.getKey())+" , "+getY(entry.getKey())+")");
            } else {
                System.out.print(" "+ entry.getKey() +" ");
            }
//            length++;
//            if (length == 10) {
//                System.out.println();
//                length=0;
//            }
        }
    }
}
