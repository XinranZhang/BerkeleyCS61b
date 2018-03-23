package lab9;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.LinkedList;

public class MyHashMap<K extends Comparable<K>, V> implements Map61B<K, V> {
    private double loadfactor;
    private int kvpairs;
    private int size; //size of the map
    private LinkedList<Item>[] LList; //store key-value pair
    private Set<K> kSet; //store set of keys

    private class Item {
        private K key;
        private V val;

        public Item(K k, V v) {
            key = k;
            val = v;
        }
    }

    public MyHashMap() {
        this(1000);
    }

    public MyHashMap(int initialSize) {
        size = initialSize;
        LList = new LinkedList[initialSize];
        kSet = new HashSet<>();
    }

    public MyHashMap(int initialSize, double loadFactor) {
        size = initialSize;
        LList = new LinkedList[initialSize];
        this.loadfactor = loadFactor;
    }

    //hash between 0 and size - 1
    private int hash(K key) {
        return (key.hashCode() & 0x7fffffff) % size;
    }

    @Override
    public void clear() {
        LList = null;
        size = 0;
    }

    /* Returns true if this map contains a mapping for the specified key. */
    @Override
    public boolean containsKey(K key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to containsKey() is null");
        }
        return get(key) != null;
    }

    /* Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        int hashedkey = hash(key);
        if (LList[hashedkey] == null) {
            return null;
        }
        for (int i = 0; i < LList[hashedkey].size(); i++) {
            if (LList[hashedkey].get(i).key.equals(key)) {
                return LList[hashedkey].get(i).val;
            }
        }
        return null;
    }

    /* Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return kvpairs;
    }

    /* Associates the specified value with the specified key in this map. */
    @Override
    public void put(K key, V value) {
        Item kv = new Item(key, value);
        int hc = hash(key);

        if (LList[hc] == null) {
            LList[hc] = new LinkedList<>();
            LList[hc].add(kv);
            kSet.add(key);
            kvpairs++;
        } else {
            for (int i = 0; i < LList[hc].size(); i++) {
                if (LList[hc].get(i).key.equals(key)) {
                    LList[hc].set(i, kv);
                }
            }
        }
    }

    /* Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet() {
        return kSet;
    }

    @Override
    public Iterator<K> iterator() {
        return kSet.iterator();
    }

    /* Removes the mapping for the specified key from this map if present.
     * Not required for Lab 8. If you don't implement this, throw an
     * UnsupportedOperationException. */
    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    /* Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 8. If you don't implement this,
     * throw an UnsupportedOperationException.*/
    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }
}
