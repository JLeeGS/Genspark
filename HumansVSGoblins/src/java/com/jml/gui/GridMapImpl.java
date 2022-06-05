package com.jml.gui;
import com.jml.dao.Goblin;
import com.jml.dao.Human;
import com.jml.dao.Humanoid;
import com.jml.dao.Land;
import com.jml.services.ActionsImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class GridMapImpl implements GridMap {
    private JButton attackBtn= new JButton();
    private JButton moveBtn= new JButton();
    JButton[][] gridButtons;
    public JTextField coordInputX=new JTextField();
    public JTextField coordInputY=new JTextField();
    private JTextArea notifyText=new JTextArea();

    private TreeMap<Integer,Land> gameInitiative;
    public TreeMap<Integer, Land> getGameInitiative() {
        return gameInitiative;
    }
    public void setGameInitiative(TreeMap<Integer, Land> gameInitiative) {
        this.gameInitiative = gameInitiative;
    }

    @Override
    public JTextArea getNotifyText(){
        return notifyText;
    }
    @Override
    public void setNotifyText(JTextArea notifyText){
        this.notifyText=notifyText;
    }
    @Override
    public JButton getActionBtn(){
        return attackBtn;
    }
    @Override
    public JButton getMoveBtn(){
        return moveBtn;
    }
    @Override
    public JButton getGridButtons(int x, int y){ return gridButtons[x][y]; }
    @Override
    public JButton[][] getGridButtons(){ return gridButtons; }
    @Override
    public void setGridButtons(JButton[][] gridButtons, int x, int y){this.gridButtons=new JButton[x][y]; }
    @Override
    public void setOneGridBtn(JButton gridBtn, int x, int y){this.gridButtons[x][y]=gridBtn;}

    public void initGridLayout(int col, int row) {
        JFrame frame=new JFrame();
        frame.setTitle("HumansVSGoblins");
        JPanel titlePanel= new JPanel();
        JPanel buttonPanel= new JPanel();
        JPanel actionPanel= new JPanel();
        JPanel notifyPanel= new JPanel();
        JLabel titleField= new JLabel();

        JScrollPane scroll;

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,500);
        frame.setBackground(Color.blue);
        frame.setLayout(new GridLayout(col, row, 10, 10));
        frame.setVisible(true);

        titleField.setBackground(new Color(25,25,25));
        titleField.setHorizontalAlignment(JLabel.CENTER);
        titleField.setOpaque(true);
        titleField.setFont(new Font("Arial", Font.BOLD, 50));
        titleField.setText("Humans VS Goblins");
        titlePanel.setLayout(new BorderLayout());
        titlePanel.setBounds(0,0, 800,100);
        titlePanel.add(titleField);
        frame.add(titlePanel, BorderLayout.NORTH);

        buttonPanel.setLayout(new GridLayout(col, row));
        buttonPanel.setBackground(new Color(150,150,150));
        buttonPanel.setName("buttonPanel");
        frame.add(buttonPanel);

        setGridButtons(gridButtons, col, row);
        for(int x=0; x<col; x++){
            for(int y=0; y<row; y++){
                gridButtons[x][y]=new JButton();
                gridButtons[x][y].setBounds(60,50,50,50);
                gridButtons[x][y].setFont(new Font("Arial", Font.BOLD, 8));
                gridButtons[x][y].addActionListener(this::setTextCoords);
                gridButtons[x][y].setSize(10,10);
                gridButtons[x][y].setText("("+x+" , "+y+")");
                gridButtons[x][y].setName("("+x+" , "+y+")");
                buttonPanel.add(gridButtons[x][y]);
            }
        }

        actionPanel.setLayout(new GridLayout(1, 2));
        actionPanel.setBackground(new Color(150,150,150));
        attackBtn.setText("Attack"); attackBtn.addActionListener(this::btnClicked);
        moveBtn.setText("Move"); moveBtn.addActionListener(this::btnClicked);
        coordInputX.setText("0");coordInputY.setText("0");
        actionPanel.add(coordInputX);actionPanel.add(coordInputY);
        actionPanel.add(attackBtn);actionPanel.add(moveBtn);
        frame.add(actionPanel);

        notifyPanel.setLayout(new GridLayout(1, 1));
        notifyPanel.setBackground(new Color(100,100,100));
        notifyPanel.setBounds(0,0, 800,800);
        notifyText.setBounds(0,0,50,20);
        notifyText.setText("Welcome!");
        notifyText.setEditable(false);
        scroll = new JScrollPane(notifyText);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        frame.add(scroll);
    }

    @Override
    public void setGridLayout(String name, int x, int y) {
        gridButtons[x][y].setText(name);
    }

    @Override
    public void removeFromGridLayout(int x, int y){
        gridButtons[x][y].setText("("+x+" , "+y+")");
    }

    @Override
    public void moveToGridBtn(String name, int x, int y){
        setGridLayout(name,x,y);
        removeFromGridLayout(x,y);
    }

    @Override
    public void setGridHumanoids(TreeMap<Integer, Land> type){
        type.forEach((k,v)-> setGridLayout(v.getHumanoid().getName(),v.getX(),v.getY()));
    }

    @Override
    public ArrayList<Humanoid> getAllHumanoids() {
        UserInputImpl userInput=new UserInputImpl();
        ArrayList<Humanoid> allHumanoids= new ArrayList<>();
        allHumanoids.addAll(userInput.getHumans()); allHumanoids.addAll(userInput.getGoblins());
        return allHumanoids;
    }

    @Override
    public Humanoid getGridHumanoid(String name){
        UserInput userInput=new UserInputImpl();
        return userInput.getHumanoidFromInitiativeName(name);
    }

    @Override
    public void setTextCoords(ActionEvent e){
        JButton btn= (JButton) e.getSource(); UserInputImpl userInput=new UserInputImpl();
            String btnName=btn.getName();
            String x = btnName.substring(1, 2);
            String y = btnName.substring(btnName.length() - 2, btnName.length() - 1);
            coordInputX.setText(x);
            coordInputY.setText(y);
        System.out.println("Coordinates Clicked: "+btn.getName());
    }

    public Humanoid getHumanoidSelected(int x,int y){
        return gameInitiative.entrySet().stream().filter(m->(m.getValue().getX()==x)&(m.getValue().getY()==y))
                .findFirst().get().getValue().getHumanoid();
    }

    @Override
    public void btnClicked(ActionEvent e){
        JButton btn= (JButton) e.getSource();UserInput userInput=new UserInputImpl();
        ActionsImpl actions= new ActionsImpl();
        LinkedHashMap<Integer,Land> queue=new LinkedHashMap<>(); queue.putAll(gameInitiative);
        Land turn = null;
        Set<Humanoid> humanoidSet=gameInitiative.entrySet().stream().map(Map.Entry::getValue).map(x->x.getHumanoid()).collect(Collectors.toSet());
            Set<Land> landSet=gameInitiative.entrySet().stream().map(Map.Entry::getValue).collect(Collectors.toSet());
                List<Land> humanList=landSet.stream().filter(x->x.getHumanoid().getClass().equals(Human.class)).collect(Collectors.toList());
            boolean humansAlive = humanoidSet.stream().anyMatch(x -> x.getClass().equals(Human.class));
            boolean goblinsAlive = humanoidSet.stream().anyMatch(x -> x.getClass().equals(Goblin.class));
            if (humansAlive & !goblinsAlive) {
                System.out.println("Victory! Humans win!");
            } else if (goblinsAlive & !humansAlive) {
                System.out.println("Defeat! Goblins win!");
            } else if (humanoidSet.isEmpty()) {
                System.out.println("DRAW Both Humans and Goblins are dead!");
            }
            turn = gameInitiative.entrySet().stream().iterator().next().getValue();
//                if (turn.getHumanoid().getClass().equals(Goblin.class)) {
//                    Land poorHuman = humanList.get((int) (Math.random() % humanList.size()));
//                    actions.goblinAttack(turn, poorHuman);
//                    String notify = turn.getHumanoid().getName() + " Attempting to attack... " + poorHuman.getHumanoid().getName();
//                    System.out.println(notify);
//                    notifyText.append("\n" + notify);
//                }

            if (btn.equals(getActionBtn())) {
                try {
                    int coordsX = Integer.parseInt(coordInputX.getText());
                    int coordsY = Integer.parseInt(coordInputY.getText());
                    Humanoid attacked = getHumanoidSelected(coordsX, coordsY);
                    String notify = turn.getHumanoid().getName() + " Attacking... " + attacked.getName();
                    System.out.println(notify);
                    notifyText.append("\n" + notify);
                    actions.attack(turn.getHumanoid(), attacked);
                    if (attacked.getHp() <= 0) {
                        getGridButtons()[coordsX][coordsY].setText("(" + coordsX + " , " + coordsY + ")");
                        gameInitiative.remove(turn);
                        gameInitiative.remove(attacked);
                    } else if (turn.getHumanoid().getHp() <= 0) {
                        getGridButtons()[turn.getX()][turn.getY()].setText("(" + turn.getX() + " , " + turn.getY() + ")");
                        gameInitiative.remove(turn);
                        gameInitiative.remove(attacked);
                    }
                } catch (Exception noHumanoidHere) {
                    String notify = "No Humanoid at: (" + coordInputX.getText() + "," + coordInputY.getText() + ")";
                    System.out.println(notify);
                    notifyText.append("\n" + notify);
                }
            } else if (btn.equals(getMoveBtn())) {
                try {
                    String notify = "Moving From: (" + turn.getX() + "," + turn.getY() + ") To:(" + coordInputX.getText() + "," + coordInputY.getText() + ")...";
                    System.out.println(notify);
                    notifyText.append("\n" + notify);
                    if (actions.canMove(turn, Integer.parseInt(coordInputX.getText()), Integer.parseInt(coordInputY.getText()))) {
                        removeFromGridLayout(turn.getX(), turn.getY());//old position remove
                        setGridLayout(turn.getHumanoid().getName(), turn.getX(), turn.getY());//new position place
                        gameInitiative.remove(turn);
                        getGridButtons()[turn.getX()][turn.getY()].setText(turn.getHumanoid().getName());
                    }
                } catch (Exception moveFailed) {
                    String notify = "Cannot Move!";
                }
            }

        queue.entrySet().stream().forEach(m->{System.out.println(m.getKey()+" "+m.getValue().getHumanoid().getName());});
        //turnInititive.forEach(t->{System.out.println(t.getKey()+" "+t.getValue().getHumanoid().getName());});
    }

    public void getGui(){
        UserInputImpl userInput=new UserInputImpl();
        initGridLayout(5,5); TreeMap<Integer,Land> initiative=userInput.setUpObjects();
        setGameInitiative(initiative);setGridHumanoids(initiative);
        gameInitiative.entrySet().stream().forEach(m->{System.out.println(m.getKey()+" "+m.getValue().getHumanoid().getName());});
    }
}
