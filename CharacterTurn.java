import java.util.Scanner;

public class CharacterTurn { // TODO: FIX THIS CLASS (run it and you will see the issue)
        
    public static boolean performTurn(Senario senario, Character character, Scanner console) { // TODO: get it to tell the speed of the char at some point either in the senario in character creation
        System.out.print("It's your turn! ");

        boolean usedMovement = false;
        boolean usedAction = false;
        boolean usedBonusAction = false;

        while(!usedMovement || !usedAction || !usedBonusAction) { // TODO: add other options (other than move and end turn)
            System.out.print("What would you like to do? ");
            String input = console.nextLine().trim().toLowerCase();
            String[] options = character.getOptions();
            String guess = StringUtil.interpretUserInput(options, input);

            if(guess.equals("Invalid Input")) {
                System.out.println("You're input was unclear. Please retype your choice. ");
            } else {
                System.out.print("You'd like to " + guess + "? (y/n) "); // TODO: add this to string util since its also used in character creation
                String response = console.nextLine().toLowerCase();
                if(response.equals("y") || response.equals("yes")) {
                    if(guess.equals("move")) {
                        if(usedMovement) {
                            System.out.println("You've already used your movement this turn.");
                        } else {
                            String moveResult = move(senario, console, character);
                            if(moveResult.equals("End of Senario")) {
                                return true; // End the senario
                            } else if (moveResult.equals("Complete")) {
                                usedMovement = true;
                            }
                        }
                    } else if(guess.equals("end turn")) {
                        usedMovement = true;
                        usedAction = true;
                        usedBonusAction = true;
                    } else {
                        System.out.println("You can't use that option again this turn.");
                    }
                } else {
                    System.out.println("Please pick one of the following options: ");
                    for(String option : options)
                        System.out.println(" " + option); 
                }
            }

        }

        System.out.println(character.getName() + "'s turn has ended.");
        return false; // Continue the senario

    }

    // Returns true if movement was successful, false if it wasn't
    public static String move(Senario senario, Scanner console, Character character) { //TODO: handle escape/end of the senario (character moves past 50 or before 0)
        int[] positions = senario.getPositions();
        int charPosition = positions[0];
        System.out.println("You are currently on square " + charPosition + ".");
        System.out.print("How far would you like to move? (Type the square number) ");

        // Checks if the input is an integer and sends an error msg if it isn't
        int square;
        if (console.hasNextInt()) {
            square = console.nextInt();
            console.nextLine(); // Consume the newline character left by nextInt()
            // continue with movement logic
        } else {
            System.out.println("Please enter a valid number.");
            console.next(); // consume the invalid input
            return "error";
        }
        int distance = Math.abs(square - charPosition);
        int charSpeed = character.getSpeed();

        // Checks if the move is valid and sends the cooresponding error msg if it isn't
        if(distance > charSpeed) {
            System.out.println("You can only move up to " + charSpeed + " spaces.");
            return "error";
        } else {
            for(int position = 1; position < positions.length; position++) {
                if(positions[position] == square) {
                    System.out.println("You can't move onto the same square as another creature.");
                    return "error";
                }
            }
        }

        // Move the character, output the new square, and redraw the senario
        senario.moveCreature(square, 0);
        System.out.println(character.getName() + " moved to square " + square + ".");
        senario.drawSenario();

        if(square < 0 || square > 50) { // Character moved out of bounds
            return "End of Senario"; // Character is out of bounds, end the senario
        }
        return "Complete";

    }   

}
