package ueb09;

import java.util.Comparator;

public class TwoThreeFourTree<K extends Comparable<K>> {

    private class Node<K> {

        private static final int ORDER = 4;
        private int numElem=0;

        public final Comparator<K> COMPARATOR = new Comparator<K>() {
            @Override
            public int compare(K o1, K o2) {
                return ((Comparable<K>) o1).compareTo(o2);
            }
        };

        // holds reference to children the ode might have
        private Node<K>[] childArray = new Node[ORDER];

        private K[] elemArray = (K[]) new Object[ORDER-1];

        private Node<K> parent;

        /**
         * Connects child to this node
         * @param childNum
         * @param child connected child
         */
        public void connectChild(int childNum, Node child) {
            childArray[childNum] = child;
            if (child != null) child.parent = this;
        }

        /**
         * Method disconnects child from this node
         * @param childNum
         * @return Disconnected child
         */
        public Node<K> disconnectChild(int childNum) {
            Node<K> temp = childArray[childNum];
            childArray[childNum] = null;
            return temp;
        }

        public Node<K> getChild(int childNum) {
            return childArray[childNum];
        }

        public Node<K>[] getChildArray() {
            return childArray;
        }

        public Node<K> getParent() { return parent; }

        public boolean isLeaf() {
            return childArray[0] == null;
        }

        /**
         * @return Number of elements in one node
         */
        public int getNumElem() {
            return numElem;
        }

        /**
         * @return Return number of elements on this node and lower
         */
        public int getNumElemLow() {

            if (this.isLeaf()) return numElem;

            int sum = numElem;
            for (int i = 0; i < numElem+1; i++) {
                sum += getChild(i).getNumElemLow();
            }

            return sum;
        }

        public K getElement(int index) { return elemArray[index]; }

        public boolean isFull() { return numElem == ORDER - 1; }

        public Node<K> firstChild() { return childArray[0]; }

        public Node<K> lastChild() { return childArray[numElem]; }

        public K getSmallestElement() { return elemArray[0]; }

        public K getLargestElement() { return elemArray[numElem -1]; }

        public int findElement(K element) {
            int temp = -1;

            for (int i = 0; i < ORDER-1; i++) {
                if (elemArray[i] == null) {
                    break;
                }
                else if(COMPARATOR.compare(elemArray[i], element) == 0) {
                    temp = i;
                }
            }
            return temp;
        }

        /**
         * Remove the right most element
         * @return removed element
         */
        public K remove() {
            numElem -= 1;
            K elem = elemArray[numElem];
            elemArray[numElem] = null;
            return elem;
        }

        /**
         * Method adds new element to current Node. Node should not be full
         * @param elem
         * @return Index of where value was added
         */
        public int addNewElem(K elem) {
            numElem += 1;
            int index = 0;

            for (int i = elemArray.length-1; i >= 0; i--){
                if (elemArray[i] == null) {
                    continue;
                } else {
                    K temp = elemArray[i];

                    if (COMPARATOR.compare(elem, temp) < 0) {
                        elemArray[i+1] = elemArray[i];
                    } else {
                        elemArray[i+1] = elem;
                        index = i +1;
                        break;
                    }
                }
            }
            elemArray[index] = elem;
            return index;
        }

        /**
         * Method removes element at index, shifts to fill space
         * @param index
         * @return Removed Element
         */
        K removeElemAt(int index) {
            K elem = getElement(index);

            int i = index;
            for (; i < numElem-1; i++) {
                elemArray[i] = elemArray[i+1];
            }
            elemArray[i] = null;
            numElem -= 1;
            return elem;
        }

        public String toString() {
            String string = " ";
            for (int i = 0; i < getNumElem(); i++) {
                string += elemArray[i] + "|";
            }
            return string;
        }
    }

    private Node<K> root = new Node<>();
    public final Comparator<K> COMPARATOR = new Comparator<K>() {
        @Override
        public int compare(K o1, K o2) {
            return ((Comparable<K>) o1).compareTo(o2);
        }
    };

    public TwoThreeFourTree() {
    }

    public K getMax() {
        return findNodeLargestElem(root).getLargestElement();
    }

    public K getMin() {
        return findNodeSmallestElem(root).getSmallestElement();
    }

    public Node<K> findNodeSmallestElem(Node<K> parent) {
        Node<K> curr = parent;

        while (!curr.isLeaf()) {
            curr = curr.firstChild();
        }
        return curr;
    }

    public Node<K> findNodeLargestElem(Node<K> parent) {
        Node<K> curr = parent;

        while (!curr.isLeaf()) {
            curr = curr.lastChild();
        }

        return curr;
    }

    public void add(K element) {

        Node<K> curr = root;
        K temp = element;

        while (!curr.isLeaf()) {
            if (curr.isFull()) {
                split(curr);
                curr = getNextChild(curr.getParent(), element);
            } else {
                curr.addNewElem(temp); 
            }
        }
        curr.addNewElem(temp);
    }

    private Node<K> getNextChild(Node<K> node, K element) {
        int i;

        int numElem  = node.getNumElem();

        for (i = 0; i < numElem; i++) {
            if (COMPARATOR.compare(element, node.getElement(i)) < 0) {
                return node.getChild(i);
            }
        }

        return node.getChild(numElem);
    }

    private void split(Node<K> node) {
        K elementB, elementC;
        Node<K> parent, child2, child3;

        //two right most items are removed from the node and stored
        int index;

        elementC = node.remove();
        elementB = node.remove();

        child2 = node.disconnectChild(2);
        child3 = node.disconnectChild(3);

        Node<K> rightNode = new Node<>();

        if (node == root) {
            root = new Node<>();
            parent = root;
            root.connectChild(0, node);
        }

        else {
            parent = node.getParent();
        }

        index = parent.addNewElem(elementB);

        int numbElem = parent.getNumElem();

        for (int i = numbElem-1; i > index; i--) {
            Node temp = parent.disconnectChild(i);
            parent.connectChild(i+1, temp);
        }

        parent.connectChild(index+1, rightNode);

        rightNode.addNewElem(elementC);
        rightNode.connectChild(0, child2);
        rightNode.connectChild(1, child3);
    }

    public static void main(String[] args) {
        TwoThreeFourTree<Integer> tree = new TwoThreeFourTree<>();

        tree.add(12);
        tree.add(11);
        tree.add(9);
    }



}



