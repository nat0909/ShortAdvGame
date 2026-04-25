import java.util.ArrayList;
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
    
    public static Character create(Scanner s) {
        String charName = charName(s);
        String charClass = charClass(s);
        String charRace = charRace(s);
        ArrayList<String> options = createPlayerOptions(s, charClass);

        int health = calcHealth(charClass, charRace);
        int speed = calcSpeed(charRace);

        Character character = new Character(charName, charClass, charRace, health, speed, options);
        return character;
    }

    private static String charName(Scanner console) {
        while(true) {
            System.out.print("\nWhat is your character's name? ");
            String userInput = console.nextLine().trim();
            if (userInput.isEmpty()) {
                System.out.println("Input cannot be blank. Please type at least on character.");
            } else {
                return userInput;
            }
        }
    }

    private static String charClass(Scanner console){
        String[] options = new String[]{"Wizard", "Cleric", "Warlock", "Eldritch knight"};

        /* Will loop until the player picks a valid class */
        while(true) {
            System.out.println("\nThere are four classes you can choose from: wizard, cleric, warlock, or eldritch knight.");
            System.out.print("What is your character's class? ");

           String chClass = makeUserPick(console, options);
            if (!chClass.equals("continue"))
                return chClass;
        }

    }

    private static String charRace(Scanner console){
        String[] options = new String[]{"Elf", "Human", "Dwarf"};

        /* Will loop until the player picks a valid race */
        while(true){
            System.out.println("\nThere are three races you can choose from: elf, human, or dwarf.");
            System.out.print("What is your character's race? ");

            String race = makeUserPick(console, options);
            if (!race.equals("continue"))
                return race;
        }        

    }

    private static ArrayList<String> createPlayerOptions(Scanner console, String charClass) {
        System.out.print("You will pick your spells based on your class. ");
        ArrayList<String> options = new ArrayList<>();

        options.add("Move");
        options.add("End turn");

        if (charClass == "Wizard") {
            // Advanced Attack Magic
            options.add("Fireball");
            options.add("Lightening strike");
            options.add("Thunder wave");

            // Basic Defensive Magic
            while(true) {
                System.out.print("Choose one defensive spell (Mana shield, Illusion, Invisibility): ");

                String spell = makeUserPick(console, new String[]{"Mana shield", "Illusion", "Invisibility"});
                if (!spell.equals("continue")) {
                    options.add(spell);
                    break;
                }
            }

            // Relocate Spell
            options.add("Relocate");

            // Gear: Wand (can't melee)

        } else if (charClass == "Cleric") {
            // Basic Attack Magic
            while(true) {
                System.out.print("Choose one attack spell (Fireball, Lightening strike, Thunder wave): ");

                String spell = makeUserPick(console, new String[]{"Fireball", "Lightening strike", "Thunder wave"});
                if (!spell.equals("continue")) {
                    options.add(spell);
                    break;
                }
            }

            // Advanced Healing Magic
            options.add("Quick patch");
            options.add("Restore");
            options.add("Adrenaline");

            // Basic Defensive Magic
            while(true) {
                System.out.print("Choose one defensive spell (Mana shield, Illusion, Invisibility): ");
                
                String spell = makeUserPick(console, new String[]{"Mana shield", "Illusion", "Invisibility"});
                if (!spell.equals("continue")) {
                    options.add(spell);
                    break;
                }
            }
            
            // Gear: Mace
            options.add("Melee attack");

        } else if (charClass == "Warlock") {
            // Advanced Attack Magic
            options.add("Fireball");
            options.add("Lightening strike");
            options.add("Thunder wave");

            // Basic Healing Magic
            while(true) {
                System.out.print("Choose one healing spell (Quick patch, Restore, Adrenaline): ");

                String spell = makeUserPick(console, new String[]{"Quick patch", "Restore", "Adrenaline"});
                if (!spell.equals("continue")) {
                    options.add(spell);
                    break;
                }
                }

                // Mind Control
                options.add("Mind control");

                // Gear: Staff
                options.add("Melee attack");

        } else if (charClass == "Eldritch knight") {
            // Advanced Defensive Magic
            options.add("Mana shield");
            options.add("Illusion");
            options.add("Invisibility");

            // Basic Healing Magic
            while(true) {
                System.out.print("Choose one healing spell (Quick patch, Restore, Adrenaline): ");

                String spell = makeUserPick(console, new String[]{"Quick patch", "Restore", "Adrenaline"});
                if (!spell.equals("continue")) {
                    options.add(spell);
                    break;
                }
            }

            // Gear: Longsword and Shield
            options.add("Melee attack");
            options.add("Raise shield");

        } else {
            System.out.println("Invalid input. View the createPlayerOptions method in the CharacterCreation class. ");
        }

        return options;
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

    private static String makeUserPick(Scanner console, String[] options) {
        String userInput = console.nextLine().trim();
        if (userInput.isEmpty())
            return "continue"; // Restart loop bc it can't be empty
        userInput = userInput.substring(0,1).toUpperCase() + userInput.substring(1).toLowerCase();
        String choice = StringUtil.findOption(options, userInput);

        if(choice.equals("Invalid Input")) {
            String guess = StringUtil.interpretUserInput(options, userInput);
            if(StringUtil.clarify(guess, console))
                return guess;
        } else {
            return choice;
        }
        
        return "continue";
    }

}