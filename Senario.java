import java.util.Scanner;

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
        
        for (int i = 1; i < creatures.length; i++) {
            creatures[i] = monsters[i - 1];
            if(ambush) { // Place every other monster behind the character if it's an ambush
                if(i % 2 == 0) {    
                    positions[i] = positions[0] - (i/2 * 5); // Place monsters 5 spaces apart behind the character
                } else {
                    positions[i] = positions[0] + ((i + 1)/2 * 5); // Place monsters 5 spaces apart in front of the character
                }
            } else {
                positions[i] = positions[0] + (5 * i); // Place monsters 5 spaces apart in front of the character
            }
        }
    }

    public void runSenario(Character character, Scanner s) {
        System.out.println("\nLoading Senario...\n");  
        drawSenario();
        
        boolean senarioOver = false;
        int curCreatureIndex = 0;
        
        while(!senarioOver) {
            if(curCreatureIndex == 0) {
                senarioOver = CharacterTurn.performTurn(this, character, s);
            } else if(creatures[curCreatureIndex] instanceof Goblin) {
                Goblin goblin = (Goblin) creatures[curCreatureIndex];
                System.out.println("\nIt's Goblin" + goblin.getNum() + "'s turn!");
                goblin.performTurn(positions, curCreatureIndex, this);
            } else { // TODO: when adding new creature type, add them here as well
                System.out.println("A creature in the list of creatures for this senario isn't in the runSenario method in the Senario class.");
            }
            curCreatureIndex = (curCreatureIndex + 1) % creatures.length;
            drawSenario();
        }
        
    }

    public void drawSenario() {    
        for(int square = 1; square <= 50; square++) {
            // Search for creatures at the position of the current square
            boolean creatureFound = false;
            for(int i = 0; i < positions.length; i++) {
                if(positions[i] == square) {
                    Creature creature = creatures[i];
                    String name = "";
                    if(creature instanceof Character) {
                        name = ((Character) creature).getName().substring(0,2);
                    } else if(creature instanceof Goblin) {
                        name = "G" + ((Goblin) creature).getNum();
                    } else { // TODO: when adding new creature type, add them here as well
                        name = "error see drawSenario method in the Senario class";
                    }

                    System.out.print(name + " ");
                    creatureFound = true;
                    break;
                }
            }
            if(!creatureFound) {
                System.out.print("__ ");
            }
        }
        System.out.println("\n");

    }

    public void endSenario() {
        // TODO: implement loot and exp gain
        // TODO: implement escape vs all enemies defeated (to determine if they get loot) and forward vs backward (to determine where the character ends up, depending on if they went <0 or >50)
    }

    public void moveCreature(int newPosition, int index) {
        positions[index] = newPosition;
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
