import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;

public class SenarioTests {
    private Senario normalSenario;
    private Senario ambushSenario;
    
    @Before
    public void setUp() {
        Creature[] monsters = new Creature[3];
        Character character = new Character("", "", "", 30, 6, new ArrayList<>()); // TODO: fill params
        monsters[0] = new Goblin(1, 1);
        monsters[1] = new Goblin(2, 2);
        monsters[2] = new Goblin(2, 3);
        
        normalSenario = new Senario(monsters, character, false);
        ambushSenario = new Senario(monsters, character, true);
    }

    @Test
    public void testNormalSenarioCharacterPosition() {
        assertEquals(15, normalSenario.getPositions()[0]);
    }

    @Test
    public void testAmbushSenarioCharacterPosition() {
        assertEquals(25, ambushSenario.getPositions()[0]);
    }

    @Test
    public void testNormalMonstersPositioning() {
        int[] positions = normalSenario.getPositions();
        // All monsters should be in front of character
        for (int i = 1; i < positions.length; i++) {
            assertTrue(positions[i] > positions[0]);
        }
        // Check spacing between monsters
        assertEquals(5, positions[2] - positions[1]);
        assertEquals(5, positions[3] - positions[2]);
    }

    @Test
    public void testAmbushMonstersPositioning() {
        int[] positions = ambushSenario.getPositions();
        // Check alternating positions (front/back)
        assertTrue(positions[1] > positions[0]); // First monster in front
        assertTrue(positions[2] < positions[0]); // Second monster behind
        assertTrue(positions[3] > positions[0]); // Third monster in front
    }

    @Test
    public void testArrayLengths() {
        assertEquals(4, normalSenario.getCreatures().length);
        assertEquals(4, normalSenario.getPositions().length);
        assertEquals(4, ambushSenario.getCreatures().length);
        assertEquals(4, ambushSenario.getPositions().length);
    }
}