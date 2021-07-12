package teachings;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class GamesSort<K, V> {


    class Node<K, V> {
        K key; V value;
        Node<K, V> next;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;

        }
    }

    ArrayList<ArrayList<Node<K, V>>> map = new ArrayList<>();
    int numBuckets;
    int size;
    Node<K, V> head = null;

    int hashCode(K key) {
        return Objects.hashCode(key);
    }

    int getIndex(K key) {
        int hashCode = hashCode(key);
        int index = hashCode % numBuckets;

        index = index < 0 ? index *-1 : index;
        return index;
    }

    public GamesSort(int numBuckets) {
        this.numBuckets = numBuckets;

        for (int i = 0; i < numBuckets; i++) {
            map.add(new ArrayList<>());

        }

    }

    void add(K key, V value) {
        int index = getIndex(key);

        ArrayList<Node<K, V>> nodes = map.get(index);

        Node<K, V> neu = new Node<>(key, value);
        
        nodes.add(neu);
        neu.next = head;
    }

    void gameSort(ArrayList<Node<K, V>> games) {

//        int i = 1;
//        while (i < games.size()) {
//            map.put(games.get(i).key, new ArrayList<Node<K, V>>());
//            i++;
//        }

//        i = 0;
//
//        while (i < games.size()) {
//            ArrayList<Node<K, V>> nodes = map.get(games.get(i).key);
//            nodes.add(games.get(i));
//            i++;
//        }
    }

    public static void main(String[] args) {


        GamesSort<Integer, String> gamesSort = new GamesSort<>(10);

        gamesSort.add(2001, "Mario Kart");
        gamesSort.add(2001, "Final Fantasy");
        gamesSort.add(2019, "Final Fantasy IV");
        gamesSort.add(2011, "Minecraft");
        gamesSort.add(1986, "Zelda");

        System.out.println(99);

    }


}
