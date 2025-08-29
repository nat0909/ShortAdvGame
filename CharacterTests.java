import static org.junit.Assert.*;

import java.util.ArrayList;
import org.junit.Test;

public class CharacterTests {
    
    private ArrayList<String> createBasicActions() {
        ArrayList<String> actions = new ArrayList<>();
        actions.add("move");
        actions.add("end turn");
        actions.add("fireball");
        actions.add("lightening strike");
        actions.add("thunder wave");
        actions.add("illusion");
        actions.add("relocate");
        return actions;
    }
    
    @Test
    public void testConstructorAndGetters() {
        ArrayList<String> actions = createBasicActions();
        Character c = new Character("Aria", "Wizard", "Elf", 20, 8, actions);

        String expected = "Name: Aria\nClass: Wizard\nRace: Elf\nHealth: 20/20\nSpeed: 8\nLevel: 1";
        assertEquals(expected, c.getInfo());
        assertEquals(20, c.getHealth());
        assertEquals(8, c.getSpeed());
        assertEquals(1, c.getLevel());
        assertArrayEquals(new String[]{"move", "end turn", "fireball", "lightening strike", "thunder wave", "illusion", "relocate"}, c.getOptions());
    }

    @Test
    public void testGetOptions() {
        ArrayList<String> actions = new ArrayList<>();
        actions.add("move");
        actions.add("end turn");
        actions.add("mana shield");
        actions.add("illusion");
        actions.add("invisibility");
        actions.add("quick patch");
        actions.add("melee attack");
        actions.add("raise shield");
        
        Character c = new Character("Test", "Eldritch knight", "Human", 28, 6, actions);
        assertArrayEquals(new String[]{"move", "end turn", "mana shield", "illusion", "invisibility", "quick patch", "melee attack", "raise shield"}, c.getOptions());
    }

    @Test
    public void testSetHealthIncreasesWithinMax() {
        Character c = new Character("Aria", "Warlock", "Elf", 20, 8, createBasicActions());
        c.setHealth(4, false);
        assertEquals(20, c.getHealth());
    }

    @Test
    public void testSetHealthIncreasesWithTempHealth() {
        Character c = new Character("Aria", "Cleric", "Elf", 24, 8, createBasicActions());
        c.setHealth(5, true);
        assertEquals(29, c.getHealth());
        String expected = "Name: Aria\nClass: Cleric\nRace: Elf\nHealth: 29/24\nSpeed: 8\nLevel: 1";
        assertEquals(expected, c.getInfo());
    }

    @Test
    public void testSetHealthDecreasesAboveZero() {
        Character c = new Character("Aria", "Warlock", "Elf", 20, 8, createBasicActions());
        c.setHealth(-10, false);
        assertEquals(10, c.getHealth());
    }

    @Test
    public void testSetHealthDecreasesBelowZero() {
        Character c = new Character("Aria", "Wizard", "Elf", 20, 8, createBasicActions());
        c.setHealth(-25, false);
        assertTrue(c.getHealth() <= 0);
    }

}
