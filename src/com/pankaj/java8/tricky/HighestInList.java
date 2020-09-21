package com.pankaj.java8.tricky;

import java.util.*;

public class HighestInList {

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
        int result = numbers.stream().mapToInt(i -> i).max().getAsInt();
        System.out.println("Highest number in List : " + result);
    }


}
