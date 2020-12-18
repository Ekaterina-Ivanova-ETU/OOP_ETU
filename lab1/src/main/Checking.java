package main;

import Matrix.Matrix;
import static java.lang.System.out;

public class Checking {
    public static void main(String[] args) {

        Matrix A = new Matrix(3, 2);
        Matrix B = new Matrix(2, 3);

        A.set(-1, 0, 0);
        A.set(2.2, 0, 1);
        A.set(5, 1, 0);
        A.set(0, 1, 1);
        A.set(-3.85, 2, 0);
        A.set(7, 2, 1);
        out.println("A =\n" + A.toString());

        B.set(0, 0, 0);
        B.set(3.33, 0, 1);
        B.set(-1.5, 0, 2);
        B.set(0.2, 1, 0);
        B.set(4.04, 1, 1);
        B.set(4, 1, 2);
        out.println("B =\n" + B.toString());

        Matrix C = A.multiplication(B);
        out.println("C = A * B =\n" + C.toString());
        out.println("Size C: " + C.getRows() + " x " + C.getColumns());
        out.println("C(2, 2) = " + C.get(2, 2));
        out.println("det|C| = " + C.determinant() + "\n");

        Matrix D = A.multiplication(0.5);
        out.println("D = 0.5 * A =\n" + D.toString());

        Matrix E = A.addition(D);
        out.println("A + D =\n" + E.toString());

        E = A.subtraction(D);
        out.println("A - D =\n" + E.toString());

        out.println("A = B ? - " + A.equals(B));
        out.println("A = D ? - " + A.equals(D));
        out.println("A = A ? - " + A.equals(A));
    }
}
