package com.geeksforgeeks;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public class BitonicSearch
{

    public static void main(String[] args)
    {
        int[] array = new int[]{8, 9, 10, 11, 12, 13, 15, 16, 4, 3, 2, 1, 0};

        Scanner scanner = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

        int T = scanner.nextInt();
        for (int t = 0; t < T; t++) {
            int N = scanner.nextInt();

            int max = findMax(array, 0, array.length - 1);
            int a = searchIncreasing(array, 0, max, N);
            if (a == -1) {
                a = searchDecreasing(array, max + 1, array.length - 1, N);
            }
            pw.println(a);

            pw.println(bitonicSearch(array, 0, array.length - 1, N));
        }

        pw.close();
        scanner.close();

    }

    private static int bitonicSearch(int[] array, int low, int high, int key)
    {
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (array[mid] == key)
                return mid;

            if (array[mid] > array[mid + 1]) {
                if (array[mid] > key) {
                    int a = searchDecreasing(array, mid + 1, array.length, key);
                    if (a == -1) {
                        a = searchIncreasing(array, low, mid - 1, key);
                    }
                    return a;

                } else if (array[mid] < key) {
                    high = mid - 1;
                }

            } else if (array[mid] < array[mid + 1]) {
                if (array[mid] > key) {
                    int a = searchIncreasing(array, low, mid - 1, key);
                    if (a == -1) {
                        a = searchDecreasing(array, mid + 1, high, key);
                    }
                    return a;

                } else if (array[mid] < key) {
                    low = mid + 1;
                }
            }
        }
        return -1;
    }

    private static int searchDecreasing(int[] array, int low, int high, int key)
    {
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (array[mid] > key) {
                low = mid + 1;
            } else if (array[mid] < key) {
                high = mid - 1;
            } else
                return mid;
        }
        return -1;
    }

    private static int searchIncreasing(int[] array, int low, int high, int key)
    {
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (array[mid] > key) {
                high = mid - 1;
            } else if (array[mid] < key) {
                low = mid + 1;
            } else
                return mid;
        }
        return -1;
    }

    private static int findMax(int[] array, int low, int high)
    {
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (array[mid] > array[mid + 1]) {
                high = mid;
            } else if (array[mid] < array[mid + 1]) {
                low = mid + 1;
            } else return mid;

        }
        return high;
    }
}
