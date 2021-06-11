package ueb08;

public class BinarySearchTree<K extends Comparable<K>> extends BinaryTree<K> {
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
        if (this.key == null) {
            this.key = key;
            return true;
        }

        int compareToKey = this.key.compareTo(key);

        // this.key < key
        if (compareToKey < 0) {
            if (right == null) {
                right = new BinarySearchTree<>(null, key, null);
                return true;
            }

            getRight().addKey(key);
            return true;
        }

        // this.key > key
        if (compareToKey > 0) {
            if (left == null) {
                left = new BinarySearchTree<>(null, key, null);
                return true;
            }

            getLeft().addKey(key);
            return true;
        }

        return false;
    }

    public BinarySearchTree<K> searchKey(K key) {
        int compareToKey = this.key.compareTo(key);

        if (compareToKey == 0) {
            return this;
        }

        if (compareToKey < 0) {
            return getRight().searchKey(key);
        }

        if (compareToKey > 0) {
            return getLeft().searchKey(key);
        }

        return null;
    }

    public BinarySearchTree<K> removeKey(K key) {
        if (this.key.compareTo(key) == 0) {
            if (this.right != null && this.left == null) {
//                K k = removeSymmetricPredecessor(this);
                return this;
            }
        }
    }

    private K removeSymmetricPredecessor(BinarySearchTree<K> parent) {

        BinarySearchTree<K> cur;
    }

}
