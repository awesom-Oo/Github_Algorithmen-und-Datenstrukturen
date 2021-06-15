package ueb09;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class RandomSkipList<E extends Comparable<E>> {

    private class SkipNode<E extends Comparable<E>> {

        private E element;
        private ArrayList<SkipNode<E>> next;

        SkipNode(E element, int height) {
            this.element = element;
            this.next = new ArrayList<>(height);
        }

        SkipNode(E element, int height, SkipNode<E> last) {
            this(element, height);
            for (int i = 0; i < height; i++) {
                next.add(last);
            }
        }

        SkipNode<E> getNext(int i){
            return next.get(i);
        }

        E getNextElement(int i){
            return next.get(i).element;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder("Node(");
            if (element != null)
                sb.append(element.toString());
            sb.append(")");
            return sb.toString();
        }
    }

    private SkipNode<E> head, tail;
    private int height, maxHeight;

    public RandomSkipList(int maxHeight) {
        this.maxHeight = maxHeight;
        this.height = 0;
        this.tail = new SkipNode<>(null, maxHeight + 1);
        this.head = new SkipNode<>(null, maxHeight + 1, tail);
    }

    private SkipNode<E> findCandidate(E element, List<SkipNode<E>> trace){
        SkipNode<E> current = head;

        for (int i = height; i >= 0; i--) {
            while (current.getNext(i) != tail && current.getNextElement(i).compareTo(element) < 0) {
                current = current.getNext(i);
            }
            trace.add(0, current);
        }
        return current.getNext(0);
    }

    private int randomHeight() {
        double r = Math.random();
        int i = 0;
        while (i < maxHeight) {
            if (r > 1 / Math.pow(2, i + 1)) {
                return i;
            }
            i++;
        }
        return 0;
    }

    public boolean add(E element) {
        List<SkipNode<E>> trace = new LinkedList<>();
        SkipNode<E> current = findCandidate(element, trace);

        if (current == tail || current.element.compareTo(element) != 0) {
            int newHeight = randomHeight();
            if (newHeight > height) {
                for (int i = height + 1; i <= newHeight; i++) {
                    trace.add(head);
                }
                height = newHeight;
            }
            SkipNode<E> newNode = new SkipNode<>(element, newHeight + 1, tail);
            for (int i = 0; i <= newHeight; i++) {
                newNode.next.set(i, trace.get(i).getNext(i));
                trace.get(i).next.set(i, newNode);
            }
            return true;
        } else {
            return false;
        }
    }

    public boolean contains(E element) {
        SkipNode<E> current = head;
        for (int i = height; i >= 0; i--) {
            while (current.getNext(i) != tail && current.getNextElement(i).compareTo(element) < 0){
                current = current.getNext(i);
            }
        }
        current = current.getNext(0);
        return current != tail && current.element.compareTo(element) == 0;
    }

    public E getNeighbour(E element) {
        SkipNode<E> current = head;
        for (int i = height; i >= 0; i--) {
            while (current.getNext(i) != tail && current.getNextElement(i).compareTo(element) < 0){
                current = current.getNext(i);
            }
        }
        current = current.getNext(0);
        if(current == tail)
            return null;
        int currentCompareElement = current.element.compareTo(element);
        if(currentCompareElement == 0 && current.getNext(0) != tail){
            return current.getNextElement(0);
        } else if (currentCompareElement > 0){
            return current.element;
        } else {
            return null;
        }
    }

}
