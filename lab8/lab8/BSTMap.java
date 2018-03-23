package lab8;
import java.util.Iterator;
import java.util.Set;


public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {
    private Node root;

    public BSTMap() {
        root = null;
    }

    private class Node {
        K key;    // key
        V val;    // associated key
        Node left, right;    // links to subtrees
        int size;    // * nodes in subtree rooted here

        public Node(K k, V v, int N) {
            this.key = k;
            this.val = v;
            this.size = N;
            this.left = null;
            this.right = null;
        }
    }

    @Override
    public void clear() {
        root = null;
    }

    /* Returns true if this map contains a mapping for the specified key. */
    @Override
    public boolean containsKey(K key) {
        return get(key) != null;
    }

    /* Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        return get(root, key);
    }

    private V get(Node x, K key) {
        if (key == null) {
            throw new IllegalArgumentException("calls get() with a null key");
        }
        if (x == null) {
            return null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp == 0) {
            return x.val;
        }
        if (cmp > 0) {
            return get(x.right, key);
        } else {
            return get(x.left, key);
        }
    }

    /* Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return size(root);
    }

    private int size(Node x) {
        return x == null ? 0 : x.size;
    }

    /* Associates the specified value with the specified key in this map. */
    @Override
    public void put(K key, V value) {
        root = put(key, value, root);
    }

    private Node put(K key, V value, Node x) {
        if (x == null) {
            return new Node(key, value, 1);
        }
        int cmp = x.key.compareTo(key);
        if (cmp > 0) {
            x.left = put(key, value, x.left);
        } else if (cmp < 0) {
            x.right = put(key, value, x.right);
        } else {
            x.val = value;
        }
        x.size = size(x.left) + size(x.right) + 1;
        return x;
    }

    /* Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
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

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }

    //prints out your BSTMap in order of increasing Key
    public void printInOrder() {
        printInOrder(root);
    }

    public void printInOrder(Node x) {
        if (x == null) {
            return;
        }
        printInOrder(x.left);
        System.out.println(x.key);
        printInOrder(x.right);
    }
}
