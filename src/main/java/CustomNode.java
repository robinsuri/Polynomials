package main.java;

public class CustomNode {
    private double coefficient;
    private int power;
    private CustomNode nextCustomNode;

    public CustomNode(double coefficient, int power, CustomNode nextCustomNode) {
        this.coefficient = coefficient;
        this.power = power;
        this.nextCustomNode = nextCustomNode;
    }

    public CustomNode(double coefficient, int power) {
        this.coefficient=coefficient;
        this.power = power;
    }

    public double getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(double coefficient) {
        this.coefficient = coefficient;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public CustomNode getNextCustomNode() {
        return nextCustomNode;
    }

    public void setNextCustomNode(CustomNode nextCustomNode) {
        this.nextCustomNode = nextCustomNode;
    }
}
