package com.study.recursion;

public class MiGong {
    public static void main(String[] args) {
        //创建迷宫数组
        int[][] map = new int[8][7];
        //设置障碍物，1 代表障碍物
        for (int i = 0; i < 8; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }
        for (int i = 0; i < 7; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }
        map[2][1] = 1;
        map[2][2] = 1;
        System.out.println("打印迷宫数组");
        for (int[] ints : map) {
            for (int anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println("\r");
        }

        setWay(map, 1, 1);
        System.out.println("打印迷宫路线");
        for (int[] ints : map) {
            for (int anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println("\r");
        }
    }

    /**
     * 找到可以走通的路线 0代表该点还没有走过，1代表障碍物，2代表该点是通的，3代表该点不通（已经走过）
     * @param map 地图数组
     * @param i 起始位置
     * @param j 起始位置
     * @return 路可以走则返回 true
     */
    public static boolean setWay(int[][] map, int i, int j) {
        //设置终点，递归出口
        if (map[6][5] == 2) {
            return true;
        }
        if (map[i][j] == 0) {
            //先假设这个点是走得通的
            map[i][j] = 2;
            //设置走的策略，下 -> 右 -> 上 -> 左，进行递归探索路线
            if (setWay(map, i + 1, j)) {
                return true;
            } else if (setWay(map, i, j + 1)) {
                return true;
            } else if (setWay(map, i - 1, j)) {
                return true;
            } else if (setWay(map, i, j - 1)) {
                return true;
            } else {
                //证明这条路走不通，进行回溯
                map[i][j] = 3;
                return false;
            }
        } else {
            //该点为1,2,3 的情况，直接返回 false
            return false;
        }

    }
}
