package com.law.niuke;

import jdk.internal.util.xml.impl.Pair;

/**
 * 描述：二分查找
 * 每次查找时，arr[mid]如果大于查找目标，则查找目标在左半区，右下标为mid-1；如果arr[mid]小于查找目标，说明目标只可能在右半区，左坐标为mid+1；
 * 当左坐标比右坐标大时，说明arr中不包含查找目标
 * 注意：
 * 1、每次查找时，区间的左/右下标为mid+-1
 * 2、二分查找的结束标志要么为已查到，要么为左下标超出右下标
 *
 * @author law
 * @create 2019-02-23 下午11:26
 */
public class BinarySearch {

    /**
     * 普通数组。递归的方式实现二分查找
     *
     * @param array
     * @param num
     * @param i
     * @param j
     * @return
     */
    public static int binarySearch(int[] array, int num, int i, int j) {
        if (i <= j) {
            int mid = (i + j) / 2;
            if (array[mid] == num) {
                return mid;
            } else if (array[mid] > num) {
                return binarySearch(array, num, i, mid - 1);
            } else {
                return binarySearch(array, num, mid + 1, j);
            }
        }
        return -1;
    }

    /**
     * 普通数组。非递归的方式实现
     *
     * @param array
     * @param num
     * @return
     */
    public static int noRecursionBinarySearch(int[] array, int num) {
        int i = 0, j = array.length - 1;
        while (i <= j) {
            int mid = (i + j) / 2;
            if (array[mid] == num) {
                return mid;
            } else if (array[mid] > num) {
                j = mid - 1;
            } else {
                i = mid + 1;
            }
        }
        return -1;
    }




    public static void main(String[] args) {
        int[] arr1 = {1, 2, 3, 4, 5, 6};
        System.out.println(binarySearch(arr1, 1, 0, arr1.length - 1));
        System.out.println(binarySearch(arr1, 88, 0, arr1.length - 1));

        System.out.println(noRecursionBinarySearch(arr1, 1));
        System.out.println(noRecursionBinarySearch(arr1, 88));
    }


}

