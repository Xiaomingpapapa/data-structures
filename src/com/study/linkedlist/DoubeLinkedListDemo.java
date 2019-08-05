package com.study.linkedlist;

/**
 * @author 33053
 * @create 2019-08-05 08:50
 * <>
 */
public class DoubeLinkedListDemo {
    public static void main(String[] args) {
        DoubeLinkedList doubeLinkedList = new DoubeLinkedList();
        doubeLinkedList.add(new HeroNode2(3, "吴用", "智多星"));
        doubeLinkedList.add(new HeroNode2(1, "宋江", "及时雨"));
        doubeLinkedList.add(new HeroNode2(2, "卢俊义", "玉麒麟"));
        doubeLinkedList.update(new HeroNode2(2, "卢俊义~~~", "玉麒麟~~~"));
        doubeLinkedList.delete(2);
        doubeLinkedList.list();
    }
}

class DoubeLinkedList {
    //初始化头结点，不存放数据
    HeroNode2 head = new HeroNode2(0, "", "");    /**
     * 从链表尾部插入，暂时不考虑顺序
     *
     * @param heroNode
     */
    public void add(HeroNode2 heroNode) {
        //初始化临时变量指向链表头部
        HeroNode2 temp = head;
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
        heroNode.pre = temp;
    }

    /**
     * 更新链表元素
     *
     * @param newHeroNode
     */
    public void update(HeroNode2 newHeroNode) {
        HeroNode2 temp = head.next;
        boolean isExists = false;
        if (temp == null) {
            System.out.println("链表为空");
            return;
        }
        while (true) {
            if (temp == null) {
                break;
            }
            if (temp.no == newHeroNode.no) {
                //找到待更新的元素
                isExists = true;
                break;
            }
            temp = temp.next;
        }
        if (isExists) {
            //更新元素信息
            temp.name = newHeroNode.name;
            temp.nickName = newHeroNode.nickName;
        } else {
            System.out.println("未找到待更新的元素");
        }
    }

    /**
     * 删除链表元素
     *
     * @param no
     */
    public void delete(int no) {
        //找到待删除元素的前一个元素
        HeroNode2 temp = head.next;
        boolean isExists = false;
        if (temp == null) {
            System.out.println("链表为空");
            return;
        }
        while (true) {
            if (temp == null) {
                break;
            }
            if (temp.no == no) {
                isExists = true;
                break;
            }
            temp = temp.next;
        }
        if (isExists) {
            temp.pre.next = temp.next;
            if (temp.next != null) {
                temp.next.pre = temp.pre;
            }
        } else {
            System.out.println("待删除元素不存在");
        }
    }

    /**
     * 遍历链表
     */
    public void list() {
        HeroNode2 temp = head.next;
        if (temp == null) {
            System.out.println("链表为空");
            return;
        }
        while (true) {
            if (temp == null) {
                break;
            }
            System.out.println(temp);
            temp = temp.next;
        }
    }
}

class HeroNode2 {
    int no;
    String name;
    String nickName;
    HeroNode2 next;
    HeroNode2 pre;


    public HeroNode2(int no, String name, String nickName) {
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
