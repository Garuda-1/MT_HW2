import java.util.Arrays;
import java.util.List;

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
        sb.append("(\"").append(node).append("\"");
        if (children != null) {
            for (Tree t : children) {
                sb.append(" ").append(t.toString());
            }
        }
        sb.append(")");
        return sb.toString();
    }
}
