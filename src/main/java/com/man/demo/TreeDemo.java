package com.man.demo;


import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class TreeDemo {
    public static void main(String[] args) {
        Node a = new Node("a");
        Node b = new Node("b");
        Node c = new Node("c");
        Node d = new Node("d");
        Node e = new Node("e");
        Node f = new Node("f");
        Node g = new Node("g");
        a.setlNode(b);
        a.setrNode(c);
        b.setlNode(d);
        b.setrNode(f);
        d.setrNode(e);
        f.setlNode(g);

        recursiveAhead(a);

        System.out.println();

        recursiveLeft(a);

        System.out.println();
        recursiveRight(a);

        System.out.println();
        System.out.println("#####################");

        nonRecursiveAhead2(a);
        System.out.println();

        nonRecursiveMiddle2(a);
        System.out.println();

        nonRecursiveEnd2(a);
        System.out.println();
    }

    private static void nonRecursiveAhead(Node n) {
        Stack<Node> s = new Stack<>();
        Node tmp = n;
        while (tmp != null) {
            System.out.print(tmp.getValue());
            Node left = tmp.getlNode();
            Node right = tmp.getrNode();
            if (left != null) {
                s.push(tmp);
                tmp = left;
                continue;
            }

            if (right != null) {
                tmp = right;
                continue;
            }
            Node middleNode = null;
            while(!s.empty()) {
                middleNode = s.pop();
                if (middleNode.getrNode() != null) {
                    break;
                }
            }
            if (middleNode == null && s.empty()) {
                break;
            }

            tmp = middleNode.getrNode();
            continue;
        }
    }

    private static void nonRecursiveMiddle(Node n) {
        Stack<Node> s = new Stack<>();
        Node tmp = n;
        while (tmp != null) {
            Node left = tmp.getlNode();
            Node right = tmp.getrNode();
            if (left != null) {
                s.push(tmp);
                tmp = left;
                continue;
            }

            System.out.print(tmp.getValue());

            if (right != null) {
                tmp = right;
                continue;
            }
            Node middleNode = null;
            while(!s.empty()) {
                middleNode = s.pop();
                System.out.print(middleNode.getValue());
                if (middleNode.getrNode() != null) {
                    break;
                }
            }
            if (middleNode == null && s.empty()) {
                break;
            }

            tmp = middleNode.getrNode();
            continue;
        }
    }

    private static void nonRecursiveEnd(Node n) {
        Stack<Node> s = new Stack<>();
        Node tmp = n;
        while (tmp != null) {
            Node left = tmp.getlNode();
            Node right = tmp.getrNode();
            if (left != null) {
                s.push(tmp);
                tmp = left;
                continue;
            }

            if (right != null) {
                s.push(tmp);
                tmp = right;
                continue;
            }

            System.out.print(tmp.getValue());

            Node middleNode = null;
            while(!s.empty()) {
                middleNode = s.peek();
                if (middleNode.getrNode() != null && middleNode.getrNode() != tmp) {
                    break;
                }

                System.out.print(middleNode.getValue());
                tmp = middleNode;
                s.pop();
            }
            if (s.empty()) {
                break;
            }

            tmp = middleNode.getrNode();
            continue;
        }
    }

    private static void nonRecursiveAhead2(Node n) {
        Node tmp;
        Stack<Node> s = new Stack<>();
        s.push(n);
        while (!s.empty()) {
            tmp = s.pop();
            if (tmp == null) {
                continue;
            }
            System.out.print(tmp.getValue());
            s.push(tmp.getrNode());
            s.push(tmp.getlNode());
        }
    }

    private static void nonRecursiveMiddle2(Node n) {
        Node tmp = n;
        Stack<Node> s = new Stack<>();

        while (tmp != null || !s.empty()) {
            while (tmp != null) {
                s.push(tmp);
                tmp = tmp.getlNode();
            }

            tmp = s.pop();
            System.out.print(tmp.getValue());
            tmp = tmp.getrNode();
        }
    }

    private static void nonRecursiveEnd2(Node n) {
        Node tmp = n;
        Stack<Node> s = new Stack<>();

        Set<Node> usedSet = new HashSet<>();

        while (tmp != null || !s.isEmpty()) {
            while (tmp != null) {
                s.push(tmp);
                tmp = tmp.getlNode();
            }

            tmp = s.pop();

            if (tmp.getrNode() == null) {
                System.out.print(tmp.getValue());
                usedSet.add(tmp);

                tmp = tmp.getrNode();
            }

            if (tmp.getrNode() != null && !usedSet.contains(tmp.getrNode())) {
                s.push(tmp);
            } else if (tmp.getrNode() != null){
                System.out.print(tmp.getValue());
                usedSet.add(tmp);
            }

            tmp = s.pop();
        }
    }

    private static void recursiveAhead(Node n) {
        if (n != null) {
            System.out.print(n.getValue());
        }
        if (n.getlNode() != null) {
            recursiveAhead(n.getlNode());
        }
        if (n.getrNode() != null) {
            recursiveAhead(n.getrNode());
        }
    }

    private static void recursiveLeft(Node n) {
        if (n.getlNode() != null) {
            recursiveLeft(n.getlNode());
        }

        if (n != null) {
            System.out.print(n.getValue());
        }

        if (n.getrNode() != null) {
            recursiveLeft(n.getrNode());
        }
    }

    private static void recursiveRight(Node n) {
        if (n.getlNode() != null) {
            recursiveRight(n.getlNode());
        }

        if (n.getrNode() != null) {
            recursiveRight(n.getrNode());
        }

        if (n != null) {
            System.out.print(n.getValue());
        }
    }
}

class Node {
    private Node lNode = null;
    private Node rNode = null;

    private String value;

    public Node() {

    }

    public Node(String value) {
        this.value = value;
    }

    public Node getlNode() {
        return lNode;
    }

    public void setlNode(Node lNode) {
        this.lNode = lNode;
    }

    public Node getrNode() {
        return rNode;
    }

    public void setrNode(Node rNode) {
        this.rNode = rNode;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}