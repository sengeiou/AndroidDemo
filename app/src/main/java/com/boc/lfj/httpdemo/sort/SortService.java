package com.boc.lfj.httpdemo.sort;

/**
 * Created by Administrator on 2017/3/5.
 */

public class SortService {

    /**
     * 冒泡排序
     */
    public static void bubbleSort(int[] numbers) {
        int size = numbers.length;
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - 1 - i; j++) {
                if (numbers[j] > numbers[j + 1]) {
                    swap(numbers, i, j);
                }
            }
        }
    }

    /**
     * 快速排序
     */
    public static int getMiddle(int[] numbers, int low, int high) {
        int temp = numbers[low]; //数组的第一个作为中轴
        while (low < high) {
            while (low < high && numbers[high] > temp) {
                high--;
            }
            numbers[low] = numbers[high];//比中轴小的记录移到低端
            while (low < high && numbers[low] < temp) {
                low++;
            }
            numbers[high] = numbers[low]; //比中轴大的记录移到高端
        }
        numbers[low] = temp; //中轴记录到尾
        return low; // 返回中轴的位置
    }

    public static void quickSort(int[] numbers, int low, int high) {
        if (low < high) {
            int middle = getMiddle(numbers, low, high);
            quickSort(numbers, low, middle - 1);
            quickSort(numbers, middle + 1, high);
        }
    }

    /**
     * 选择排序
     */
    public static void selectSort(int[] numbers) {
        int size = numbers.length;
        for (int i = 0; i < size; i++) {
            int k = i;
            for (int j = size - 1; j > i; j++) {
                if (numbers[j] < numbers[k]) {
                    k = j;
                }
            }
            if (i != k) {
                swap(numbers, i, k);
            }
        }
    }

    public static void insertSort(int[] numbers) {
        int size = numbers.length;
        int temp = 0;
        int j = 0;
        for (int i = 0; i < size; i++) {
            temp = numbers[i];
            for ( j = i; j > 0 && temp < numbers[j - 1]; j--) {
                numbers[j] = numbers[j - 1];
            }
            numbers[j] = temp;
        }
    }

    //交换数组中两个数值位置，并且不需要temp
    public static void swap(int data[], int i, int j) {
        if (i == j) {
            return;
        }
        data[i] = data[i] + data[j];
        data[j] = data[i] - data[j];
        data[i] = data[i] - data[j];
    }

    public static void main(String[] args) {
        int[] numbers = {10, 20, 15, 0, 6, 7, 2, 1, -5, 55};

        bubbleSort(numbers);

        quickSort(numbers, 0, numbers.length - 1);

        selectSort(numbers);

        insertSort(numbers);
    }
}
