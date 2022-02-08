package com.jml.services;

import com.jml.dao.Humanoid;
import com.jml.dao.Inventory;
import com.jml.dao.Land;
import com.jml.dao.Spell;

import java.util.HashMap;

public interface Actions {

    public void move(Land land, Object selected, int x, int y);
    public int attack(Humanoid attacker, Humanoid attacked);
    public Humanoid loot(Humanoid inv, String loot) ;
    public Humanoid equip(Humanoid equipping, Inventory inv, String item);
    public String spell(String type);
    public int cast(Humanoid attacked, Humanoid attacker, String spell);
}
