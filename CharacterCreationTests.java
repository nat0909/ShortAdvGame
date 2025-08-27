import static org.junit.Assert.*;
import org.junit.Test;
import java.io.ByteArrayInputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Scanner;

public class CharacterCreationTests {

    @Test
    public void testValidCharacterCreation() {
        String input = "Aragorn\nwarlock\nhuman\ny\nquick patch\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(inputStream);

        Character c = CharacterCreation.create(scanner);
        String expected = "Name: Aragorn\nClass: Warlock\nRace: Human";
        assertEquals(expected, c.getInfo());
    }

    @Test
    public void testSuggestClassAccepted() {
        String input = "Luna\nwzr\ny\nelf\ny\nquick patch\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(inputStream);

        Character c = CharacterCreation.create(scanner);
        String expected = "Name: Luna\nClass: Warlock\nRace: Elf";
        assertEquals(expected, c.getInfo());
    }

    @Test
    public void testSuggestRejectedThenAccepted() {
        String input = "Kira\nwarloc\nn\nwarlock\nelf\ny\nquick patch\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(inputStream);

        Character c = CharacterCreation.create(scanner);
        String expected = "Name: Kira\nClass: Warlock\nRace: Elf";
        assertEquals(expected, c.getInfo());
    }

    @Test
    public void testBlankName() {
        String input = "\nwarlock\nhuman\ny\nquick patch\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(inputStream);

        Character c = CharacterCreation.create(scanner);
        String expected = "Name: \nClass: Warlock\nRace: Human";
        assertEquals(expected, c.getInfo());
    }

    @Test
    public void testInvalidClassThenValid() {
        String input = "Lyra\nnecromancer\nwizard\ny\nhuman\ny\nillusion\n"; // Invalid class first
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(inputStream);

        Character c = CharacterCreation.create(scanner);
        String expected = "Name: Lyra\nClass: Wizard\nRace: Human";
        assertEquals(expected, c.getInfo());
    }

    @Test
    public void testInvalidRaceThenValid() {
        String input = "Thorn\nwarlock\norc\ndwarf\ny\nquick patch\n"; // Invalid race first
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(inputStream);

        Character c = CharacterCreation.create(scanner);
        String expected = "Name: Thorn\nClass: Warlock\nRace: Dwarf";
        assertEquals(expected, c.getInfo());
    }

    @Test
    public void testMixedCaseInputHandling() {
        String input = "Aeon\nWiZArD\ny\nHuMaN\ny\nillusion\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(inputStream);

        Character c = CharacterCreation.create(scanner);
        String expected = "Name: Aeon\nClass: Wizard\nRace: Human";
        assertEquals(expected, c.getInfo());
    }

    @Test
    public void testNoSuggestionAccepted() {
        String input = "Nova\nwrldock\nn\nwizard\ny\nelf\ny\nillusion\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(inputStream);

        Character c = CharacterCreation.create(scanner);
        String expected = "Name: Nova\nClass: Wizard\nRace: Elf";
        assertEquals(expected, c.getInfo());
    }

    @Test
    public void testMultiWordName() {
        String input = "Lady Arwen Evenstar\nwizard\ndwarf\ny\nillusion\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(inputStream);

        Character c = CharacterCreation.create(scanner);
        String expected = "Name: Lady Arwen Evenstar\nClass: Wizard\nRace: Dwarf";
        assertEquals(expected, c.getInfo());
    }

    @Test
    public void testNameWithSpecialCharactersAndSpaces() {
        String input = "Sir-Galahad the Pure\ncleric\nelf\ny\nfireball\nillusion\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(inputStream);

        Character c = CharacterCreation.create(scanner);
        String expected = "Name: Sir-Galahad the Pure\nClass: Cleric\nRace: Elf";
        assertEquals(expected, c.getInfo());
    }

    @Test
    public void testClassWithExtraSpacesBetweenWords() {
        String input = "Nyx\neldritch     knight\ny\nelf\ny\nquick patch\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(inputStream);

        Character c = CharacterCreation.create(scanner);
        String expected = "Name: Nyx\nClass: Eldritch knight\nRace: Elf";
        assertEquals(expected, c.getInfo());
    }

    @Test
    public void testRaceWithTrailingSpecialCharacters() {
        String input = "Tova\nwizard\nelf!\nn\nelf\ny\nillusion\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(inputStream);

        Character c = CharacterCreation.create(scanner);
        String expected = "Name: Tova\nClass: Wizard\nRace: Elf";
        assertEquals(expected, c.getInfo());
    }

