public class Spells {


    // Attack spells
    
    public static int fireball(int level) { // Takes an action to cast, can hit multiple enemies
        return (int)(Math.random() * level + 10); // Damage
    }

    public static int lighteningStrike(int level) { // Takes an action to cast, hits one enemy
        return (int)(Math.random() * level + 15); // Damage
    }

    public static int thunderWave(int level) { // Takes a bonus action to cast, push enemies back and deals damage
        return (int)(Math.random() * level + 5); // Damage
    }



    // Defensive spells

     public static int manaShield(int level) { // Takes a bonus action to cast, reduces damage taken by each attack until you're next turn
        return 4 + level * 2; // Damage reduced
    }

    public static int illusion(int level) { // Takes an action to cast, confuses an enemy and prevents them from attacking you
       return level / 2; // Turns effective
    }

    public static int invisibility(int level) { // Takes an action to cast, allows you to be invisible (attacking an enemy will break it)
        return level / 2; // Turns effective
    }



    // Healing spells

    public static int quickPatch(int level) { // Takes a bonus action to cast, heals a certain amount of health
        return (int)(Math.random() * level + 5); // Health restored
    }

    public static int restore(int level) { // Takes an action to cast, heals a certain amount of health
        return (int)(Math.random() * level * 2 + 10); // Health restored
    }

    public static int adrenaline(int level) { // Takes a bonus action to cast, give the character temporary health for a certain number of turns
        return (int)(Math.random() * level * 2 + 10); // Temp. health restored
    }



    // Warlock specific spell

    public static int mindControl(int level) { // Takes an action to cast, allows you to control an enemy as your bonus action each turn (if you don't use your bonus action on them, they revert back to normal)                          
        return level / 2; // Turns effective
    }


    // Wizard specific spell

    public static int teleportation(int level) { // Takes a bonus action to cast, allows you to teleport a certain distance away                         
        return 50 + level * 5; // Number of feet your character can teleport
    }
    
}
