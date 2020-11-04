import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Parsing error tests")
public class ParsingErrorsTests extends AbstractParserTests {
    private void testWithThrown(final String input, final String expectedMessage) {
        Throwable thrown = assertThrows(ParseException.class, () -> test(input));
        assertNotNull(thrown);
        assertEquals(thrown.getClass(), ParseException.class);
        assertEquals(expectedMessage, thrown.getMessage());
    }

    @Test
    @DisplayName("Empty input")
    public void testEmptyInput() {
        testWithThrown("", "Illegal character \uFFFF at position 1");
    }

    @Test
    @DisplayName("Typo in \"var\" keyword")
    public void testTypoInVarKeyword() {
        testWithThrown("vsr x: Array<Int>;", "Illegal character s at position 2");
    }

    @Test
    @DisplayName("\"var\" keyword case sensitivity")
    public void testVarKeywordCaseSensitivity() {
        testWithThrown("Var x: Array<Int>;", "Illegal character V at position 1");
    }

    @Test
    @DisplayName("Missing colon")
    public void testMissingColon() {
        testWithThrown("var x Array<Int>;", "Illegal character < at position 12");
    }

    @Test
    @DisplayName("Not Array type")
    public void testNotArrayType() {
        testWithThrown("var x: List<Int>;", "Illegal character L at position 8");
    }

    @Test
    @DisplayName("Missing Array generic")
    public void testMissingArrayGeneric() {
        testWithThrown("var x: Array;", "Illegal character ; at position 13");
    }

    @Test
    @DisplayName("Missing semicolon")
    public void testMissingSemicolon() {
        testWithThrown("var x: Array<Int>", "Illegal character \uFFFF at position 18");
    }

    @Test
    @DisplayName("Invalid identifier")
    public void testInvalidIdentifier() {
        testWithThrown("var 3: Array<4>;", "Illegal character 3 at position 5");
    }
}
