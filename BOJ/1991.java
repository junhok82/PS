import java.io.*;
import java.util.*;

public class Main {
    static class Node {
        int data;
        Node left;
        Node right;

        public Node(int data, Node left, Node right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }

        private void preOrder(Node node) {
            if(node != null) {
                System.out.print((char) (node.data + 'A'));
                preOrder(node.left);
                preOrder(node.right);
            }
        }

        private void inOrder(Node node) {
            if(node != null) {
                inOrder(node.left);
                System.out.print((char) (node.data + 'A'));
                inOrder(node.right);
            }
        }

        private void postOrder(Node node) {
            if(node != null) {
                postOrder(node.left);
                postOrder(node.right);
                System.out.print((char) (node.data + 'A'));
            }
        }
        private void print(Node root) {
            this.preOrder(root);
            System.out.println();
            this.inOrder(root);
            System.out.println();
            this.postOrder(root);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Map<Integer,Node> map = new HashMap<>();

        for (int i = 0; i < 27; i++) {
            map.put(i,new Node(i,null,null));
        }

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            Node newNode = map.get(str.charAt(0) - 'A');
            if(str.charAt(2) != '.') newNode.left = map.get(str.charAt(2) - 'A');
            if(str.charAt(4) != '.') newNode.right = map.get(str.charAt(4) - 'A');
        }

        Node root = map.get(0);
        root.print(root);
    }
}
