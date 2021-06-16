package ueb09;

public class TwoThreeFourTree<K extends Comparable> {

    private class Node<K> {

        private static final int ORDER = 4;
        private int numItems;
        private Node<K> parent;

        // holds reference to children the ode might have
        private Node<K>[] childArray = new Node[ORDER];

        private K[] itemArray = (K[]) new Object[ORDER-1];

        public void connectChild(int childNum, Node child) {
            childArray[childNum] = child;
            if (child != null) child.parent = this;
        }

        public Node<K> disconnectChild(int childNum) {
            Node<K> temp = childArray[childNum];
            childArray[childNum] = null;
            return temp;
        }

        public Node<K> getChild(int childNum) {
            return childArray[childNum];
        }

        public Node<K> getParent() { return parent; }

        public boolean isLeaf() {
            return (childArray[0] == null) ? true : false;
        }

        public int getNumItems() {
            return numItems;
        }

        public K getElement(int index) {
            return itemArray[index];
        }

        public boolean isFull() { return numItems == ORDER - 1; }

        public Node<K> firstChild() { return childArray[0]; }

        public Node<K> lastChild() { return childArray[numItems]; }

        public K getSmallestElement() { return itemArray[0]; }

        public K getLargestElement() { return itemArray[numItems-1]; }

        public int findElement(K element) {
            int flag = -1;

            for (int i = 0; i < 3; i++) {
                if (itemArray[i] == null) {
                    break;
                }
                else if()
            }
        }

    }
}



