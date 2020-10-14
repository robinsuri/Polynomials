package main.java;

public class CustomLinkedList {
    private CustomNode head;

    public CustomLinkedList(CustomNode head) {
        this.head = head;
    }

    public CustomNode getHead() {
        return head;
    }

    public void setHead(CustomNode head) {
        this.head = head;
    }

    public void add(CustomNode newNode, CustomNode previousNode, CustomNode nextNode) {
        if (previousNode != null)
            previousNode.setNextCustomNode(newNode);
        newNode.setNextCustomNode(nextNode);
        if (previousNode == null)
            head = newNode;
    }

    public void addHead(CustomNode customNode) {
        head = customNode;
    }

    public void removeHead() {
        head = head.getNextCustomNode();
    }

    // remove the currenNode from the list
    public void remove(CustomNode currentNode, CustomNode previousNode) {
        if (previousNode == null) {
            head = currentNode.getNextCustomNode();
        } else {
            previousNode.setNextCustomNode(currentNode.getNextCustomNode());
        }

    }

    public int getSize() {
        int size = 0;
        CustomNode currentNode = head;
        while (currentNode != null) {
            size++;
            currentNode = currentNode.getNextCustomNode();
        }
        return size;
    }

    public void append(CustomNode newNode) {
        CustomNode currentNode = head;
        if (head == null) {
            head = newNode;
            return;
        }
        CustomNode lastNode = null;
        while (currentNode != null) {
            lastNode = currentNode;
            currentNode = currentNode.getNextCustomNode();
        }
        lastNode.setNextCustomNode(newNode);
    }

    public boolean isEmpty() {
        return head == null;
    }
}
