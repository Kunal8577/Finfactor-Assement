package com.pokedex.pokedex_project.cache;
//package com.example.pokedex.cache;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache<K, V> {
    private final int maxEntries;
    private final long defaultTtlMs;
    private final LinkedHashMap<K, CacheEntry<V>> map;
    private long hits = 0;
    private long misses = 0;

    private static class CacheEntry<V> {
        V value;
        long expiresAt;
        CacheEntry(V value, long expiresAt) { this.value = value; this.expiresAt = expiresAt; }
    }

    public LRUCache(int maxEntries, long defaultTtlMs) {
        this.maxEntries = maxEntries;
        this.defaultTtlMs = defaultTtlMs;
        this.map = new LinkedHashMap<>(16, 0.75f, true);
    }

    public synchronized V get(K key) {
        CacheEntry<V> e = map.get(key);
        if (e == null) { misses++; return null; }
        if (System.currentTimeMillis() > e.expiresAt) {
            map.remove(key);
            misses++;
            return null;
        }
        hits++;
        return e.value;
    }

    public synchronized void put(K key, V value) { put(key, value, defaultTtlMs); }

    public synchronized void put(K key, V value, long ttlMs) {
        if (map.size() >= maxEntries) {
            Iterator<K> it = map.keySet().iterator();
            if (it.hasNext()) map.remove(it.next());
        }
        map.put(key, new CacheEntry<>(value, System.currentTimeMillis() + ttlMs));
    }

    public synchronized void clear() { map.clear(); hits = 0; misses = 0; }

    public synchronized Map<String, Object> stats() {
        Map<String,Object> s = new java.util.HashMap<>();
        s.put("size", map.size());
        s.put("maxEntries", maxEntries);
        s.put("defaultTtlMs", defaultTtlMs);
        s.put("hits", hits);
        s.put("misses", misses);
        return s;
    }
}

