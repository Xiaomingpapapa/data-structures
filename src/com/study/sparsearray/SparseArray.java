package com.study.sparsearray;

import javax.xml.ws.Holder;
import java.io.*;
import java.util.Map;

public class SparseArray {
    public static void main(String[] args) throws Exception {
        //初始化棋盘
        int[][] chessArray = new int[11][11];
        //初始化棋子，0 表示没有棋子，1 表示黑子，2 表示蓝子
        chessArray[1][2] = 1;
        chessArray[2][3] = 2;
        chessArray[4][6] = 1;
        //打印棋子
        for (int[] row : chessArray) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println("\n");
        }

        //统计棋盘上非默认值的个数
        int notDefaultValue = 0;
        for (int i = 0; i < chessArray.length; i++) {
            for (int j = 0; j < chessArray[0].length; j++) {
                if (chessArray[i][j] != 0) {
                    notDefaultValue++;
                }
            }
        }

        //创建稀疏数组
        int[][] sparseArray = new int[notDefaultValue + 1][3];
        //打印初始化的稀疏数组
        for (int[] row : sparseArray) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println("\n");
        }

        //对稀疏数组初始化赋值
        sparseArray[0][0] = chessArray.length;
        sparseArray[0][1] = chessArray[0].length;
        sparseArray[0][2] = notDefaultValue;
        //对稀疏数组进行后续赋值
        int valueIndex = 1;//非默认值从数组第二行开始赋值
        for (int i = 0; i < chessArray.length; i++) {
            for (int j = 0; j < chessArray[0].length; j++) {
                if (chessArray[i][j] != 0) {
                    sparseArray[valueIndex][0] = i + 1;
                    sparseArray[valueIndex][1] = j + 1;
                    sparseArray[valueIndex][2] = chessArray[i][j];
                    valueIndex++;
                }
            }
        }

        //打印赋值过后的稀疏数组
        for (int[] row : sparseArray) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println("\n");
        }

        //将稀疏数组转换到二维数组
        int chessArrayRowValue = sparseArray[0][0];
        int chessArrayColValue = sparseArray[0][1];
        int[][] chessArray2 = new int[chessArrayRowValue][chessArrayColValue];
        //从第二行开始遍历
        for (int i = 1; i < sparseArray.length; i++) {
            for (int j = 0; j < sparseArray[0].length; j++) {
                int tempRowValue = sparseArray[i][0];
                int tempColValue = sparseArray[i][1];
                int tempValue = sparseArray[i][2];
                chessArray2[tempRowValue - 1][tempColValue - 1] = tempValue;
            }
        }

        //打印转换得到的二维数组
        for (int[] row : chessArray2) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println("\n");
        }

        //将稀疏数组存盘
        //创建文件
        File dataFile = new File("map.data");
        if (!dataFile.exists()) {
            dataFile.createNewFile();
        }
        //创建文件的写入流
        FileWriter fileWriter = null;
        fileWriter = new FileWriter(dataFile);

        //读取稀疏数组写入文件
        for (int i = 0; i < sparseArray.length; i++) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int j = 0; j < sparseArray[0].length; j++) {
                if (j != sparseArray[0].length - 1) {
                    stringBuilder.append(sparseArray[i][j] + ":");
                } else {
                    stringBuilder.append(sparseArray[i][j]);
                }
            }
            stringBuilder.append("\n");
            fileWriter.append(stringBuilder.toString());
            fileWriter.flush();
        }

        //读取文件中的稀疏数组
        FileReader fileReader = new FileReader(new File("map.data"));
        char[] arrayChars = new char[1024];
        fileReader.read(arrayChars);
        String arrayString = String.valueOf(arrayChars).trim();
        String[] splitString = arrayString.split("\n");

        //将文本还原成稀疏数组
        int[][] sparseArray2 = new int[splitString.length][3];
        int count = 0;
        for (int i = 0; i < sparseArray2.length; i++) {
            String[] arrayValueChar = splitString[count].split(":");
            sparseArray2[i][0] = Integer.parseInt(arrayValueChar[0]);
            sparseArray2[i][1] = Integer.parseInt(arrayValueChar[1]);
            sparseArray2[i][2] = Integer.parseInt(arrayValueChar[2]);
            count++;
        }

        System.out.println("读取文件还原后的稀疏数组");
        for (int[] row : sparseArray2) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println("\n");
        }
    }
}

