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
        String expected = "Name: Aragorn\nClass: Warlock\nRace: Human\nHealth: 22/22\nSpeed: 6\nLevel: 1\nOptions: [Move, End turn, Fireball, Lightening strike, Thunder wave, Quick patch, Mind control, Melee attack]";
        assertEquals(expected, c.getInfo());
    }

    @Test
    public void testSuggestClassAccepted() {
        String input = "Luna\nwzr\ny\nelf\ny\nquick patch\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(inputStream);

        Character c = CharacterCreation.create(scanner);
        String expected = "Name: Luna\nClass: Warlock\nRace: Elf\nHealth: 20/20\nSpeed: 8\nLevel: 1\nOptions: [Move, End turn, Fireball, Lightening strike, Thunder wave, Quick patch, Mind control, Melee attack]";
        assertEquals(expected, c.getInfo());
    }

    @Test
    public void testSuggestRejectedThenAccepted() {
        String input = "Kira\nwarloc\nn\nwarlock\nelf\ny\nquick patch\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(inputStream);

        Character c = CharacterCreation.create(scanner);
        String expected = "Name: Kira\nClass: Warlock\nRace: Elf\nHealth: 20/20\nSpeed: 8\nLevel: 1\nOptions: [Move, End turn, Fireball, Lightening strike, Thunder wave, Quick patch, Mind control, Melee attack]";
        assertEquals(expected, c.getInfo());
    }

    @Test
    public void testBlankName() {
        String input = "\nLin\nwarlock\nhuman\ny\nquick patch\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(inputStream);

        Character c = CharacterCreation.create(scanner);
        String expected = "Name: Lin\nClass: Warlock\nRace: Human\nHealth: 22/22\nSpeed: 6\nLevel: 1\nOptions: [Move, End turn, Fireball, Lightening strike, Thunder wave, Quick patch, Mind control, Melee attack]";
        assertEquals(expected, c.getInfo());
    }

    @Test
    public void testInvalidClassThenValid() {
        String input = "Lyra\nnecromancer\nwizard\ny\nhuman\ny\nillusion\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(inputStream);

        Character c = CharacterCreation.create(scanner);
        String expected = "Name: Lyra\nClass: Wizard\nRace: Human\nHealth: 22/22\nSpeed: 6\nLevel: 1\nOptions: [Move, End turn, Fireball, Lightening strike, Thunder wave, Illusion, Relocate]";
        assertEquals(expected, c.getInfo());
    }

    @Test
    public void testInvalidRaceThenValid() {
        String input = "Thorn\nwarlock\norc\ndwarf\ny\nquick patch\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(inputStream);

        Character c = CharacterCreation.create(scanner);
        String expected = "Name: Thorn\nClass: Warlock\nRace: Dwarf\nHealth: 26/26\nSpeed: 4\nLevel: 1\nOptions: [Move, End turn, Fireball, Lightening strike, Thunder wave, Quick patch, Mind control, Melee attack]";
        assertEquals(expected, c.getInfo());
    }

    @Test
    public void testMixedCaseInputHandling() {
        String input = "Aeon\nWiZArD\ny\nHuMaN\ny\nillusion\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(inputStream);

        Character c = CharacterCreation.create(scanner);
        String expected = "Name: Aeon\nClass: Wizard\nRace: Human\nHealth: 22/22\nSpeed: 6\nLevel: 1\nOptions: [Move, End turn, Fireball, Lightening strike, Thunder wave, Illusion, Relocate]";
        assertEquals(expected, c.getInfo());
    }

    @Test
    public void testNoSuggestionAccepted() {
        String input = "Nova\nwrldock\nn\nwizard\ny\nelf\ny\nillusion\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(inputStream);

        Character c = CharacterCreation.create(scanner);
        String expected = "Name: Nova\nClass: Wizard\nRace: Elf\nHealth: 20/20\nSpeed: 8\nLevel: 1\nOptions: [Move, End turn, Fireball, Lightening strike, Thunder wave, Illusion, Relocate]";
        assertEquals(expected, c.getInfo());
    }

    @Test
    public void testMultiWordName() {
        String input = "Lady Arwen Evenstar\nwizard\ndwarf\ny\nillusion\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(inputStream);

        Character c = CharacterCreation.create(scanner);
        String expected = "Name: Lady Arwen Evenstar\nClass: Wizard\nRace: Dwarf\nHealth: 26/26\nSpeed: 4\nLevel: 1\nOptions: [Move, End turn, Fireball, Lightening strike, Thunder wave, Illusion, Relocate]";
        assertEquals(expected, c.getInfo());
    }

    @Test
    public void testNameWithSpecialCharactersAndSpaces() {
        String input = "Sir-Galahad the Pure\ncleric\nelf\ny\nfireball\nillusion\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(inputStream);

        Character c = CharacterCreation.create(scanner);
        String expected = "Name: Sir-Galahad the Pure\nClass: Cleric\nRace: Elf\nHealth: 24/24\nSpeed: 8\nLevel: 1\nOptions: [Move, End turn, Fireball, Quick patch, Restore, Adrenaline, Illusion, Melee attack]";
        assertEquals(expected, c.getInfo());
    }

    @Test
    public void testClassWithExtraSpacesBetweenWords() {
        String input = "Nyx\neldritch     knight\ny\nelf\ny\nquick patch\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(inputStream);

        Character c = CharacterCreation.create(scanner);
        String expected = "Name: Nyx\nClass: Eldritch knight\nRace: Elf\nHealth: 26/26\nSpeed: 8\nLevel: 1\nOptions: [Move, End turn, Mana shield, Illusion, Invisibility, Quick patch, Melee attack, Raise shield]";
        assertEquals(expected, c.getInfo());
    }

    @Test
    public void testRaceWithTrailingSpecialCharacters() {
        String input = "Tova\nwizard\nelf!\nn\nelf\ny\nillusion\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(inputStream);

        Character c = CharacterCreation.create(scanner);
        String expected = "Name: Tova\nClass: Wizard\nRace: Elf\nHealth: 20/20\nSpeed: 8\nLevel: 1\nOptions: [Move, End turn, Fireball, Lightening strike, Thunder wave, Illusion, Relocate]";
        assertEquals(expected, c.getInfo());
    }

    @Test
    public void testClassWithHyphen() {
        String input = "Fen\neldritch-knight\nn\neldritch knight\ny\nhuman\ny\nquick patch\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(inputStream);

        Character c = CharacterCreation.create(scanner);
        String expected = "Name: Fen\nClass: Eldritch knight\nRace: Human\nHealth: 28/28\nSpeed: 6\nLevel: 1\nOptions: [Move, End turn, Mana shield, Illusion, Invisibility, Quick patch, Melee attack, Raise shield]";
        assertEquals(expected, c.getInfo());
    }

    @Test
    public void testClassWithPunctuationAndCorrected() {
        String input = "Gideon\nwar.lock\nn\nwarlock\ny\ndwarf\ny\nquick patch\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(inputStream);

        Character c = CharacterCreation.create(scanner);
        String expected = "Name: Gideon\nClass: Warlock\nRace: Dwarf\nHealth: 26/26\nSpeed: 4\nLevel: 1\nOptions: [Move, End turn, Fireball, Lightening strike, Thunder wave, Quick patch, Mind control, Melee attack]";
        assertEquals(expected, c.getInfo());
    }



    @Test
    public void testRaceWithSpecialCharAndCorrection() {
        String input = "Zana\ncleric\nelf@\nn\nelf\ny\nfireball\nillusion\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(inputStream);

        Character c = CharacterCreation.create(scanner);
        String expected = "Name: Zana\nClass: Cleric\nRace: Elf\nHealth: 24/24\nSpeed: 8\nLevel: 1\nOptions: [Move, End turn, Fireball, Quick patch, Restore, Adrenaline, Illusion, Melee attack]";
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
    public void testWizardOptions() throws Exception {
        String input = "Illusion\n";
        Scanner scanner = new Scanner(input);

        Method method = CharacterCreation.class.getDeclaredMethod("createPlayerOptions", Scanner.class, String.class);
        method.setAccessible(true);
        @SuppressWarnings("unchecked")
        ArrayList<String> options = (ArrayList<String>) method.invoke(null, scanner, "Wizard");

        assertEquals(7, options.size());
        assertTrue(options.contains("Move"));
        assertTrue(options.contains("End turn"));
        assertTrue(options.contains("Fireball"));
        assertTrue(options.contains("Lightening strike"));
        assertTrue(options.contains("Thunder wave"));
        assertTrue(options.contains("Illusion"));
        assertTrue(options.contains("Relocate"));
    }

    @Test
    public void testWizardOptionsWithManaShield() throws Exception {
        String input = "Mana shield\n";
        Scanner scanner = new Scanner(input);

        Method method = CharacterCreation.class.getDeclaredMethod("createPlayerOptions", Scanner.class, String.class);
        method.setAccessible(true);
        @SuppressWarnings("unchecked")
        ArrayList<String> options = (ArrayList<String>) method.invoke(null, scanner, "Wizard");

        assertEquals(7, options.size());
        assertTrue(options.contains("Move"));
        assertTrue(options.contains("End turn"));
        assertTrue(options.contains("Fireball"));
        assertTrue(options.contains("Lightening strike"));
        assertTrue(options.contains("Thunder wave"));
        assertTrue(options.contains("Mana shield"));
        assertTrue(options.contains("Relocate"));
    }

    @Test
    public void testWizardOptionsWithInvisibility() throws Exception {
        String input = "Invisibility\n";
        Scanner scanner = new Scanner(input);

        Method method = CharacterCreation.class.getDeclaredMethod("createPlayerOptions", Scanner.class, String.class);
        method.setAccessible(true);
        @SuppressWarnings("unchecked")
        ArrayList<String> options = (ArrayList<String>) method.invoke(null, scanner, "Wizard");

        assertEquals(7, options.size());
        assertTrue(options.contains("Move"));
        assertTrue(options.contains("End turn"));
        assertTrue(options.contains("Fireball"));
        assertTrue(options.contains("Lightening strike"));
        assertTrue(options.contains("Thunder wave"));
        assertTrue(options.contains("Invisibility"));
        assertTrue(options.contains("Relocate"));
    }

    @Test
    public void testWizardOptionsWithInvalidThenValidDefensiveSpell() throws Exception {
        String input = "fireball\nillusion\n";
        Scanner scanner = new Scanner(input);

        Method method = CharacterCreation.class.getDeclaredMethod("createPlayerOptions", Scanner.class, String.class);
        method.setAccessible(true);
        @SuppressWarnings("unchecked")
        ArrayList<String> options = (ArrayList<String>) method.invoke(null, scanner, "Wizard");

        assertEquals(7, options.size());
        assertTrue(options.contains("Move"));
        assertTrue(options.contains("End turn"));
        assertTrue(options.contains("Fireball"));
        assertTrue(options.contains("Lightening strike"));
        assertTrue(options.contains("Thunder wave"));
        assertTrue(options.contains("Illusion"));
        assertTrue(options.contains("Relocate"));
    }

    @Test
    public void testWizardOptionsWithEmptyThenValidDefensiveSpell() throws Exception {
        String input = "\nmana shield\n";
        Scanner scanner = new Scanner(input);

        Method method = CharacterCreation.class.getDeclaredMethod("createPlayerOptions", Scanner.class, String.class);
        method.setAccessible(true);
        @SuppressWarnings("unchecked")
        ArrayList<String> options = (ArrayList<String>) method.invoke(null, scanner, "Wizard");

        assertEquals(7, options.size());
        assertTrue(options.contains("Move"));
        assertTrue(options.contains("End turn"));
        assertTrue(options.contains("Fireball"));
        assertTrue(options.contains("Lightening strike"));
        assertTrue(options.contains("Thunder wave"));
        assertTrue(options.contains("Mana shield"));
        assertTrue(options.contains("Relocate"));
    }

    @Test
    public void testWizardOptionsWithMixedCaseDefensiveSpell() throws Exception {
        String input = "InViSiBiLiTy\n";
        Scanner scanner = new Scanner(input);

        Method method = CharacterCreation.class.getDeclaredMethod("createPlayerOptions", Scanner.class, String.class);
        method.setAccessible(true);
        @SuppressWarnings("unchecked")
        ArrayList<String> options = (ArrayList<String>) method.invoke(null, scanner, "Wizard");

        assertEquals(7, options.size());
        assertTrue(options.contains("Move"));
        assertTrue(options.contains("End turn"));
        assertTrue(options.contains("Fireball"));
        assertTrue(options.contains("Lightening strike"));
        assertTrue(options.contains("Thunder wave"));
        assertTrue(options.contains("Invisibility"));
        assertTrue(options.contains("Relocate"));
    }

    @Test
    public void testClericOptions() throws Exception {
        String input = "Fireball\nIllusion\n";
        Scanner scanner = new Scanner(input);

        Method method = CharacterCreation.class.getDeclaredMethod("createPlayerOptions", Scanner.class, String.class);
        method.setAccessible(true);
        @SuppressWarnings("unchecked")
        ArrayList<String> options = (ArrayList<String>) method.invoke(null, scanner, "Cleric");

        assertEquals(8, options.size());
        assertTrue(options.contains("Move"));
        assertTrue(options.contains("End turn"));
        assertTrue(options.contains("Fireball"));
        assertTrue(options.contains("Quick patch"));
        assertTrue(options.contains("Restore"));
        assertTrue(options.contains("Adrenaline"));
        assertTrue(options.contains("Illusion"));
        assertTrue(options.contains("Melee attack"));
    }

    @Test
    public void testWarlockOptions() throws Exception {
        String input = "Quick patch\n";
        Scanner scanner = new Scanner(input);

        Method method = CharacterCreation.class.getDeclaredMethod("createPlayerOptions", Scanner.class, String.class);
        method.setAccessible(true);
        @SuppressWarnings("unchecked")
        ArrayList<String> options = (ArrayList<String>) method.invoke(null, scanner, "Warlock");

        assertEquals(8, options.size());
        assertTrue(options.contains("Move"));
        assertTrue(options.contains("End turn"));
        assertTrue(options.contains("Fireball"));
        assertTrue(options.contains("Lightening strike"));
        assertTrue(options.contains("Thunder wave"));
        assertTrue(options.contains("Quick patch"));
        assertTrue(options.contains("Mind control"));
        assertTrue(options.contains("Melee attack"));
    }

    @Test
    public void testEldritchKnightOptions() throws Exception {
        String input = "Quick patch\n";
        Scanner scanner = new Scanner(input);

        Method method = CharacterCreation.class.getDeclaredMethod("createPlayerOptions", Scanner.class, String.class);
        method.setAccessible(true);
        @SuppressWarnings("unchecked")
        ArrayList<String> options = (ArrayList<String>) method.invoke(null, scanner, "Eldritch knight");

        assertEquals(8, options.size());
        assertTrue(options.contains("Move"));
        assertTrue(options.contains("End turn"));
        assertTrue(options.contains("Mana shield"));
        assertTrue(options.contains("Illusion"));
        assertTrue(options.contains("Invisibility"));
        assertTrue(options.contains("Quick patch"));
        assertTrue(options.contains("Melee attack"));
        assertTrue(options.contains("Raise shield"));
    }

    @Test
    public void testInvalidClass() throws Exception {
        String input = "Bard\n";
        Scanner scanner = new Scanner(input);

        Method method = CharacterCreation.class.getDeclaredMethod("createPlayerOptions", Scanner.class, String.class);
        method.setAccessible(true);
        @SuppressWarnings("unchecked")
        ArrayList<String> options = (ArrayList<String>) method.invoke(null, scanner, "Bard");

        assertEquals(2, options.size());
        assertTrue(options.contains("Move"));
        assertTrue(options.contains("End turn"));
    }

    @Test
    public void testClassWithLeadingAndTrailingSpaces() {
        String input = "Zara\n   wizard   \ny\nhuman\ny\nillusion\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(inputStream);

        Character c = CharacterCreation.create(scanner);
        String expected = "Name: Zara\nClass: Wizard\nRace: Human\nHealth: 22/22\nSpeed: 6\nLevel: 1\nOptions: [Move, End turn, Fireball, Lightening strike, Thunder wave, Illusion, Relocate]";
        assertEquals(expected, c.getInfo());
    }

    @Test
    public void testRaceWithLeadingAndTrailingSpaces() {
        String input = "Kai\nwizard\ny\n   elf   \ny\nillusion\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(inputStream);

        Character c = CharacterCreation.create(scanner);
        String expected = "Name: Kai\nClass: Wizard\nRace: Elf\nHealth: 20/20\nSpeed: 8\nLevel: 1\nOptions: [Move, End turn, Fireball, Lightening strike, Thunder wave, Illusion, Relocate]";
        assertEquals(expected, c.getInfo());
    }

    @Test
    public void testClassWithOnlySpaces() {
        String input = "Nova\n   \ncleric\ny\nhuman\ny\nfireball\nillusion\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(inputStream);

        Character c = CharacterCreation.create(scanner);
        String expected = "Name: Nova\nClass: Cleric\nRace: Human\nHealth: 26/26\nSpeed: 6\nLevel: 1\nOptions: [Move, End turn, Fireball, Quick patch, Restore, Adrenaline, Illusion, Melee attack]";
        assertEquals(expected, c.getInfo());
    }

    @Test
    public void testRaceWithOnlySpaces() {
        String input = "Echo\nwarlock\ny\n   \ndwarf\ny\nquick patch\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(inputStream);

        Character c = CharacterCreation.create(scanner);
        String expected = "Name: Echo\nClass: Warlock\nRace: Dwarf\nHealth: 26/26\nSpeed: 4\nLevel: 1\nOptions: [Move, End turn, Fireball, Lightening strike, Thunder wave, Quick patch, Mind control, Melee attack]";
        assertEquals(expected, c.getInfo());
    }

    @Test
    public void testClassWithTabsAndNewlines() {
        String input = "Sage\n\t\nwizard\ny\nhuman\ny\nillusion\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(inputStream);

        Character c = CharacterCreation.create(scanner);
        String expected = "Name: Sage\nClass: Wizard\nRace: Human\nHealth: 22/22\nSpeed: 6\nLevel: 1\nOptions: [Move, End turn, Fireball, Lightening strike, Thunder wave, Illusion, Relocate]";
        assertEquals(expected, c.getInfo());
    }

    @Test
    public void testRaceWithTabsAndNewlines() {
        String input = "River\ncleric\ny\n\t\nhuman\ny\nfireball\nillusion\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(inputStream);

        Character c = CharacterCreation.create(scanner);
        String expected = "Name: River\nClass: Cleric\nRace: Human\nHealth: 26/26\nSpeed: 6\nLevel: 1\nOptions: [Move, End turn, Fireball, Quick patch, Restore, Adrenaline, Illusion, Melee attack]";
        assertEquals(expected, c.getInfo());
    }

    @Test
    public void testMultipleEmptyInputsBeforeValidClass() {
        String input = "Atlas\n\n\n   \n\t\nwarlock\ny\nhuman\ny\nquick patch\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(inputStream);

        Character c = CharacterCreation.create(scanner);
        String expected = "Name: Atlas\nClass: Warlock\nRace: Human\nHealth: 22/22\nSpeed: 6\nLevel: 1\nOptions: [Move, End turn, Fireball, Lightening strike, Thunder wave, Quick patch, Mind control, Melee attack]";
        assertEquals(expected, c.getInfo());
    }

    @Test
    public void testMultipleEmptyInputsBeforeValidRace() {
        String input = "Iris\nwizard\ny\n\n\n   \n\t\nelf\ny\nillusion\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(inputStream);

        Character c = CharacterCreation.create(scanner);
        String expected = "Name: Iris\nClass: Wizard\nRace: Elf\nHealth: 20/20\nSpeed: 8\nLevel: 1\nOptions: [Move, End turn, Fireball, Lightening strike, Thunder wave, Illusion, Relocate]";
        assertEquals(expected, c.getInfo());
    }

}