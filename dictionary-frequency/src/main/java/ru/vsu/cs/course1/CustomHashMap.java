package ru.vsu.cs.course1;

import java.util.*;

public class CustomHashMap implements Map<String, Integer> {
    private static class Entry {
        String key;
        Integer value;
        Entry next;

        Entry(String key, Integer value) {
            this.key = key;
            this.value = value;
        }
    }

    private Entry[] table;
    private int size;
    private static final int INITIAL_CAPACITY = 16;
    private static final double LOAD_FACTOR = 0.75;

    public CustomHashMap() {
        table = new Entry[INITIAL_CAPACITY];
        size = 0;
    }

    private int hash(String key) {
        // Улучшенное хеширование для лучшего распределения
        int h = key.hashCode();
        h ^= (h >>> 20) ^ (h >>> 12);
        return Math.abs(h ^ (h >>> 7) ^ (h >>> 4)) % table.length;
    }

    private void resize() {
        Entry[] oldTable = table;
        table = new Entry[oldTable.length * 2];
        size = 0;
        for (Entry entry : oldTable) {
            while (entry != null) {
                put(entry.key, entry.value);
                entry = entry.next;
            }
        }
    }

    @Override
    public Integer put(String key, Integer value) {
        if (key == null) throw new IllegalArgumentException("Key cannot be null");
        // Разрешаем null для значений
        if ((double) (size + 1) / table.length > LOAD_FACTOR) {
            resize();
        }
        int index = hash(key);
        Entry entry = table[index];
        while (entry != null) {
            if (entry.key.equals(key)) {
                Integer oldValue = entry.value;
                entry.value = value;
                return oldValue;
            }
            entry = entry.next;
        }
        Entry newEntry = new Entry(key, value);
        newEntry.next = table[index];
        table[index] = newEntry;
        size++;
        return null;
    }

    @Override
    public Integer get(Object key) {
        if (key == null) return null;
        int index = hash((String) key);
        Entry entry = table[index];
        while (entry != null) {
            if (entry.key.equals(key)) {
                return entry.value;
            }
            entry = entry.next;
        }
        return null;
    }

    @Override
    public Integer getOrDefault(Object key, Integer defaultValue) {
        Integer value = get(key);
        return value != null ? value : defaultValue;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean containsKey(Object key) {
        return get(key) != null;
    }

    @Override
    public boolean containsValue(Object value) {
        for (Entry entry : table) {
            while (entry != null) {
                if (Objects.equals(entry.value, value)) {
                    return true;
                }
                entry = entry.next;
            }
        }
        return false;
    }

    @Override
    public Integer remove(Object key) {
        if (key == null) return null;
        int index = hash((String) key);
        Entry entry = table[index];
        Entry prev = null;
        while (entry != null) {
            if (entry.key.equals(key)) {
                if (prev == null) {
                    table[index] = entry.next;
                } else {
                    prev.next = entry.next;
                }
                size--;
                return entry.value;
            }
            prev = entry;
            entry = entry.next;
        }
        return null;
    }

    @Override
    public void putAll(Map<? extends String, ? extends Integer> m) {
        for (Map.Entry<? extends String, ? extends Integer> entry : m.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public void clear() {
        table = new Entry[INITIAL_CAPACITY];
        size = 0;
    }

    @Override
    public Set<String> keySet() {
        Set<String> keys = new HashSet<>();
        for (Entry entry : table) {
            while (entry != null) {
                keys.add(entry.key);
                entry = entry.next;
            }
        }
        return keys;
    }

    @Override
    public Collection<Integer> values() {
        List<Integer> values = new ArrayList<>();
        for (Entry entry : table) {
            while (entry != null) {
                values.add(entry.value);
                entry = entry.next;
            }
        }
        return values;
    }

    @Override
    public Set<Map.Entry<String, Integer>> entrySet() {
        Set<Map.Entry<String, Integer>> entries = new HashSet<>();
        for (Entry entry : table) {
            while (entry != null) {
                entries.add(new AbstractMap.SimpleEntry<>(entry.key, entry.value));
                entry = entry.next;
            }
        }
        return entries;
    }
}