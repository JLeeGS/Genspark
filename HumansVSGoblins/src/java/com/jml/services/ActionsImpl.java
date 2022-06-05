package com.jml.services;

import com.jml.dao.*;
import com.jml.gui.GridMapImpl;

import javax.swing.*;
import java.util.*;

public class ActionsImpl implements Actions {
    public ActionsImpl(){
        super();
    }

//    @Override
//    public String move(Land land, Object selected, int x, int y) {
//        HashMap<Object,TreeMap<Integer,Integer>> willMove = land.getGrid();
//        TreeMap<Integer,Integer> newCoords= land.setCoords(x,y);
//
//        int movement= Math.abs((int)Math.sqrt(land.getX(selected)^2- x^2) -(land.getY(selected)^2- y^2));
//        if(((Humanoid) selected).getSpeed()-movement>=0&!land.isOccupied(x,y)){
//            land.getGrid().remove(selected); //old position
//            land.getGrid().put(selected, newCoords); //new position
//        }
//        return "Moved to X: "+x+" Y: "+y;
//    }
    @Override
    public boolean canMove(Land selected, int x, int y) {
        GridMapImpl grid=new GridMapImpl();Land land=new Land();JButton[][] gridBtns=grid.getGridButtons();
        int movement= Math.abs((int)Math.sqrt(selected.getX()^2- x^2) -(selected.getY()^2- y^2));
        if(selected.getHumanoid().getSpeed()-movement>=0&(gridBtns[x][y].getText()!=gridBtns[x][y].getName())){ //if can move and not occupied
            return true;
        }
        return false;
    }

    @Override
    public String attack(Humanoid attacker, Humanoid attacked) {
        String outcome="";
        int roll=(int)(Math.random()*19+1);int mod=(attacker.getStrength()/3);int total=roll+mod;
        if(roll==20){
            attacked.setHp((attacked.getHp() - mod*2));
            outcome="NAT 20! Rolled: "+total+"+ You Dealt"+mod*2+" dmg";
        }
        else if(total>10&attacked.getAc()<total){
            //goes to defender for AC
            attacked.setHp(attacked.getHp() - mod);
            outcome="You rolled: "+total+" and dealt "+mod+" dmg";
        }
        else if(roll==1){
            attacker.setHp(attacker.getHp()-1);
            outcome="NAT 1! rolled: "+total+" YOU TAKE 1 dmg";
        }
        else{
            System.out.println("You rolled: "+total+ " You Missed!");
            if(attacked.getAc()<total){
               outcome="AC is higher, Enemy Defended!";
            }
        }
        System.out.println(outcome);
        return outcome;
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
    public boolean canAttack(Land attacker, Land attacked){
        //opponent is position getx>=+1 , getx>=+1
        int xAttacked=attacked.getX(); int yAttacked=attacked.getY();
        int xAttacker=attacked.getX(); int yAttacker= attacked.getY();
        if((xAttacked==xAttacker+1)|(yAttacked==yAttacker+1)|(xAttacked==xAttacker-1)|(yAttacked==yAttacker-1)
                &yAttacked-1!=0&xAttacked-1!=0){ //need to make sure one square apart but not coords 0,0
            return true;
        }//System.out.println(xAttacked+" "+yAttacked+"\n "+xAttacker+" "+yAttacked);
        return false;
    }

    @Override
    public String goblinAttack(Land attacker, Land attacked){
        //AI for attacker
        String notify="";
        int hpold=attacked.getHumanoid().getHp();
        if(canAttack(attacker, attacked)){
            attack(attacker.getHumanoid(),attacked.getHumanoid());
            int hp=attacked.getHumanoid().getHp()-hpold;
            notify=attacker.getHumanoid().getName()+" Attacked Human for "+hp+" dmg!";
            System.out.println(notify);
        }
        else{
            int x=attacked.getX(attacked)+1;
            int y=attacked.getY(attacked);
            canMove(attacker, x, y);
            notify=attacker.getHumanoid().getName()+ " Is Moving to ("+x+","+y+")";
            System.out.println(notify);
        }
        return notify;
    }
}
