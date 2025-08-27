import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) { 
        clearConsole();
        Scanner s = new Scanner(System.in);
        Character character = CharacterCreation.create(s);
        runSenario(character, s);

        s.close();
    }

    public static Character createCharacter(Scanner s) {
        Character character = CharacterCreation.create(s);
        System.out.println(character.getInfo());
        return character;
    }

    public static void runSenario(Character character, Scanner s) {
        Senario senario = new Senario(new Creature[]{new Goblin(1), new Goblin(2), new Goblin(2)}, character, false);
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