import java.util.Scanner;

public class Actions {


    // Attack spells
    
    public static int fireball(int level) { // Takes an action to cast, can hit multiple enemies
        return (int)(1 + Math.random() * 3 + level/2); // Damage
    }

    public static int lighteningStrike(int level) { // Takes an action to cast, hits one enemy
        return (int)(1 + Math.random() * 3 + level); // Damage
    }

    public static int thunderWave(int level) { // Takes a bonus action to cast, push enemies back and deals damage
        return (int)(1 + Math.random() * 3 + level/3); // Damage
    }


    // TODO: add other spells to characterTurn.java
    // Defensive spells

     public static int manaShield(int level) { // Takes a bonus action to cast, reduces damage taken by each attack until you're next turn
        return (int)(1 + Math.random() * 3 + level/3); // Damage reduced
    }

    public static int illusion(int level) { // Takes an action to cast, confuses an enemy and prevents them from attacking you
       return Math.min(level, 5);  // Turns effective
    }

    public static int invisibility(int level) { // Takes an action to cast, allows you to be invisible (attacking an enemy will break it)
        return Math.min(level, 5); // Turns effective
    }



    // Healing spells

    public static int quickPatch(int level) { // Takes a bonus action to cast, heals a certain amount of health
        return (int)(1 + Math.random() * 3 + level/2); // Health restored
    }

    public static int restore(int level) { // Takes an action to cast, heals a certain amount of health
        return (int)(1 + Math.random() * 3 + level*2); // Health restored
    }

    public static int adrenaline(int level) { // Takes a bonus action to cast, give the character temporary health for a certain number of turns
        return (int)(1 + Math.random() * 3 + level); // Temp. health restored
    }



    // Warlock specific spell

    public static int mindControl(int level) { // Takes an action to cast, allows you to control an enemy as your bonus action each turn (if you don't use your bonus action on them, they revert back to normal)                          
        return Math.min(level, 5); // Turns effective
    }


    // Wizard specific spell

    public static int relocate(int level) { // Takes an action to cast, allows you to teleport a certain distance in any direction                      
        return Math.min(5 + level*5, 50); // Number of feet your character can teleport an enemy in any direction
    }


    // Melee attacks

    public static int warlockMeleeAttack(int level) {
        return (int)(1 + Math.random() * 3 + level/2); // Damage
    }

    public static int clericMeleeAttack(int level) {
        return (int)(1 + Math.random() * 3 + level); // Damage
    }

    public static int eldritchKnightMeleeAttack(int level) {
        return (int)(1 + Math.random() * 3 + level*2); // Damage
    }

    private static int meleeAttack(Scanner console, int[] positions, int charPosition, String[] names) {
        positions[0] = 500; // Makes sure the character isn't included in the options
        int[] optionIndexs = StringUtil.returnNearbyNumsIndexs(charPosition, 2, positions);
        if(optionIndexs.length == 0) {
            System.out.println("There are no enemies in range to attack.");
            return -1; // No enemies in range
        }

        String[] options = new String[optionIndexs.length + 1];
        for(int i = 0; i < optionIndexs.length; i++) {
            options[i] = names[optionIndexs[i]];
        }
        options[optionIndexs.length] = "x"; // Option to go back

        while(true) {
            System.out.println("You have a range of 2. Type the name of the enemy you want to attack. (x to go back)");
            String userInput = console.nextLine().trim();
            String choice = StringUtil.findOption(options, userInput);
            if(choice.equals("x")) {
                return -1; // Go back
            } else if (choice.equals("Invalid Input")) {
                System.out.println("Please pick one of the options.");
                continue; // Restarts the loop so the player can pick again
            } else {
                for(int i = 0; i < optionIndexs.length; i++) {
                    if(choice.equals(names[i]))
                        return optionIndexs[i]; // Returns the index of the creature in the Creatures array
                }
                System.out.println("Error: see meleeAtack in Actions.java"); // TODO: test this method and finish the other meleeAttack methods
                return -1;
            }
        }

    }


    // Eldritch Knight specific defense

    public static String raiseShield(int level) { // Takes a bonus action to use, blocks the next attack against you                     
        return "block"; // Placeholder
    }


}
