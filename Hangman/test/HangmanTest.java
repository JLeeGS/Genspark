import org.junit.jupiter.api.*;
import java.util.ArrayList;
import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class HangmanTest {

    @BeforeEach
    void setUp(){
        System.out.println("Starting Test");
    }

    @Test
    void startHanger(){
        ArrayList<String> testarrs= new ArrayList<String>();
        Collections.addAll(testarrs,   "+---+",//0
                "\n    |" ,//1
                "\n    |" ,//2
                "\n    |" ,//3
                "\n    |" , //4
                "\n   ===" ,//5
                "\n" ,//6
                "\nMissing letters"); //7
        assertEquals(testarrs,HangmanMain.setHanger(0));
    }

    @Test
    void lastHanger(){
        ArrayList<String> testarrs= new ArrayList<String>();
        Collections.addAll(testarrs,   "+---+",//0
                "\n O  |" ,//1
                "\n\\|/ |" ,//2
                "\n |  |" ,//3
                "\n/ \\ |" , //4
                "\n   ===" ,//5
                "\n" ,//6
                "\nMissing letters"); //7
        assertEquals(testarrs,HangmanMain.setHanger(7));
    }
    @Test
    void lastHangerFile(){
        ArrayList<String> testarrs= new ArrayList<String>();
        Collections.addAll(testarrs,   "+---+",//0
                " O  |" ,//1
                "\\|/ |" ,//2
                " |  |" ,//3
                "/ \\ |" , //4
                "   ===" //5
               ); //6
        assertEquals(HangmanMain.setHangerFile(7).subList(0,5), testarrs.subList(0,5));
    }

    @AfterEach
    void tearDown(){
        System.out.println("Tests Complete");
    }
}
