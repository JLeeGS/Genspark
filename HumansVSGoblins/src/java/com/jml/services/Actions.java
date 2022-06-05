package com.jml.services;

import com.jml.dao.Humanoid;
import com.jml.dao.Inventory;
import com.jml.dao.Land;
import com.jml.dao.Spell;

import javax.swing.*;
import java.util.HashMap;

public interface Actions {

    //public String move(Land land, Object selected, int x, int y);
    boolean canMove(Land selected, int x, int y);

    public String attack(Humanoid attacker, Humanoid attacked);
    public Humanoid loot(Humanoid inv, String loot) ;
    public Humanoid equip(Humanoid equipping, Inventory inv, String item);
    public String spell(String type);
    public int cast(Humanoid attacked, Humanoid attacker, String spell);
    public boolean canAttack(Land attacker, Land attacked);
    public String goblinAttack(Land attacker, Land attacked);
}
