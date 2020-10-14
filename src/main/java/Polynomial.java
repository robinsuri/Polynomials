package main.java;

public interface Polynomial {
    int degree();
    Polynomial add(Polynomial p);
    Polynomial mult(Polynomial p);
    void  mult(double scalar);
    double getCoefficient(int power) throws ExponentOutOfRangeException;
    void setCoefficient(double newCoefficient, int power) throws ExponentOutOfRangeException;
    double evaluate(double x);
    void displayPolynomial();
}
