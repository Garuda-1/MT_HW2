import org.junit.jupiter.api.*;

@DisplayName("Grammar tests")
public class GenericTests extends AbstractParserTests {
    @Test
    @DisplayName("Simple generic (G =>* I)")
    public void testSimpleGeneric() throws ParseException {
        test("var x: Array<Int>;");
    }

    @Test
    @DisplayName("Enclosed generic (G =>* I<I>")
    public void testEnclosedGeneric() throws ParseException {
        test("var x: Array<Array<Int>>;");
    }

    @Test
    @DisplayName("Several arguments generic (G =>* I<I, I>)")
    public void testSeveralArgumentsGeneric() throws ParseException {
        test("var x: Array<Map<Int, Double>>;");
    }

    @Test
    @DisplayName("Deep generic (G =>* I<I<I<I<I>>>>)")
    public void testDeepGeneric() throws ParseException {
        test("var x: Array<Array<Array<Array<Int>>>>;");
    }

    @Test
    @DisplayName("Complex generic (G =>* I<I<I, I>, I<I<I>>>)")
    public void testComplexGeneric() throws ParseException {
        test("var x: Array<X<A<B, C>, D<E<F>>>>;");
    }
}
