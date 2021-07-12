package ueb08;

public class BinarySearchTree<K extends Comparable<K>> extends BinaryTree<K> {
    /**
     * creates a new instance
     *
     * @param left  the left subtree
     * @param key   the key of the tree node
     * @param right the right subtree
     */
    public BinarySearchTree(BinarySearchTree<K> left, K key, BinarySearchTree<K> right) {
        super(left, key, right);
    }

    public BinarySearchTree() {
        super(null, null, null);
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

    public void addKey(K key) {
        if (this.key == null) {
            this.key = key;
        }

        int compareToKey = this.key.compareTo(key);

        // this.key < key
        if (compareToKey < 0) {
            if (getRight() == null) {
                right = new BinarySearchTree<>(null, key, null);

            }

            getRight().addKey(key);
        }

        // this.key > key
        if (compareToKey > 0) {
            if (left == null) {
                left = new BinarySearchTree<>(null, key, null);
            }

            getLeft().addKey(key);
        }

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

    public void remove(K key) {

        BinarySearchTree<K> parent = null;
        BinarySearchTree<K> current = this;

        // search key in BST and set its parent pointer
        while (current != null && current.key != key) {
            parent = current;

            // key < current.key
            if (key.compareTo(current.key) < 0) {
                current = current.getLeft();
            }
            // key > current.key
            else {
                current = current.getRight();
            }
        }

        // return if key is not found in the tree
        if (current == null) return;

        // case 1: node to be deleted has no children
        if (current.left == null && current.right == null) {

            // if the node to be deleted is not the root node then set its parent left/right child to nill
            if (current != this) {
                if (parent.left == current) {
                    parent.left = null;
                } else {
                    parent.right = null;
                }
            }
            // if the tree has only a root node set it´s key to nill
            else {
                this.key = null;
            }
        }

        // case 2: node to be deleted has two children
        else if (current.left != null && current.right != null) {

            BinarySearchTree<K> successor = getMinKey(current.getRight());

            K min = successor.key;

            System.out.println(99);
            remove(min);

            current.key = min;

        }



        // case 3: node to be deleted has only one child
        else {

            // choose the child node
            BinarySearchTree<K> child;
            if (current.left != null) child = current.getLeft();
            child = current.getRight();

            if (current != this) {
                if (current == parent.getLeft()) {
                    parent.left = child;
                } else {
                    parent.right = child;
                }
            }
            // if the node to be deleted is a root node then set the root node to the child
            else {
                this.key = child.key;
            }
        }
    }


    private void removeSymmetricPredecessor(BinarySearchTree<K> current) {

        BinarySearchTree<K> successor = current.getRight();


        while (successor.left != null) {
            successor = successor.getLeft();
        }

        current.key = successor.key;

        if (successor.right != null) {
            successor.key = successor.getRight().key;
            successor.left = successor.getRight().getLeft();
            successor.right = successor.getRight().getRight();
        }

    }

    public BinarySearchTree<K> getMinKey(BinarySearchTree current) {

        while (current.getLeft() != null) {
            current = current.getLeft();
        }

        return current;
    }

    public BinarySearchTree<K> getMaxKey() {
        BinarySearchTree<K> current = this;

        while (current.getRight() != null) {
            current = current.getRight();
        }

        return current;
    }

    public BinarySearchTree<K> getPredecessor(BinarySearchTree<K> node) {

        BinarySearchTree<K> current = this;
        BinarySearchTree<K> pred = null;


        while (true) {
            if (node.key.compareTo(current.key) < 0) {
                current = current.getLeft();
            }

            else if(node.key.compareTo(current.key) > 0) {
                pred = current;
                current = current.getRight();
            } else {
                if (current.left != null) {
                    pred = getMaxKey();
                }
                break;
            }

            if (current == null) return null;

        }

        return pred;
    }

    public BinarySearchTree<K> getSuccessor(BinarySearchTree<K> node) {

        BinarySearchTree<K> current = this;
        BinarySearchTree<K> successor = null;

        while (true) {

            // if the given key is less than the root visit the left subtree
            if (node.key.compareTo(current.key) < 0) {
                successor = current;
                current = current.getLeft();
            }

            else if (node.key.compareTo(current.key) > 0) {
                current = current.getRight();
            } else {
                if (current.right != null) {
                    successor = getMinKey(current);
                }
                break;
            }

            if (current == null) return null;
        }
        return successor;
    }

    public static void main(String[] args) {

        BinarySearchTree<Integer> binarySearchTree = new BinarySearchTree<Integer>(
                new BinarySearchTree<Integer>(
                        new BinarySearchTree<>(null, 8, null),
                        10,
                        new BinarySearchTree<>(null, 12, null)),
                15,
                new BinarySearchTree<Integer>(
                        new BinarySearchTree<Integer>(
                                new BinarySearchTree<>(null, 16, null),
                                18,
                                new BinarySearchTree<>(null, 19, null)),
                        20,
                        new BinarySearchTree<>(null, 30, null))
        );



        binarySearchTree.remove(20);



    }

}
