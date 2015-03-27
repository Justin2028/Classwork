package edu.govschool.map;

import java.util.LinkedList;
import java.util.List;

/**
 * Basic implementation of a map data structure.
 * @author Mr. Davis
 * @param <K> the key type
 * @param <V> the value type
 */
public class Map<K, V>
{
    // List of our key/value pairs
    private List<KeyValuePair<K, V>> list;
    
    /**
     * Creates a new empty Map.
     */
    public Map()
    {
        this.list = new LinkedList<>();
    }
    
    /**
     * Creates a new Map with the specified keys and values. If the parameter
     * arrays differ in length, the shorter length will be used.
     * @param keys an array of keys
     * @param values an array of values
     */
    public Map(K[] keys, V[] values)
    {
        this();
        
        int length = (keys.length > values.length) ? 
                                                    values.length : keys.length;
        
        for (int i = 0; i < length; i++) {
            list.add(new KeyValuePair(keys[i], values[i]));
        }
    }
    
    /**
     * Add a key/value pair to the Map.
     * @param key the new key
     * @param value the new value
     * @throws IllegalArgumentException the key is already in the map
     */
    public void set(K key, V value) throws IllegalArgumentException
    {
        for (KeyValuePair<K, V> p : this.list) {
            if (p.getKey().equals(key) || key == p.getKey()) {
                throw new IllegalArgumentException("Key already in map.");
            }
        }
        
        list.add(new KeyValuePair(key, value));
    }
    
    /**
     * Get a value for a given key in the Map.
     * @param key the key to lookup
     * @return the associated value, or null if the key is not found
     */
    public V get(K key)
    {
        try {
            return getPair(key, "Key not found in map.").getValue();
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
    
    /**
     * Change the value of a stored key. If the key is not found in the map,
     * the method does nothing.
     * @param key the key of the pair to update
     * @param value the new value of the pair
     */
    public void updateValueForKey(K key, V value)
    {
        try {
            getPair(key, "Key is not found").updateValue(value);
        } catch (IllegalArgumentException e) {}
    }
    
    /**
     * Get the key/value pair stored with the given key.
     * @param key the key to find
     * @param err the error message to display if the key is not found
     * @return the pair with the given key
     * @throws IllegalArgumentException the key is not found
     */
    private KeyValuePair<K, V> getPair(K key, String err) 
                                                throws IllegalArgumentException
    {
        for (KeyValuePair<K, V> p : this.list) {
            if (p.getKey().equals(key) || key == p.getKey()) {
                return p;
            }
        }
        
        throw new IllegalArgumentException(err);
    }
    
    /**
     * Remove a key/value pair in the Map.
     * @param key the key of the pair to remove
     * @return true if the pair was removed, false otherwise
     */
    public boolean remove(K key)
    {
        for (int i = 0; i < this.list.size(); i++) {
            KeyValuePair<K, V> p = this.list.get(i);
            if (p.getKey().equals(key) || key == p.getKey()) {
                this.list.remove(p);
                return true;
            }
        }
        
        return false;
    }
    
    /**
     * Create a string representation of a Map.
     * @return the string representation
     */
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        
        for (KeyValuePair p : this.list) {
            String pair = p.toString() + "\n";
            sb.append(pair);
        }
        
        return sb.toString();
    }
    
    /**
     * A private class to represent key/value pairs in our map.
     * @param <K> the type of the keys
     * @param <V> the type of the values
     */
    private class KeyValuePair<K, V>
    {
        private final K key;
        private V value;
        
        /**
         * Create a new key/value pair.
         * @param key the key
         * @param value the value
         */
        public KeyValuePair(K key, V value)
        {
            this.key = key;
            this.value = value;
        }
        
        /**
         * Get the key stored in this key/value pair.
         * @return the key
         */
        public K getKey()
        {
            return this.key;
        }
        
        /**
         * Get the value stored in this key/value pair.
         * @return the value
         */
        public V getValue()
        {
            return this.value;
        }
        
        /**
         * Change the value stored in the pair.
         * @param value the new value
         */
        public void updateValue(V value)
        {
            this.value = value;
        }
        
        /**
         * Create a string representation of a key/value pair.
         * @return the string representation
         */
        @Override
        public String toString()
        {
            return "{ " + this.key + " -> " + this.value + " }";
        }
    }
    
    public static void main(String[] args) 
    {
        String[] keys = {"one", "two", "three"};
        Integer[] vals = {1, 2, 3};
        Map<String, Integer> map = new Map<>(keys, vals);
        
        System.out.println(map);
        
        System.out.println(map.get("two"));
        System.out.println(map.get("tw"));
        
        map.remove("two");
        
        System.out.println(map);
    }
}