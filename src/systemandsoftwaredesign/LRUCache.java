package systemandsoftwaredesign;

import java.util.HashMap;
import java.util.Map;

/**
 * Design a Least Recently Used (LRU) cache.
 *
 * <ul>
 *   <li>{@code LRUCache(int capacity)} initializes a cache with positive capacity.</li>
 *   <li>{@code get(int key)} returns the stored value or {@code -1} if the key is absent.</li>
 *   <li>{@code put(int key, int value)} updates an existing key or inserts a new pair.
 *       If insertion exceeds capacity, evict the least recently used key.</li>
 * </ul>
 *
 * <p>Both operations must run in {@code O(1)} average time.
 *
 * <p>Example
 * <pre>{@code
 * LRUCache cache = new LRUCache(2);
 * cache.put(1, 1);
 * cache.put(2, 2);
 * cache.get(1); // 1
 * cache.put(3, 3); // Evicts key 2.
 * cache.get(2); // -1
 * cache.put(4, 4); // Evicts key 1.
 * cache.get(1); // -1
 * cache.get(3); // 3
 * cache.get(4); // 4
 * }</pre>
 */
public class LRUCache {
    Map<Integer, Node> cache = new HashMap<>();
    int capacity;
    private Node head;
    private Node tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {

        if(cache.containsKey(key)){
            Node node = cache.get(key);
            moveToHead(node);
            return node.value;
        } else {
            return -1;
        }

    }

    public void put(int key, int value) {
        if (cache.containsKey(key)) {
            Node node = cache.get(key);
            node.value = value;
            moveToHead(node);
            return;
        }

        if (cache.size() >= capacity) {
            int keyToRemove = tail.key;
            removeNode(tail);
            cache.remove(keyToRemove);
        }

        Node node = new Node(key, value);
        cache.put(key, node);
        addToHead(node);
    }

    private void addToHead(Node node) {
        if (head == null) {
            head = node;
            tail = node;
        } else {
            node.next = head;
            head.prev = node;
            head = node;
        }
    }

    private void removeNode(Node node) {
        if (node.prev != null) {
            node.prev.next = node.next;
        } else {
            head = node.next;
        }

        if (node.next != null) {
            node.next.prev = node.prev;
        } else {
            tail = node.prev;
        }

        node.prev = null;
        node.next = null;
    }

    private void moveToHead(Node node) {
        removeNode(node);
        addToHead(node);
    }

    static void main() {
        LRUCache cache = new LRUCache(2);
        int[] expected = {1, -1, -1, 3, 4};
        int[] actual = new int[expected.length];

        cache.put(1, 1);
        cache.put(2, 2);
        actual[0] = cache.get(1);
        cache.put(3, 3); // Evicts key 2.
        actual[1] = cache.get(2);
        cache.put(4, 4); // Evicts key 1.
        actual[2] = cache.get(1);
        actual[3] = cache.get(3);
        actual[4] = cache.get(4);

        if (!java.util.Arrays.equals(expected, actual)) {
            throw new AssertionError("Expected " + java.util.Arrays.toString(expected)
                    + ", but got " + java.util.Arrays.toString(actual));
        }
        System.out.println(java.util.Arrays.toString(actual));
    }

    private static class Node {
        int key;
        int value;
        Node prev;
        Node next;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
}
