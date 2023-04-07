//**Create a class for the clusters that contains a list of nodes in the cluster and the cluster head.**
import java.util.ArrayList;

public class p2 {
    private ArrayList<Node> nodes; // list of nodes in the cluster
    private Node head; // cluster head

    // constructor
    public p2(Node head_node) {
        head = head_node;
        nodes = new ArrayList<Node>();
        nodes.add(head_node);
    }

    // add a node to the cluster
    public void add_node(Node node) {
        nodes.add(node);
    }

    // get the list of nodes in the cluster
    public ArrayList<Node> get_nodes() {
        return nodes;
    }

    // get the cluster head
    public Node get_head() {
        return head;
    }

    // set the cluster head
    public void set_head(Node new_head) {
        head = new_head;
    }
}
