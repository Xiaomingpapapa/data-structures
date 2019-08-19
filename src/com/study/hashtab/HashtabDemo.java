package com.study.hashtab;

public class HashtabDemo {
    public static void main(String[] args) {
        Employ employ = new Employ(1, "xxx");
        Employ employ1 = new Employ(2, "ccc");
        Employ employ2 = new Employ(3, "vvv");
        Employ employ3 = new Employ(4, "bbb");

        HashTab hashTab = new HashTab(7);
        hashTab.add(employ);
        hashTab.add(employ1);
        hashTab.add(employ2);
        hashTab.add(employ3);

        hashTab.list();

        Employ employ4 = hashTab.get(3);
        System.out.println(employ4);

        hashTab.remove(3);

        hashTab.list();

    }
}

class HashTab {
    EmployLinkedList[] employLinkedLists;
    int size;

    public HashTab(int size) {
        this.size = size;
        employLinkedLists = new EmployLinkedList[size];
        init();
    }

    /**
     * 初始化链表数组
     */
    public void init() {
        for (int i = 0; i < employLinkedLists.length; i++) {
            employLinkedLists[i] = new EmployLinkedList();
        }
    }

    public void add(Employ employ) {
        int linkedListNo = hashFun(employ.getId());
        employLinkedLists[linkedListNo].add(employ);
    }

    public void list() {
        for (EmployLinkedList employLinkedList : employLinkedLists) {
            if (!employLinkedList.isEmpty()) {
                employLinkedList.list();
            }
        }
    }

    public Employ get(int id) {
        int linkedListNo = hashFun(id);
        return employLinkedLists[linkedListNo].findEmployById(id);
    }

    public void remove(int id) {
        int linkedListNo = hashFun(id);
        employLinkedLists[linkedListNo].deleteEmployById(id);
    }


    /**
     * 散列函数
     *
     * @param id
     * @return
     */
    public int hashFun(int id) {
        return id % size;
    }
}

class EmployLinkedList {
    private Employ head;

    public void add(Employ employ) {
        if (head == null) {
            head = employ;
            return;
        }

        Employ temp = head;
        while (true) {
            if (temp.next == null) {
                break;
            }
            temp = temp.next;
        }
        temp.next = employ;
    }

    public void list() {
        if (head == null) {
            System.out.println("当前链表为空");
            return;
        }

        Employ temp = head;
        while (true) {
            System.out.println(temp);
            if (temp.next == null) {
                break;
            }
            temp = temp.next;
        }
    }


    public boolean isEmpty() {
        return head == null;
    }

    public Employ findEmployById(int id) {
        if (head == null) {
            System.out.println("当前链表为空");
            return null;
        }

        boolean isExists = false;
        Employ temp = head;
        while (true) {
            if (temp.getId() == id) {
                isExists = true;
                break;
            }
            if (temp.next == null) {
                break;
            }
            temp = temp.next;
        }
        if (isExists) {
            return temp;
        }
        System.out.printf("id为%d的元素不存在", id);
        return null;
    }

    public void deleteEmployById(int id) {
        if (head == null) {
            System.out.println("当前链表为空");
            return;
        }
        boolean isExists = false;
        Employ temp = head;
        while (true) {
            if (temp.next == null && temp.getId() == id) {
                //要删除的为头结点
                head = null;
                break;
            } else if (temp.next == null) {
                System.out.println("待删除元素不存在");
                break;
            } else if (temp.next.getId() == id) {
                isExists = true;
                break;
            }
            temp = temp.next;
        }
        if (isExists) {
            temp.next = null;
        }
    }
}

class Employ {
    private int id;
    private String name;
    public Employ next;

    public Employ(int id, String name) {
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

    @Override
    public String toString() {
        return "Employ{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
