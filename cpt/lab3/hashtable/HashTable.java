package hashtable;

import java.util.LinkedList;
import java.util.Objects;
import java.util.function.BiConsumer;

/** Простая хэш‑таблица (separate chaining). */
public class HashTable<K, V> {

    public static class Entry<K, V> {
        private final K key;
        private V value;
        public Entry(K key, V value) { this.key = key; this.value = value; }
        public K getKey() { return key; }
        public V getValue() { return value; }
        public void setValue(V value) { this.value = value; }
        public String toString() { return "(" + key + ", " + value + ")"; }
    }

    private LinkedList<Entry<K,V>>[] table;
    private int size;
    private final double loadFactor = 0.75;

    @SuppressWarnings("unchecked")
    public HashTable(int initialCapacity) {
        if (initialCapacity < 4) initialCapacity = 4;
        table = (LinkedList<Entry<K,V>>[]) new LinkedList[initialCapacity];
        size = 0;
    }

    public HashTable() { this(16); }

    public int size() { return size; }
    public boolean isEmpty() { return size == 0; }

    private int index(K key, int capacity) {
        int h = Objects.hashCode(key);
        return (h & 0x7fffffff) % capacity;
    }

    public void put(K key, V value) {
        ensureCapacity();
        int idx = index(key, table.length);
        if (table[idx] == null) table[idx] = new LinkedList<>();
        for (Entry<K,V> e : table[idx]) {
            if (Objects.equals(e.getKey(), key)) { e.setValue(value); return; }
        }
        table[idx].add(new Entry<>(key, value));
        size++;
    }

    public V get(K key) {
        int idx = index(key, table.length);
        LinkedList<Entry<K,V>> bucket = table[idx];
        if (bucket == null) return null;
        for (Entry<K,V> e : bucket) if (Objects.equals(e.getKey(), key)) return e.getValue();
        return null;
    }

    public V remove(K key) {
        int idx = index(key, table.length);
        LinkedList<Entry<K,V>> bucket = table[idx];
        if (bucket == null) return null;
        var it = bucket.iterator();
        while (it.hasNext()) {
            Entry<K,V> e = it.next();
            if (Objects.equals(e.getKey(), key)) { it.remove(); size--; return e.getValue(); }
        }
        return null;
    }

    public boolean containsKey(K key) { return get(key) != null; }

    public void forEach(BiConsumer<K,V> consumer) {
        for (LinkedList<Entry<K,V>> bucket : table) {
            if (bucket == null) continue;
            for (Entry<K,V> e : bucket) consumer.accept(e.getKey(), e.getValue());
        }
    }

    @SuppressWarnings("unchecked")
    private void ensureCapacity() {
        if ((double)(size + 1) / table.length <= loadFactor) return;
        LinkedList<Entry<K,V>>[] old = table;
        table = (LinkedList<Entry<K,V>>[]) new LinkedList[old.length * 2];
        int oldSize = size; size = 0;
        for (LinkedList<Entry<K,V>> bucket : old) {
            if (bucket == null) continue;
            for (Entry<K,V> e : bucket) put(e.getKey(), e.getValue());
        }
        size = oldSize; // корректировка (put уже увеличил)
    }
}
