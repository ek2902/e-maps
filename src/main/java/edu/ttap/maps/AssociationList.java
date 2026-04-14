package edu.ttap.maps;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * An association list is an implementation of a map via a list of key-value pairs.
 */
public class AssociationList<K, V> implements Map<K, V> {

    public class Pair<T, U> {
        public T fst;
        public U snd;
        public Pair(T fst, U snd) {
            this.fst = fst;
            this.snd = snd;
        }
    }

    public ArrayList<Pair<K, V>> map;

    /**
     * Clears the association list, removing all key-value pairs.
     */
    @Override
    public void clear() {
        map = new ArrayList<>();
    }

    /**
     * @param key the key to check
     * @return true iff this map contains a mapping for the specified key
     */
    @Override
    public boolean containsKey(Object key) {
        for (int i = 0; i < map.size(); i++) {
            if (map.get(i).fst.equals(key)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param value the value to check
     * @return true iff this map maps one or more keys to the specified value
     */
    @Override
    public boolean containsValue(Object value) {
       for (int i = 0; i < map.size(); i++) {
            if (map.get(i).snd.equals(value)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @return a set view of the mappings contained in this map
     */
    @Override
    public Set<Entry<K, V>> entrySet() {
        // NOTE: you do not need to implement this method!
        throw new UnsupportedOperationException("Unimplemented method 'entrySet'");
    }

    /**
     * @param key the key whose associated value is to be returned
     * @return the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key
     */
    @Override
    public V get(Object key) {
        for (int i = 0; i < map.size(); i++) {
            if (map.get(i).fst.equals(key)) {
                return map.get(i).snd;
            }
        }
        return null;
    }

    /**
     * @return true iff this map contains no key-value mappings
     */
    @Override
    public boolean isEmpty() {
        return map.size() == 0;
    }

    /**
     * @return a set view of the keys contained in this map
     */
    @Override
    public Set<K> keySet() {
        Set<K> set = new HashSet<>();

        for (int i = 0; i < map.size(); i++) {
            set.add(map.get(i).fst);
        }

        return set;
    }

    /**
     * If there is no entry for key in the map, updates the entry to associate key
     * with value. Otherwise, it updates the entry for key accordingly.
     * @param key the key with which the specified value is to be associated
     * @param value the value to be associated with the specified key
     * @return the previous value associated with key, or null if there was no
     *         mapping for key
     */
    @Override
    public V put(K key, V value) {
        V ret = null;

        for (int i = 0; i < map.size(); i++) {
            if (map.get(i).fst.equals(key)) {
                ret = map.get(i).snd;
                map.get(i).snd = value;
            } else {
                Pair<K, V> add = new Pair<K, V>(key, value);
                map.add(add);
            }
        }

        return ret;
    }

    /**
     * Copies all of the mappings from the specified map to this map. The effect of this
     * operation is equivalent to applying the put(K, V) operation to each entry in the
     * specified map.
     * @param m the map whose mappings are to be copied to this map
     */
    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        for (int i = 0; i < m.size(); i++) {
        }
        // TODO: implement me!
        throw new UnsupportedOperationException("Unimplemented method 'putAll'");
    }

    /**
     * Removes the mapping for a key from this map if it is present.
     * @param key the key whose mapping is to be removed from the map
     * @return the previous value associated with key, or null if there was no mapping for
     *         key.
     */
    @Override
    public V remove(Object key) {
        V ret = null;

        for (int i = 0; i < map.size(); i++) {
            if (map.get(i).fst.equals(key)) {
                ret = map.get(i).snd;
                map.remove(i);
            }
        }
        
        return ret;
    }

    /**
     * @return the number of key-value mappings in this map
     */
    @Override
    public int size() {
        int count = 0;

        for (int i = 0; i < map.size(); i++) {
            if (map.get(i).fst != null) {
                count++;
            }
        }
        
        return count;
    }

    /**
     * @return a collection of the values contained in this map, e.g., a list
     */
    @Override
    public Collection<V> values() {
        Collection<V> vals = new ArrayList<>();
        for (int i = 0; i < map.size(); i++) {
            put(map.get(i).fst, map.get(i).snd);
        }

        return vals;
        // TODO: Implement me!
    }
}
