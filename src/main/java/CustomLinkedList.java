package main.java;

public class CustomLinkedList {
    private CustomNode head;
    private int size;

    public CustomLinkedList(CustomNode head, int size) {
        this.head = head;
        this.size = size;
    }

    public CustomNode getHead() {
        return head;
    }

    public void setHead(CustomNode head) {
        this.head = head;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void add(CustomNode newNode, CustomNode previousNode, CustomNode nextNode) {
        if (previousNode != null)
            previousNode.setNextCustomNode(newNode);
        newNode.setNextCustomNode(nextNode);
        if (previousNode == null)
            head = newNode;
        size++;
    }

    public void addHead(CustomNode customNode) {
        head = customNode;
        size++;
    }

    public void removeHead() {
        head = head.getNextCustomNode();
        size--;

    }
}
