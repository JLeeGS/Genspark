package com.jml.services;

import com.jml.dao.*;
import java.util.*;

public class ActionsImpl implements Actions {
    public ActionsImpl(){
        super();
    }

    @Override
    public void move(Land land, Object selected, int x, int y) {
        HashMap<Object,TreeMap<Integer,Integer>> willMove = land.getGrid();
        TreeMap<Integer,Integer> newCoords= land.setCoords(x,y);

        int movement= Math.abs((int)Math.sqrt(land.getX(selected)^2- x^2) -(land.getY(selected)^2- y^2));
        if(((Humanoid) selected).getSpeed()-movement>=0&!land.isOccupied(x,y)){
            land.getGrid().remove(selected); //old position
            land.getGrid().put(selected, newCoords); //new position
        }
        else{
            //cannot move, illegal speed
        }
    }

    @Override
    public int attack(Humanoid attacker, Humanoid attacked) {
        int roll=(int)(Math.random()*19+1);int mod=(attacker.getStrength()/3);int total=roll+mod;
        if(roll==20){
            attacked.setHp((attacked.getHp() - mod*2));
            System.out.println("NAT 20! Rolled: "+total+"+ You Dealt"+mod*2+" dmg");
        }
        else if(total>10&attacked.getAc()<total){
            //goes to defender for AC
            attacked.setHp(attacked.getHp() - mod);
            System.out.println("You rolled: "+total+" and dealt "+mod+" dmg");
        }
        else if(roll==1){
            attacker.setHp(attacker.getHp()-1);
            System.out.println("NAT 1! rolled: "+total+" YOU TAKE 1 dmg");
        }
        else{
            System.out.println("You rolled: "+total+ " You Missed!");
            if(attacked.getAc()<total){
               System.out.print("AC is higher, Enemy Defended!");
            }
        }
        return attacked.getHp();
    }

    @Override
    public Humanoid loot(Humanoid inv, String loot) {
        Inventory inventory=new Inventory();
        if(inventory.items.size()!=inv.getInventory().getSpace())
            inv.getInventory().items.add(loot);
        return inv;
    }

    @Override
    public Humanoid equip(Humanoid equipping, Inventory inv, String item) {
        DropTableImpl dropTable=new DropTableImpl();
        if(inv.items.contains(item)){
            dropTable.statIncrease(item,equipping);
        }
        return equipping;
    }

    @Override
    public String spell(String type) {
        Spell spell=new Spell();
        return spell.getSpellDescription(type);
    }

    @Override
    public int cast(Humanoid attacked, Humanoid attacker, String cast){
        Spell spell=new Spell();
        attacked.setHp(attacked.getHp()- spell.getSpellDamage(cast));
        return attacked.getHp();
    }

    @Override
    public boolean canAttack(Humanoid attacker, Humanoid attacked, Land land){
        //opponent is position getx>=+1 , getx>=+1
        int xAttacked=land.getX(attacked); int yAttacked=land.getY(attacked);
        int xAttacker= land.getX(attacker); int yAttacker= land.getY(attacker);
        if((xAttacked==xAttacker+1)|(yAttacked==yAttacker+1)|(xAttacked==xAttacker-1)|(yAttacked==yAttacker-1)
                &yAttacked-1!=0&xAttacked-1!=0){ //need to make sure one square apart but not coords 0,0
            return true;
        }//System.out.println(xAttacked+" "+yAttacked+"\n "+xAttacker+" "+yAttacked);
        return false;
    }

    @Override
    public void goblinAttack(HashMap<String, Humanoid> attacker, Humanoid attacked, Land land){
        //AI for attacker
        int hpold=attacked.getHp();
        if(canAttack(attacker.entrySet().stream().iterator().next().getValue(), attacked, land)){
            attack(attacker.entrySet().stream().iterator().next().getValue(),attacked);
            int hp=attacked.getHp()-hpold;
            System.out.println(attacker.entrySet().stream().iterator().next().getKey()+" Attacked Human for "+hp+" dmg!");
        }
        else{
            int x=land.getX(attacked)+1;
            int y=land.getX(attacked);
            move(land, attacker.entrySet().iterator().next().getValue(), x, y);
            System.out.println(attacker.entrySet().stream().iterator().next().getKey()+ " is Moving to "+x+" "+y);
        }
    }

}
