package com.pankaj.interview;

import java.util.*;

public class Test {

    public static void main(String[] args) {
        List<String> list= Arrays.asList("JavaFX", null, "OpenCV", "Hadoop");

//        Collections.sort(list);
//        list.stream().forEach(System.out::println);
        // Error: NullPointerException

        //Arrays.sort(list, Comparator.nullsLast(String::compareTo).compare(s1, s2);
        //list.stream().forEach(System.out::println);

        Collections.sort(list,Comparator.nullsLast(Comparator.naturalOrder()));
        list.stream().forEach(System.out::println);


    }
}
