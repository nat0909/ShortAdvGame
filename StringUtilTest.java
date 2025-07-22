import static org.junit.Assert.*;
import org.junit.Test;

public class StringUtilTest {

    @Test
    public void testValidUniqueMatch() {
        String[] options = {"apple", "banana", "grape"};
        String input = "apgle";
        String expected = "apple"; // 'apple' matches best
        String result = StringUtil.interpretUserInput(options, input);
        assertEquals(expected, result);
    }

    @Test
    public void testNoClearMatch() {
        String[] options = {"cat", "car", "cab"};
        String input = "cap";
        String expected = "Invalid Input"; // equally close matches
        String result = StringUtil.interpretUserInput(options, input);
        assertEquals(expected, result);
    }

    @Test
    public void testExactMatch() {
        String[] options = {"dog", "duck", "donkey"};
        String input = "duck";
        String expected = "duck"; // should return exact match
        String result = StringUtil.interpretUserInput(options, input);
        assertEquals(expected, result);
    }

    @Test
    public void testEmptyInput() {
        String[] options = {"yes", "no"};
        String input = "";
        String expected = "Invalid Input";
        String result = StringUtil.interpretUserInput(options, input);
        assertEquals(expected, result);
    }

    @Test
    public void testInputLongerThanOptions() {
        String[] options = {"ok", "go"};
        String input = "okay";
        String expected = "Invalid Input";
        String result = StringUtil.interpretUserInput(options, input);
        assertEquals(expected, result);
    }

}