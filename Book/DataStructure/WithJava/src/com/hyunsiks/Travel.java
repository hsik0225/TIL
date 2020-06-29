package com.hyunsiks;

public class Travel {

    private Node start;

    // 여행 생성자
    public Travel() {
        start = null;
    }

    class Node {
        private String name;

        private Node leftNode;

        private Node rightNode;

        public Node(String name, Node leftNode, Node rightNode) {
            this.name = name;
            this.leftNode = leftNode;
            this.rightNode = rightNode;
        }
    }

    // 지도 만들기
    public Node map() {

        // H 기준 왼쪽 섬들
        Node tNode = new Node("T",null, null);
        Node aNode = new Node("A",null, tNode);
        Node eNode = new Node("E",aNode, null);
        Node nNode = new Node("N",null, null);
        Node uNode = new Node("U", nNode, null);
        Node fNode = new Node("F", uNode, eNode);

        // H 기준 오른쪽 섬들
        Node zNode = new Node("Z",null, null);
        Node yNode = new Node("Y",null, tNode);
        Node kNode = new Node("K",null, yNode);
        Node sNode = new Node("S",zNode, kNode);

        return new Node("H", fNode, sNode);
    }

    public static void main(String[] args) {
        Travel travel = new Travel();
        travel.start = travel.map();

        System.out.print("A - 코스: ");
        travel.aCourse(travel.start);

        System.out.print("\nB - 코스: ");
        travel.bCourse(travel.start);

        System.out.print("\nC - 코스: ");
        travel.cCourse(travel.start);
    }

    public void aCourse(Node n) {
        if (n != null) {
            System.out.print(n.name + "-> "); // 섬 방문
            aCourse(n.leftNode);
            aCourse(n.rightNode);
        }
    }

    public void bCourse(Node n) {
        if (n != null) {
            bCourse(n.leftNode);
            System.out.print(n.name + "-> "); // 섬 방문
            bCourse(n.rightNode);
        }
    }

    public void cCourse(Node n) {
        if (n != null) {
            cCourse(n.leftNode);
            cCourse(n.rightNode);
            System.out.print(n.name + "-> "); // 섬 방문
        }
    }
}
