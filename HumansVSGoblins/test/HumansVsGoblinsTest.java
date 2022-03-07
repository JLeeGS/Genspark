import com.jml.dao.*;
import com.jml.gui.GridMapImpl;
import com.jml.gui.UserInput;
import com.jml.services.DropTableImpl;
import com.jml.gui.UserInputImpl;
import org.junit.Assert;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

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
        Human steve=new Human("Steve",10, 10,10,10,10,10,10,10,10);
        assertEquals(steve.getCharisma(), 10);
        Goblin gobby=new Goblin("Gobby", 10,15,10);
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
    void getHumanoidList(){
        UserInput userInput=new UserInputImpl();
        //Human human=new Human("Steve",10,10,10);userInput.getHumans().add(human);
        //Goblin goblin=new Goblin("Gobve",10,10,10);userInput.getGoblins().add(goblin);
        userInput.setUpObjects();
        ArrayList<Humanoid> allHum=new ArrayList<>(); allHum.addAll(userInput.getHumans()); allHum.addAll(userInput.getGoblins());
        allHum.forEach(hu->System.out.println(hu));
    }

    @Test
    void turnOrder(){
        UserInputImpl userInput=new UserInputImpl();
        //Humanoid namedHumanoid=new Human();namedHumanoid.setName("Billy");
        //user.addInitiative(1,2,namedHumanoid); Land land=new Land(1,2,namedHumanoid);
        //assertEquals(user.getHumanoidFromInitiative(namedHumanoid).getName(), "Billy");
        userInput.setUpObjects();
        System.out.println(userInput.getInitiative());
    }

    @Test
    void getGridHumanoid(){
        UserInput userInput=new UserInputImpl();
        userInput.setUpObjects();
        System.out.println(userInput.getHumanoidFromInitiativeName("Player 1"));
    }

    @Test
    void getBtnTxt(){
        GridMapImpl grid=new GridMapImpl();
        UserInput userInput=new UserInputImpl(); userInput.setUpObjects();
        String name= grid.getGridButtons()[0][1].getText();
        System.out.println(name);
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

    @Test
    void getInitiative(){
        UserInputImpl userInput=new UserInputImpl();
        userInput.setUpObjects();
        System.out.println(userInput.getInitiative());
    }

    @Test
    void containsObject() {
        Land land=new Land(1,2,new Goblin());
        UserInput userInput=new UserInputImpl();
        //assertEquals(userInput.containsHumanoidType(userInput.setUpObjects(), Human.class), true);
        TreeMap<Integer,Land> testMap= new TreeMap<Integer, Land>(Map.of(10,new Land(1,1,new Goblin())));
        assertEquals(testMap.entrySet().stream().anyMatch(x->x.getValue().getHumanoid().getClass().equals(Goblin.class)),true);
    }

    @AfterEach
    void tearDown(){
        System.out.println("Tests Complete");
    }
}
