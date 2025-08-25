import java.util.Scanner;

/**
 * CharacterCreation is a utility class for creating a new Character object based on user input through the console. 
 * It prompts the player to enter a character name, select a class from available options (wizard, cleric, warlock,
 * eldritch knight), and choose a race (elf, human, dwarf).
 * 
 * The class handles ambiguous responses using the StringUtil.interpretUserInput method to clarify what the user
 * meant in certain situations for the class and race. 
 */
public class CharacterCreation {
    
    public static Character create() {
        Scanner s = new Scanner(System.in);
        String charName = charName(s);
        String charClass = charClass(s);
        String charRace = charRace(s);
        s.close();

        int health = calcHealth(charClass, charRace);
        int speed = calcSpeed(charRace);
        Character c = new Character(charName, charClass, charRace, health, speed);
        return c;
    }

    private static String charName(Scanner console) {
        System.out.print("What is your character's name? ");
        String charName = console.nextLine();
        return charName;
    }

    private static String charClass(Scanner console){
        System.out.print("There are four classes you can choose from: wizard, cleric, warlock, or eldritch knight. ");
        int classAccepted = 0;
        /* Will loop until the player picks a valid class */
        while(classAccepted == 0){
            System.out.print("What is your character's class? ");
            String charClass = console.nextLine().toLowerCase();
            if(charClass.equals("wizard")){
                return "Wizard";
            } else if(charClass.equals("cleric")){
                return "Cleric";
            } else if(charClass.equals("warlock")){
                return "Warlock";
            }  else if(charClass.equals("eldritch knight")){
                return "Eldritch knight";
            } else {
                String guess = StringUtil.interpretUserInput(new String[]{"wizard", "cleric", "warlock", "eldritch knight"}, charClass);
                if(guess.equals("Invalid Input")) {
                    System.out.println("You're input was unclear. Please retype your class. ");
                } else {
                    System.out.println("Did you mean " + guess + "? (y/n) ");
                    String response = console.nextLine().toLowerCase();
                    if(response.equals("y") || response.equals("yes")) {
                        return guess.substring(0,1).toUpperCase() + guess.substring(1);
                    } else {
                        System.out.println("Please pick one of the classes: wizard, cleric, warlock, or eldritch knight. ");
                    }
                }
            }
        }
        return "error";
    }

    private static String charRace(Scanner console){
        System.out.print("There are three races you can choose from: elf, human, or dwarf. ");
        int classAccepted = 0;
        /* Will loop until the player picks a valid race */
        while(classAccepted == 0){
            System.out.print("What is your character's race? ");
            String race = console.nextLine().toLowerCase();
            if(race.equals("elf")){
                return "Elf";
            } else if(race.equals("human")){
                return "Human";
            } else if(race.equals("dwarf")){
                return "Dwarf";
            } else {
                String guess = StringUtil.interpretUserInput(new String[]{"elf", "human", "dwarf"}, race);
                if(guess.equals("Invalid Input")) {
                    System.out.println("You're input was unclear. Please retype your class. ");
                } else {
                    System.out.println("Did you mean " + guess + "? (y/n) ");
                    String response = console.nextLine().toLowerCase();
                    if(response.equals("y") || response.equals("yes")) {
                        return guess.substring(0,1).toUpperCase() + guess.substring(1);
                    } else {
                        System.out.println("Please pick one of the races: elf, human, or dwarf. ");
                    }
                }
            }
        }        
        return "error";
    }

    private static int calcHealth(String charClass, String charRace) {
        int health = 20;

        if(charClass.equals("Cleric")) {
            health += 4;
        } else if(charClass.equals("Eldritch knight")) {
            health += 6;
        }

        if(charRace.equals("Human")) {
            health += 2;
        } else if(charRace.equals("Dwarf")) {
            health += 6;
        }

        return health;
    }

    private static int calcSpeed(String charRace) {
        int speed = 4;

        if(charRace.equals("Elf")) {
            speed += 4;
        } else if(charRace.equals("Human")) {
            speed += 2;
        }
        
        return speed;
    }

}