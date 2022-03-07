package com.jml.gui;

import com.jml.dao.Humanoid;
import com.jml.dao.Land;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

public interface GridMap {
    JTextArea getNotifyText();
    public void setNotifyText(JTextArea notifyText);
    public JButton getActionBtn();
    public JButton getMoveBtn();
    public JButton getGridButtons(int x, int y);
    JButton[][] getGridButtons();

    public void setGridButtons(JButton[][] gridButtons, int x, int y);
    public void setOneGridBtn(JButton gridBtn, int x, int y);
    public void initGridLayout(int col, int row);
    public void setGridLayout(String name, int x, int y);
    public void removeFromGridLayout(int x, int y);
    public void moveToGridBtn(String name, int x, int y);
    //public void setGridHumanoids(HashMap<String, Humanoid> type, Land land);

    void setGridHumanoids(TreeMap<Integer, Land> type);

    public ArrayList<Humanoid> getAllHumanoids();

    Humanoid getGridHumanoid(String name);

    void setTextCoords(ActionEvent e);

    public void btnClicked(ActionEvent e);
}
