package com.law.niuke;

/**
 * 描述：
 *
 * @author law
 * @create 2019-02-24 上午12:34
 */
public class SortedArraySearch {

    /**
     * from 牛客网 剑指offer
     * 在一个二维数组中（每个一维数组的长度相同），每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。
     * 请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
     * 数组长度为n>0
     *
     * 思路1：利用规律，如果右上角的数比目标大，去掉该列；如果比目标小，去掉该行。时间复杂度为log(m+n)
     * 思路2：利用二分查找，时间复杂度为nlogm
     */
    public static boolean search(int[][] array, int target) {
        if (array == null || array.length == 0 || array[0].length == 0) {
            return false;
        }
        int i = 0, j = array[0].length - 1;
        while (i < array.length && j >= 0) {
            if (array[i][j] > target) {
                j--;
            } else if (array[i][j] < target) {
                i++;
            } else {
                return true;
            }
        }
        return false;
    }
}
