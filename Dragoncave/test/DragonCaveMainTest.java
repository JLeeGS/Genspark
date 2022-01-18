import org.junit.jupiter.api.*;
import static org.junit.Assert.assertEquals;

public class DragonCaveMainTest {
    DragonCaveMain dc= new DragonCaveMain();

    @BeforeEach
    void setUp(){

    }

    @Test
    void getStart(){
        String s="Start test";
        dc.setStart(s);
        assertEquals("Start Msg Fail",s,dc.getStart());
    }

    @Test
    void getEnd1(){
        String e1="End1 Test";
        dc.setEnd1(e1);
        assertEquals("Ending 1 Fail",e1,dc.getEnd1());
    }

    @Test
    void getEnd2(){
        String e2="End2 Test";
        dc.setEnd2(e2);
        assertEquals("Ending 2 Fail",e2,dc.getEnd2());
    }

    @AfterEach
    void tearDown(){
        System.out.println("Tests Complete");
    }

}