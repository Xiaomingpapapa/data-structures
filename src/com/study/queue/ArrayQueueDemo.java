package com.study.queue;

import java.util.Scanner;

public class ArrayQueueDemo {
    public static void main(String[] args) {
        ArrayQueue queue = new ArrayQueue(3);
        System.out.println("测试数组实现队列~~~~~~");
        char key = ' ';//接受用户的输入
        boolean loop = true;
        Scanner scanner = new Scanner(System.in);
        while (loop) {
            System.out.println("s(show):显示队列");
            System.out.println("e(exit):退出程序");
            System.out.println("a(add):添加元素到队列");
            System.out.println("g(get):获取队列元素");
            System.out.println("h(head):获取队列头部元素");
            key = scanner.next().charAt(0);
            switch (key) {
                case 's':
                    queue.showQueue();
                    break;
                case 'e':
                    loop = false;
                    break;
                case 'a':
                    System.out.println("请输入待加入的元素");
                    int value = scanner.nextInt();
                    queue.addQueue(value);
                    break;
                case 'g':
                    try {
                        queue.getQueue();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        queue.headQueue();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
            }
        }
    }
}

class ArrayQueue {
    int maxSize;//队列最大容量
    int rear;//指向队列尾部元素
    int front;//指向队列头部的前一个位置
    int arr[];//存放队列数据的数组

    /**
     * 初始化队列属性
     * @param maxSize
     */
    public ArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        rear = -1;
        front = -1;
        arr = new int[maxSize];
    }

    /**
     * 判断队列是否为空
     * @return
     */
    public boolean isEmpty() {
        return rear == front;
    }

    /**
     * 判断队列是否已满
     * @return
     */
    public boolean isFull() {
        return rear == maxSize - 1;
    }

    /**
     * 往队列加入元素
     * @param value
     */
    public void addQueue(int value) {
        if (isFull()) {
            System.out.println("队列已满，无法加入");
            return;
        }
        rear++;
        arr[rear] = value;
    }

    /**
     * 取队列元素
     * @return
     */
    public int getQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空，无法取元素");
        }
        front++;
        return arr[front];
    }

    /**
     * 获取队首元素
     * @return
     */
    public void headQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空，无法取元素");
        }
        System.out.println(arr[front + 1]);
    }

    /**
     * 打印队列所有元素
     */
    public void showQueue() {
        if (isEmpty()) {
            System.out.println("当前队列为空，没有元素");
            return;
        }
        for (int i = front + 1; i <= rear; i ++) {
            System.out.printf("arr[%d]:%d\n", i, arr[i]);
        }
    }

}
