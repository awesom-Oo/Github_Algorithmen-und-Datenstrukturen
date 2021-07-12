package ueb05;

import java.util.HashMap;

/**
 * A buffer based on the least recently used strategy.
 * The buffer keeps a configurable number of elements.
 * Once full, it evicts the element that has not been accessed longest,
 * i.e. the put or get operation is furthest away from the present.
 */
public class LRUBuffer<Key, Value> {

    /**
     * The maximum size of the buffer. Once reached, an element will be replaced.
     */
    private final int capacity;
    private HashMap<Key, Value> hashMap;
    private SimpleDoublyLinkedList<Key> simpleDoublyLinkedList = new SimpleDoublyLinkedList<>();
    private static int size=0;

    public LRUBuffer(int capacity) {
        this.capacity = capacity;
        hashMap = new HashMap<>(capacity);
    }

    /**
     * Puts the given key and value into the buffer possibly replacing and returning a value previously
     * associated with the key. If the buffer is full, the least recently used element is evicted.
     * The operation has an expected runtime of O(1).
     * @param key The search key of the element.
     * @param value The value associated with the key.
     * @return The value previously associated with key; null if the key is not present in the buffer.
     */
    public Value put(Key key, Value value){

        if (size < capacity){
            simpleDoublyLinkedList.addFirst(key);
            size += 1;
            return value;
        }
        Key key1 = simpleDoublyLinkedList.removeLast();
        hashMap.remove(key1);
        hashMap.put(key, value);
        simpleDoublyLinkedList.addFirst(key);
        return value;

    }

    /**
     * Gets the value associated with the given key.
     * The operation has an expected runtime of O(1).
     * @param key The search key of the element.
     * @return The value associated with the key; null if the key is not present in the buffer.
     */
    public Value get(Key key){

        if (hashMap.containsKey(key)) {
            //simpleDoublyLinkedList.remove(key);
            simpleDoublyLinkedList.addFirst(key);

            return hashMap.get(key);
        }

        return null;
    }

    public void pairSum(Integer[] arr, int sum) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j <arr.length; j++) {
                if (i + j == sum) {
                    System.out.println(i + "+" + j);
                }
            }
        }
    }
}
