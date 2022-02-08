package com.jml.dao;

import java.util.HashMap;
import java.util.List;

public class Inventory {
    private int space, weight;
    private int gold=0;
    public int getSpace() {
        return space;
    }
    public void setSpace(int space){
        this.space=space;
    }
    public int getWeight() {
        return weight;
    }
    public void setWeight(int weight) {
        this.weight = weight;
    }
    public int getGold() {
        return gold;
    }
    public void setGold(int gold) {
        this.gold = gold;
    }
    public List<String> items;
    public Inventory(){
        super();
    }
    public Inventory(int space, int weight, int gold, List<String> items) {
        //temp set for testing game rules
        space=20;weight=10000;gold=0;

        this.space = space;
        this.weight = weight;
        this.gold= gold;
        this.items=items;
    }
}
