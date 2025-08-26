/**
 * Represents a scenario in a short adventure game, managing the positions and the character and monsters 
 * on a linear path. It provides functionality for initializing the scenario with ambush logic,
 * handling player turns with input interpretation, drawing the scenario to the console,
 * moving creatures, and accessing the current positions and creatures.
 */

public class Senario {
    private Creature[] creatures; // Character included
    private int[] positions; // Positions of the creatures on a linear path

    public Senario(Creature[] monsters, Character character, boolean ambush) {
        creatures = new Creature[monsters.length + 1];
        positions = new int[monsters.length + 1];
        
        creatures[0] = character;
        if(ambush) {
            positions[0] = 25; // Character starts at position 20
        } else {
            positions[0] = 15; // Character starts at position 10
        }
        
        for (int i = 1; i < monsters.length; i++) {
            creatures[i] = monsters[i];
            if(ambush && i % 2 == 1) { // Place every other monster behind the character if it's an ambush
                positions[i] = positions[0] - (5 + (i / 2) * 5); // Place monsters 5 spaces apart behind the character
            } else {
                positions[i] = positions[0] + (5 + (i / 2) * 5); // Place monsters 5 spaces apart in front of the character
            }
        }
    }

    public void drawSenario() {
        System.out.println("Loading Senario...");        
        System.out.println("\n\n");

        for(int square = 5; square <= 50; square+=5) {
        // Search for creatures at this position
            System.out.println("_ _ _ _ ");
            boolean creatureFound = false;
            for(int i = 0; i < positions.length; i++) {
                if(positions[i] == square) {
                    System.out.print(creatures[i] + " ");
                    creatureFound = true;
                    break;
                }
            }
            if(!creatureFound) {
                System.out.println("_ ");
            }
        }
    }

    public void moveCreature(int newPosition, Creature creature) {
        for(int i = 0; i < creatures.length; i++) {
            if(creatures[i] == creature) {
                positions[i] = newPosition;
                break;
            }
        }

        // if(creature instanceof Character){
        //     boolean escape = false;
        //     boolean wentForward = false;

        //     if(newPosition > 50)
        //         wentForward = true;

        //     for (Creature c : creatures) {
        //         if (c.getHostile()) {
        //             escape = true;
        //             break;
        //         }
        //     }
        // }

    }

    public int[] getPositions() {
        int[] positionsCopy = new int[positions.length];
        for (int i = 0; i < positions.length; i++) {
            positionsCopy[i] = positions[i];
        }
        return positionsCopy;
    }

    public Creature[] getCreatures() {
        Creature[] creaturesCopy = new Creature[creatures.length];
        for (int i = 0; i < creatures.length; i++) {
            creaturesCopy[i] = creatures[i];
        }
        return creaturesCopy;
    }
    
}
