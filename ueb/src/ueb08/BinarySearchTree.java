package ueb08;

public class BinarySearchTree <K extends Comparable<K>> extends BinaryTree<K> {
    /**
     * creates a new instance
     *
     * @param left  the left subtree
     * @param key   the key of the tree node
     * @param right the right subtree
     */
    public BinarySearchTree(BinaryTree<K> left, K key, BinaryTree<K> right) {
        super(left, key, right);
    }

    /**
     * returns the left subtree
     */
    public BinarySearchTree<K> getLeft() {
        return (BinarySearchTree<K>) left;
    }

    /**
     * returns the right subtree
     */
    public BinarySearchTree<K> getRight() {
        return (BinarySearchTree<K>) right;
    }

    public boolean addKey(K key) {


    }

    public BinarySearchTree<K> searchKey(K key) {

    }

    public BinarySearchTree<K> removeKey(K key) {

    }
}
