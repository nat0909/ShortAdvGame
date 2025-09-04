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

            String userInput = console.nextLine().trim();
            if (userInput.isEmpty())
                continue; // Restart loop bc it can't be empty
            userInput = userInput.substring(0,1).toUpperCase() + userInput.substring(1).toLowerCase();
            String charClass = StringUtil.findOption(options, userInput);

            if(charClass.equals("Invalid Input")) {
                String guess = StringUtil.interpretUserInput(options, userInput);
                if(StringUtil.clarify(guess, console))
                    return guess;
            } else {
                return charClass;
            }
        }

    }

    private static String charRace(Scanner console){
        String[] options = new String[]{"Elf", "Human", "Dwarf"};

        /* Will loop until the player picks a valid race */
        while(true){
            System.out.println("\nThere are three races you can choose from: elf, human, or dwarf.");
            System.out.print("What is your character's race? ");

            String userInput = console.nextLine().trim();
            if (userInput.isEmpty())
                continue; // Restart loop bc it can't be empty
            userInput = userInput.substring(0,1).toUpperCase() + userInput.substring(1).toLowerCase();
            String charRace = StringUtil.findOption(options, userInput);

            if(charRace.equals("Invalid Input")) {
                String guess = StringUtil.interpretUserInput(options, userInput);
                if(StringUtil.clarify(guess, console))
                    return guess;
            } else {
                return charRace;
            }
        }        

    }

    public static ArrayList<String> createPlayerOptions(Scanner console, String charClass) {
        System.out.print("You will pick your spells based on your class. ");
        ArrayList<String> options = new ArrayList<>();

        options.add("move");
        options.add("end turn");

        switch (charClass) {
            case "Wizard":
                // Advanced Attack Magic
                options.add("fireball");
                options.add("lightening strike");
                options.add("thunder wave");

                // Basic Defensive Magic
                System.out.print("Choose one defensive spell (mana shield, illusion, invisibility): ");
                options.add(console.nextLine().trim().toLowerCase()); // TODO: handle invalid inputs for the spells

                // Relocate Spell
                options.add("relocate");
                
                // Gear: Wand (can't melee)
                break;

            case "Cleric":
                // Basic Attack Magic
                System.out.print("Choose one attack spell (fireball, lightening strike, thunder wave): ");
                options.add(console.nextLine().trim().toLowerCase());

                // Advanced Healing Magic
                options.add("quick patch");
                options.add("restore");
                options.add("adrenaline");

                // Basic Defensive Magic
                System.out.print("Choose one defensive spell (mana shield, illusion, invisibility): ");
                options.add(console.nextLine().trim().toLowerCase());
                
                // Gear: Mace
                options.add("melee attack");
                break;

            case "Warlock":
                // Advanced Attack Magic
                options.add("fireball");
                options.add("lightening strike");
                options.add("thunder wave");

                // Basic Healing Magic
                System.out.print("Choose one healing spell (quick patch, restore, adrenaline): ");
                options.add(console.nextLine().trim().toLowerCase());

                // Mind Control
                options.add("mind control");
                
                // Gear: Staff
                options.add("melee attack");
                break;

            case "Eldritch knight":
                // Advanced Defensive Magic
                options.add("mana shield");
                options.add("illusion");
                options.add("invisibility");

                // Basic Healing Magic
                System.out.print("Choose one healing spell (quick patch, restore, adrenaline): ");
                options.add(console.nextLine().trim().toLowerCase());
                
                // Gear: Longsword and Shield
                options.add("melee attack");
                options.add("raise shield");
                break;

            default:
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

}