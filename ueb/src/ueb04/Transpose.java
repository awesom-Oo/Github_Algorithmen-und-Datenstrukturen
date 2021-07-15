package ueb04;



public class Transpose<E> extends SimpleLinkedList<E> {

    public E get (int n) {
        int count = 1;

        if (n == 0) return head.next.element;

        Node<E> prevPrv = head;
        Node<E> prev = head.next;
        Node<E> current = head.next.next;

        while (current.next != null && count < n) {
            prevPrv = prev;
            prev = current;
            current = current.next;
            count++;
        }

        prev.next = current.next;
        current.next = prev;
        prevPrv.next = current;
        return current.element;
    }



    public static void main(String[] args) {

        Transpose<Integer> transpose = new Transpose<>();
        transpose.addFirst(3);
        transpose.addFirst(4);
        transpose.addFirst(6);
        transpose.addFirst(8);
        transpose.addFirst(1);
        transpose.addFirst(9);

        Integer integer = transpose.get(3);

        System.out.println(integer);


    }


}
