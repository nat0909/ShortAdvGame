
// Each senario has 1 character and 1-9 monsters in an ambush or 1-7 monsters in a normal encounter
public class Senario {
    private String[] creatures; // Character included
    private int[] positions; // Positions of the creatures on a linear path

    public Senario(String[] monsters, boolean ambush) {
        creatures = new String[monsters.length + 1];
        positions = new int[monsters.length + 1];
        
        creatures[0] = "Character";
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

    public void generateSenario() {
        System.out.println("Loading Senario...");        
        System.out.println("\n\n");

        for(int square = 5; square <= 50; square+=5) {
        // Search for creatures at this position
            System.out.println("_ _ _ _ ");
            boolean creatureFound = false;
            for(int i = 0; i < positions.length; i++) {
                if(positions[i] == square) {
                    System.out.print(creatures[i].charAt(0) + " ");
                    creatureFound = true;
                    break;
                }
            }
            if(!creatureFound) {
                System.out.println("_ ");
            }
        }

    }

    public int[] getPositions() {
        return positions;
    }

    public String[] getCreatures() {
        return creatures;
    }
    
}
