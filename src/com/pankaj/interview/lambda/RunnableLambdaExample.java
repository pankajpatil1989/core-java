package com.pankaj.interview.lambda;

public class RunnableLambdaExample {
    public static void main(String[] args) {
        /**
         *  prior to java 8
         */
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("Inside run() method:old");
            }
        };

        new Thread(runnable).start();

    }

}
