package composite;

import java.util.ArrayList;
import java.util.List;

/**
 * @author:李罡毛
 * @date:2021/2/21 15:28
 * 组合模式
 */
public class Test {
    public static void main(String[] args) {
        BranchNode root = new BranchNode("root");
        BranchNode chapter1 = new BranchNode("chapter1");
        BranchNode chapter2 = new BranchNode("chapter2");
        Node r1 = new LeafNode("r1");
        Node c11 = new LeafNode("c11");
        Node c12 = new LeafNode("c12");
        BranchNode b21 = new BranchNode("section21");
        Node c211 = new LeafNode("c211");
        Node c212 = new LeafNode("c212");

        root.add(chapter1);
        root.add(chapter2);
        root.add(r1);
        chapter1.add(c11);
        chapter1.add(c12);
        chapter2.add(b21);
        b21.add(c211);
        b21.add(c212);

        tree(root, 0);

    }
    static void tree(Node node ,int depth){
        for (int i = 0; i < depth; i++) System.out.print("--");
        node.p();
        if (node instanceof BranchNode){
            for (Node n : ((BranchNode) node).getNodes()) {
                tree(n,depth+1);
            }
        }

    }
}
interface Node{
    void p();
}
class LeafNode implements Node{

    private String name;

    public LeafNode(String name) {
        this.name = name;
    }

    @Override
    public void p() {
        System.out.println(name);
    }
}
class BranchNode implements Node{

    private String name;
    private List<Node> nodes = new ArrayList<>();

    @Override
    public void p() {
        System.out.println(name);
    }

    public BranchNode(String name) {
        this.name = name;
    }

    public void add(Node node){
        this.nodes.add(node);
    }

    public List<Node> getNodes() {

        return nodes;
    }
}
