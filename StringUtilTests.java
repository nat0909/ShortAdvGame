import static org.junit.Assert.*;
import org.junit.Test;

public class StringUtilTests {

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
        String expected = "ok";
        String result = StringUtil.interpretUserInput(options, input);
        assertEquals(expected, result);
    }

    @Test
    public void testValidPositiveInteger() {
        assertTrue(StringUtil.isInteger("123"));
    }

    @Test
    public void testValidNegativeInteger() {
        assertTrue(StringUtil.isInteger("-456"));
    }

    @Test
    public void testZero() {
        assertTrue(StringUtil.isInteger("0"));
    }

    @Test
    public void testEmptyString() {
        assertFalse(StringUtil.isInteger(""));
    }

    @Test
    public void testWhitespaceOnly() {
        assertFalse(StringUtil.isInteger("   "));
    }

    @Test
    public void testNonNumericCharacters() {
        assertFalse(StringUtil.isInteger("abc"));
        assertFalse(StringUtil.isInteger("12a"));
        assertFalse(StringUtil.isInteger("!@#"));
    }

    @Test
    public void testDecimalNumber() {
        assertFalse(StringUtil.isInteger("3.14"));
    }

    @Test
    public void testNullInput() {
        assertFalse(StringUtil.isInteger(null));
    }

    @Test
    public void testIntegerOverflow() {
        assertTrue(StringUtil.isInteger(String.valueOf(Integer.MAX_VALUE)));
        assertTrue(StringUtil.isInteger(String.valueOf(Integer.MIN_VALUE)));
        assertFalse(StringUtil.isInteger("999999999999999999999999999999"));
    }


}