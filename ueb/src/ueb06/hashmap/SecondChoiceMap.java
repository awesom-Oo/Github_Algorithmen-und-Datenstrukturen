package ueb06.hashmap;


import ueb06.hashmap.HashFunction;
import ueb06.hashmap.Map;
import ueb06.hashmap.MapEntry;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * This class implements a map that uses second choice hashing
 *
 * @param <K>
 * @param <V>
 */
public class SecondChoiceMap<K,V> implements Map<K, V> {

    private final List<List<MapEntry<K, V>>> table1;

    private final List<List<MapEntry<K, V>>> table2;

    private final HashFunction<K> h1;

    private final HashFunction<K> h2;

    public final int numBuckets;

    public SecondChoiceMap(int numBuckets, HashFunction<K> h1, HashFunction<K> h2) {
        this.numBuckets = numBuckets;
        this.h1 = h1;
        this.h2 = h2;
        table1 = new ArrayList<>();
        table2 = new ArrayList<>();
        for ( int i = 0; i < numBuckets; i++ ) {
            table1.add( new LinkedList<>() );
            table2.add( new LinkedList<>() );
        }
    }


    @Override
    public V get(K key) {
        // TODO: Implement
        return null;
    }

    @Override
    public void put(K key, V value) {
        // TODO: Implement

    }

    @Override
    public void remove(K key) {
        // TODO: Implement

    }

    public String toString(){
        StringBuilder str = new StringBuilder("Table1: [");

        this.table1.forEach(str::append);
        str.append("]\nTable2: [");
        this.table2.forEach(str::append);

        return str.append(']').toString();
    }

}