    @Test
    public void testClassWithHyphen() {
        String input = "Fen\neldritch-knight\nn\neldritch knight\ny\nhuman\ny\nquick patch\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(inputStream);

        Character c = CharacterCreation.create(scanner);
        String expected = "Name: Fen\nClass: Eldritch knight\nRace: Human";
        assertEquals(expected, c.getInfo());
    }

    @Test
    public void testClassWithPunctuationAndCorrected() {
        String input = "Gideon\nwar.lock\nn\nwarlock\ny\ndwarf\ny\nquick patch\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(inputStream);

        Character c = CharacterCreation.create(scanner);
        String expected = "Name: Gideon\nClass: Warlock\nRace: Dwarf";
        assertEquals(expected, c.getInfo());
    }

    @Test
    public void testRaceWithSpecialCharAndCorrection() {
        String input = "Zana\ncleric\nelf@\nn\nelf\ny\nfireball\nillusion\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(inputStream);

        Character c = CharacterCreation.create(scanner);
        String expected = "Name: Zana\nClass: Cleric\nRace: Elf";
        assertEquals(expected, c.getInfo());
    }

    @Test
    public void testCalcHealthUsingReflection() throws Exception {
        Method method = CharacterCreation.class.getDeclaredMethod("calcHealth", String.class, String.class);
        method.setAccessible(true);
        int result = (int) method.invoke(null, "Cleric", "Human");
        assertEquals(26, result);
    }

    @Test
    public void testCalcSpeedUsingReflection() throws Exception {
        Method method = CharacterCreation.class.getDeclaredMethod("calcSpeed", String.class);
        method.setAccessible(true);
        int result = (int) method.invoke(null, "Elf");
        assertEquals(8, result);
    }

    @Test
    public void testWizardOptions() {
        String input = "illusion\n";
        Scanner scanner = new Scanner(input);

        ArrayList<String> options = CharacterCreation.createPlayerOptions(scanner, "Wizard");

        assertEquals(7, options.size());
        assertTrue(options.contains("move"));
        assertTrue(options.contains("end turn"));
        assertTrue(options.contains("fireball"));
        assertTrue(options.contains("lightening strike"));
        assertTrue(options.contains("thunder wave"));
        assertTrue(options.contains("illusion"));
        assertTrue(options.contains("relocate"));
    }
    
    @Test
    public void testClericOptions() {
        String input = "fireball\nillusion\n";
        Scanner scanner = new Scanner(input);

        ArrayList<String> options = CharacterCreation.createPlayerOptions(scanner, "Cleric");

        assertEquals(8, options.size());
        assertTrue(options.contains("move"));
        assertTrue(options.contains("end turn"));
        assertTrue(options.contains("fireball"));
        assertTrue(options.contains("quick patch"));
        assertTrue(options.contains("restore"));
        assertTrue(options.contains("adrenaline"));
        assertTrue(options.contains("illusion"));
        assertTrue(options.contains("melee attack"));
    }

    @Test
    public void testWarlockOptions() {
        String input = "quick patch\n";
        Scanner scanner = new Scanner(input);

        ArrayList<String> options = CharacterCreation.createPlayerOptions(scanner, "Warlock");

        assertEquals(8, options.size());
        assertTrue(options.contains("move"));
        assertTrue(options.contains("end turn"));
        assertTrue(options.contains("fireball"));
        assertTrue(options.contains("lightening strike"));
        assertTrue(options.contains("thunder wave"));
        assertTrue(options.contains("quick patch"));
        assertTrue(options.contains("mind control"));
        assertTrue(options.contains("melee attack"));
    }

    @Test
    public void testEldritchKnightOptions() {
        String input = "quick patch\n";
        Scanner scanner = new Scanner(input);

        ArrayList<String> options = CharacterCreation.createPlayerOptions(scanner, "Eldritch knight");

        assertEquals(8, options.size());
        assertTrue(options.contains("move"));
        assertTrue(options.contains("end turn"));
        assertTrue(options.contains("mana shield"));
        assertTrue(options.contains("illusion"));
        assertTrue(options.contains("invisibility"));
        assertTrue(options.contains("quick patch"));
        assertTrue(options.contains("melee attack"));
        assertTrue(options.contains("raise shield"));
    }

    @Test
    public void testInvalidClass() {
        String input = "Bard\n";
        Scanner scanner = new Scanner(input);

        ArrayList<String> options = CharacterCreation.createPlayerOptions(scanner, "Bard");

        assertEquals(2, options.size());  // Only "move" and "end turn" should be added
        assertTrue(options.contains("move"));
    }

}