package org.example;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> integers = new ArrayList<>();
        integers.add(1);
        integers.add(2);
        integers.add(3);
        integers.add(4);
        integers.add(5);
        integers.add(6);
        integers.add(7);
        MyArrayList<Integer> myArrayList = new MyArrayList<>(integers);
        MyArrayList.bubbleSort(myArrayList);
        System.out.println(myArrayList);

    }
}