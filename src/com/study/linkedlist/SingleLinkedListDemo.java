package com.study.linkedlist;

import java.text.BreakIterator;
import java.time.temporal.Temporal;
import java.util.Stack;

public class SingleLinkedListDemo {
    public static void main(String[] args) {
        SingleLinkedList singleLinkedList1 = new SingleLinkedList();
        singleLinkedList1.addByOrder(new HeroNode(3, "吴用", "智多星"));
        singleLinkedList1.addByOrder(new HeroNode(1, "宋江", "及时雨"));
        singleLinkedList1.addByOrder(new HeroNode(2, "卢俊义", "玉麒麟"));
        singleLinkedList1.addByOrder(new HeroNode(4, "林冲", "豹子头"));

        SingleLinkedList singleLinkedList2 = new SingleLinkedList();
        singleLinkedList2.addByOrder(new HeroNode(5, "鲁智深", "花和尚"));
        singleLinkedList2.addByOrder(new HeroNode(6, "李逵", "黑旋风"));

        //合并两个有序链表
        HeroNode heroNode = mergeLinkedList(singleLinkedList1.head, singleLinkedList2.head);
        SingleLinkedList mergedSingleLinkedList = new SingleLinkedList();
        mergedSingleLinkedList.head = heroNode;
        mergedSingleLinkedList.list();

    }


    /**
     * 获取链表有效元素个数
     *
     * @param head
     * @return
     */
    public static int getLength(HeroNode head) {
        if (head.next == null) {
            return 0;
        }
        HeroNode temp = head.next;
        int size = 0;
        while (true) {
            if (temp == null) {
                break;
            }
            size++;
            temp = temp.next;
        }
        return size;
    }

    /**
     * 获取链表倒数第 index 个元素
     *
     * @param head
     * @param index
     * @return
     */
    public static HeroNode getLastIndexNode(HeroNode head, int index) {
        if (head.next == null) {
            System.out.println("链表为空");
            return null;
        }
        //输入的 index 不符合规则
        if (index <= 0 || index > getLength(head)) {
            return null;
        }

        HeroNode curentNode = head.next;
        int size = getLength(head);
        //往前遍历 size - index 次
        for (int i = 0; i < size - index; i++) {
            curentNode = curentNode.next;
        }
        return curentNode;
    }

    /**
     * 逆序打印链表节点
     *
     * @param head
     */
    public static void reversePrint(HeroNode head) {
        if (head.next == null) {
            return;
        }
        //借助 stack 数据结构
        Stack<HeroNode> heroNodeStack = new Stack<>();
        HeroNode temp = head.next;
        while (temp != null) {
            heroNodeStack.push(temp);
            temp = temp.next;
        }

        //对 stack 进行出栈操作打印链表元素
        while (heroNodeStack.size() != 0) {
            System.out.println(heroNodeStack.pop());
        }

    }

    /**
     * 链表反转
     *
     * @param head
     */
    public static void reverseLinkedList(HeroNode head) {
        //当链表有效元素个数为 0 或者 1 时不进行反转，直接返回
        if (head.next == null || head.next.next == null) {
            return;
        }
        //创建一个临时的 head 节点
        HeroNode reverseNode = new HeroNode(0, "", "");
        HeroNode temp = head;
        while (temp.next != null) {
            //设置临时变量保存遍历到的有效节点
            HeroNode currentHeroNode = temp.next;
            //将原链表中遍历到的有效节点摘下
            temp.next = temp.next.next;
            //将获取到的有效节点插入在 reverseNode 节点的后面
            currentHeroNode.next = reverseNode.next;
            reverseNode.next = currentHeroNode;
        }
        //用原来的链表的 head 节点替换 reverse 节点，完成链表反转
        head.next = reverseNode.next;
    }

    /**
     * 合并两个有序链表链表，合并之后链表依然有序
     *
     * @param head1
     * @param head2
     */
    public static HeroNode mergeLinkedList(HeroNode head1, HeroNode head2) {
        if (head1 == null && head2 == null) {
            return null;
        }
        if (head1 == null) {
            return head2;
        }
        if (head2 == null) {
            return head1;

        }
        //两个链表都不为空的情况
        //初始化新链表头结点
        HeroNode newHeroNode = null;
        //去除一个头结点，只留下一个头结点
        if (head1.no == 0) {
            head1 = head1.next;
        }
        if (head1.no > head2.no) {
            // newHeroNode 始终指向小的节点
            newHeroNode = head2;
            //接着递归比较另一个还没放进链表的元素 head1
            newHeroNode.next = mergeLinkedList(head1, head2.next);
        } else {
            newHeroNode = head1;
            newHeroNode.next = mergeLinkedList(head1.next, head2);

        }
        return newHeroNode;
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
     * 更新链表元素
     *
     * @param newHeroNode
     */
    public void update(HeroNode newHeroNode) {
        HeroNode temp = head.next;
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
        HeroNode temp = head;
        boolean isExists = false;
        if (temp.next == null) {
            System.out.println("链表为空");
            return;
        }
        while (true) {
            if (temp.next == null) {
                break;
            }
            if (temp.next.no == no) {
                isExists = true;
                break;
            }
            temp = temp.next;
        }
        if (isExists) {
            temp.next = temp.next.next;
        } else {
            System.out.println("待删除元素不存在");
        }
    }


    /**
     * 遍历链表
     */
    public void list() {
        HeroNode temp = head.next;
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
