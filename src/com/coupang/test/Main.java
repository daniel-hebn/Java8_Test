package com.coupang.test;

import java.io.BufferedReader;

public class Main {

    private static BufferedReaderProcessor ONE_LINE_READER = (BufferedReader br) -> br.readLine();
    private static BufferedReaderProcessor TWO_LINE_READER = (BufferedReader br) -> br.readLine() +  br.readLine();

    public static void main(String[] args) throws Exception {
//        LambdaExample lambdaExample = new LambdaExample();
//
//        // NOTE: 자바 8 이전
//        System.out.println(lambdaExample.processFile());
//        System.out.println("===");
//
//        // NOTE: 자바 8 이후
//        System.out.println(lambdaExample.processFile((BufferedReader br) -> br.readLine()));
//        System.out.println(lambdaExample.processFile((BufferedReader br) -> br.readLine() + br.readLine()));
        //        System.out.println(ONE_LINE_READER);
        //        System.out.println(TWO_LINE_READER);


        StreamExample streamExample = new StreamExample();
//        streamExample.filterExample();
//        streamExample.distinctExample();
        streamExample.mapExample();
//        streamExample.anyMatchExample();
//        streamExample.intStreamExample();
//        streamExample.optionalExample();
//        streamExample.cretaStaticsRPS();
    }
}
