import java.util.Scanner;

/* 
 * Will prompt the user for their character's name, class, and race
 * Used by the Character class to create a new character
 */

public class CharacterCreation {
    
    public static Character create() {
        Scanner s = new Scanner(System.in);
        String charName = charName(s);
        String charClass = charClass(s);
        String charRace = charRace(s);
        s.close();
        Character c = new Character(charName, charClass, charRace);
        return c;
    }

    private static String charName(Scanner console) {
        System.out.print("What is your character's name? ");
        String charName = console.next();
        return charName;
    }

    private static String charClass(Scanner console){
        System.out.print("There are four classes you can choose from: wizard, cleric, warlock, or eldritch kight. ");
        int classAccepted = 0;
        /* Will loop until the player picks a valid class */
        while(classAccepted == 0){
            System.out.print("What is your character's class? ");
            String charClass = console.next().toLowerCase();
            if(charClass.equals("wizard") || charClass.equals("wiz")){
                return "Wizard";
            } else if(charClass.equals("cleric") || charClass.equals("c")){
                return "Cleric";
            } else if(charClass.equals("warlock") || charClass.equals("war")){
                return "Warlock";
            }  else if(charClass.equals("eldirtch_kight") || charClass.equals("eldritch") || charClass.equals("e") || charClass.equals("ek")){
                return "Eldritch Kight";
            } else {
                System.out.println("That is not one of the options. Please pick one of the provided options.");
            }
        }
        return "error";
    }

    private static String charRace(Scanner console){
        System.out.print("There are three races you can choose from: elf, human, or dwarf. ");
        int classAccepted = 0;
        /* Will loop until the player picks a valid race */
        while(classAccepted == 0){
            System.out.print("What is your character's race? ");
            String race = console.next().toLowerCase();
            if(race.equals("elf") || race.equals("e")){
                return "Elf";
            } else if(race.equals("human") || race.equals("h")){
                return "Human";
            } else if(race.equals("dwarf") || race.equals("d")){
                return "Dwarf";
            } else {
                System.out.println("That is not one of the options. Please pick one of the provided options.");
            }
        }        
        return "error";
    }

}