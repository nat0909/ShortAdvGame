import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class SenarioTests {
    private Senario normalSenario;
    private Senario ambushSenario;
    
    @Before
    public void setUp() {
        String[] monsters = {"Goblin", "Orc", "Troll"};
        normalSenario = new Senario(monsters, false);
        ambushSenario = new Senario(monsters, true);
    }

    @Test
    public void testNormalSenarioCharacterPosition() {
        // Test that character starts at position 15 in normal scenario
        assertEquals(15, normalSenario.getPositions()[0]);
    }

    @Test
    public void testAmbushSenarioCharacterPosition() {
        // Test that character starts at position 25 in ambush scenario
        assertEquals(25, ambushSenario.getPositions()[0]);
    }

    @Test
    public void testCreaturesArrayContainsCharacter() {
        // Test that first element is "Character"
        assertEquals("Character", normalSenario.getCreatures()[0]);
    }

    @Test
    public void testMonstersPositionedCorrectly() {
        // Test monster positioning in normal scenario
        int[] positions = normalSenario.getPositions();
        assertTrue(positions[1] > positions[0]); // First monster should be ahead of character
        assertEquals(5, positions[2] - positions[1]); // Monsters should be 5 spaces apart
    }
}