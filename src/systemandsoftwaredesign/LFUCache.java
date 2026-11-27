package systemandsoftwaredesign;

import java.util.HashMap;
import java.util.Map;

/**
 * Design a Least Frequently Used (LFU) cache.
 *
 * <ul>
 *   <li>{@code LFUCache(int capacity)} initializes the cache with the given capacity.</li>
 *   <li>{@code get(int key)} returns the stored value or {@code -1} if the key is absent.</li>
 *   <li>{@code put(int key, int value)} updates an existing key or inserts a new pair.
 *       If the cache is full, evict the key with the smallest use count before
 *       inserting. Break ties by evicting the least recently used key among
 *       those with the same count.</li>
 * </ul>
 *
 * <p>A newly inserted key starts with a use count of {@code 1}. A successful
 * {@code get} or a {@code put} on an existing key increments its count.
 *
 * <p>Both operations must run in {@code O(1)} average time.
 *
 * <p>Example
 * <pre>{@code
 * LFUCache cache = new LFUCache(2);
 * cache.put(1, 1);
 * cache.put(2, 2);
 * cache.get(1); // 1
 * cache.put(3, 3); // Evicts key 2.
 * cache.get(2); // -1
 * cache.get(3); // 3
 * cache.put(4, 4); // Evicts key 1 after a frequency tie.
 * cache.get(1); // -1
 * cache.get(3); // 3
 * cache.get(4); // 4
 * }</pre>
 */
public class LFUCache {
    private final Map<Integer, Node> cache = new HashMap<>();
    private final Map<Integer, DoublyLinkedList> frequencyMap = new HashMap<>();

    private int capacity;
    private int minFrequency;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.minFrequency = 0;

    }

    public int get(int key) {
        Node node = cache.get(key);

        if(node == null) {
            return -1;
        }

        updateFrequency(node);

        return node.value;
    }

    public void put(int key, int value) {

        // 1. A chave já existe?
        if (cache.containsKey(key)) {
            Node node = cache.get(key);

            node.value = value;
            updateFrequency(node);

            return;
        }

        // 2. A chave não existe. O cache está cheio?
        if (cache.size() >= capacity) {
            DoublyLinkedList minList = frequencyMap.get(minFrequency);

            Node nodeToRemove = minList.removeTail();

            cache.remove(nodeToRemove.key);
        }

        // 3. Cria o novo nó (frequency = 1)
        Node newNode = new Node(key, value);

        // 4. Registra no HashMap principal
        cache.put(key, newNode);

        // 5. Encontra ou cria a lista da frequência 1
        DoublyLinkedList list = frequencyMap.computeIfAbsent(
                1,
                freq -> new DoublyLinkedList()
        );

        // 6. Adiciona o nó à lista
        list.addToHead(newNode);

        // 7. Atualiza a menor frequência
        minFrequency = 1;
    }

    private void updateFrequency(Node node) {
        int oldFrequency = node.frequency;

        // 1. Encontra a lista da frequência antiga
        DoublyLinkedList oldList = frequencyMap.get(oldFrequency);

        // 2. Remove o nó da lista antiga
        oldList.removeNode(node);

        // 3. Atualiza a menor frequência, se necessário
        if(oldList.size == 0 && oldFrequency == minFrequency) {
            minFrequency++;
        }

        // 4. Incrementa a frequência do nó
        node.frequency++;

        // 5. Busca ou cria a lista da nova frequência
        DoublyLinkedList newList = frequencyMap.computeIfAbsent(
                node.frequency,
                freq -> new DoublyLinkedList()
        );

        // 6. Adiciona o nó à nova lista
        newList.addToHead(node);
    }

    static void main() {
        LFUCache cache = new LFUCache(2);
        int[] expected = {1, -1, 3, -1, 3, 4};
        int[] actual = new int[expected.length];

        cache.put(1, 1);
        cache.put(2, 2);
        actual[0] = cache.get(1);
        cache.put(3, 3); // Evicts key 2.
        actual[1] = cache.get(2);
        actual[2] = cache.get(3);
        cache.put(4, 4); // Evicts key 1 after a frequency tie.
        actual[3] = cache.get(1);
        actual[4] = cache.get(3);
        actual[5] = cache.get(4);

        if (!java.util.Arrays.equals(expected, actual)) {
            throw new AssertionError("Expected " + java.util.Arrays.toString(expected)
                    + ", but got " + java.util.Arrays.toString(actual));
        }
        System.out.println(java.util.Arrays.toString(actual));
    }

    private static class Node {
        int key;
        int value;
        int frequency;

        Node prev;
        Node next;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
            this.frequency = 1;
        }
    }

    private static class DoublyLinkedList {
        Node head;
        Node tail;
        int size;

        private void addToHead(Node node) {
            if (head == null) {
                head = node;
                tail = node;
            } else {
                node.next = head;
                head.prev = node;
                head = node;
            }

            size++;
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

            size--;
        }

        private Node removeTail() {
            if (tail == null) {
                return null;
            }

            Node node = tail;
            removeNode(node);

            return node;
        }
    }
}
