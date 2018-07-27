// Generic Classes
// Exercise 19.9

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Generic Classes
 * Written by Daniel de Sao Jose
 *
 * (Sort ArrayList ) Write the following method that sorts an ArrayList :
 * public static <E extends Comparable<E>>
 * void sort(ArrayList<E> list)
 */

public class DanielAssignment5 {
    public static void main(String[] args) {

        Random random = new Random();

        // Create an ArrayList of Integers. Integers are comparable.
        ArrayList<Integer> myIntegerList = new ArrayList<>();
        for(int i = 0; i < 6; i++) {
            myIntegerList.add(random.nextInt(10));
        }

        // Exercise using sort with the ArrayList of Integer objects.
        System.out.println("An ArrayList of Integer objects contain the following elements:");
        for (Object o : myIntegerList) {
            System.out.println(" " + o);
        }
        System.out.println("After using the sort method, the ArrayList of Integer objects contain the " +
                "following elements:");
        sort(myIntegerList);
        for (Object o : myIntegerList) {
            System.out.println(" " + o);
        }
        System.out.println();

        // Create an ArrayList of Doubles. Doubles are comparable.
        ArrayList<Double> myDoubleList = new ArrayList<>();
        for(int i = 0; i < 6; i++) {
            myDoubleList.add(random.nextDouble());
        }

        System.out.println("An ArrayList of Double objects contain the following elements:");
        for (Object o : myDoubleList) {
            System.out.println(" " + o);
        }
        sort(myDoubleList);
        System.out.println("After using the sort method, the ArrayList of Double objects contain the " +
                "following elements:");
        for (Object o : myDoubleList) {
            System.out.println(" " + o);
        }
        System.out.println();
    }

    public static <E extends Comparable<E>> void sort(ArrayList<E> list) {
        Collections.sort(list);
    }

}

