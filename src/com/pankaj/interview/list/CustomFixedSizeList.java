package com.pankaj.interview.list;

import java.util.ArrayList;

public class CustomFixedSizeList<T> extends ArrayList<T>{
    private int limit;

    public CustomFixedSizeList(int limit){
        this.limit = limit;
    }

    @Override
    public boolean add(T item){
        if (this.size() > limit - 1) {
            throw new RuntimeException("List exceed.");

        }
        super.add(item);
        return true;
    }

    public static void main(String[] args) {
        CustomFixedSizeList customFixedSizeList = new CustomFixedSizeList(2);
        customFixedSizeList.add(1); // [0]
        customFixedSizeList.add(2); // [1]
//        customFixedSizeList.add(3); // [2]  java.lang.RuntimeException: List exceed.

        System.out.println(customFixedSizeList.size());

    }

}



