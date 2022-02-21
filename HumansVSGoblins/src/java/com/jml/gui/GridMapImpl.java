package com.jml.gui;
import com.jml.dao.Humanoid;
import com.jml.dao.Land;
import com.jml.services.ActionsImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GridMapImpl implements GridMap {
    private JButton attackBtn= new JButton();
    private JButton moveBtn= new JButton();
    JButton[][] gridButtons;

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
    public void setGridButtons(JButton[][] gridButtons, int x, int y){this.gridButtons=new JButton[x][y]; }
    @Override
    public void setOneGridBtn(JButton gridBtn, int x, int y){this.gridButtons[x][y]=gridBtn;}

    public void initGridLayout(int col, int row) {
        ActionsImpl actions =new ActionsImpl();
        JFrame frame=new JFrame();
        frame.setTitle("HumansVSGoblins");
        JPanel titlePanel= new JPanel();
        JPanel buttonPanel= new JPanel();
        JPanel actionPanel= new JPanel();
        JLabel titleField= new JLabel();
        JPanel notifyPanel= new JPanel();


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
        frame.add(buttonPanel);

        setGridButtons(gridButtons, col, row);
        for(int x=0; x<col; x++){
            for(int y=0; y<row; y++){
                gridButtons[x][y]=new JButton();
                gridButtons[x][y].setBounds(60,30,30,30);
                gridButtons[x][y].setFont(new Font("Arial", Font.BOLD, 120));
                gridButtons[x][y].addActionListener(this::buttonClicked);
                gridButtons[x][y].setSize(10,10);
                buttonPanel.add(gridButtons[x][y]);
            }
        }

        actionPanel.setLayout(new GridLayout(1, 2));
        actionPanel.setBackground(new Color(150,150,150));
        attackBtn.setText("Attack"); attackBtn.addActionListener(this::buttonClicked);
        moveBtn.setText("Move"); moveBtn.addActionListener(this::buttonClicked);
        actionPanel.add(attackBtn);actionPanel.add(moveBtn);
        frame.add(actionPanel);

        notifyPanel.setLayout(new GridLayout(1, 1));
        notifyPanel.setBackground(new Color(100,100,100));
    }

    @Override
    public void setGridLayout(String name, int x, int y) {
        gridButtons[x][y].setText(name);
    }

    @Override
    public void removeFromGridLayout(int x, int y){
        gridButtons[x][y].setText("");
    }

    @Override
    public void moveToGridBtn(String name, int x, int y){
        setGridLayout(name,x,y);
        removeFromGridLayout(x,y);
    }

    @Override
    public void setGridHumanoids(HashMap<String, Humanoid> type, Land land){
        type.entrySet().forEach(humanoid->
                setGridLayout(humanoid.getKey(), land.getX(humanoid.getValue()), land.getY(humanoid.getValue())));
    }

    @Override
    public void buttonClicked(ActionEvent e){
        JButton btnSelected= (JButton) e.getSource();
        System.out.println("button clicked: "+ btnSelected.getText());
    }

}
