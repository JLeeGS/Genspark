package com.jml.gui;

import com.jml.dao.Goblin;
import com.jml.dao.Human;
import com.jml.dao.Humanoid;
import com.jml.dao.Land;
import com.jml.services.ActionsImpl;
import com.jml.services.DropTableImpl;

import java.awt.event.ActionEvent;
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
    public void testGame() {
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
        land.initGrid(10,10);
        //land.getGrid().put(goblinMinion,land.setCoords(1, 1));land.getGrid().put(chefGob,land.setCoords(1, 2));land.getGrid().put(player1,land.setCoords(4, 4));
        //initiate turn order
        addInitiative(1,1,goblinMinion); addInitiative(1,2,chefGob); addInitiative(4,4,player1);
        //set up GUI
        setUpGrid();
        //Turn Order
        //move and attack action allowed?

        //start turns, 1 move and 1 action per turn
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

//    public void addToTurnOrder(HashMap<String, Humanoid> humanoid){
//        //sort by initiative for each
//        int i=0;
//        for(Map.Entry<String, Humanoid> h:humanoid.entrySet()) {
//            int rollInitiative=(int)(Math.random() * 19)+1;
//            while(!getTurnOrder().navigableKeySet().contains(rollInitiative)) {
//                getTurnOrder().put(rollInitiative, new HashMap<>(Map.of(h.getKey(), h.getValue())));
//            }
//        }
//    }

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
    public Humanoid getHumanoidFromInitiative(Humanoid name){
        return getInitiative().entrySet().stream().filter(x->x.getValue().getHumanoid()
                .equals(name)).findFirst().get().getValue().getHumanoid();
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

    @Override
    public boolean canAttack(Humanoid attacker, Humanoid attacked){
        //opponent is position getx>=+1 , getx>=+1
        return actions.canAttack(attacker,attacked,land);
    }

    @Override
    public void humanAttack(Humanoid attacker, Humanoid attacked){
        actions.attack(attacker, attacked);
    }
    @Override
    public void goblinAttack(HashMap<String, Humanoid> attacker, Humanoid attacked){
        //AI for attacker
        actions.goblinAttack(attacker,attacked,land);
    }

    public void playerAction(ActionEvent e, Humanoid attacker, Humanoid attacked, Land land) {
        GridMapImpl grid=new GridMapImpl(); ActionsImpl actions=new ActionsImpl();
        if (e.getSource() == grid.getActionBtn()) {
            //grid.notifyText.append(actions.attack(attacker, land.getHumanoid(land.getCoords(attacked))));
            grid.getNotifyText().setText("Test");
        }
            else if(e.getSource()== grid.getMoveBtn()){
            grid.getNotifyText().append(actions.move(land, attacker, grid.coordInputX.getX(), grid.coordInputY.getY()));
        }
    }
}
