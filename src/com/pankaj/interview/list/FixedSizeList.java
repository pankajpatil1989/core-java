package com.pankaj.interview.list;

import java.util.Arrays;
import java.util.List;

public class FixedSizeList {

    public static void main(String[] args) {
        List<String> list = Arrays.asList(new String[2]);

        // java.lang.UnsupportedOperationException
//        list.add("abc");
//        list.add("pqr");
//        list.add("xyz");
        System.out.println("List[0]: "+list.get(0)); // null
        System.out.println("List1]: "+list.get(1));  // null
//        System.out.println("List1]: "+list.get(2)); // Error:ArrayIndexOutOfBoundsException: 2

        list.set(0, "abc");
        list.set(1, "pqr");

        // java.lang.ArrayIndexOutOfBoundsException: 2
//        list.set(2, "xyz");

        System.out.println(list.size());
    }
}
