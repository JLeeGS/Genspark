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
            land.setGrid(selected, newCoords); //new position
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

}
