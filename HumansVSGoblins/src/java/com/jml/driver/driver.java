package com.jml.driver;

import com.jml.dao.Goblin;
import com.jml.dao.Human;
import com.jml.dao.Humanoid;
import com.jml.dao.Land;
import com.jml.gui.GridMapImpl;
import com.jml.services.ActionsImpl;
import com.jml.services.DropTableImpl;

import java.awt.event.ActionEvent;
import java.util.*;

public class driver {
    Land land = new Land();
    ActionsImpl actions = new ActionsImpl();
    DropTableImpl dt = new DropTableImpl();
    GridMapImpl grid=new GridMapImpl();
    private int x = 0, y = 0;

    public void setX(int x) {
        this.x = x;
    }
    public int getX() {
        return x;
    }
    public void setY(int y) {
        this.y = y;
    }
    public int getY() {
        return y;
    }

    public TreeMap<Integer, HashMap<String, Humanoid>> turnOrder = new TreeMap<Integer, HashMap<String, Humanoid>>(Collections.reverseOrder());
    public HashMap<String, Humanoid> humans = new HashMap<String, Humanoid>();
    public HashMap<String, Humanoid> goblins = new HashMap<String, Humanoid>();

    public static void main(String args[]) {
        driver start = new driver();
        start.newGame();
    }

    public void newGame() {
        //initialize everything
        setX(5);setY(5);
        testGame();
    }

    public void testGame() {
        //populate enemies and equip items
        //not yet implement enemies attack for turn based
        Goblin goblinMinion = new Goblin(10, 0, 10);
        Goblin chefGob = new Goblin(10, 12, 15);
        dt.statIncrease("Rare Sword", chefGob);
        goblins.put("goblinMinion", goblinMinion);
        goblins.put("chefGob", chefGob);
        //add Humans
        Human player1 = new Human(15, 12, 18);
        dt.statIncrease("Legendary Sword", player1);
        dt.statIncrease("Legendary Armor", player1);
        humans.put("Player 1", player1);
        //add to Map
        land.initGrid(10,10);
        land.getGrid().put(goblinMinion,land.setCoords(1, 1));
        land.getGrid().put(chefGob,land.setCoords(1, 2));
        land.getGrid().put(player1,land.setCoords(4, 4));
        //for(Map.Entry<Integer, Humanoid> entry: turnOrder.entrySet()){land.getGrid().put(entry.getValue(), land.setCoords((int)Math.random()*19,(int)Math.random()*19));}
        //initiate turn order
        addToTurnOrder(goblins);
        addToTurnOrder(humans);
        //set up GUI
        setGrid();
        //Turn Order
        System.out.println("Turn Order: ");
        turnOrder.forEach((n, m) -> {
            System.out.println(n + " " + m.entrySet().iterator().next().getKey() +
                    " Hp: " + m.entrySet().iterator().next().getValue().getHp());
        }); //view turnorder
        //move and attack action allowed
        //iterate through the treemap

        //start turns, 1 move and 1 action per turn
        while (!turnOrder.isEmpty()) {
            for (Map.Entry<Integer, HashMap<String, Humanoid>> queue : turnOrder.entrySet()) {
                choices(queue.getValue());
            }
            //remove from team and turn order
            goblins.entrySet().stream().filter(n -> isDead(n.getValue())).forEach(n -> {
                System.out.println(n.getKey() + " is Dead");
                turnOrder.remove(turnOrder.get(n)); //need to get initiative key
                goblins.remove(n.getKey());
                //remove from grid
                grid.removeFromGridLayout(land.getX(n.getKey()), land.getY(n.getKey()));
            });
            humans.entrySet().stream().filter(n -> isDead(n.getValue())).forEach(n -> {
                System.out.println(n.getKey() + " is Dead");
                turnOrder.remove(n.getKey());
                humans.remove(n.getKey());
                //remove from grid
                grid.removeFromGridLayout(land.getX(n.getKey()), land.getY(n.getKey()));
            });
            //handles battle end and drops chest
            teamDead();
            //new game?
        }
    }

    public void choices(HashMap<String, Humanoid> next) {
        Humanoid turn = next.entrySet().stream().iterator().next().getValue();
        System.out.println(next.entrySet().stream().iterator().next().getKey() + "'s Turn");
        printGrid();
        if (turn.getClass().equals(Goblin.class)) {
            ArrayList<String> randomize = new ArrayList<String>(humans.keySet());
            String ran = randomize.get((int) Math.random() * humans.size());
            //get random human
            goblinAttack(next, humans.get(ran));
        } else {
            try {
                Scanner scan = new Scanner(System.in);
                System.out.println("\nSelect an Action: \n1. Attack? \n2. Move?");
                int playerAction = scan.nextInt();
                System.out.println("Select the Target:");
                for (Map.Entry<String, Humanoid> g : goblins.entrySet()) {
                    System.out.println(g.getKey());
                }
                String target = scan.next();
                if (playerAction == 1 /*& canAttack(next, goblins.get(target))*/) {
                    humanAttack(turn, goblins.get(target));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void printGrid(){
        land.printGrid();
    }

    public void addToTurnOrder(HashMap<String, Humanoid> humanoid){
        //sort by initiative for each
        int i=0;
        for(Map.Entry<String, Humanoid> h:humanoid.entrySet()) {
            int rollInitiative=(int)(Math.random() * 19)+1;
            while(!turnOrder.navigableKeySet().contains(rollInitiative)) {
                turnOrder.put(rollInitiative, new HashMap<>(Map.of(h.getKey(), h.getValue())));
            }
        }
    }

    public boolean isDead(Humanoid humanoid){
        if(humanoid.getHp()<=0){
            return true;
        }
        else return false;
    }

    public void teamDead(){
        if (goblins.isEmpty()){
            System.out.println("Human Victory!");
            System.out.println("New Rewards: "+ dt.itemDrop());
            System.exit(0);
        }
        else if(humans.isEmpty()){
            System.out.println("Goblin Victory :(");
            System.exit(0);
        }
        else if(humans.isEmpty()&goblins.isEmpty()){
            System.out.println("Draw!");
            System.exit(0);
        }
    }

    public boolean canAttack(Humanoid attacker, Humanoid attacked){
        //opponent is position getx>=+1 , getx>=+1
        return actions.canAttack(attacker,attacked,land);
    }

    public void humanAttack(Humanoid attacker, Humanoid attacked){
        actions.attack(attacker, attacked);
    }

    public void goblinAttack(HashMap<String, Humanoid> attacker, Humanoid attacked){
        //AI for attacker
        actions.goblinAttack(attacker,attacked,land);
    }

    public void setGrid(){
        grid.initGridLayout(getX(),getY());
        grid.setGridHumanoids(humans, land);
        grid.setGridHumanoids(goblins, land);
    }

    public void selectBtn(Humanoid attacker, Humanoid attacked, ActionEvent e){
        if(e.getSource()==grid.getActionBtn()){
            humanAttack(attacker, attacked);
        }
        else if(e.getSource()==grid.getMoveBtn()){
            if(actions.canAttack(attacker, attacked, land)){
            }
        }
    }
}
