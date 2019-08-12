package com.study.recursion;

public class Queue8 {

    //初始化数组来存放问题的解法，数组的下标表示放置第几个皇后（0-7），数组下标对应的值表示每一行皇后放置的位置（0-7）
    int max = 8;
    int[] arr = new int[max];
    static int count = 0;
    static int judgeCount = 0;

    public static void main(String[] args) {
        Queue8 queue8 = new Queue8();
        queue8.check(0);
        System.out.printf("共有%d种解法", count);
        System.out.printf("共判断%d次", judgeCount);
    }

    /**
     * 打印每次正确的解法
     */
    public void print() {
        count++;
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println("\n");
    }

    /**
     * 判断当前放置的皇后位置是否与之前的皇后冲突
     *
     * @param n 表示第几个皇后
     */
    public boolean judge(int n) {
        judgeCount++;
        for (int i = 0; i < n; i++) {
            //判断皇后是否放在同一列，或者同一斜线（根据正方形对角线）
            if (arr[n] == arr[i] || Math.abs(n - i) == Math.abs(arr[n] - arr[i])) {
                return false;
            }
        }
        return true;
    }

    /**
     * 递归寻找所有解法
     *
     * @param n 放置第几个皇后
     */
    public void check(int n) {
        //标识所有皇后已放完，结束递归
        if (n == max) {
            print();
            return;
        }

        //这边注意，每一层 check 方法中都会有这个循环回溯过程，这个回溯过程从后往前，直到找出所有正确的解
        for (int i = 0; i < max; i++) {
            arr[n] = i;
            if (judge(n)) {
                //如果当前皇后放置合理，继续放置下一个皇后
                check(n + 1);
            }
            //否则直接进行回溯
        }
    }


}
