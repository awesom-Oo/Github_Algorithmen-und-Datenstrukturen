package ueb05;

import java.util.Arrays;

/**
 * Eine Klasse die Hashtablle mittel LinearProbing verwaltet
 * @param <Key> Der Schlüssel eines Datensatzes in der HAshtabelle
 * @param <Value> Der Wert eines Datensatzes
 */
public class LinearProbing<Key extends Comparable<Key>, Value> implements Hashing<Key, Value> , Remove<Key,Value> {

    /**
     *
     * @param <Key>
     * @param <Value>
     */
    static class DataSet<Key, Value> {
        Key key;
        Value value;
        boolean removed;

        public DataSet(Key key, Value value) {
            this.key = key;
            this.value = value;
        }

        public Value getValue() {
            return value;
        }

        @Override
        public String toString() {
            return "Data [" +
                    "key=" + key +
                    "-> value=" + value +
                    ']';
        }
    }

    private FixedRangeHashFunction<Key> function;
    private DataSet<Key, Value>[] dataSets;
    private int count = 0;
    private int low, size;

    public LinearProbing(int size, FixedRangeHashFunction<Key> function) {
        if (size != (function.maxHashValue()) - function.minHashValue() + 1)
            throw new IllegalArgumentException("The functionrange needs to match the given size");
        dataSets = new DataSet[size];
        this.size = size;
        this.function = function;
        low = function.minHashValue();
    }

    @Override
    public Value put(Key key, Value value) throws IllegalStateException {
        int hash = function.hash(key);

        while (dataSets[hash] != null && dataSets[hash].key.compareTo(key) == 0) {
            if (hash < dataSets.length -1) hash += 1;
        }
        if (dataSets[hash] != null) {
            Value v = dataSets[hash].value;
            dataSets[hash] = new DataSet<>(key, value);
            return v;
        }
        dataSets[hash] = new DataSet<>(key, value);
        count += 1;
        return value;
    }

//    public Value put(Key key, Value value) {
//
//        int hash = function.hash(key);
//
//        if (dataSets[hash] == null) {
//            dataSets[hash] = new DataSet<>(key, value);
//        } else {
//            for (int j = 0; j < size; j++) {
//                int t = (hash + j*j) %size;
//
//                if (dataSets[t] == null) {
//                    dataSets[t] = new DataSet<>(key, value);
//                    break;
//                }
//            }
//        }
//
//        return value;
//    }


    @Override
    public Value get(Key key) {
        int temp = function.hash(key), temp2 = dataSets.length;
        while (dataSets[temp] != null && temp2-- > 0
                && dataSets[temp].key.compareTo(key) != 0) {
            if (temp < dataSets.length - 1) {
                temp++;
            } else {
                temp = 0;
            }
        }
        if (dataSets[temp]  != null && !dataSets[temp].removed && dataSets[temp].key.compareTo(key) == 0) {
            return dataSets[temp].value;
        }
        return null;
    }

    @Override
    public Value remove(Key key) {
        int temp = function.hash(key), counter = dataSets.length;
        while(dataSets[temp] != null  && (dataSets[temp].removed || dataSets[temp].key.compareTo(key) != 0)
                && counter > 0) {
            if (temp < dataSets.length - 1) {
                temp++;
            } else {
                temp = 0;
            }
            counter--;
        }
        if(dataSets[temp] != null && dataSets[temp].key.compareTo(key) == 0) {
            Value v = dataSets[temp].value;
            dataSets[temp].key = null;
            dataSets[temp].value = null;
            dataSets[temp].removed = true;
            count--;
            return v;
        }
        return null;
    }

    @Override
    public String toString() {
        return "hashTable=" + Arrays.toString(dataSets) +
                '}';
    }

    public static void main(String[] args) {
        FixedRangeHashFunction<Integer> f = new FixedRangeHashFunction<Integer>() {
            @Override
            public int hash(Integer o) {
                return o % 9;
            }

            @Override
            public int minHashValue() {
                return 0;
            }

            @Override
            public int maxHashValue() {
                return 8;
            }
        } ;
        LinearProbing<Integer,String> l = new LinearProbing<>(9, f);
        l.put(3,"hallo");
        l.put(4, "guten");
        l.put(0, "schönen");
        l.put(0, "Tag");
        l.put(9, "Mittag");
        l.put(18, "Abend");
        l.put(27, "Nachmittag");
        l.put(1, "schönen");
        l.put(5, "schönen");
        l.put(5, "Nachtigall");
        l.put(7, "schönen");


    }
}

