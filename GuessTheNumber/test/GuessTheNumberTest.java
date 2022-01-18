import org.junit.jupiter.api.*;
import static org.junit.Assert.assertEquals;

public class GuessTheNumberTest {
    GuessTheNumberMain gn=new GuessTheNumberMain();

    @BeforeEach
    void setUp(){

    }

    @Test
    void getName(){
        String n="Name Test";
        gn.setName(n);
        assertEquals("Start Msg Fail",n,gn.getName());
    }
    @Test
    void getRandom(){
        int r=30;
        gn.setRandom(r);
        assertEquals("Start Msg Fail",r,gn.getRandom());
    }
    @Test
    void getGuess(){
        int g=25;
        gn.setGuess(g);
        assertEquals("Start Msg Fail",g,gn.getGuess());
    }

    @AfterEach
    void tearDown(){
        System.out.println("Tests Complete");
    }

}