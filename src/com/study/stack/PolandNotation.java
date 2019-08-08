package com.study.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PolandNotation {
    public static void main(String[] args) {
        // 中缀转换成逆波兰表达式 (3 + 5) * 2 - 3 => 3 5 + 2 * 3 -
        String polandNoationExpression = "30 5 + 2 * 3 -";
        List<String> charList = getListString(polandNoationExpression);
        cal(charList);
    }

    private static void cal(List<String> charList) {
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
        System.out.printf("运算结果=%d", stack.pop());
    }

    private static List<String> getListString(String polandNoationExpression) {
        List<String> list = new ArrayList<>();
        //根据空格分割字符
        String[] chars = polandNoationExpression.split(" ");
        for (String aChar : chars) {
            list.add(aChar);
        }
        return list;
    }


}


