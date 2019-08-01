package com.study.linkedlist;

public class SingleLinkedListDemo {
    public static void main(String[] args) {
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        singleLinkedList.addByOrder(new HeroNode(3, "吴用", "智多星"));
        singleLinkedList.addByOrder(new HeroNode(1, "宋江", "及时雨"));
        singleLinkedList.addByOrder(new HeroNode(2, "卢俊义", "玉麒麟"));
        singleLinkedList.addByOrder(new HeroNode(2, "卢俊义", "玉麒麟"));
        singleLinkedList.list();
    }
}

class SingleLinkedList {
    //初始化头结点，不存放数据
    HeroNode head = new HeroNode(0, "", "");

    /**
     * 从链表尾部插入，暂时不考虑顺序
     *
     * @param heroNode
     */
    public void add(HeroNode heroNode) {
        //初始化临时变量指向链表头部
        HeroNode temp = head;
        while (true) {
            //遍历找到链表尾部元素
            if (temp.next == null) {
                break;
            }
            //指针变量后移
            temp = temp.next;
        }
        //将元素加入链表尾部
        temp.next = heroNode;
    }

    /**
     * 按照节点的编号进行顺序添加
     *
     * @param heroNode
     */
    public void addByOrder(HeroNode heroNode) {
        //头结点不能动，通过临时变量来遍历链表
        HeroNode temp = head;
        //判断待加入的节点是否已经存在于链表中，默认不存在
        boolean isExists = false;
        while (true) {
            if (temp.next == null) {
                break;
            }
            if (temp.next.no > heroNode.no) {
                break;//找到合适插入的位置
            }
            if (temp.next.no == heroNode.no) {
                isExists = true;//该编号的元素已经存在
                break;
            }
            temp = temp.next;
        }
        if (isExists) {
            System.out.println("该元素" + heroNode + "已经存在，不能插入");
            return;
        }
        //让当前待插入的元素指向待插入位置的下一个元素
        heroNode.next = temp.next;
        temp.next = heroNode;
    }

    /**
     * 遍历链表
     */
    public void list() {
        HeroNode temp = head.next;
        while (true) {
            if (temp == null) {
                break;
            }
            System.out.println(temp);
            temp = temp.next;
        }
    }

}

class HeroNode {
    int no;
    String name;
    String nickName;
    HeroNode next;


    public HeroNode(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
