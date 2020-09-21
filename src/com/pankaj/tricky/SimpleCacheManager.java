package com.pankaj.tricky;

import java.util.*;

public class SimpleCacheManager {

    private static SimpleCacheManager instance;
    private static Object monitor = new Object();
    private Map<String, Object> cache = Collections.synchronizedMap(new HashMap<String, Object>());

    private SimpleCacheManager() {
    }

    public void put(String cacheKey, Object value) {
        System.out.println("In put");
        cache.put(cacheKey, value);
    }

    public Object get(String cacheKey) {
        System.out.println("In get");
        return cache.get(cacheKey);
    }

    public void clear(String cacheKey) {
        System.out.println("In clear");
        cache.put(cacheKey, null);
    }

    public void clear() {
        cache.clear();
    }

    public static SimpleCacheManager getInstance() {
        if (instance == null) {
            synchronized (monitor) {
                if (instance == null) {
                    instance = new SimpleCacheManager();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {

        Employee emp1 = new Employee(5, "pqr", 10000);
        Employee emp2 = new Employee(2, "xyz", 5000);
        Employee emp3 = new Employee(7, "abc", 11000);
        Employee emp4 = new Employee(1, "asdf", 2000);
        Employee emp5 = new Employee(1, "fdsa", 5000);


        SimpleCacheManager simpleCacheManager = new SimpleCacheManager();

        simpleCacheManager.put(emp1.getName(),emp1);
        simpleCacheManager.put(emp2.getName(),emp2);
        simpleCacheManager.put(emp3.getName(),emp3);
        simpleCacheManager.put(emp4.getName(),emp4);
        simpleCacheManager.put(emp5.getName(),emp5);

        simpleCacheManager.get("abc");
        simpleCacheManager.get("abc");

    }

}