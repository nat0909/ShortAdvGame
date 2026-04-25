import java.util.Scanner;

public class CharacterTurn {
        
    public static boolean performTurn(Senario senario, Character character, Scanner console) { 
        System.out.print("\nIt's your turn! ");

        boolean usedMovement = false;
        boolean usedAction = false;
        boolean usedBonusAction = false;

        while(!usedMovement || !usedAction || !usedBonusAction) {
            System.out.print("What would you like to do? ");
            String input = console.nextLine().trim().toLowerCase();
            String[] options = character.getOptions();

            // Ensures it is a valid option
            String choice = StringUtil.findOption(options, input);
            if(choice.equals("Invalid Input")) {
                choice = StringUtil.interpretUserInput(options, input);
                if(!StringUtil.clarify(choice, console)) 
                    continue; // Restarts the loop so the character can pick again if they say no
            }

            if (choice == "Move") {
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
            } else if (choice == "Melee attack") {
                if(usedAction) {
                    System.out.println("You've already used your action this turn.");
                } else {
                    String charClass = character.getCharClass();
                    int[] positions = senario.getPositions();
                    if(charClass.equals("Warlock")) {
                        //int damage = Actions.warlockMeleeAttack(character.getLevel());
                    } else if (charClass.equals("Cleric")) {
                        //int damage = Actions.clericMeleeAttack(character.getLevel());
                    } else if (charClass.equals("Eldritch Knight")) {
                        //int damage = Actions.eldritchKnightMeleeAttack(character.getLevel());
                    }
                }
            } else if (choice == "End turn") {
                usedMovement = true;
                usedAction = true;
                usedBonusAction = true;
            } else {
                System.out.println("Please pick one of the options.");
            }

        }

        System.out.println(character.getName() + "'s turn has ended.");
        return false; // Continue the senario

    }

    // Returns true if movement was successful, false if it wasn't
    public static String move(Senario senario, Scanner console, Character character) { //TODO: handle escape/end of the senario (character moves past 50 or before 0)
        int[] positions = senario.getPositions();
        int charPosition = positions[0];

        // Loops until the player picks a number to move to or decides not to move
        while(true) {
            System.out.println("\nYou are currently on square " + charPosition + ", and you have a movement speed of " + character.getSpeed() + ".");
            System.out.print("How far would you like to move? (+/- a num, x to pick a different option) "); // TODO: change move system to +/- a num instead of typing a sqaure

            // Checks if the input is empty, has a length less than 2 length, does not have + or - first, or is not num after that
            // If this is the case, it sends an error message and restarts the loop
            String userInput = console.nextLine().trim();
            if (userInput.isEmpty() || userInput.length() < 2 ||
            !(userInput.substring(0, 1).equals("+") || userInput.substring(0, 1).equals("-")) ||
            !StringUtil.isInteger(userInput.substring(1))) {
                System.out.println("Please pick one of the options.");
                continue;
            }
            
            // Checks if the user is asking to repick
            if (userInput.equals("x") || userInput.equals("X"))
                return "Repick";

            String direction = userInput.substring(0,1);
            int squares = Integer.parseInt(userInput.substring(1));
            int charSpeed = character.getSpeed();
            int square;

            if(direction.equals("+")) {
                square = charPosition + squares;
            } else if (direction.equals("-")) {
                square = charPosition - squares;
            } else {
                System.out.println("Error: Wasn't + or - and wasn't caught earlier\nLocation: move in CharacterTurn");
                return "error";
            }

            // Checks if the move is valid and sends the cooresponding error msg if it isn't
            if(squares > charSpeed) {
                System.out.println("You can only move up to " + charSpeed + " spaces.");
            } else {
                for(int position = 1; position < positions.length; position++) {
                    if(positions[position] == square) {
                        System.out.println("You can't move onto the same square as another creature.");
                        break;
                    } else if (position == positions.length - 1) {
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
            }

        }
    }   

}
