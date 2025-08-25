import static org.junit.Assert.*;
import org.junit.Test;
import java.io.ByteArrayInputStream;
import java.lang.reflect.Method;

public class CharacterCreationTests {

    @Test
    public void testValidCharacterCreation() {
        String input = "Aragorn\nwarlock\nhuman\ny\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Character c = CharacterCreation.create();
        String expected = "Name: Aragorn\nClass: Warlock\nRace: Human";
        assertEquals(expected, c.getInfo());
    }

    @Test
    public void testSuggestClassAccepted() {
        String input = "Luna\nwzr\ny\nelf\ny\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Character c = CharacterCreation.create();
        String expected = "Name: Luna\nClass: Warlock\nRace: Elf";
        assertEquals(expected, c.getInfo());
    }

    @Test
    public void testSuggestRejectedThenAccepted() {
        String input = "Kira\nwarloc\nn\nwarlock\nelf\ny\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Character c = CharacterCreation.create();
        String expected = "Name: Kira\nClass: Warlock\nRace: Elf";
        assertEquals(expected, c.getInfo());
    }

    @Test
    public void testBlankName() {
        String input = "\nwarlock\nhuman\ny\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Character c = CharacterCreation.create();
        String expected = "Name: \nClass: Warlock\nRace: Human";
        assertEquals(expected, c.getInfo());
    }

    @Test
    public void testInvalidClassThenValid() {
        String input = "Lyra\nnecromancer\nwizard\ny\nhuman\ny\n"; // Invalid class first
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Character c = CharacterCreation.create();
        String expected = "Name: Lyra\nClass: Wizard\nRace: Human";
        assertEquals(expected, c.getInfo());
    }

    @Test
    public void testInvalidRaceThenValid() {
        String input = "Thorn\nwarlock\norc\ndwarf\ny\n"; // Invalid race first
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Character c = CharacterCreation.create();
        String expected = "Name: Thorn\nClass: Warlock\nRace: Dwarf";
        assertEquals(expected, c.getInfo());
    }

    @Test
    public void testMixedCaseInputHandling() {
        String input = "Aeon\nWiZArD\ny\nHuMaN\ny\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Character c = CharacterCreation.create();
        String expected = "Name: Aeon\nClass: Wizard\nRace: Human";
        assertEquals(expected, c.getInfo());
    }

    @Test
    public void testNoSuggestionAccepted() {
        String input = "Nova\nwrldock\nn\nwizard\ny\nelf\ny\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Character c = CharacterCreation.create();
        String expected = "Name: Nova\nClass: Wizard\nRace: Elf";
        assertEquals(expected, c.getInfo());
    }

    @Test
    public void testMultiWordName() {
        String input = "Lady Arwen Evenstar\nwizard\ndwarf\ny\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Character c = CharacterCreation.create();
        String expected = "Name: Lady Arwen Evenstar\nClass: Wizard\nRace: Dwarf";
        assertEquals(expected, c.getInfo());
    }

    @Test
    public void testNameWithSpecialCharactersAndSpaces() {
        String input = "Sir-Galahad the Pure\ncleric\nelf\ny\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Character c = CharacterCreation.create();
        String expected = "Name: Sir-Galahad the Pure\nClass: Cleric\nRace: Elf";
        assertEquals(expected, c.getInfo());
    }

    @Test
    public void testClassWithExtraSpacesBetweenWords() {
        String input = "Nyx\neldritch     knight\ny\nelf\ny\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Character c = CharacterCreation.create();
        String expected = "Name: Nyx\nClass: Eldritch knight\nRace: Elf";
        assertEquals(expected, c.getInfo());
    }

    @Test
    public void testRaceWithTrailingSpecialCharacters() {
        String input = "Tova\nwizard\nelf!\nn\nelf\ny\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Character c = CharacterCreation.create();
        String expected = "Name: Tova\nClass: Wizard\nRace: Elf";
        assertEquals(expected, c.getInfo());
    }

    @Test
    public void testClassWithHyphen() {
        String input = "Fen\neldritch-knight\nn\neldritch knight\ny\nhuman\ny\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Character c = CharacterCreation.create();
        String expected = "Name: Fen\nClass: Eldritch knight\nRace: Human";
        assertEquals(expected, c.getInfo());
    }

    @Test
    public void testClassWithPunctuationAndCorrected() {
        String input = "Gideon\nwar.lock\nn\nwarlock\ny\ndwarf\ny\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Character c = CharacterCreation.create();
        String expected = "Name: Gideon\nClass: Warlock\nRace: Dwarf";
        assertEquals(expected, c.getInfo());
    }

    @Test
    public void testRaceWithSpecialCharAndCorrection() {
        String input = "Zana\ncleric\nelf@\nn\nelf\ny\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Character c = CharacterCreation.create();
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

}