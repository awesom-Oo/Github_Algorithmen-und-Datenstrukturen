package ueb04;

import java.util.NoSuchElementException;

public class SimpleLinkedList<E> {

    static class Node<E> {
        E element;
        Node<E> next;

        Node(E element, Node<E> next) {
            this.element = element;
            this.next = next;
        }
    }

    protected Node<E> head = new Node<>(null, null);

    public void addFirst(E element) {
        head.next = new Node<>(element, head.next);
    }

    public E getFirst() {
        if (isEmpty())
            throw new NoSuchElementException();
        return head.next.element;
    }

    public E removeFirst() {
        if (isEmpty())
            throw new NoSuchElementException();
        E result = getFirst();
        head.next = head.next.next;
        return result;
    }

    public void addLast(E element) {
        Node<E> tmp = head;
        while (tmp.next != null)
            tmp = tmp.next;
        tmp.next = new Node<>(element, null);
    }

    public E getLast() {
        if (isEmpty())
            throw new NoSuchElementException();
        Node<E> tmp = head;
        while (tmp.next != null)
            tmp = tmp.next;
        return tmp.element;
    }

    public E removeLast() {
        if (isEmpty())
            throw new NoSuchElementException();
        Node<E> tmp = head;
        while (tmp.next.next != null) {
            tmp = tmp.next;
        }
        E result = tmp.next.element;
        tmp.next = null;
        return result;
    }

    public E get(int n) {
        if(isEmpty()){
            throw new NoSuchElementException();
        }
        int count = 0;
        Node<E> tmp = head.next;
        while (tmp.next != null && count < n) {
            tmp = tmp.next;
            count++;
        }
        if (count < n)
            throw new NoSuchElementException();
        return tmp.element;
    }

    public boolean isEmpty() {
        return head.next == null;
    }

    public int size() {
        int count = 0;
        Node<E> tmp = head;
        while (tmp.next != null) {
            count++;
            tmp = tmp.next;
        }
        return count;
    }

}
