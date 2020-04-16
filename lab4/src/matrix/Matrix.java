package matrix;

import java.util.concurrent.CountDownLatch;

public class Matrix {
    private double[][] doubles;
    private int row;
    private int column;

    public Matrix(int row, int column) {
        this.row = row;
        this.column = column;
        doubles = new double[row][column];
    }

    public void setValue(int row, int column, double value) {
        doubles[row][column] = value;
    }

    public void print() {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                System.out.print(doubles[i][j] + " ");
            }
            System.out.println();
        }
    }

    public double getValue(int row, int column) {
        return doubles[row][column];
    }

    public Matrix multiply(Matrix matrix) throws Exception {
        if (this.column != matrix.row) {
            throw new Exception("矩阵乘法无效 需满足a*b b*c的格式");
        }
        Matrix result = new Matrix(this.row, matrix.column);
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < matrix.column; j++) {
                result.setValue(i, j, getXy(i, j, matrix));
            }
        }
        return result;
    }

    public Matrix multiply(Matrix matrix, int threadNum) throws Exception {
        if (threadNum < 0) {
            throw new Exception("param threadNum < 0");
        }
        if (this.column != matrix.row) {
            throw new Exception("矩阵乘法无效 需满足a*b b*c的格式");
        }

        final CountDownLatch countDownLatch = new CountDownLatch(threadNum);
        Matrix result = new Matrix(this.row, matrix.column);
        for (int i = 0; i < threadNum; i++) {
            final int r = i;
            Thread thread = new Thread(() -> {
                for (int j = r; j < result.row; j += threadNum) {
                    for (int k = 0; k < result.column; k++) {
                        result.setValue(j, k, getXy(j, k, matrix));
                    }
                }
                countDownLatch.countDown();
            });
            thread.start();
        }
        countDownLatch.await();
        return result;
    }

    private double getXy(int x, int y, Matrix matrix) {
        double result = 0;
        int i = 0;
        int j = 0;
        while (i < column && j < matrix.row) {
            result += this.getValue(x, i) * matrix.getValue(j, y);
            i++;
            j++;
        }
        return result;
    }
}
