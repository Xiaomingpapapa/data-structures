package com.study.queue;

import java.util.Scanner;

public class CircleArrayQueueDemo {
    public static void main(String[] args) {
        CircleArrayQueue queue = new CircleArrayQueue(4);
        System.out.println("测试数组实现环形队列队列~~~~~~");
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

class CircleArrayQueue {
    int maxSize;//队列最大容量
    int rear;//指向队列尾部元素后一个位置
    int front;//指向队列头部元素
    int arr[];//存放队列数据的数组

    public CircleArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[maxSize];
        rear = 0;
        front = 0;
    }

    /**
     * 判断队列是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return rear == front;
    }

    /**
     * 判断队列是否已满
     *
     * @return
     */
    public boolean isFull() {
        return (rear + 1) % maxSize == front;
    }

    /**
     * 往队列加入元素
     *
     * @param value
     */
    public void addQueue(int value) {
        if (isFull()) {
            System.out.println("队列已满，无法加入");
            return;
        }
        arr[rear] = value;
        rear = (rear + 1) % maxSize;
    }

    /**
     * 取队列元素
     *
     * @return
     */
    public int getQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空，无法取元素");
        }
        int value = arr[front];
        front = (front + 1) % maxSize;
        return value;
    }

    /**
     * 获取队首元素
     *
     * @return
     */
    public void headQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空，无法取元素");
        }
        System.out.println(arr[front]);
    }

    /**
     * 打印队列所有元素
     */
    public void showQueue() {
        if (isEmpty()) {
            System.out.println("当前队列为空，没有元素");
            return;
        }
        for (int i = front; i < front + size(); i++) {
            System.out.printf("arr[%d]:%d\n", i % maxSize, arr[i % maxSize]);
        }
    }

    /**
     * 获取队列有效元素个数
     *
     * @return
     */
    public int size() {
        return (rear + maxSize - front) % maxSize;
    }


}
