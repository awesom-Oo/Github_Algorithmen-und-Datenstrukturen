//package ueb05;
//
//import com.sun.jdi.Value;
//
//import java.util.Iterator;
//
//public class LinkedList<T> implements Iterable<T> {
//
//    private static class Node<T> {
//        Node<T> next;
//        T value;
//
//        public Node(T value, Node<T> next) {
//            this.next = next;
//            this.value = value;
//        }
//
//        public Node<T> getNext() {
//            return next;
//        }
//
//        public T getValue() {
//            return value;
//        }
//
//        public void setNext(Node<T> next) {
//            this.next = next;
//        }
//
//        public void setValue(T value) {
//            this.value = value;
//        }
//
//        @Override
//        public String toString() {
//            return "Node{" + "next=" + next + ", value=" + value + '}';
//        }
//    }
//
//    Node<T> head = null;
//
//    public boolean add(T value) {
//        head = new Node<>(value, head);
//        return true;
//    }
//
//    public boolean remove(T value) {
//        if (head == null) {
//            return false;
//        }
//
//        if (head.value.equals(value)) {
//            head = head.next;
//            return true;
//        }
//
//        Node<T> tmp = head;
//        while (tmp.next != null) {
//            if (tmp.next.value.equals(value)) {
//                tmp.next = tmp.next.next;
//                return true;
//            }
//            tmp = tmp.next;
//        }
//
//        return false;
//
//    }
//
//    public boolean remove(int index) {
//        int count=1;
//
//        if (head==null) return false;
//
//        if (index < 0) return false;
//
//        if (index == 0) {
//            head = head.next;
//            return true;
//        }
//
//        Node<T> tmp = head;
//        while (tmp.next != null) {
//            if (index == count) {
//                tmp.next = tmp.next.next;
//                return true;
//            }
//
//            tmp = tmp.next;
//            count++;
//        }
//
//        return false;
//    }
//
//    public Value get(int index) {
//
//    }
//
//    public boolean contains(T value) {
//
//        for (T value1 : this) {
//            if (value1.equals(value)) return true;
//        }
//        return false;
//    }
//
//    @Override
//    public Iterator<T> iterator() {
//        return new Iterator<T>() {
//            Node<T> node = head;
//
//            @Override
//            public boolean hasNext() {
//                return node.next != null;
//            }
//
//            @Override
//            public T next() {
//                T tmp = node.value;
//                node = node.next;
//                return tmp;
//            }
//        };
//    }
//
//    public static void main(String[] args) {
//        LinkedList<Integer> linkedList = new LinkedList<>();
//        Iterator<Integer> iterator = linkedList.iterator();
//        while (iterator.hasNext()) {
//            System.out.println(iterator.next());
//        }
//    }
//}
