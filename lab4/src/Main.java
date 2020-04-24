import matrix.Matrix;
import matrix.MatrixGenerator;

public class Main {
    private static final int THREAD_NUM = 10;

    public static void main(String[] args) throws Exception {
        Matrix matrix1 = MatrixGenerator.generate(500, 500);
        Matrix matrix2 = MatrixGenerator.generate(500, 500);

        long threadStart = System.currentTimeMillis();
        Matrix matrix3 = matrix1.multiply(matrix2, THREAD_NUM);
        long threadEnd = System.currentTimeMillis();

        long normalStart = System.currentTimeMillis();
        Matrix matrix4 = matrix1.multiply(matrix2);
        long normalEnd = System.currentTimeMillis();

        System.out.println("Thread(num:" + THREAD_NUM + " ) time: " + (threadEnd - threadStart));
        System.out.println("Normal time: " + (normalEnd - normalStart));

    }
}
