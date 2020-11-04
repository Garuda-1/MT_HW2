import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class IdentifierTests extends AbstractParserTests {
    @Test
    @DisplayName("Only alphabet identifier")
    public void testOnlyAlphabetIdentifier() throws ParseException {
        test("var someArray: Array<SomeClass>;");
    }

    @Test
    @DisplayName("Alphabet and underscores identifier")
    public void testAlphabetAndUnderscoresIdentifier() throws ParseException {
        test("var camel_case: Array<Weird_Case>;");
    }

    @Test
    @DisplayName("Alphabet and digits identifier")
    public void testAlphabetAndDigitsIdentifier() throws ParseException {
        test("var a0: Array<Type3>;");
    }

    @Test
    @DisplayName("Digits and underscores identifier")
    public void testDigitsAndUnderscoresIdentifier() throws ParseException {
        test("var _0: Array<_42>;");
    }

    @Test
    @DisplayName("All types of characters identifier")
    public void allTypesOfCharactersIdentifier() throws ParseException {
        test("var MT19937_joke: Array<__depreCated__>;");
    }
}
