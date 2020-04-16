package matrix;

import java.util.Random;

public class MatrixGenerator {
    public static Matrix generate(int rowNum, int columnNum) {
        Random random = new Random(System.currentTimeMillis());
        Matrix matrix = new Matrix(rowNum,columnNum);
        for (int i = 0; i < rowNum; i++) {
            for (int j = 0; j < columnNum; j++) {
                matrix.setValue(i,j,random.nextInt(100));
            }
        }
        return matrix;
    }

}
