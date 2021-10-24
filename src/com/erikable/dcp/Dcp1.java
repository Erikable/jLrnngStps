package com.erikable.dcp;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/*
This problem was asked by Apple.
A fixed point in an array is an element whose value is equal to its index.
Given a sorted array of distinct elements, return a fixed point, if one exists. Otherwise, return False.
For example, given [-6, 0, 2, 40], you should return 2. Given [1, 5, 7, 8], you should return False.
 */
public class Dcp1 {
    public static void main(String[] args) {
        int[] arr1 = new int[]{1, 2, 3, 5, 4, 6};
        int[] arr2 = new int[]{9, 1, 3, 2, 4, 8};
        int[] arr3 = new int[]{9, 21, 3, 2, 23, 8};

        //System.out.println(ret(mmm(arr2)));
        System.out.println(mmm(arr3));
    }

    public static Object mmm(int[] array) {
        Object obj;
        for (int i = 0; i < array.length; i++){
            if (i == array[i]) {
                obj = i;
                return obj;
            }
        }
        boolean f = false;
        return f;
    }

//    public static List<Object> mmm(int[] array) {
//        List<Object> list = new ArrayList<>();
//        for (int i = 0; i < array.length; i++){
//            if (i == array[i]) {
//                list.add((Integer)i);
//                return list;
//            }
//        }
//        boolean f = false;
//        list.add((Boolean)f);
//        return list;
//    }
//
//    public static Object ret(List<Object> list) {
//        Object o = list.get(0);
//        return o;
//    }

}
