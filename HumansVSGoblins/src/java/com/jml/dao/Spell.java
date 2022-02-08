package com.jml.dao;

import java.util.HashMap;

public class Spell {
    private String type;
    private int dmg;
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public int getDmg() {
        return dmg;
    }
    public void setDmg(int dmg) {
        this.dmg = dmg;
    }
    public Spell(){
        super();
    }
    public Spell(String type, int dmg){
        this.type=type;
        this.dmg=dmg;
    }
    public HashMap<String,HashMap<String,Integer>> getSpellList(){
        HashMap<String,HashMap<String,Integer>> spell=new HashMap<String,HashMap<String,Integer>>();
        HashMap<String,Integer> description=new HashMap<String,Integer>();
        description.put("Casts a Fireball for 10 damage", 10);spell.put("Fireball", description);

        return spell;
    }
    public HashMap<String, Integer> getSpell(String spell){
        return getSpellList().get(spell);
    }

    public Integer getSpellDamage(String spell){
        return getSpell(spell).get(getSpell(spell));
    }

    public String getSpellDescription(String spell){
        return getSpell(spell).keySet().toArray()[0].toString();
    }
}
