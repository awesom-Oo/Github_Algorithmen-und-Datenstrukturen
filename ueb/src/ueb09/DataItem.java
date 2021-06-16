package ueb09;

public class DataItem<K> {
    private K value;

    public DataItem(K value) {
        this.value = value;
    }

    public K getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "#" + value;
    }
}
