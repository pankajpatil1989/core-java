package com.pankaj.interview.designpattern;

import java.util.Arrays;
import java.util.List;

public class StrategyDesignPattern {

    public static void main(String[] args) {

        List<Strategy> strategies =
                Arrays.asList(
                        new LazyStratgey(),
                        new ActiveStratgey()
                );

        for(Strategy stg : strategies){
            stg.performTask();
        }
        // Output:
//        Perform task a day before deadline!
//        Perform task now!
    }
}
interface Strategy {
    public void performTask();
}

class LazyStratgey implements Strategy{

    @Override
    public void performTask() {
        System.out.println("Perform task a day before deadline!");
    }

}

class ActiveStratgey implements Strategy{

    @Override
    public void performTask() {
        System.out.println("Perform task now!");
    }

}