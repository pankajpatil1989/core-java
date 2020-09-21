package com.pankaj.tricky;

interface Cache {
    public void methodA();
}

class MemoryCache implements Cache {

    private static MemoryCache SINGLE_INSTANCE = null;
    private static int count = 0;
    private MemoryCache() {}
    public static MemoryCache getInstance() {
        if (SINGLE_INSTANCE == null) {
            synchronized (MemoryCache.class) {
                if (SINGLE_INSTANCE == null) {
                    count++;
                    SINGLE_INSTANCE = new MemoryCache();
                }
            }
        }
        return SINGLE_INSTANCE;
    }

    @Override
    public void methodA() {
        System.out.println("MemoryCache::methodA() called and Count = " + count);

    }
}

class DiskCache implements Cache {

    private static DiskCache SINGLE_INSTANCE = null;
    private static int count = 0;
    private DiskCache() {}
    public static DiskCache getInstance() {
        if (SINGLE_INSTANCE == null) {
            synchronized (MemoryCache.class) {
                if (SINGLE_INSTANCE == null) {
                    count++;
                    SINGLE_INSTANCE = new DiskCache();
                }
            }
        }
        return SINGLE_INSTANCE;
    }

    @Override
    public void methodA() {
        System.out.println("DiskCache::methodA() called and Count =" + count);
    }
}

public class ClientPreventInstantiateConcreteClass {

    public static void main(String[] args) {
        // Not allowed compilation error
        // MemoryCache memoryCache1 = new MemoryCache();

        MemoryCache memoryCache1 = MemoryCache.getInstance();
        memoryCache1.methodA();
        // MemoryCache::methodA() called and Count = 1

        MemoryCache memoryCache2 = MemoryCache.getInstance();
        memoryCache2.methodA();
        // MemoryCache::methodA() called and Count = 1

        DiskCache diskCache1 = DiskCache.getInstance();
        diskCache1.methodA();
        // DiskCache::methodA() called and Count = 1

        DiskCache diskCache2 = DiskCache.getInstance();
        diskCache2.methodA();
        // DiskCache::methodA() called and Count = 1

        // try to break rules
        // 1> using reflection : not able to create instance
//        try {
//            Class cls = Class.forName("MemoryCache");
//            MemoryCache obj = (MemoryCache) cls.newInstance();
//            MemoryCache obj1 = (MemoryCache) cls.newInstance();
//
//            System.out.println(obj);
//            System.out.println(obj1);
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }

        // 2> using cloning : not able to create instance
//        try {
//            MemoryCache obj2 = (MemoryCache) memoryCache1.clone();
//            System.out.println(obj2);
//        } catch (CloneNotSupportedException e) {
//            e.printStackTrace();
//        }

        // 3> using class loader : not able to create instance
//        MemoryCache obj = null;
//        try {
//            obj = (MemoryCache) new MemoryCache().getClass()
//                    .getClassLoader().loadClass("JBTClassLoader").newInstance();
//            // Fully qualified classname should be used.
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }





    }
}
