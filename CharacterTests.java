import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import org.junit.Test;

public class CharacterTests {
    
    @Test
    public void testConstructorAndGetters() {
        Character c = new Character("Aria", "Rogue", "Elf", 25, 40, new ArrayList<>());

        assertEquals("Name: Aria\nClass: Rogue\nRace: Elf", c.getInfo());
        assertEquals(25, c.getHealth());
        assertEquals(40, c.getSpeed());
        assertEquals(1, c.getLevel()); // Initial level should be 1
    }

    @Test
    public void testSetHealthIncreasesWithinMax() {
        Character c = new Character("Aria", "Rogue", "Elf", 25, 40, new ArrayList<>());
        c.setHealth(4, false); // 25 + 4 = 29 → capped at maxHealth (25), so should reset to 25

        assertEquals(25, c.getHealth());
    }

    @Test
    public void testSetHealthIncreasesWithTempHealth() {
        Character c = new Character("Aria", "Rogue", "Elf", 25, 40, new ArrayList<>());
        c.setHealth(5, true); // allows going beyond maxHealth

        assertEquals(30, c.getHealth());
    }

    @Test
    public void testSetHealthDecreasesAboveZero() {
        Character c = new Character("Aria", "Rogue", "Elf", 25, 40, new ArrayList<>());
        c.setHealth(-10, false); // 25 - 10 = 15

        assertEquals(15, c.getHealth());
    }

    @Test
    public void testSetHealthDecreasesBelowZero() {
        Character c = new Character("Aria", "Rogue", "Elf", 10, 40, new ArrayList<>());
        c.setHealth(-15, false); // 10 - 15 = -5

        assertTrue(c.getHealth() <= 0); // May print death message, health remains at -5
    }

}
