package com.jml.gui;

import com.jml.dao.Goblin;
import com.jml.dao.Human;
import com.jml.dao.Humanoid;
import com.jml.dao.Land;

import java.util.*;

public interface UserInput {
    void handleTeamRemoval();

    public void printGrid();


    TreeMap<Integer,Land> setUpObjects();

    void handleTurns();

    public void setUpGrid();

    void addInitiative(int x, int y, Humanoid humanoid);

    Humanoid getHumanoidFromInitiativeName(String name);

    Object getHumanoidType(Humanoid humanoid);

    public boolean isDead(Humanoid humanoid);
    public void teamDead();

    TreeMap<Integer, Land> getInitiative();

    void setInitiative(TreeMap<Integer, Land> initiative);

    List<Human> getHumans();

    void setHumans(List<Human> humans);

    List<Goblin> getGoblins();

    void setGoblins(List<Goblin> goblins);
}
