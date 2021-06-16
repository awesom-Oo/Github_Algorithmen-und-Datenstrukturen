package ueb09.timTry;

import java.util.concurrent.ArrayBlockingQueue;

class Tree234<K extends Comparable<K>> {
    Node<K> root = new Node<>();

    public DataItem<K> find(K value) {
        Node<K> node = root;
        while (node != null) {
            DataItem<K> item = node.findItem(value);
            if (item == null) {
                node = node.getNextNode(value);
            } else {
                return item;
            }
        }
        return null;
    }

    public void insert(DataItem<K> item) {
        Node<K> node = root;
        while (node != null) {
            if (node.isFull()) {
                // split when encountering a full node
                split(node);
                node = node.getParent().getNextNode(item.getValue());
            } else if (node.isLeaf()) {
                //Found the leaves
                break;
            } else {
                //Continue to look down
                node = node.getNextNode(item.getValue());
            }
        }
        node.insertItem(item);
    }

    public void split(Node<K> node) {
        DataItem<K> item1 = node.remove(1);
        DataItem<K> item2 = node.remove(2);
        Node<K> child2 = node.disconnect(2);
        Node<K> child3 = node.disconnect(3);

        Node<K> newRight = new Node<>();
        newRight.insertItem(item2);
        newRight.connect(0, child2);
        newRight.connect(1, child3);

        if (node == root) {
            //When the root node is full
            root = new Node<>();
            root.insertItem(item1);
            root.connect(0, node);
            root.connect(1, newRight);
            node.setParent(root);
            newRight.setParent(root);
        } else {
            // child node
            int loc = node.getParent().insertItem(item1);
            Node<K> parent = node.getParent();
            int i;
            for (i = node.getParent().getNumItems(); i > loc + 1; i--) {
                parent.connect(i, parent.getChild(i - 1));
            }
            node.getParent().connect(i, newRight);
            newRight.setParent(parent);
        }
        //You can find that the tree is up and up when you insert it.
    }

    public void levelTraverse() throws InterruptedException {
        if (root == null) {
            return;
        }
        ArrayBlockingQueue<Node<K>> que = new ArrayBlockingQueue<>(20);
        que.put(root);
        while (!que.isEmpty()) {
            Node<K> node = que.poll();
            node.displayNode();
            if (!node.isLeaf()) {
                for (int i = 0; i <= node.getNumItems(); i++) {
                    que.put(node.getChild(i));
                }
            }
        }
    }
}
