package com.jml.services;

import com.jml.dao.Humanoid;

public interface DropTable {

    public String getItemQuality(int rare);
    public int itemQuality(String type);
    public String getItemType(int drop);
    public String itemDrop();
    public Humanoid statIncrease(String item, Humanoid humanoid);
}
