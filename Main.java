public class Main {
    
    public static void main(String[] args) { 
        clearConsole();
        Character character = CharacterCreation.create();
        runSenario(character);
    }

    public static Character createCharacter() {
        Character character = CharacterCreation.create();
        System.out.println(character.getInfo());
        return character;
    }

    public static void runSenario(Character character) {
        // Senario senario = new Senario(new Creature[]{new Goblin(1), new Goblin(2)}, character, false);
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