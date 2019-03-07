package com.law.niuke;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 描述：矩阵相关的算法题
 *
 * @author law
 * @create 2019-03-08 上午12:53
 */
public class MatrixAl {

    /**
     * 给定一个由 0 和 1 组成的矩阵，找出每个元素到最近的 0 的距离。
     * <p>
     * 两个相邻元素间的距离为 1 。
     * <p>
     * 示例 1:
     * 输入:
     * <p>
     * 0 0 0
     * 0 1 0
     * 0 0 0
     * <p>
     * 输出:
     * <p>
     * 0 0 0
     * 0 1 0
     * 0 0 0
     *
     * @param matrix
     * @return
     */
    public int[][] s1(int[][] matrix) {
        //BFS思路：最先被确认的是0，然后是1、2.....
        if (matrix.length == 0) {
            return new int[0][0];
        }
        int n = matrix.length, m = matrix[0].length;
        int[][] ret = new int[n][m];
        byte[][] map = new byte[n][m];

        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] == 0) {
                    queue.add(new int[]{i, j});
                    map[i][j] = 1;
                }
            }
        }

        //上下左右
        int[][] vectors = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        while (!queue.isEmpty()) {
            int[] ele = queue.poll();
            //搜索上、下、左、右
            for (int[] v : vectors) {
                int i = ele[0] + v[0], j = ele[1] + v[1];
                if (i < 0 || i >= n || j < 0 || j >= m) {
                    continue;
                }
                if (map[i][j] == 0) {
                    ret[i][j] = ret[ele[0]][ele[1]] + 1;
                    map[i][j] = 1;
                    queue.add(new int[]{i, j});
                }
            }
        }

        return ret;
    }


}
