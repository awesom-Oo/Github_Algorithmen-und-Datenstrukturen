package ueb08;

import java.util.ArrayList;

/**
 * A special type of BinaryTree with a structure that makes searching for elements easier
 * @param <K>
 */
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

    @Override
    public BinarySearchTree<K> getLeft() {
        return (BinarySearchTree<K>) left;
    }

    @Override
    public BinarySearchTree<K> getRight() {
        return (BinarySearchTree<K>) right;
    }

    /**This Method adds an Element to a BinarySearchTree
     *
     * @param key the element which is to be added to the structure
     * @throws IllegalArgumentException if the element is already in the structure
     */
    public void add(K key) throws IllegalArgumentException{
        if(this.key == null) {
            left = new BinarySearchTree<>(null,null,null);
            right = new BinarySearchTree<>(null,null,null);
            this.key = key;
        }
        else if (key.compareTo(this.key) < 0) {
            getLeft().add(key);
        }
        else if (key.compareTo(this.key) > 0) {
            getRight().add(key);
        }
        else {
            throw new IllegalArgumentException("The given key was already in the structure");
        }
    }

    public BinarySearchTree<K> search(K key) {
        if(this.key == null) {
            return null;
        }
        else if(this.key.compareTo(key) == 0) {
            return this;
        }
        else if(left != null && key.compareTo(this.key) < 0) {
            return getLeft().search(key);
        }
        else if(right != null && key.compareTo(this.key) > 0) {
            return getRight().search(key);
        }
        else{
            return null;
        }
    }

    public void remove(K key) {
        if(this.key == null) {
            return;
        }
        else if(this.key.compareTo(key) == 0) {
            if (left.key == null) {
                this.key = right.key;
                this.right = getRight().getRight();
            }
            else if (right.key == null){
                this.key = getLeft().key;
                this.left = getLeft().getLeft();
            }
            else {
                removeSymmetricPredecessor(this);
            }
        }
        else if(left != null && key.compareTo(this.key) < 0) {
            getLeft().remove(key);
        }
        else if(right != null && key.compareTo(this.key) > 0) {
            getRight().remove(key);
        }
        else{
            return;
        }

    }

    public void removeSymmetricPredecessor(BinarySearchTree<K> tree) {
        BinarySearchTree<K> removingTree = tree;
        if(removingTree.right.left.key != null) {
            removingTree = removingTree.getRight();
            while(removingTree.left.left != null) {
                removingTree = removingTree.getLeft();
            }
            tree.key = removingTree.key;
            tree = tree.getRight();
            while(tree.left.left.key != null) {
                tree = tree.getLeft();
            }
            tree.left = new BinarySearchTree<K>(null,null,null);

        }
        else {
            tree.key = tree.right.key;
            tree.right = tree.right.getRight();
        }
    }

    public K getMinKey() {
        if(this.left != null && this.left.key != null) {
            return getLeft().getMinKey();
        }
        return key;
    }

    public K getMaxKey() {
        if(this.right != null && this.right.key != null) {
            return getRight().getMaxKey();
        }
        return key;
    }

    public BinarySearchTree<K> getPredecessor(BinarySearchTree<K> tree) {
        BinarySearchTree<K> Predecessor = null;
        BinarySearchTree<K> Predecessor2 = null;
        if(tree.key.compareTo(this.key) == 0){
            if(left.key != null) {
                Predecessor = getLeft();
                while(Predecessor.getRight().key != null) {
                    Predecessor = Predecessor.getRight();
                }
                return Predecessor;

            }
            return null;
        }
        else if(right.key != null && key.compareTo(tree.key) <= 0) {
            Predecessor = getRight();
            Predecessor2 = getRight().getPredecessor(tree);
        }
        else if(left.key != null && key.compareTo(key) >= 0) {
            Predecessor = getLeft();
            Predecessor2 = getLeft().getPredecessor(tree);
        }
        if (Predecessor != null && (Predecessor2 == null || Predecessor.key.compareTo(Predecessor2.key) < 0)) {
            return Predecessor;
        }
        return Predecessor2;
    }

    public BinarySearchTree<K> getSuccessor(BinarySearchTree<K> tree) {
        BinarySearchTree<K> Successor = null;
        BinarySearchTree<K> Successor2 = null;
        if(tree.key.compareTo(this.key) == 0){
            if(right.key != null) {
                Successor = getRight();
                while(Successor.getLeft().key != null) {
                    Successor = Successor.getLeft();
                }
                return Successor;

            }
            return null;
        }
        else if(left.key != null && key.compareTo(tree.key) >= 0) {
            Successor = getLeft();
            Successor2 = getLeft().getPredecessor(tree);
        }
        else if(right.key != null && key.compareTo(key) <= 0) {
            Successor = getRight();
            Successor2 = getRight().getPredecessor(tree);
        }
        if (Successor != null && (Successor2 == null || Successor.key.compareTo(Successor2.key) > 0)) {
            return Successor;
        }
        return Successor2;
    }

    public void print() {
        ArrayList<String>[] arrayLists = new ArrayList[size()];
        levelOrderRecursive(arrayLists, 0);
        for(ArrayList<String> list : arrayLists) {
            if(list == null) {
                return;
            }
            System.out.println(list);
        }
    }

    public void levelOrderRecursive(ArrayList<String>[] arrayLists, int depth) {
        if (arrayLists[depth] == null) {
            arrayLists[depth] = new ArrayList<>();
        }
        arrayLists[depth].add(key + "  ");
        if(left != null) {
            getLeft().levelOrderRecursive(arrayLists, depth + 1);
        }
        if(right != null) {
            getRight().levelOrderRecursive(arrayLists, depth + 1);
        }
    }

    /**
     * Counts all the branches in the heapstructure
     * @return the number of branches in the structure
     */
    public int size() {
        int l = 0;
        int r = 0;
        if (left != null) {
            l = getLeft().size();
        }
        if (right != null) {
            r = getRight().size();
        }
        if(l < r) {
            return 1 + r;
        }
        return 1 + l;
    }
}
