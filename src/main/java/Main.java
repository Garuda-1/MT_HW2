import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();

        Parser parser = new Parser();
        Tree result;
        try {
            result = parser.parse(new ByteArrayInputStream(line.getBytes()));
            Tree.convertToDot(result, line, "graphs/spread.dot");
            System.out.println(result);
        } catch (ParseException | IOException e) {
            System.out.println(e.toString());
        }
    }
}
