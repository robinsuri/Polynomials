package main.java;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class ReferencePolynomial implements Polynomial {

    private CustomLinkedList polynomialCustomLinkedList;

    public ReferencePolynomial() {
        polynomialCustomLinkedList = new CustomLinkedList(null);
    }

    public ReferencePolynomial(CustomLinkedList polynomialCustomLinkedList) {
        this.polynomialCustomLinkedList = polynomialCustomLinkedList;
    }

    public CustomLinkedList getPolynomialCustomLinkedList() {
        return polynomialCustomLinkedList;
    }

    @Override
    public int degree() {
        if (polynomialCustomLinkedList.getSize() == 0)
            return 0;
        return polynomialCustomLinkedList.getHead().getPower();
    }

    @Override
    public Polynomial add(Polynomial p) {
        ReferencePolynomial referencePolynomial = (ReferencePolynomial) p;
        CustomLinkedList firstPolynomialList = this.polynomialCustomLinkedList;
        CustomLinkedList secondPolynomialList = referencePolynomial.polynomialCustomLinkedList;
        CustomLinkedList sumLinkedList = new CustomLinkedList(null);
        if (firstPolynomialList == null) {
            return new ReferencePolynomial(secondPolynomialList);
        }
        if (secondPolynomialList == null) {
            return new ReferencePolynomial(firstPolynomialList);
        }
        CustomNode firstCustomNode = firstPolynomialList.getHead();
        CustomNode secondCustomNode = secondPolynomialList.getHead();
        CustomNode newCustomNode;
        while (firstCustomNode != null && secondCustomNode != null) {
            if (firstCustomNode.getPower() == secondCustomNode.getPower()) {
                double sum = firstCustomNode.getCoefficient() + secondCustomNode.getCoefficient();
                newCustomNode = new CustomNode(sum, firstCustomNode.getPower());
                if (sum != 0)
                    sumLinkedList.append(newCustomNode);
                firstCustomNode = firstCustomNode.getNextCustomNode();
                secondCustomNode = secondCustomNode.getNextCustomNode();
            } else if (firstCustomNode.getPower() < secondCustomNode.getPower()) {
                newCustomNode = new CustomNode(secondCustomNode.getCoefficient(), secondCustomNode.getPower());
                sumLinkedList.append(newCustomNode);
                secondCustomNode = secondCustomNode.getNextCustomNode();
            } else {
                newCustomNode = new CustomNode(firstCustomNode.getCoefficient(), firstCustomNode.getPower());
                sumLinkedList.append(newCustomNode);
                firstCustomNode = firstCustomNode.getNextCustomNode();
            }
        }
        if (firstCustomNode != null) {
            sumLinkedList.append(firstCustomNode);
        } else if (secondCustomNode != null) {
            sumLinkedList.append(secondCustomNode);
        }

        return new ReferencePolynomial(sumLinkedList);
    }


    @Override
    public Polynomial mult(Polynomial p) {
        CustomLinkedList firstPolynomialLinkedList = this.polynomialCustomLinkedList;
        ReferencePolynomial secondPolynomial = (ReferencePolynomial) p;
        CustomLinkedList secondPolynomialLinkedList = secondPolynomial.getPolynomialCustomLinkedList();

        if (firstPolynomialLinkedList.isEmpty() || (firstPolynomialLinkedList.getSize() == 1
                && firstPolynomialLinkedList.getHead().getCoefficient() == 0))
            return new ReferencePolynomial();

        else if (secondPolynomialLinkedList.isEmpty() || (secondPolynomialLinkedList.getSize() == 1
                && secondPolynomialLinkedList.getHead().getCoefficient() == 0))
            return new ReferencePolynomial();

        CustomNode firstPolynomialCurrentNode = firstPolynomialLinkedList.getHead();
        CustomNode secondPolynomialCurrentNode;

        Set<CustomLinkedList> multLinkedListSet = new HashSet<>();
        while (firstPolynomialCurrentNode != null) {
            int power = firstPolynomialCurrentNode.getPower();
            secondPolynomialCurrentNode = secondPolynomialLinkedList.getHead();
            double coefficient = firstPolynomialCurrentNode.getCoefficient();
            CustomLinkedList multLinkedList = new CustomLinkedList(null);
            while (secondPolynomialCurrentNode != null) {
                int newPower = power + secondPolynomialCurrentNode.getPower();
                double newCofficient = coefficient * secondPolynomialCurrentNode.getCoefficient();
                CustomNode newNode = new CustomNode(newCofficient, newPower);
                multLinkedList.append(newNode);
                secondPolynomialCurrentNode = secondPolynomialCurrentNode.getNextCustomNode();
            }
            multLinkedListSet.add(multLinkedList);
            firstPolynomialCurrentNode = firstPolynomialCurrentNode.getNextCustomNode();
        }

        Iterator itr = multLinkedListSet.iterator();
        ReferencePolynomial initialPolynomial = new ReferencePolynomial();
        while (itr.hasNext()) {
            CustomLinkedList multLinkedList = (CustomLinkedList) itr.next();
            initialPolynomial = (ReferencePolynomial) initialPolynomial.add(new ReferencePolynomial(multLinkedList));
        }
        return initialPolynomial;
    }

    @Override
    public void mult(double scalar) {
        if (scalar == 0) {
            polynomialCustomLinkedList = new CustomLinkedList(null);
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
                polynomialCustomLinkedList.append(new CustomNode(newCoefficient, power));
        } else if (currentNode.getPower() == power) {
            if (newCoefficient == 0) {
                if (previousNode == null)
                    polynomialCustomLinkedList.removeHead();
                else
                    polynomialCustomLinkedList.remove(currentNode, previousNode);
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
        Polynomial polynomial = new ReferencePolynomial(new CustomLinkedList(nextNode));
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
            polynomial += (flag == true) ? "" : " + ";
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
