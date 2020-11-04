public class ParseException extends Exception {
    public ParseException(String message) {
        super(message);
    }

    public ParseException(Throwable cause) {
        super(cause);
    }

    ParseException(LexicalAnalyzer lex) {
        super(String.format("Illegal character %c at position %d", (char) lex.getCurChar(), lex.getCurPos()));
    }
}
