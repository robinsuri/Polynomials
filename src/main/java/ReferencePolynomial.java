package main.java;

public class ReferencePolynomial implements Polynomial {
    private CustomLinkedList polynomialCustomLinkedList;

    public ReferencePolynomial() {
        polynomialCustomLinkedList = new CustomLinkedList(null, 0);
    }

    public ReferencePolynomial(CustomLinkedList polynomialCustomLinkedList) {
        this.polynomialCustomLinkedList = polynomialCustomLinkedList;
    }

    @Override
    public int degree() {
        if (polynomialCustomLinkedList.getSize() == 0)
            return 0;
        return polynomialCustomLinkedList.getHead().getPower();
    }

    @Override
    public Polynomial add(Polynomial p) {
        return null;
    }

    @Override
    public Polynomial mult(Polynomial p) {
        return null;
    }

    @Override
    public void mult(double scalar) {
        if (scalar == 0) {
            polynomialCustomLinkedList = new CustomLinkedList(null, 0);
            return;
        }
        CustomNode currentNode = polynomialCustomLinkedList.getHead();
        while (currentNode != null) {
            double currentCoefficient = currentNode.getCoefficient();
            currentNode.setCoefficient(currentCoefficient * scalar);
            currentNode = currentNode.getNextCustomNode();
        }
    }

    @Override
    public double getCoefficient(int power) throws ExponentOutOfRangeException {
        if (polynomialCustomLinkedList.getSize() == 0) {
            if (power > 0)
                throw new ExponentOutOfRangeException();
            return 0.0;
        }
        CustomNode currentNode = polynomialCustomLinkedList.getHead();
        if (currentNode != null && currentNode.getPower() < power) {
            return 0.0;
        }
        while (currentNode != null && currentNode.getPower() > power)
            currentNode = currentNode.getNextCustomNode();
        if (currentNode == null || currentNode.getPower() < power)
            return 0.0;
        else
            return currentNode.getCoefficient();
    }

    @Override
    public void setCoefficient(double newCoefficient, int power) throws ExponentOutOfRangeException {
        CustomNode currentNode = polynomialCustomLinkedList.getHead();
        if (currentNode == null) {
            if (newCoefficient == 0)
                return;
            polynomialCustomLinkedList.addHead(new CustomNode(newCoefficient, power));
            return;
        }
        CustomNode previousNode = null;

        while (currentNode != null && currentNode.getPower() > power) {
            previousNode = currentNode;
            currentNode = currentNode.getNextCustomNode();
        }
        if (currentNode == null) {
            if (newCoefficient != 0)
                polynomialCustomLinkedList.add(new CustomNode(newCoefficient, power),
                        previousNode, null);
        } else if (currentNode.getPower() == power) {
            if (newCoefficient == 0) {
                if (previousNode == null)
                    polynomialCustomLinkedList.removeHead();
                else
                    previousNode.setNextCustomNode(currentNode.getNextCustomNode());
                return;
            }
            currentNode.setCoefficient(newCoefficient);
        } else if (currentNode.getPower() < power) {
            if (newCoefficient != 0)
                polynomialCustomLinkedList.add(new CustomNode(newCoefficient, power),
                        previousNode, currentNode);
        }

    }

    @Override
    public double evaluate(double x) {
        CustomLinkedList polynomialCustomLinkedList = this.polynomialCustomLinkedList;
        if (polynomialCustomLinkedList.getSize() == 0)
            return 0;
        CustomNode head = polynomialCustomLinkedList.getHead();
        double coefficient = head.getCoefficient();
        int power = head.getPower();
        double value = coefficient * Math.pow(x, power);
        CustomNode nextNode = head.getNextCustomNode();
        Polynomial polynomial = new ReferencePolynomial(new CustomLinkedList(nextNode,
                polynomialCustomLinkedList.getSize() - 1));
        return value + polynomial.evaluate(x);
    }

    @Override
    public void displayPolynomial() {
        if (polynomialCustomLinkedList.getSize() == 0) {
            System.out.println("0.0");
            return;
        }
        String polynomial = "";
        CustomNode currentNode = polynomialCustomLinkedList.getHead();
        double coefficient = currentNode.getCoefficient();
        int power = currentNode.getPower();
        if (polynomialCustomLinkedList.getSize() == 1) {
            if (power == 0) {
                polynomial += String.format("%s", coefficient);
                System.out.println(polynomial);
                return;
            }
            if (power == 1) {
                polynomial += String.format("%sx", coefficient);
                System.out.println(polynomial);
                return;
            } else {
                polynomial += String.format("%sx^%s", coefficient, power);
                System.out.println(polynomial);
                return;

            }
        }
        boolean flag = true;
        while (currentNode != null) {
            coefficient = currentNode.getCoefficient();
            power = currentNode.getPower();
            polynomial += (flag == true) ? "" : "+";
            if (power == 1)
                polynomial += String.format("%sx", coefficient);
            else if (power == 0)
                polynomial += String.format("%s", coefficient);
            else
                polynomial += String.format("%sx^%s", coefficient, power);
            currentNode = currentNode.getNextCustomNode();
            flag = false;
        }
        System.out.println(polynomial);
    }
}
