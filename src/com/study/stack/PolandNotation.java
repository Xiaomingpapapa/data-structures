package com.study.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PolandNotation {
    public static void main(String[] args) {
        // 中缀转换成逆波兰表达式 (3 + 5) * 2 - 3 => 3 5 + 2 * 3 -
//        String polandNoationExpression = "30 5 + 2 * 3 -";
//        List<String> charList = getListString(polandNoationExpression);
//        cal(charList);
        // 中缀表达式字符 list 获取 (3+5)*2-3
        String infixExpression = "(3+5)*2-3";
        List list = getInfixStringList(infixExpression);
        List list2 = parseSuffixExpressionList(list);
        System.out.printf("表达式" + infixExpression + "的运算结果=%d", cal(list2));
    }


    private static int cal(List<String> charList) {
        //初始化栈存放操作数
        Stack<Integer> stack = new Stack();
        int result = 0;
        for (String s : charList) {
            //判断字符是否为数字
            if (s.matches("\\d+")) {
                //是数字直接入栈
                stack.push(Integer.parseInt(s));
            } else {
                int num1 = stack.pop();
                int num2 = stack.pop();
                if (s.equals("+")) {
                    result = num1 + num2;
                } else if (s.equals("-")) {
                    result = num2 - num1;
                } else if (s.equals("*")) {
                    result = num1 * num2;
                } else if (s.equals("/")) {
                    result = num2 / num1;
                }
                //将每次的运算结果再次入栈
                stack.push(result);
            }
        }
        return stack.pop();
    }

    public static List<String> parseSuffixExpressionList(List<String> ls) {
        //栈1 放运算符号
        Stack<String> stack1 = new Stack<>();
        //栈2 存放转换过程中表达式元素，在这边为了方便（如果使用 stack 后续还得逆序输出），直接使用 list 存放
        List<String> stack2 = new ArrayList<>();
        for (String l : ls) {
            if (l.matches("\\d+")) {
                //如果是数字直接入栈2
                stack2.add(l);
            } else if (l.equals("(")) {
                //左括号直接入栈1
                stack1.push(l);
            } else if (l.equals(")")) {
                //右括号则暂停入栈操作，检查 stack1 元素，直到匹配到一个 "(" 则停止检查
                while (!stack1.peek().equals("(")) {
                    //不断遍历 stack1 元素，直到检查到第一个 "("，期间将遍历得到的运算符压入栈2
                    stack2.add(stack1.pop());
                }
                //抛弃这对 "(" 括号，")" 不进行入栈操作
                stack1.pop();
            } else {
                while (stack1.size() > 0 && getOperationPriority(l) <= getOperationPriority(stack1.peek())) {
                    //如果当前字符为运算符号的情况，当 stack1 栈顶运算符进行优先级比较
                    //如果优先级比栈顶元素低，将栈顶元素弹出压入 stack2
                    stack2.add(stack1.pop());
                }
                stack1.push(l);
            }
        }

        //最后将 stack1 中剩余的元素都压入 stack2
        while (stack1.size() > 0) {
            stack2.add(stack1.pop());
        }
        return stack2;
    }

    public static List<String> getInfixStringList(String expression) {
        List<String> list = new ArrayList<>();
        char c = ' ';
        int index = 0;
        //拼接多位数
        String num = "";
        do {
            if ((c = expression.charAt(index)) < 48 || (c = expression.charAt(index)) > 57) {
                //说明 c 不是数字
                list.add(String.valueOf(c));
                index++;
            } else {
                num = "";
                //拼接多位数
                while (index < expression.length() && ((c = expression.charAt(index)) >= 48 && (c = expression.charAt(index)) <= 57)) {
                    num += String.valueOf(expression.charAt(index));
                    index++;
                }
                list.add(num);
            }

        } while (index < expression.length());
        return list;
    }

    public static List<String> getListString(String polandNoationExpression) {
        List<String> list = new ArrayList<>();
        //根据空格分割字符
        String[] chars = polandNoationExpression.split(" ");
        for (String aChar : chars) {
            list.add(aChar);
        }
        return list;
    }

    /**
     * 获取运算符优先级
     *
     * @return
     */
    public static int getOperationPriority(String operation) {
        switch (operation) {
            case "+":
                return 1;
            case "-":
                return 1;
            case "*":
                return 2;
            case "/":
                return 2;
            default:
                System.out.println("请输入有效运算符");
                return -1;
        }
    }
}


