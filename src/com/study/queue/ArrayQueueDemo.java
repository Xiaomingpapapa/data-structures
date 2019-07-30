package com.study.queue;

import java.lang.reflect.Array;

public class ArrayQueueDemo {
    public static void main(String[] args) {
        ArrayQueue arrayQueue = new ArrayQueue(3);
        arrayQueue.addQueue(1);
        arrayQueue.addQueue(2);
        arrayQueue.addQueue(3);
        System.out.println("队列头部：" + arrayQueue.getQueue());
        System.out.println(arrayQueue.headQueue());
        arrayQueue.showQueue();
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
    public int headQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空，无法取元素");
        }
        return arr[front + 1];
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
            System.out.printf("队列元素%d:%d\n", i, arr[i]);
        }
    }

}
