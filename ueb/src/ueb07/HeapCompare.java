package ueb07;

import java.util.*;

public class HeapCompare {

/**
     * Speed tests
     * @param args */


    public static void main(String[] args) {
        HeapStack<Integer> h = new HeapStack<>(1000000);
        Stack<Integer> s = new Stack<>();

        int[] ints = randomArray(1000000);

        long startTimer = System.currentTimeMillis();
        for(int elem: ints) {
            h.push(elem);
        }
        long endTimer = System.currentTimeMillis();
        System.out.println("HeapStack auffüllen: " + (endTimer - startTimer));

        startTimer = System.currentTimeMillis();
        for(int elem: ints) {
            s.push(elem);
        }
        endTimer = System.currentTimeMillis();
        System.out.println("Stack auffüllen: " + (endTimer - startTimer));



        startTimer = System.currentTimeMillis();
        for(int i = 0; i < 1000000; i++) {
            h.peek();
        }
        endTimer = System.currentTimeMillis();
        System.out.println("HeapStack spicken: " + (endTimer - startTimer));

        startTimer = System.currentTimeMillis();
        for(int i = 0; i < 1000000; i++) {
            s.peek();
        }
        endTimer = System.currentTimeMillis();
        System.out.println("Stack spicken: " + (endTimer - startTimer));


        startTimer = System.currentTimeMillis();
        for(int i = 0; i < 1000000; i++) {
            h.pop();
        }
        endTimer = System.currentTimeMillis();
        System.out.println("HeapStack leeren: " + (endTimer - startTimer));

        startTimer = System.currentTimeMillis();
        for(int i = 0; i < 1000000; i++) {
            s.pop();
        }
        endTimer = System.currentTimeMillis();
        System.out.println("Stack leeren: " + (endTimer - startTimer));

        HeapQueue<Integer> hq = new HeapQueue<>(1000000);
        ArrayDeque<Integer> q = new ArrayDeque<>();

        System.out.println();
        System.out.println();

        startTimer = System.currentTimeMillis();
        for(int elem: ints) {
            hq.add(elem);
        }
        endTimer = System.currentTimeMillis();
        System.out.println("HeapQueue auffüllen: " + (endTimer - startTimer));

        startTimer = System.currentTimeMillis();
        for(int elem: ints) {
            q.add(elem);
        }
        endTimer = System.currentTimeMillis();
        System.out.println("ArrayDeque auffüllen: " + (endTimer - startTimer));



        startTimer = System.currentTimeMillis();
        for(int i = 0; i < 1000000; i++) {
            hq.peek();
        }
        endTimer = System.currentTimeMillis();
        System.out.println("HeapQueue spicken: " + (endTimer - startTimer));

        startTimer = System.currentTimeMillis();
        for(int i = 0; i < 1000000; i++) {
            q.peek();
        }
        endTimer = System.currentTimeMillis();
        System.out.println("ArrayDeque spicken: " + (endTimer - startTimer));


        startTimer = System.currentTimeMillis();
        for(int i = 0; i < 1000000; i++) {
            hq.poll();
        }
        endTimer = System.currentTimeMillis();
        System.out.println("HeapQueue leeren: " + (endTimer - startTimer));

        startTimer = System.currentTimeMillis();
        for(int i = 0; i < 1000000; i++) {
            q.poll();
        }
        endTimer = System.currentTimeMillis();
        System.out.println("ArrayDeque leeren: " + (endTimer - startTimer));
    }

/**
     * Creates an random Integerarray
     * @param i size of the integer
     * @return the random array*/


    public static int[] randomArray(int i) {
        int[] ints = new int[i];
        for(int temp = 0; temp < i; i++) {
            ints[temp] = (int) Math.random() * Integer.MAX_VALUE;
        }
        return ints;
    }
}
