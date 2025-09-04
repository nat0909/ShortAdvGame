

public class Goblin extends Creature {
    private int num;

    public Goblin(int level, int num) {
        super(5 + level, 5, true, level); // Goblins have a max health of 5 + their level and speed of 5
        this.num = num;
    }

    public void performTurn(int[] positions, int index, Senario senario) {
               
        // Move: close to target as possible
        senario.moveCreature(move(positions, index), index);

        // If target is in range (adjacent to goblin):)
            // Action + Bonus Action: double dagger attack with dagger (range 1)
        // Else if target is in range (up to 3 spaces away):
            // Action: attack sling (range 3)
        // Else
            // It isn't able to attack

    }

    public int daggerAttack(boolean bonusAttack) {  
        if(bonusAttack) {
            return 1 + super.getLevel() / 2; // Goblin's dagger attack when using a bonus action
        } 
        return 3 + super.getLevel(); // Goblin's dagger attack when using an action
    }

    public int slingAttack() {
        return super.getLevel();
    }

    public int move(int[] positions, int index) { // Distance from char
        int position = positions[index];
        int charPosition = positions[0];

        return StringUtil.findClosestNum(position, charPosition, super.getSpeed(), positions);
    }

    public int getNum() {
        return num;
    }
}
