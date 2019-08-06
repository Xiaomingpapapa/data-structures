package com.study.stack;

/**
 * @author 33053
 * @create 2019-08-06 08:48
 * <>
 */
public class ArrayStackDemo {
    public static void main(String[] args) {

    }
}

class ArrayStack {
    //栈顶指针
    int top = -1;
    int[] stack;
    int maxSize;

    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[maxSize];
    }

    /**
     * 判断栈是否为空
     * @return
     */
    public boolean isEmpty() {
        return top == -1;
    }

    /**
     * 判断栈是否已满
     * @return
     */
    public boolean isFull() {
        return top == maxSize - 1;
    }

    /**
     * 往栈里添加元素
     * @param value
     */
    public void push(int value) {
        if (isFull()) {
            System.out.println("栈已满");
            return;
        }
        top++;
        stack[top] = value;
    }

    /**
     * 从栈里取元素
     * @return
     */
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈为空，不可取元素");
        }
        int val = stack[top];
        top--;
        return val;
    }

    /**
     * 打印栈元素
     */
    public void showStack() {
        if (isEmpty()) {
            System.out.println("栈为空");
            return;
        }
        for (int i = 0; i <= top; i++) {
            System.out.printf("stack[%d]=%d", i, stack[i]);
        }
    }


}