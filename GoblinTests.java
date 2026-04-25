import static org.junit.Assert.*;
import org.junit.Test;

public class GoblinTests {

    private Goblin sampleGoblin1(){
        return new Goblin(3, 1); // Level = 3
    }

    @Test
    public void testConstructorAndGetters() { // Many of the getters are from Creature
        Goblin g = sampleGoblin1();
        assertEquals(8, g.getHealth());
        assertEquals(5, g.getSpeed());
        assertTrue(g.isHostile());
        assertEquals(3, g.getLevel());
        assertEquals(1, g.getNum());
    }

    @Test
    public void testDaggerAttackBonusAttack() {
        Goblin g1 = sampleGoblin1();
        int attack1 = g1.daggerAttack(true);
        assertEquals(2, attack1);
    }

    @Test
    public void testDaggerAttackNotBonusAttack() {
        Goblin g1 = sampleGoblin1();
        int attack1 = g1.daggerAttack(false);
        assertEquals(6, attack1);
    }

    public void testSlingAttack() {
        Goblin g1 = sampleGoblin1();
        int slingAttack1 = g1.slingAttack();
        assertEquals(3, slingAttack1);
    }

    // Move method uses Senario

}
