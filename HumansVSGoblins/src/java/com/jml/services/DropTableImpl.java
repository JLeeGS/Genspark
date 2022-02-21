package com.jml.services;

import com.jml.dao.Humanoid;
import java.util.Random;

public class DropTableImpl implements DropTable{
    public int rare;
    public DropTableImpl(){
        super();
    }
    public DropTableImpl(int rare){
        this.rare=rare;
    }
    //chance based loot

    @Override
    public String getItemQuality(int rare){
        //stat increase
        if (rare==20){
            return "Legendary";
        }
        else if(rare==1){
            return "Unique";
        }
        else if(rare>=15){
            return "Rare";
        }
        else if(rare<15&rare>=5){
            return "Common";
        }
        else{
            return null;
        }
    }

    @Override
    public int itemQuality(String type){
        if (type.contains("Legendary")){
            return 8;
        }
        else if(type.contains("Unique")){
            return 6;
        }
        else if(type.contains("Rare")){
            return 4;
        }
        else if(type.contains("Common")){
            return 2;
        }
        else{
            return 0;
        }
    }

    @Override
    public String getItemType(int drop){
        Random ran= new Random();int random=ran.nextInt(21);
        String item="";
        if(drop>=15){
            item="Sword";
        }
        else if(drop>=10&&drop<=15){
            item="Accessory";
        }
        else{
            item="Armor";
        }
        return item;
    }

    @Override
    public String itemDrop(){
        Random ran= new Random();int random=ran.nextInt(21);
        return getItemQuality(random)+" "+ getItemType(random);
    }

    @Override
    public Humanoid statIncrease(String item, Humanoid humanoid){
        int itemQuality=itemQuality(item);
        if(item.contains("Sword")){
            humanoid.setStrength(humanoid.getStrength()+itemQuality);
            return humanoid;
        }
        else if(item.contains("Accessory")){
            humanoid.setIntelligence(humanoid.getIntelligence()+itemQuality);
            return humanoid;
        }
        else if(item.contains("Armor")){
            int ac=0;
            if (itemQuality==0){
                ac=0;
            }
            else{
                ac=itemQuality/2;
            }
            humanoid.setAc(humanoid.getAc()+ac);
            return humanoid;
        }
        else{
            //cannot equip
            return humanoid;
        }
    }
}
