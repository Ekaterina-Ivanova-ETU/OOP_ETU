package Matrix;

import java.util.Arrays;
import java.util.Objects;

public class Matrix {
    private final double[][] matrix;
    public final int rows;
    public final int columns;


    public Matrix(int rows, int columns) {
        if (rows <= 0 || columns <= 0)
            throw new IllegalArgumentException("The matrix must contain at least one element.");

        matrix = new double[rows][columns];
        this.rows = rows;
        this.columns = columns;
    }

    public double get(int row, int column) {
        if (rows < 0 || columns < 0 || row >= rows || column >= columns)
            throw new IllegalArgumentException("Indexes of matrix elements cannot be negative or greater than or equal to the number of rows / columns in the matrix.");

        return matrix[row][column];
    }

    public void set(double value, int row, int column) {
        if (row < 0 || column < 0 || row >= rows || column >= columns)
            throw new IllegalArgumentException("Indexes of matrix elements cannot be negative or greater than or equal to the number of rows / columns in the matrix.");

        matrix[row][column] = value;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public Matrix addition(Matrix addMatrix) {
        if (addMatrix == null)
            throw new IllegalArgumentException("The matrix cannot be null.");
        if (rows != addMatrix.rows || columns != addMatrix.columns)
            throw new IllegalArgumentException("The dimensions of the matrices are not equal.");

        Matrix resMatrix = new Matrix(this.rows, this.columns);
        double sum;
        for(int i = 0; i < this.rows; i++) {
            for(int j = 0; j < this.columns; j++) {
                sum = this.get(i, j) + addMatrix.get(i, j);
                resMatrix.set(sum, i, j);
            }
        }

        return resMatrix;
    }

    public Matrix subtraction(Matrix subMatrix) {
        if (subMatrix == null)
            throw new IllegalArgumentException("The matrix cannot be null.");
        if (rows != subMatrix.rows || columns != subMatrix.columns)
            throw new IllegalArgumentException("The dimensions of the matrices are not equal.");

        Matrix resMatrix = new Matrix(this.rows, this.columns);
        double diff;
        for(int i = 0; i < this.rows; i++) {
            for(int j = 0; j < this.columns; j++) {
                diff = this.get(i, j) - subMatrix.get(i, j);
                resMatrix.set(diff, i, j);
            }
        }

        return resMatrix;
    }

    public Matrix multiplication(Matrix multiMatrix) {
        if (multiMatrix == null)
            throw new IllegalArgumentException("The matrix cannot be null.");
        if (columns != multiMatrix.rows)
            throw new IllegalArgumentException("The number of columns in the first matrix is not equal to the number of rows in the second one.");

        Matrix resMatrix = new Matrix(this.rows, multiMatrix.columns);
        double multi = 0.0;
        for(int i = 0; i < this.rows; i++) {
            for(int j = 0; j < multiMatrix.columns; j++) {
                   for(int k = 0; k < this.columns; k++) {
                       multi += this.get(i, k) * multiMatrix.get(k, j);
                   }
                resMatrix.set(multi, i, j);
                multi = 0.0;
            }
        }

        return resMatrix;
    }

    public Matrix multiplication(double scalar) {
        Matrix resMatrix = new Matrix(this.rows, this.columns);

        double multi;
        for(int i = 0; i < this.rows; i++) {
            for(int j = 0; j < this.columns; j++) {
                multi = scalar * this.get(i, j);
                resMatrix.set(multi, i, j);
            }
        }

        return resMatrix;
    }

    public double determinant() {
        if (rows != columns)
            throw new IllegalArgumentException("Not a square matrix.");

        return calculateDeterminant(matrix);
    }

    private double calculateDeterminant(double[][] matrix) {
        double result = 0.0;
        if (matrix.length == 1) {
            result = matrix[0][0];
            return result;
        }
        if (matrix.length == 2) {
            result = matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
            return result;
        }
        for (int i = 0; i < matrix[0].length; i++) {
            double[][] tempMatrix = new double[matrix.length - 1][matrix[0].length - 1];
            for (int j = 1; j < matrix.length; j++) {
                for (int k = 0; k < matrix[0].length; k++) {
                    if (k < i) {
                        tempMatrix[j - 1][k] = matrix[j][k];
                    } else if (k > i) {
                        tempMatrix[j - 1][k - 1] = matrix[j][k];
                    }
                }
            }
            result += matrix[0][i] * Math.pow(-1, i) * calculateDeterminant(tempMatrix);
        }

        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (this.getClass() != obj.getClass()) return false;

        Matrix comparedMatrix = (Matrix) obj;

        if (rows != comparedMatrix.rows || columns != comparedMatrix.columns) return false;

        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < columns; j++) {
                if (matrix[i][j] != comparedMatrix.get(i, j)) return false;
            }
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = 0;
        for(int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                result +=  31 * result + (int) matrix[i][j];
            }
        }

        return result;
    }

    @Override
    public String toString() {
        StringBuilder stringB = new StringBuilder();

        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < columns; j++) {
                    stringB.append(String.format("%.2f", matrix[i][j]) + " ");
            }
            stringB.append("\n");
        }

        return stringB.toString();
    }
}