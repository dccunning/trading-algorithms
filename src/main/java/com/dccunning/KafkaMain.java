package com.dccunning;

/**
 * Run all Kafka producers and consumers
 */
public class KafkaMain {
    public static void main(String[] args) {
        System.out.println("Hello and welcome!");
        System.out.print('\n');
        int total = 0;
        for (int i = 5; i <= 100; i=i+5) {
            total += i;
            System.out.println("i = " + i);
        }
        System.out.println(total/5);
    }
}