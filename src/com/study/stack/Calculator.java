package com.study.stack;

public class Calculator {
    public static void main(String[] args) {
        String operString = "30+20*6-2";
        ClaculatorArryaStack numStack = new ClaculatorArryaStack(10);
        ClaculatorArryaStack operStack = new ClaculatorArryaStack(10);
        //遍历字符串的索引
        int index = 0;
        int num1 = 0;
        int num2 = 0;
        //获取到的运算符
        int oper = ' ';
        int result = 0;
        char ch = ' ';
        String numStr = "";
        while (true) {
            ch = operString.substring(index, index + 1).charAt(0);
            //判断遍历到的是否为运算符
            if (operStack.isOper(ch)) {
                //判断运算符栈是否为空
                if (operStack.isEmpty()) {
                    operStack.push(ch);
                } else {
                    //判断当前即将入栈的运算符优先级是否小于等于栈顶元素
                    if (operStack.priority(ch) <= operStack.priority(operStack.peek())) {
                        //从数栈中 pop 两个元素，符号栈中 pop 一个运算符
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        result = operStack.cal(num1, num2, oper);
                        //将运算结果入数栈
                        numStack.push(result);
                        //操作符入栈
                        operStack.push(ch);
                    } else {
                        //直接入栈
                        operStack.push(ch);
                    }
                }
            } else {
                //数字直接入栈，注意这里有个小细节，char 转换成 int 是隐式转换，对应的是该 char 的 asscii 码值，而数字字符对应的 asscii 值比该数字大 48
//                numStack.push(ch - 48);
                //针对多位数进行处理
                if (index + 1 == operString.length()) {
                    //代表已经扫描到表达式最后
                    numStack.push(ch - 48);
                } else {
                    //判断下一个字符是数字还是运算符
                    if (operStack.isOper(operString.substring(index + 1, index + 2).charAt(0))) {
                        numStack.push(ch - 48);
                    } else {
                        numStr = ch + operString.substring(index + 1, index + 2);
                        numStack.push(Integer.parseInt(numStr));
                        index++;
                    }
                }
            }
            index++;
            //判断是否扫描完毕
            if (index >= operString.length()) {
                break;
            }
        }

        /**
         * 开始运算
         */
        while (true) {
            //符号栈为空，运算完毕
            if (operStack.isEmpty()) {
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            result = operStack.cal(num1, num2, oper);
            numStack.push(result);
        }

        System.out.printf(operString + "=%d", numStack.pop());

    }
}

class ClaculatorArryaStack {
    //栈顶指针
    int top = -1;
    int[] stack;
    int maxSize;

    public ClaculatorArryaStack(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[maxSize];
    }

    /**
     * 判断栈是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return top == -1;
    }

    /**
     * 判断栈是否已满
     *
     * @return
     */
    public boolean isFull() {
        return top == maxSize - 1;
    }

    /**
     * 往栈里添加元素
     *
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
     *
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
     * 返回栈顶元素的值
     * @return
     */
    public int peek() {
        return stack[top];
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
            System.out.printf("stack[%d]=%d\n", i, stack[i]);
        }
    }

    /**
     * 判断是否为操作运算符
     * @param ch
     * @return
     */
    public boolean isOper(char ch) {
        return ch == '+' || ch == '-' || ch == '*' || ch == '/';
    }

    /**
     * 返回运算符的优先级
     * @param ch
     * @return
     */
    public int priority(int ch) {
        if (ch == '*' || ch == '/') {
            return 1;
        }
        if (ch == '+' || ch == '-') {
            return 0;
        }
        return -1;
    }

    /**
     * 运算方法
     * @param num1
     * @param num2
     * @param oper
     * @return
     */
    public int cal(int num1, int num2, int oper) {
        int result = 0;
        switch (oper) {
            case '*':
                result = num1 * num2;
                break;
            case '/':
                result = num2 / num1;
                break;
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num2 - num1;
                break;
        }
        return result;
    }
}
