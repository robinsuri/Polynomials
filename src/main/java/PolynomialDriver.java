package main.java;

// ********************************************************
// File name: PolynomialDriver.java
// A driver testing class Polynomial.
// By Chi-Cheng Lin
// Date: 09/07/2020
// ********************************************************
public class PolynomialDriver {

    static public void showInfo(Polynomial p) {
        p.displayPolynomial();
        System.out.println("Its degree is " + p.degree() + "\n");
    } // showInfo

    static public void main(String[] args) throws ExponentOutOfRangeException {
        //Test case 1: Test for constructor, degree(), and displayPolynomial()
        System.out.println("Test case 1: constructor");
        Polynomial p = new ReferencePolynomial();
        System.out.print("p = ");
        showInfo(p);

        // Test case 2.1: Test for setCoefficient(), getCoefficient(), degree(),
        //                and displayPolynomial()
        System.out.println("Test case 2.1: basics");
        p.setCoefficient(3.0, 0);
        System.out.println("Coefficient of power 0 = " + p.getCoefficient(0));
        System.out.println("Coefficient of power 4 = " + p.getCoefficient(4));
        System.out.print("p = ");
        showInfo(p);

        // Test case 2.2: Test for setCoefficient(), getCoefficient(), degree(),
        //                and displayPolynomial()
        System.out.println("Test case 2.2:");
        p.setCoefficient(-7.5, 4);
        System.out.println("Coefficient of power 4 = " + p.getCoefficient(4));
        System.out.print("p = ");
        showInfo(p);

        // Test case 2.3: Test for setCoefficient(), getCoefficient(), degree(),
        //                and displayPolynomial()
        System.out.println("Test case 2.3:");
        p.setCoefficient(-5.5, 3);
        p.setCoefficient(2.5, 4);
        System.out.println("Coefficient of power 4 = " + p.getCoefficient(4));
        System.out.println("Coefficient of power 3 = " + p.getCoefficient(3));
        System.out.print("p = ");
        showInfo(p);

        // Test case 2.4: Test for setCoefficient(), degree(), and displayPolynomial()
        System.out.println("Test case 2.4:");
        p.setCoefficient(0, 6);
        System.out.print("p = ");
        showInfo(p);

        // Test case 3.1: Test for setCoefficient(0), degree(), and displayPolynomial()
        System.out.println("Test case 3.1:");
        p.setCoefficient(0, 4);
        System.out.print("p = ");
        showInfo(p);

        // Test case 3.2: Test for setCoefficient(0), degree(), and displayPolynomial()
        System.out.println("Test case 3.2:");
        p.setCoefficient(0, 0);
        System.out.print("p = ");
        showInfo(p);

        // Set up polynomial q
        System.out.println("\nChange q and display it: ");
        Polynomial q = new ReferencePolynomial();
        q.setCoefficient(8.75, 2);
        q.setCoefficient(5, 4);
        q.setCoefficient(2, 0);
        q.setCoefficient(1, 1);
        System.out.print("q = ");
        q.displayPolynomial();

        // Test case 4: Test for mult(double)
        System.out.println("\nTest case 4.1: mult(double)");
        q.mult(-3);
        System.out.print("\nq = -3 * q = ");
        showInfo(q);

        q.mult(12.5);
        System.out.print("\nq = 12.5 * q = ");
        showInfo(q);

        // Test case 4: Test for mult(double), result is 0 with degree 0
        System.out.println("Test case 4.2: special case for result 0; degree becomes 0");
        q.mult(0.0);
        System.out.print("\nq = 0 * q = ");
        showInfo(q);

        // Test case 5.1: Test for add()
        // Set up polynomial p
        System.out.println("Test case 5.1: add()");
        p.setCoefficient(10.5, 1);
        p.setCoefficient(2.25, 5);
        System.out.print("p = ");
        showInfo(p);

        //test for add()
        System.out.println("\nq = p + p: ");
        q = p.add(p);
        System.out.print("q = ");
        q.displayPolynomial();

        // Test case 5.2: Test for add()
        // Set up polynomial q
        System.out.println("\nTest case 5.2:");
        System.out.println("\nChange q and display it: ");
        q.setCoefficient(8.125, 2);
        q.setCoefficient(5, 4);
        q.setCoefficient(2, 0);
        q.setCoefficient(1, 1);
        System.out.print("q = ");
        q.displayPolynomial();

        //test for add()
        Polynomial r = p.add(q);
        System.out.print("\nr = p + q = ");
        showInfo(r);
        System.out.print("where p = ");
        p.displayPolynomial();
        System.out.print("  and q = ");
        q.displayPolynomial();

        // Test case 5.3: Test for add() special case
        // degree updated as higher terms in sum becomes 0
        System.out.println("\nTest case 5.3: special case when higher terms become 0");
        p.setCoefficient(-5.0, 4);
        q.setCoefficient(-2.25, 5);
        r = p.add(q);
        System.out.print("\nr = p + q = ");
        showInfo(r);
        System.out.print("where p = ");
        p.displayPolynomial();
        System.out.print("  and q = ");
        q.displayPolynomial();

        // Test case 6: Test for evaluate()
        //test for evaluate()
        System.out.println("\nTest case 6: evaluate()");
        p.setCoefficient(-1, 5);
        System.out.print("p = ");
        showInfo(p);
        System.out.println("The value of p at 0 is " + p.evaluate(0));
        System.out.println("The value of p at 1.0 is " + p.evaluate(1.0));
        System.out.println("The value of p at 2.0 is " + p.evaluate(2.5));
        System.out.println("The value of p at -15.6 is " + p.evaluate(-15.0));

        // Test for mult(Polynomial)
        // Test case M.1
        System.out.println("\nTest case M.1:");
        Polynomial s = new ReferencePolynomial();
        Polynomial t = new ReferencePolynomial();
        s.setCoefficient(1, 2);
        r = s.mult(t);
        System.out.print("\nr = s * t = ");
        r.displayPolynomial();
        System.out.print("where s = ");
        s.displayPolynomial();
        System.out.print("  and t = ");
        t.displayPolynomial();

        // Test case M.2
        System.out.println("\nTest case M.2:");
        t.setCoefficient(1, 1);
        r = s.mult(t);
        System.out.print("\nr = s * t = ");
        r.displayPolynomial();
        System.out.print("where s = ");
        s.displayPolynomial();
        System.out.print("  and t = ");
        t.displayPolynomial();

        // Test case M.3
        System.out.println("\nTest case M.3:");
        s.setCoefficient(1, 1);
        s.setCoefficient(1, 0);
        t.setCoefficient(-1, 0);
        r = t.mult(t);
        System.out.print("\nr = t * t = ");
        r.displayPolynomial();
        System.out.print("where t = ");
        t.displayPolynomial();

        // Test case M.4
        System.out.println("\nTest case M.4:");
        r = s.mult(t);
        System.out.print("\nr = s * t = ");
        r.displayPolynomial();
        System.out.print("where s = ");
        s.displayPolynomial();
        System.out.print("  and t = ");
        t.displayPolynomial();

        // Test case M.5
        System.out.println("\nTest case M.5:");
        r = s.mult(s);
        System.out.print("\nr = s * s = ");
        r.displayPolynomial();
        System.out.print("where s = ");
        s.displayPolynomial();

        // Test case M.6
        System.out.println("\nTest case M.6:");
        p.setCoefficient(-1, 2);
        r = p.mult(q);
        System.out.print("\nr = p * q = ");
        r.displayPolynomial();
        System.out.print("where p = ");
        p.displayPolynomial();
        System.out.print("  and q = ");
        q.displayPolynomial();

        // Exception Tests

        // Test case Exception on setCoefficient()
        System.out.println("\nTest case E.1: setCoefficient()");
        // Try setting coefficient for power 1000
        try {
            p.setCoefficient(1.2, 1000);
        }
        catch (ExponentOutOfRangeException e) {
            System.out.println("\n" + e);
        }

        // Test case Exception on getCoefficient()
        System.out.println("\nTest case E.2: getCoefficient()");
        // Try getting coefficient for power 1000
        try {
            System.out.println("The coefficient of power 800 is " + p.getCoefficient(800) + "\n");
        }
        catch (ExponentOutOfRangeException e) {
            System.out.println("\n" + e);
        }

        // Test case Exception on mult()
        System.out.println("\nTest case E.3: multiply()");
        // Try exception generated by mult()
        try {
            p.setCoefficient(1, 80);
            q.setCoefficient(1, 90);
            q = q.mult(p);
            showInfo(q);
        }
        catch (ExponentOutOfRangeException e) {
            System.out.println("\n" + e);
        }
    } // main
} // PolynomialDriver
