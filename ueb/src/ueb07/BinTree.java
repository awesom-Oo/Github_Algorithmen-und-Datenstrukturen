package ueb07;


import java.util.LinkedList;

/**
 * This class implements a binary tree
 *
 * @param <T>
 */
public class BinTree<T> {

    protected BinTree<T> left, right;
    protected T key;

    /**
     * creates a new instance
     * @param l the left subtree
     * @param k the key of the tree node
     * @param r the right subtree
     */
    public BinTree(BinTree<T> l, T k, BinTree<T> r) {
        this.left = l;
        this.key = k;
        this.right = r;
    }

    /**
     * returns the left subtree
     */
    public BinTree<T> getLeft() {
        return left;
    }

    /**
     * returns the left subtree
     */
    public BinTree<T> getRight() {
        return right;
    }

    /**
     * returns the key
     */
    public T getKey() {
        return key;
    }

    /**
     * checks if the tree is empty
     * @return true, if tree is empty, else false
     */
    public boolean isEmpty() {
        return ((left == null) && (right == null) && (key == null));
    }

    /**
     * prints the tree in the In-Order representation on the console
     */
    public void inOrder() {
        if (left != null) {
            left.inOrder();
        }

        System.out.print(key + " ");

        if (right != null) {
            right.inOrder();
        }

    }

    /**
     * prints the tree in the Pre-Order representation on the console
     */
    public void preOrder() {
        System.out.print(key + " ");

        if (left != null) {
            left.preOrder();
        }

        if (right != null) {
            right.preOrder();
        }

    }

    /**
     * prints the tree in the Post-Order representation on the console
     */
    public void postOrder() {
        if (left != null) {
            left.postOrder();
        }

        if (right != null) {
            right.postOrder();
        }

        System.out.print(key + " ");
    }

    /**
     * prints the tree in the Level-Order representation on the console
     */
    public void levelOrder() {

        int i;
        LinkedList<BinTree<T>> tree = new LinkedList<BinTree<T>>();
        tree.add(this);

        while (!tree.isEmpty()) {
            BinTree<T> currentNode = tree.poll();
            System.out.println(currentNode.key + " ");
            if (currentNode.left != null) {
                tree.add(currentNode.left);
            }

            if (currentNode.right != null) {
                tree.add(currentNode.right);
            }
        }
    }

    public static void main(String[] args) {
        BinTree<Character> eNode = new BinTree<>(null, 'e', null);
        BinTree<Character> fNode = new BinTree<>(eNode, 'f', null);
        BinTree<Character> cNode = new BinTree<>(null, 'c', null);
        BinTree<Character> aNode = new BinTree<>(fNode, 'a', cNode);
        BinTree<Character> bNode = new BinTree<>(null, 'b', null);
        BinTree<Character> hNode = new BinTree<>(null, 'h', bNode);
        BinTree<Character> dNode = new BinTree<>(hNode, 'd', null);
        BinTree<Character> gNode = new BinTree<>(aNode, 'g', dNode);
        gNode.levelOrder();
    }

}
