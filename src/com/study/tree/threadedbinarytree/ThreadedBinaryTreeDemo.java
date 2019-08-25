package com.study.tree.threadedbinarytree;

/**
 * 线索化二叉树
 */
public class ThreadedBinaryTreeDemo {
    public static void main(String[] args) {
        HeroNode root = new HeroNode(1, "xxx");
        HeroNode heroNode1 = new HeroNode(3, "zzz");
        HeroNode heroNode2 = new HeroNode(6, "ccc");
        HeroNode heroNode3 = new HeroNode(8, "vvv");
        HeroNode heroNode4 = new HeroNode(10, "bbb");
        HeroNode heroNode5 = new HeroNode(14, "nnn");

        root.setLeft(heroNode1);
        root.setRight(heroNode2);
        heroNode1.setLeft(heroNode3);
        heroNode1.setRight(heroNode4);
        heroNode2.setLeft(heroNode5);

        ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree(root);
//        threadedBinaryTree.threadedBinaryTree();

//        System.out.println(heroNode4.getLeft());
//        System.out.println(heroNode4.getRight());

//        threadedBinaryTree.threadedList();
        threadedBinaryTree.infixOrder(root);

    }
}

class ThreadedBinaryTree {
    private HeroNode root;


    public ThreadedBinaryTree(HeroNode root) {
        this.root = root;
    }


    public void threadedBinaryTree() {
        this.threadedBinaryTree(root);
    }

    public void threadedList() {
        HeroNode temp = root;

        // 从根节点开始遍历
        while (temp != null) {
            // 优先找到被线索化处理的第一个节点
            while (temp.getLeftType() == 0) {
                temp = temp.getLeft();
            }
            System.out.println(temp);

            // 检查当前节点是否存在后继节点，有则直接输出后继节点
            while (temp.getRightType() == 1) {
                temp = temp.getRight();
                System.out.println(temp);
            }


            temp = temp.getRight();
        }
    }

    // 存储当前节点的前驱节点，初始化为 null
    private HeroNode pre = null;

    /**
     * 对二叉树进行中序线索化
     * @param heroNode 需要线索化的节点
     */
    public void threadedBinaryTree(HeroNode heroNode) {
        if (heroNode == null) {
            return;
        }

        // 先线索化左子树
        threadedBinaryTree(heroNode.getLeft());

        // 线索化当前节点
        if (heroNode.getLeft() == null) {
            heroNode.setLeft(pre);
            // 1 代表前继/后继节点
            heroNode.setLeftType(1);
        }

        // 以当前节点左右前继节点的后继节点，只有遍历到下一个节点之后才知道当前节点是谁的前驱节点
        if (pre != null && pre.getRight() == null) {
            pre.setRight(heroNode);
            pre.setRightType(1);
        }

        // 处理完当前节点之后让当前节点作为下一个节点的前驱节点
        pre = heroNode;

        // 线索化右子树
        threadedBinaryTree(heroNode.getRight());
    }

    /**
     * 中序遍历二叉树
     */
    public void infixOrder(HeroNode heroNode) {
        if (heroNode == null) {
            return;
        }

        infixOrder(heroNode.getLeft());
        System.out.println(heroNode);
        infixOrder(heroNode.getRight());
    }
}

class HeroNode {
    private int id;
    private String name;
    private HeroNode left;
    private HeroNode right;

    // 标识节点左右指针指向的节点类型：左/右子树 or 前驱/后继节点
    private int leftType;
    private int rightType;

    public HeroNode(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
