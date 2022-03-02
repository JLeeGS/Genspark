package com.jml.gui;

import com.jml.dao.Goblin;
import com.jml.dao.Human;
import com.jml.dao.Humanoid;
import com.jml.dao.Land;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

public interface UserInput {
    void handleTeamRemoval();

    //public void choices(HashMap<String, Humanoid> next);
    public void printGrid();
    public void setUpGrid();

    void addInitiative(int x, int y, Humanoid humanoid);

    Humanoid getHumanoidFromInitiativeName(String name);

    Humanoid getHumanoidFromInitiative(Humanoid name);

    public boolean isDead(Humanoid humanoid);
    public void teamDead();
    boolean canAttack(Humanoid attacker, Humanoid attacked);
    public void humanAttack(Humanoid attacker, Humanoid attacked);
    public void goblinAttack(HashMap<String, Humanoid> attacker, Humanoid attacked);

    TreeMap<Integer, Land> getInitiative();

    void setInitiative(TreeMap<Integer, Land> initiative);

    List<Human> getHumans();

    void setHumans(List<Human> humans);

    List<Goblin> getGoblins();

    void setGoblins(List<Goblin> goblins);

    public void testGame();

}
