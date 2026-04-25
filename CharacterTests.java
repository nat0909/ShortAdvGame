import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Test;

public class CharacterTests {
    
    private static ArrayList<String> createBasicActions() {
        ArrayList<String> actions = new ArrayList<>();
        actions.add("Move");
        actions.add("End turn");
        actions.add("Fireball");
        actions.add("Lightening strike");
        actions.add("Thunder wave");
        actions.add("Illusion");
        actions.add("Relocate");
        return actions;
    }

    public static Character sampleCharacter() {
        return new Character("Aria", "Wizard", "Elf", 20, 8, createBasicActions());
    }
    
    @Test
    public void testConstructorAndGetters() {
        Character c = sampleCharacter();
        String expected = "Name: Aria\nClass: Wizard\nRace: Elf\nHealth: 20/20\nSpeed: 8\nLevel: 1\nOptions: " + createBasicActions().toString();
        assertEquals(expected, c.getInfo());
        assertEquals(20, c.getHealth());
        assertEquals(8, c.getSpeed());
        assertEquals(1, c.getLevel());
        assertArrayEquals(createBasicActions().toArray(), c.getOptions());
    }

    @Test
    public void testGetOptions() {
        Character c = sampleCharacter();
        assertArrayEquals(createBasicActions().toArray(), c.getOptions());
    }

    @Test
    public void testSetHealthIncreasesWithinMax() {
        Character c = sampleCharacter();
        c.setHealth(4, false);
        assertEquals(20, c.getHealth());
    }

    @Test
    public void testSetHealthIncreasesWithTempHealth() {
        Character c = sampleCharacter();
        c.setHealth(5, true);
        assertEquals(25, c.getHealth());
        String expected = "Name: Aria\nClass: Wizard\nRace: Elf\nHealth: 25/20\nSpeed: 8\nLevel: 1\nOptions: " + createBasicActions().toString();
        assertEquals(expected, c.getInfo());
    }

    @Test
    public void testSetHealthDecreasesAboveZero() {
        Character c = sampleCharacter();
        c.setHealth(-10, false);
        assertEquals(10, c.getHealth());
    }

    @Test
    public void testSetHealthDecreasesBelowZero() {
        Character c = sampleCharacter();
        c.setHealth(-25, false);
        assertTrue(c.getHealth() <= 0);
    }

}
