public class StringUtil {
    
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
}
