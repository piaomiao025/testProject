package com.man.demo.algorithm;

/**
 * @description:
 * @author: manxiaoqing
 * @create: 2018-11-27 10:12
 **/
public class LinkedListMergeSort {
    public static void main(String[] args) {
        Node head = new Node(10);
        Node a1 = new Node(3);
        Node a2 = new Node(1);
        Node a3 = new Node(9);
        Node a4 = new Node(4);
        Node a5 = new Node(7);
        Node a6 = new Node(3);
        head.setNext(a1);
        a1.setNext(a2);
        a2.setNext(a3);
        a3.setNext(a4);
        a4.setNext(a5);
        a5.setNext(a6);


        Node tmp = head;
        while(tmp != null) {
            System.out.print(tmp.getValue() + ", ");
            tmp = tmp.getNext();
        }

        System.out.println();

        Node sorted = mergeSort(head);

        tmp = sorted;
        while(tmp != null) {
            System.out.print(tmp.getValue() + ", ");
            tmp = tmp.getNext();
        }
    }

    private static Node mergeSort(Node n) {
        if (n == null || n.getNext() == null) {
            return n;
        }
        int count = 1;
        Node tmp = n;
        while (tmp.getNext() != null) {
            count++;
            tmp = tmp.getNext();
        }
        if (count == 2) {
            if (n.getValue() > n.getNext().getValue()) {
                tmp = n.getNext();
                tmp.setNext(n);
                n.setNext(null);
                return tmp;
            }
            return n;
        }

        Node[] nodes = splitList(n);

        Node a = mergeSort(nodes[0]);
        Node b = mergeSort(nodes[1]);

        return mergeSorted(a, b);
    }

    private static Node mergeSorted(Node a, Node b) {
        for (Node i = a ; i != null ;) {
            Node tmp = i.getNext();
            for (Node j = b ; j != null ; j = j.getNext()) {
                if (i.getValue() > j.getValue() && j.getNext() == null) {
                    j.setNext(i);
                    i.setNext(null);
                    break;
                } else if (i.getValue() > j.getValue() && j.getNext() != null && i.getValue() < j.getNext().getValue()) {
                    Node tmp2 = j.getNext();
                    j.setNext(i);
                    i.setNext(tmp2);
                    break;
                } else if (i.getValue() == j.getValue()) {
                    Node tmp2 = j.getNext();
                    j.setNext(i);
                    i.setNext(tmp2);
                    break;
                } else if (i.getValue() < j.getValue()){
                    i.setNext(j);
                    b = i;
                    break;
                }
            }
            i = tmp;
        }

        return b;
    }

    private static Node[] splitList(Node n) {
        Node p1 = n, p2 = n.getNext().getNext();
        while (p1 != null && p2 != null) {
            if (p2.getNext() == null) {
                break;
            }
            p1 = p1.getNext();
            p2 = p2.getNext().getNext();
        }
        Node tmp = p1.getNext();
        p1.setNext(null);
        Node[] nodes = new Node[2];
        nodes[0] = n;
        nodes[1] = tmp;
        return nodes;
    }
}

class Node {
    private Node next = null;
    private int value;

    public Node(int value) {
        this.value = value;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
