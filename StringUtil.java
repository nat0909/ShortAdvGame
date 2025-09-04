import java.util.HashSet;
import java.util.Set;
import java.util.Scanner;

public class StringUtil {
    
    /**
     * Searches for a user-provided input within a list of valid options, ignoring case sensitivity.
     *
     * @param options An array of valid string options to match against.
     * @param input   The user's input string to be validated.
     * @return        The matching option if found; otherwise, returns "Invalid Input".
     */
    public static String findOption(String[] options, String input) {
        for(String option : options) {
            if(option.equalsIgnoreCase(input)) {
                return option;
            }
        }
        return "Invalid Input";
    }
    
    
    /**
     * Interprets user input by matching it against an array of predefined options to determine the most likely intended
     * choice so that when the user misspells a word, the code will interpret what they likely meant.
     *
     * @param options An array of strings representing the valid choices.
     * @param input   A string containing the user-provided input to evaluate.
     * @return        The best-matching option, or "Invalid Input" if there’s no clear match.
     */
    public static String interpretUserInput(String[] options, String input) {
        
        int[] possibleOptions = new int[options.length];

        for(int letter = 0; letter < input.length(); letter++) {
            for(int option = 0; option < options.length; option++){
                String curOption = options[option];
                if(curOption.length() > letter && curOption.charAt(letter) == input.charAt(letter)) { 
                    possibleOptions[option]++;
                }
            }
        }

        int max = 0;
        boolean multBestOptions = true;
        String bestOption = "";
        for(int option = 0; option < options.length; option++) {
            if(possibleOptions[option] > max) {
                max = possibleOptions[option];
                multBestOptions = false;
                bestOption = options[option];
            } else if(possibleOptions[option] == max) {
                multBestOptions = true;
            }
        }

        if(multBestOptions == true){
            return "Invalid Input";
        } else {
            return bestOption;
        }

    }

    /**
     * Prompts the user to confirm a guessed option and interprets their response.
     *
     * @param guess    The guessed option to confirm with the user.
     * @param console  A Scanner object used to read user input from the console.
     * @return         True if the user confirms the guess; false otherwise.
     */
    public static boolean clarify(String guess, Scanner console) {
        if(guess.equals("Invalid Input")) {
            System.out.println("You're input was unclear. Please retype your class. ");
        } else {
            System.out.print("Did you mean " + guess + "? (y/n) ");
            String response = console.nextLine().trim().toLowerCase();
            if(response.equals("y") || response.equals("yes")) {
                return true;
            } else {
                System.out.println("Please pick one of the options.");
            }
        }

        return false;
    }


    /**
     * Finds the closest available number to the target, starting from a given current number,
     * searching in one direction (up or down), and avoiding numbers that are already taken.
     *
     * @param cur     The number to start the search from.
     * @param target  The target number to get as close to as possible.
     * @param max     The maximum distance to search from cur.
     * @param taken   An array of numbers that are already taken and should be avoided.
     * @return        The closest available number to the target that is not taken.
     */
    public static int findClosestNum(int cur, int target, int max, int[] taken) { // TODO: review and impliment tests
        // Create a set for fast lookup of taken numbers
        Set<Integer> takenSet = new HashSet<>();
        for (int num : taken) {
            takenSet.add(num);
        }

        int closest = cur; // Start with cur as the default
        int minDiff = Math.abs(cur - target);

        // Determine search direction
        boolean searchUp = cur < target;

        // Search only in one direction
        for (int i = 1; i <= max; i++) {
            int candidate = searchUp ? cur + i : cur - i;
            if (takenSet.contains(candidate)) continue;

            int diff = Math.abs(candidate - target);
            if (diff < minDiff) {
                minDiff = diff;
                closest = candidate;
            }
        }

        return closest;

    }

    /**
     * Checks if a string is an integer.
     *
     * @param input   The String it checks if it is a number.
     * @return        True if input is number; false otherwise.
     */
    public static boolean isInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException | NullPointerException e) {
            return false;
        }
    }

}

