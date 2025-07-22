public class Character {

    private String name;
    private String charClass;
    private String race;

    public Character(String name, String charClass, String race) {
        this.name = name;
        this.charClass = charClass;
        this.race = race;
    }

    public String getInfo() {
         return "Name: " + name + "\nClass: " + charClass + "\nRace: " + race; 
    }
    
}