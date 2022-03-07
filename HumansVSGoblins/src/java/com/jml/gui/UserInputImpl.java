package com.jml.gui;

import com.jml.dao.Goblin;
import com.jml.dao.Human;
import com.jml.dao.Humanoid;
import com.jml.dao.Land;
import com.jml.services.ActionsImpl;
import com.jml.services.DropTableImpl;

import java.util.*;

public class UserInputImpl implements UserInput {

    Land land = new Land();
    ActionsImpl actions = new ActionsImpl();
    DropTableImpl dt = new DropTableImpl();
    GridMapImpl grid=new GridMapImpl();

    //public TreeMap<Integer, HashMap<String, Humanoid>> turnOrder = new TreeMap<Integer, HashMap<String, Humanoid>>(Collections.reverseOrder());
    private TreeMap<Integer, Land> initiative=new TreeMap<>(Collections.reverseOrder());
    private List<Human> humans = new ArrayList<Human>(); private List<Goblin> goblins = new ArrayList<Goblin>();

    @Override
    public TreeMap<Integer, Land> getInitiative() {
        return initiative;
    }
    @Override
    public void setInitiative(TreeMap<Integer, Land> initiative) {
        this.initiative = initiative;
    }
    @Override
    public List<Human> getHumans() {
        return humans;
    }
    @Override
    public void setHumans(List<Human> humans) {
        this.humans = humans;
    }
    @Override
    public List<Goblin> getGoblins() {
        return goblins;
    }
    @Override
    public void setGoblins(List<Goblin> goblins) {
        this.goblins = goblins;
    }

    private int x = 0, y = 0;
    //public TreeMap<Integer, HashMap<String, Humanoid>> getTurnOrder(){
    //    return turnOrder;
    //}

    @Override
    public TreeMap<Integer,Land> setUpObjects() {
        System.out.println("Starting Game");
        //populate enemies and equip items
        //not yet implement enemies attack for turn based
        Goblin goblinMinion = new Goblin("goblinMinion",10, 0, 10); Goblin chefGob = new Goblin("chefGob", 10, 12, 15);
        dt.statIncrease("Rare Sword", chefGob);
        getGoblins().add(goblinMinion); getGoblins().add(chefGob);
        //add Humans
        Human player1 = new Human("Player 1", 15, 12, 18);
        dt.statIncrease("Legendary Sword", player1); dt.statIncrease("Legendary Armor", player1);
        getHumans().add(player1);
        //add to Map
        //land.initGrid(10,10);
        //initiate turn order
        addInitiative(1,1,goblinMinion); addInitiative(1,2,chefGob); addInitiative(4,4,player1);
        //set up GUI
        //setUpGrid();
        return getInitiative();
        //Turn Order
        //move and attack action allowed?
        //start turns, 1 move and 1 action per turn
        //handleTurns();
    }

    @Override
    public void handleTurns(){
        while (!getInitiative().isEmpty()) {
            for (Map.Entry<Integer, Land> queue : getInitiative().entrySet()) {
                //turn
            }
            //remove from team and turn order
            handleTeamRemoval();
            //handles battle end and drops chest
            teamDead();
            //new game?
        }
    }

    @Override
    public void setUpGrid(){
        grid.initGridLayout(5,5);
        grid.setGridHumanoids(getInitiative());
    }

    @Override
    public void handleTeamRemoval(){
        getInitiative().entrySet().stream().filter(hp->hp.getValue().getHumanoid().getHp()<=0).forEach(hu -> {
            System.out.println(hu.getValue().getHumanoid().getName() + " is Dead");
            getInitiative().remove(hu);
            if(hu.getValue().getHumanoid().getClass()==Goblin.class) {
                getGoblins().remove(hu.getValue().getHumanoid());
            }
            else if(hu.getValue().getHumanoid().getClass()==Human.class){
                getHumans().remove(hu.getValue().getHumanoid());
            }
            //remove from grid
            grid.removeFromGridLayout(hu.getValue().getX(), hu.getValue().getY());
        });
    }

//    @Override
//    public void choices(HashMap<String, Humanoid> next) {
//        //old print console code
//        Humanoid turn = next.entrySet().stream().iterator().next().getValue();
//        System.out.println(next.entrySet().stream().iterator().next().getKey() + "'s Turn");
//        printGrid();
//        if (turn.getClass().equals(Goblin.class)) {
//            ArrayList<String> randomize = new ArrayList<String>(getHumans().keySet());
//            String ran = randomize.get((int) Math.random() * getHumans().size());
//            //get random human
//            goblinAttack(next, getHumans().get(ran));
//        } else {
//            try {
//                Scanner scan = new Scanner(System.in);
//                System.out.println("\nSelect an Action: \n1. Attack? \n2. Move?");
//                int playerAction = scan.nextInt();
//                System.out.println("Select the Target:");
//                for (Map.Entry<String, Humanoid> g : getGoblins().entrySet()) {
//                    System.out.println(g.getKey());
//                }
//                String target = scan.next();
//                if (playerAction == 1 /*& canAttack(next, goblins.get(target))*/) {
//                    humanAttack(turn, getGoblins().get(target));
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }

    @Override
    public void printGrid(){
        land.printGrid();
    }

    @Override
    public void addInitiative(int x, int y, Humanoid humanoid){
        int rollInitiative=(int)(Math.random() * 19)+1;
        Land location=new Land(x,y,humanoid);
        initiative.put(rollInitiative,location);
    }

    @Override
    public Humanoid getHumanoidFromInitiativeName(String name){
        return getInitiative().entrySet().stream().filter(x->x.getValue().getHumanoid().getName()
                .equals(name)).findFirst().get().getValue().getHumanoid();
    }

    @Override
    public Object getHumanoidType(Humanoid humanoid){
        return getInitiative().entrySet().stream().filter(x->x.getValue().getHumanoid()
                .equals(humanoid)).findFirst().get().getValue().getHumanoid().getClass();
    }

    @Override
    public boolean isDead(Humanoid humanoid){
        if(humanoid.getHp()<=0){
            return true;
        }
        else return false;
    }

    @Override
    public void teamDead(){
        if (getGoblins().isEmpty()){
            System.out.println("Human Victory!");
            System.out.println("New Rewards: "+ dt.itemDrop());
            System.exit(0);
        }
        else if(getHumans().isEmpty()){
            System.out.println("Goblin Victory :(");
            System.exit(0);
        }
        else if(getHumans().isEmpty()&getGoblins().isEmpty()){
            System.out.println("Draw!");
            System.exit(0);
        }
    }
}
