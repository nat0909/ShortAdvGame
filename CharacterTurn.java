import java.util.Scanner;

public class CharacterTurn {
        
    public void playerTurn(String[] options, Senario senario, Character character) { 
        Scanner console = new Scanner(System.in);
        System.out.println("It's your turn!");
        boolean validOption = false;

        boolean usedMovement = false;
        boolean usedAction = false;
        boolean usedBonusAction = false;

        while(!validOption && !usedMovement && !usedAction && !usedBonusAction) { // TODO: add other options (other than move and end turn)
            System.out.print("What would you like to do? ");
            String input = console.nextLine().toLowerCase();
            String guess = StringUtil.interpretUserInput(options, input);
            if(guess.equals("Invalid Input")) {
                System.out.println("You're input was unclear. Please retype your class. ");
            } else {
                System.out.println("Did you mean " + guess + "? (y/n) ");
                String response = console.nextLine().toLowerCase();
                if(response.equals("y") || response.equals("yes")) {
                    if(guess.equals("Move")) {
                        if(usedMovement) {
                            System.out.println("You've already used your movement this turn.");
                        } else {
                            validOption = movement(senario, console, character);
                            usedMovement = true;
                        }
                    } else if(guess.equals("end turn")) {
                        validOption = true;
                        usedMovement = true;
                        usedAction = true;
                        usedBonusAction = true;
                    } else {
                        System.out.println("You can't use that option again this turn.");
                    }
                } else {
                    System.out.println("Please pick a valid option: ");
                    for(String option : options)
                        System.out.println(" " + option); 
                }
            }
        }

        console.close();

    }

    public boolean movement(Senario senario, Scanner console, Character character) { //TODO: handle escape/end of the senario (character moves past 50 or before 0)
        int[] positions = senario.getPositions();
        int charPosition = positions[0];
        System.out.println("You are currently on square " + charPosition + ".");
        System.out.print("How far would you like to move? (Type the square number) ");

        int square = console.nextInt(); // TODO: handle non-integer input
        int distance = square - charPosition;
        int charSpeed = character.getSpeed();

        // Checks if the move is valid and sends the cooresponding error msg if it isn't
        if(distance > charSpeed) {
            System.out.println("You can only move up to " + charSpeed + " spaces.");
            return false;
        } else {
            for(int position = 1; position < positions.length; position++) {
                if(position == square) {
                    System.out.println("You can't move onto the same square as another creature.");
                    return false;
                }
            }
        }

        senario.moveCreature(square, character);
        System.out.println(character.getName() + " moved to square " + square + ".");

        return true;

    }   


}
