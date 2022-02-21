import com.jml.dao.*;
import com.jml.driver.driver;
import com.jml.gui.GridMapImpl;
import com.jml.services.DropTableImpl;
import org.junit.jupiter.api.*;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class HumansVsGoblinsTest {
    @BeforeEach
    void setUp(){
        System.out.println("Starting Test");
    }

    @Test
    void getGoblin(){
        Goblin goblin=new Goblin();
        assertEquals(goblin.getClass(), new Goblin().getClass());
    }

    @Test
    void getHuman(){
        Human human=new Human();
        human.setHp(10);
        assertEquals(human.getClass(), new Human().getClass());
        assertEquals(human.getHp(), 10);
    }

    @Test
    void setHumanoid(){
        Human steve=new Human(10, 10,10,10,10,10,10,10,10);
        assertEquals(steve.getCharisma(), 10);
        Goblin gobby=new Goblin(10,15,10);
        assertEquals(gobby.getAc(), 15);
    }

    @Test
    void spell(){
        Spell cast=new Spell();
        HashMap<String,HashMap<String,Integer>> testSpellList=new HashMap<>();HashMap<String,Integer> description=new HashMap<>();
        description.put("Casts a Fireball for 10 damage", 10); testSpellList.put("Fireball", description);
        assertEquals(cast.getSpellList(), testSpellList);
    }

    @Test
    void inventory(){
        Inventory inv=new Inventory();
        inv.setGold(100); inv.setSpace(10); inv.setWeight(1000);
        assertEquals(inv.getGold(), 100);assertEquals(inv.getSpace(), 10);assertEquals(inv.getWeight(), 1000);
    }

    @Test
    void turnOrder(){
        driver dr=new driver();
        HashMap<String, Humanoid> namedHumanoid=new HashMap<>();
        namedHumanoid.put("JerryOne",new Human());
        dr.addToTurnOrder(namedHumanoid);
        assertTrue(dr.turnOrder.containsValue(namedHumanoid));
    }

    @Test
    void equipItems(){
        DropTableImpl dt=new DropTableImpl();
        Human humanExperienced=new Human();
        humanExperienced.setStrength(10);
        dt.statIncrease("Rare Sword", humanExperienced); //+4
        assertEquals(humanExperienced.getStrength(), 14);
    }

    @Test
    void gridGui(){
        GridMapImpl grid=new GridMapImpl();
        grid.initGridLayout(10,10);
        grid.setGridLayout("testName", 3, 5);
        assertEquals(grid.getGridButtons(3,5).getText(),"testName");
    }

    @AfterEach
    void tearDown(){
        System.out.println("Tests Complete");
    }
}
