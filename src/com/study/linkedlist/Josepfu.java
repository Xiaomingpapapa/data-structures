package com.study.linkedlist;

public class Josepfu {
    public static void main(String[] args) {
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList(10);
//        circleSingleLinkedList.showBoy();
        circleSingleLinkedList.countBoy(10, 5, 3);
    }
}

class CircleSingleLinkedList {
    Boy first = null;

    public CircleSingleLinkedList(int num) {
        addBoy(num);
    }

    /**
     * 创建指定 boy 数量的链表
     *
     * @param num
     */
    public void addBoy(int num) {
        if (num < 1) {
            System.out.println("请输入有效数字");
            return;
        }
        Boy curBoy = null;
        for (int i = 1; i <= num; i++) {
            Boy boy = new Boy(i);
            if (i == 1) {
                //初始化头结点
                first = boy;
                boy.next = first;
            } else {
                curBoy.next = boy;
                boy.next = first;
            }
            //curBoy 指向当前链表最后一个元素
            curBoy = boy;
        }
    }

    /**
     * 遍历链表
     */
    public void showBoy() {
        if (first == null) {
            System.out.println("链表为空");
            return;
        }
        Boy curBoy = first;
        while (true) {
            System.out.printf("小孩的编号%d\n", curBoy.no);
            curBoy = curBoy.next;
            if (curBoy == first) {
                break;
            }
        }

    }

    /**
     * 打印出圈序列
     * @param num 圈中男孩个数
     * @param startNum 从第几个男孩开始报数
     * @param countNum 报数的次数
     */
    public void countBoy(int num, int startNum, int countNum) {
        if (num < 1 || startNum < 1 || startNum > num) {
            System.out.println("请输入有效参数");
            return;
        }
        //添加男孩
        addBoy(num);
        //初始化 helper 指针，指向 first 前一个位置，初始化指向链表最后一个男孩位置
        Boy helper = first;
        while (true) {
            if (helper.next == first) {
                break;
            }
            helper = helper.next;
        }

        //first 和 helper 同时向前移动 startNum - 1 个位置
        for (int i = 0; i < startNum - 1; i++) {
            first = first.next;
            helper = helper.next;
        }

        //出圈操作
        while (true) {
            if (helper == first) {
                break;
            }
            for (int i = 0; i < countNum - 1; i++) {
                first = first.next;
                helper = helper.next;
            }
            System.out.printf("出圈男孩序号%d\n", first.no);
            //出圈操作
            first = first.next;
            helper.next = first;
        }
        System.out.printf("最后出圈的男孩序号%d", first.no);

    }
}

class Boy {
    int no;
    Boy next;

    public Boy(int no) {
        this.no = no;
    }
}
