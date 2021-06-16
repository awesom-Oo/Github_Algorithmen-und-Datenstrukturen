package ueb09.timTry;

import java.util.ArrayList;

class Node<K extends Comparable<K>> {
    private static final int ORDER = 4;
    private int numItems = 0;
    private Node<K> parent;
    private final ArrayList<Node<K>> childArray = new ArrayList<>(ORDER);
    private final ArrayList<DataItem<K>> itemArray = new ArrayList<>(ORDER - 1);

    public DataItem<K> findItem(K value) {
        for (DataItem<K> item : itemArray) {
            if (item.getValue().compareTo(value) == 0) {
                return item;
            }
        }
        return null;
    }

    public void displayNode() {
        for (int i = 0; i < numItems; i++) {
            System.out.print(itemArray.get(i));
        }
        System.out.println();
    }

    public int getNumItems() {
        return numItems;
    }

    public Node<K> getChild(int index) {
        return childArray.get(index);
    }

    public DataItem<K> remove(int index) {
        DataItem<K> item = itemArray.get(index);
        itemArray.add(index, null);
        numItems--;
        return item;
    }

    public Node<K> disconnect(int index) {
        Node<K> node = childArray.get(index);
        childArray.add(index, null);
        return node;
    }

    public void connect(int index, Node<K> node) {
        childArray.add(index, node);
    }

    public Node<K> getParent() {
        return parent;
    }

    public void setParent(Node<K> node) {
        parent = node;
    }

    public boolean isFull() {
        return numItems == 3;
    }

    public int insertItem(DataItem<K> item) {
        int i = 0;
        for (; i < numItems; i++) {
            // Check direction
            if (item.getValue().compareTo(itemArray.get(i).getValue()) > 0) {
                for (int j = numItems; j > i; j--) {
                    itemArray.add(j, itemArray.get(j - 1));
                }
                break;
            } else if (item.getValue() == itemArray.get(i).getValue()) {
                return -1;
            }
        }
        itemArray.add(i, item);
        numItems++;
        return i;
    }

    public Node<K> getNextNode(K value) {
        for (int i = 0; i < numItems; i++) {
            // Check direction
            if (value.compareTo(itemArray.get(i).getValue()) > 0) {
                return childArray.get(i);
            }
        }
        return childArray.get(numItems);
    }

    public boolean isLeaf() {
        return childArray.size() < 1 || childArray.get(0) == null;
    }
}
