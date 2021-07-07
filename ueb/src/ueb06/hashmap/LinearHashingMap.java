/*
package hashmap;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

*/
/**
 * This class implements a map that uses linear hashing to expand the underlying hash table
 *
 * @param <K>
 * @param <V>
 *//*

public class LinearHashingMap<K, V> implements Map<K, V> {
    
	private final int initialBucketCount;
	
	private final double alphaMax;
	
	private final List<List<MapEntry<K, V>>> buckets;

	private final HashFunction<K> hashFunction;

    private int currentLevel = 0;

    private int expansionPointer = 0;

    private int numberOfElements;

    

    public LinearHashingMap(final int initialBucketCount, double alphaMax, HashFunction<K> h) {
        this.initialBucketCount = initialBucketCount;
        this.alphaMax = alphaMax;

        buckets = new ArrayList<>();
        
        for (int i = 0; i < initialBucketCount; i++)
            buckets.add(i, new LinkedList<MapEntry<K, V>>());

        hashFunction = h;
    }

    */
/**
     * get the address for the given key with respect to current level
     * @param key
     * @return the address for the given key with respect to current level
     *//*

    public int getAddress(K key) {
    	//TODO
        return 0;
    }
    
    */
/**
     * get the current alpha value
     * @return the current alpha value
     *//*

    public double getAlpha() {
    	//TODO
    	return 0;
    }

    */
/**
     * check if number of elements in hash table exceeds threshold
     * @return true if the hash table needs to be extended
     *//*

    public boolean checkOverflow() {
    	//TODO
    	return false;
    }
   
    */
/**
     * expands the hash table
     *//*

    protected void split() {
    	//TODO
    }
    
    public V get(K key) {
        //TODO
        return null;
    }

    public void put(K key, V value) {
        Iterator<MapEntry<K, V>> iterator = buckets.get(getAddress(key)).iterator();
        MapEntry<K, V> next;
        while (iterator.hasNext()) {
            next = iterator.next();
            if (next.getKey().equals(key)) {
                next.setValue(value);
                return;
            }
        }
        
        buckets.get(getAddress(key)).add(new MapEntry<K, V>(key, value));
        
        numberOfElements++;
        if (checkOverflow()) split();
        return;
    }



    public void remove(K key) {
        Iterator<MapEntry<K, V>> iterator = buckets.get(getAddress(key)).iterator();
        MapEntry<K, V> next;
        while (iterator.hasNext()) {
            next = iterator.next();
            if (next.getKey().equals(key)) {
                iterator.remove();
                numberOfElements--;
                break;
            }
        }
        return;
    }
    
    public String toString(){
        StringBuilder str = new StringBuilder();
        for (Iterable<?> bucket : buckets) {
            str.append('[');
            for (Object entry : bucket)
                str.append(entry);

            str.append(']');
        }

        return str.toString();
    }
    
}
*/
