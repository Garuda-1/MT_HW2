import java.io.ByteArrayInputStream;

public abstract class AbstractParserTests {
    protected final Parser parser = new Parser();

    protected void test(final String input) throws ParseException {
        parser.parse(new ByteArrayInputStream(input.getBytes()));
    }
}
