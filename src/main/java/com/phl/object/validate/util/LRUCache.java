package com.phl.object.validate.util;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017-01-18.
 */
public class LRUCache<K,V> extends LinkedHashMap<K,V> {
    private static int DEFAULT_INITIAL_CAPACITY=100;
    private static int initialCapacity=DEFAULT_INITIAL_CAPACITY;
    public LRUCache(){
        super(DEFAULT_INITIAL_CAPACITY,0.75f,false);
    }
    public LRUCache(int initialCapacity){
        super(initialCapacity,0.75f,false);
        this.initialCapacity=initialCapacity;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry eldest) {
        return size() > initialCapacity;
    }
}
