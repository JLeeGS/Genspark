package com.jml.gui;

import com.jml.dao.Humanoid;
import com.jml.dao.Land;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.HashMap;

public interface GridMap {
    public JButton getActionBtn();
    public JButton getMoveBtn();
    public JButton getGridButtons(int x, int y);
    public void setGridButtons(JButton[][] gridButtons, int x, int y);
    public void setOneGridBtn(JButton gridBtn, int x, int y);
    public void initGridLayout(int col, int row);
    public void setGridLayout(String name, int x, int y);
    public void removeFromGridLayout(int x, int y);
    public void moveToGridBtn(String name, int x, int y);
    public void setGridHumanoids(HashMap<String, Humanoid> type, Land land);
    public void buttonClicked(ActionEvent e);
}
