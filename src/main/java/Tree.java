import java.io.*;
import java.util.*;

public class Tree {
    String node;
    List<Tree> children;

    public Tree(String node, Tree... children) {
        this.node = node;
        this.children = Arrays.asList(children);
    }

    public Tree(String node) {
        this.node = node;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("(").append(node);
        if (children != null) {
            for (Tree t : children) {
                sb.append(" ").append(t.toString());
            }
        }
        sb.append(")");
        return sb.toString();
    }

    private static String getTag(final int n) {
        return String.format("n%03d", n);
    }

    public static void convertToDot(final Tree tree, final String input, final String filename) throws IOException {
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(filename)))) {
            final Queue<Map.Entry<Tree, Integer>> q = new LinkedList<>();
            q.add(new AbstractMap.SimpleEntry<>(tree, 0));
            int nodeCounter = 1;

            writer.println("graph \"\"");
            writer.println("\t{");
            writer.format("\tlabel=\"%s\"\n", input);
            writer.println();
            writer.format("\t%s ;\n", getTag(0));

            while (!q.isEmpty()) {
                final Map.Entry<Tree, Integer> entry = q.poll();
                writer.format("\t%s [label=\"%s\"] ;\n", getTag(entry.getValue()), entry.getKey().node);
                if (entry.getKey().children == null) {
                    continue;
                }
                for (Tree child : entry.getKey().children) {
                    writer.format("\t%s -- %s ;\n", getTag(entry.getValue()), getTag(nodeCounter));
                    q.add(new AbstractMap.SimpleEntry<>(child, nodeCounter));
                    nodeCounter++;
                }
            }

            writer.println("\t}");
        }
    }
}
