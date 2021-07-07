package ueb06.hashmap;

import ueb05.FixedRangeHashFunction;
import ueb05.Hashing;
import ueb05.Remove;

import java.security.KeyPair;
import java.util.Arrays;
import java.util.Map;

/**
 * Eine Klasse die Hashtablle mittel LinearProbing verwaltet
 * @param <Key> Der Schlüssel eines Datensatzes in der HAshtabelle
 * @param <Value> Der Wert eines Datensatzes
 */
class LinearProbing<Key extends Comparable<Key>, Value> implements Hashing<Key, Value>, Remove<Key,Value> {
	private FixedRangeHashFunction<Key> function;
	private DataSet<Key, Value>[] hashTable;
	private int elements = 0;
	private int low;

	// TODO: 19.05.2021 5.2.c)

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
		System.out.println(l);
		System.out.println(l.get(7));
		System.out.println(l.get(1));
		System.out.println(l.get(6));
		System.out.println();
		System.out.println(l.remove(1));
		System.out.println(l.remove(5));
		System.out.println(l.remove(5));
		System.out.println(l.remove(11));
		System.out.println(l);



	}

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

	public LinearProbing(int size, FixedRangeHashFunction<Key> function) {
		if (size != (function.maxHashValue()) - function.minHashValue() + 1)
			throw new IllegalArgumentException("The functionrange needs to match the given size");
		hashTable = new DataSet[size];
		this.function = function;
		low = function.minHashValue();
	}

	@Override
	public Value put(Key key, Value value) throws IllegalStateException {
		if (elements >= hashTable.length)
			throw new IllegalStateException("Element can't be added to a full hashtable");
		int temp = function.hash(key) - low;
		while (hashTable[temp] != null && !hashTable[temp].removed && hashTable[temp].key.compareTo(key) != 0) {
			if (temp < hashTable.length - 1) {
				temp++;
			} else {
				temp = 0;
			}
		}
		if (hashTable[temp] != null) {
			Value v = hashTable[temp].value;
			hashTable[temp] = new DataSet<>(key, value);
			return v;
		}
		hashTable[temp] = new DataSet<>(key, value);
		elements++;
		return null;
	}

	@Override
	public Value get(Key key) {
		int temp = function.hash(key), temp2 = hashTable.length;
		while (hashTable[temp] != null && temp2-- > 0
				&& hashTable[temp].key.compareTo(key) != 0) {
			if (temp < hashTable.length - 1) {
				temp++;
			} else {
				temp = 0;
			}
		}
		if (hashTable[temp]  != null && !hashTable[temp].removed && hashTable[temp].key.compareTo(key) == 0) {
			return hashTable[temp].value;
		}
		return null;
	}

	@Override
	public Value remove(Key key) {
		int temp = function.hash(key), counter = hashTable.length;
		while(hashTable[temp] != null  && (hashTable[temp].removed || hashTable[temp].key.compareTo(key) != 0)
				&& counter > 0) {
			if (temp < hashTable.length - 1) {
				temp++;
			} else {
				temp = 0;
			}
			counter--;
		}
		if(hashTable[temp] != null && hashTable[temp].key.compareTo(key) == 0) {
			Value v = hashTable[temp].value;
			hashTable[temp].key = null;
			hashTable[temp].value = null;
			hashTable[temp].removed = true;
			elements--;
			return v;
		}
		return null;
	}

	@Override
	public String toString() {
		return "hashTable=" + Arrays.toString(hashTable) +
				'}';
	}
}
