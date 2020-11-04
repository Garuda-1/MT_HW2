import java.io.IOException;
import java.io.InputStream;

public class LexicalAnalyzer {
    private final InputStream is;
    private int curChar;
    private int curPos;
    private Token curToken;

    public LexicalAnalyzer(final InputStream is) {
        this.is = is;
        curPos = 0;
    }

    private static boolean isBlank(final int c) {
        return c == ' ' || c == '\r' || c == '\n' || c == '\t';
    }

    private void nextChar() throws ParseException {
        curPos++;
        try {
            curChar = is.read();
        } catch (IOException e) {
            throw new ParseException(e);
        }
    }

    public void nextToken() throws ParseException {
        nextChar();
        while (isBlank(curChar)) {
            nextChar();
        }
        if (Character.isAlphabetic(curChar) || curChar == '_') {
            curToken = Token.ALPHA_OR_UNDERSCORE;
        } else if (Character.isDigit(curChar)) {
            curToken = Token.DIGIT;
        } else if (curChar == ':') {
            curToken = Token.COLON;
        } else if (curChar == ';') {
            curToken = Token.SEMICOLON;
        } else if (curChar == ',') {
            curToken = Token.COMMA;
        } else if (curChar == '<') {
            curToken = Token.LEFT_ANGLE_BRACKET;
        } else if (curChar == '>') {
            curToken = Token.RIGHT_ANGLE_BRACKET;
        } else if (curChar == -1) {
            curToken = Token.END;
        } else {
            throw new ParseException(String.format("Unknown character %c at position %d", (char) curChar, curPos));
        }
    }

    public Token getCurToken() {
        return curToken;
    }

    public int getCurPos() {
        return curPos;
    }

    public int getCurChar() {
        return curChar;
    }
}
