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
        System.out.println(list);
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
}


