package com.jml.dao;


import java.util.Map;

public class Humanoid {
    private int hp, speed, ac;
    private int strength, dexterity, constitution, intelligence, wisdom, charisma;
    private Inventory inventory;
    private String name;
    public Humanoid(){
        super();
    }

    public Humanoid(int hp, int speed, int ac, int strength, int dexterity, int constitution, int intelligence, int wisdom, int charisma){
        this.hp = hp;
        this.speed = speed;
        this.ac = ac;
        this.strength = strength;
        this.dexterity = dexterity;
        this.constitution = constitution;
        this.intelligence = intelligence;
        this.wisdom = wisdom;
        this.charisma = charisma;
    }
    public Humanoid(String name, int hp, int speed, int ac, int strength, int dexterity, int constitution, int intelligence, int wisdom, int charisma){
        this.hp = hp;
        this.speed = speed;
        this.ac = ac;
        this.strength = strength;
        this.dexterity = dexterity;
        this.constitution = constitution;
        this.intelligence = intelligence;
        this.wisdom = wisdom;
        this.charisma = charisma;
    }

    public String getName(){return name;}

    public void setName(String name){this.name=name;}

    public Humanoid(Inventory inventory){
        this.inventory= inventory;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getDexterity() {
        return dexterity;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    public int getConstitution() {
        return constitution;
    }

    public void setConstitution(int constitution) {
        this.constitution = constitution;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public int getWisdom() {
        return wisdom;
    }

    public void setWisdom(int wisdom) {
        this.wisdom = wisdom;
    }

    public int getCharisma() {
        return charisma;
    }

    public void setCharisma(int charisma) {
        this.charisma = charisma;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getAc() {
        return ac;
    }

    public void setAc(int ac) {
        this.ac = ac;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }
}
