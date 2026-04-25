import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) { 
        clearConsole();
        Scanner s = new Scanner(System.in);
        Character c = createCharacter(s);
        runSenario(c, s);

        s.close();
    }

    public static Character createCharacter(Scanner s) {
        //Character character = CharacterCreation.create(s);
        Character character = CharacterTests.sampleCharacter(); // Creates sample character, when finished testing delete this line and use the line above instead
        System.out.println(character.getInfo());
        return character;
    }

    public static void runSenario(Character character, Scanner s) {
        Senario senario = new Senario(new Creature[]{new Goblin(1, 1), new Goblin(2, 2), new Goblin(2, 3)}, character, true);
        senario.runSenario(character, s);
    }

    public static void clearConsole() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}