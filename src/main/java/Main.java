import java.io.ByteArrayInputStream;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();

        Parser parser = new Parser();
        Tree result;
        try {
            result = parser.parse(new ByteArrayInputStream(line.getBytes()));
            System.out.println(result);
        } catch (ParseException e) {
            System.out.println(e.toString());
        }
    }
}
