package ueb09.timTry;

class DataItem<K extends Comparable<K>> {
    private final K value;

    public DataItem(K v) {
        value = v;
    }

    @Override
    public String toString() {
        return "/" + value;
    }

    public K getValue() {
        return value;
    }
}
