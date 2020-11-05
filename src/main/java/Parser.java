import java.io.InputStream;

public class Parser {
    private LexicalAnalyzer lex;

    private void readKeyword(final String word) throws ParseException {
        for (int i = 0; i < word.length(); i++) {
            if (lex.getCurToken() != Token.ALPHA_OR_UNDERSCORE || lex.getCurChar() != word.charAt(i)) {
                throw new ParseException(lex);
            }
            lex.nextToken();
        }
    }

    private String readIdentifier() throws ParseException {
        final StringBuilder identifier = new StringBuilder();
        if (lex.getCurToken() == Token.ALPHA_OR_UNDERSCORE) {
            identifier.append((char) lex.getCurChar());
            lex.nextToken();
        } else {
            throw new ParseException(lex);
        }
        while (lex.getCurToken() == Token.ALPHA_OR_UNDERSCORE || lex.getCurToken() == Token.DIGIT) {
            identifier.append((char) lex.getCurChar());
            lex.nextToken();
        }
        if (identifier.length() == 1 && identifier.charAt(0) == '_') {
            throw new ParseException(lex);
        }
        return identifier.toString();
    }

    private Tree S() throws ParseException {
        if (lex.getCurToken() == Token.ALPHA_OR_UNDERSCORE) {
            final Tree vSubTree = V();
            final Tree ssSubTree = SS();
            return new Tree("S", vSubTree, ssSubTree);
        }
        throw new ParseException(lex);
    }
    
    private Tree V() throws ParseException {
        final String keyword = "var";
        readKeyword(keyword);
        return new Tree(keyword);
    }
    
    private Tree SS() throws ParseException {
        if (lex.getCurToken() == Token.ALPHA_OR_UNDERSCORE) {
            final Tree iSubTree = I();
            if (lex.getCurToken() != Token.COLON) {
                throw new ParseException(lex);
            }
            lex.nextToken();
            final Tree aSubTree = A();
            if (lex.getCurToken() != Token.LEFT_ANGLE_BRACKET) {
                throw new ParseException(lex);
            }
            lex.nextToken();
            final Tree gSubTree = G();
            if (lex.getCurToken() != Token.RIGHT_ANGLE_BRACKET) {
                throw new ParseException(lex);
            }
            lex.nextToken();
            if (lex.getCurToken() != Token.SEMICOLON) {
                throw new ParseException(lex);
            }
            lex.nextToken();
            return new Tree("SS", iSubTree, new Tree(":"), aSubTree, new Tree("<"), gSubTree,
                            new Tree(">"), new Tree(";"));
        } else {
            throw new ParseException(lex);
        }
    }

    private Tree I() throws ParseException {
        final String identifier = readIdentifier();
        return new Tree(identifier);
    }

    private Tree A() throws ParseException {
        final String keyword = "Array";
        readKeyword(keyword);
        return new Tree(keyword);
    }

    private Tree G() throws ParseException {
        if (lex.getCurToken() == Token.ALPHA_OR_UNDERSCORE || lex.getCurToken() == Token.DIGIT) {
            final Tree iSubTree = I();
            final Tree ggSubTree = GG();
            return new Tree("G", iSubTree, ggSubTree);
        } else {
            throw new ParseException(lex);
        }
    }

    private Tree GG() throws ParseException {
        switch (lex.getCurToken()) {
            case LEFT_ANGLE_BRACKET: {
                lex.nextToken();
                final Tree gSubTree = G();
                final Tree gggSubTree = GGG();
                if (lex.getCurToken() != Token.RIGHT_ANGLE_BRACKET) {
                    throw new ParseException(lex);
                }
                lex.nextToken();
                return new Tree("GG", new Tree("<"), gSubTree, gggSubTree, new Tree(">"));
            }
            case COMMA:
            case RIGHT_ANGLE_BRACKET: {
                return new Tree("GG");
            }
            default: {
                throw new ParseException(lex);
            }
        }
    }

    private Tree GGG() throws ParseException {
        switch (lex.getCurToken()) {
            case COMMA: {
                lex.nextToken();
                final Tree gSubTree = G();
                final Tree gggSubTree = GGG();
                return new Tree("GGG", new Tree(","), gSubTree, gggSubTree);
            }
            case RIGHT_ANGLE_BRACKET: {
                return new Tree("GGG");
            }
            default: {
                throw new ParseException(lex);
            }
        }
    }

    Tree parse(InputStream is) throws ParseException {
        lex = new LexicalAnalyzer(is);
        lex.nextToken();
        return S();
    }
}
