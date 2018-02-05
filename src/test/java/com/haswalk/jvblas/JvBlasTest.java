package com.haswalk.jvblas;

import org.junit.Test;

import java.util.Arrays;

/**
 * Created by wangx on 2017/10/16.
 */
public class JvBlasTest {

    @Test
    public void testddot() {
        double[] X = {1,2,3,4,5,6,7};
        double[] Y = {1,2,3,4,5,6,7};
        int n = X.length;
        double ddot = JvBlas.ddot(n, X, 1, Y, 1);
        System.out.println(ddot);
    }

    @Test
    public void testdnrm2() {
        double[] x = {1,2,3,4};
        int n = x.length;
        double v = JvBlas.dnrm2(n, x, 1);
        System.out.println(v);
    }

    @Test
    public void testdasum() {
        double[] x = {-1, 1, -10, 1, 2};
        int n = x.length;
        double dasum = JvBlas.dasum(n, x, 1);
        System.out.println(dasum);
    }

    @Test
    public void testdgemm() {
        int m = 4, n = 5, k = 3;
        int LDA = k, LDB = n, LDC = n;
        double[] A = {1,2,3,
                        4,2,2,
                        3,4,5,
                        4,2,1};
        double[] B = {1,2,3,6,6,
                        3,1,5,1,7,
                        4,6,8,8,10};
        double[] C = new double[m * n];

        double alpha = 1;
        double beta = 0;
        JvBlas.dgemm(101, 111, 111,
                m, n, k, alpha, A, LDA, B, LDB, beta, C, LDC);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(C[j + i * n] + "\t");
            }
            System.out.println();
        }
    }

    @Test
    public void testdsymm() {
        int m = 5, n = 4;
        int LDA = n, LDB = n, LDC = n;
        double[] A = {1,4,6,3,
                        4,2,3,2,
                        6,3,2,4,
                        3,2,4,5};
        double[] B = {1,2,3,6,
                        6,3,1,5,
                        1,7,4,6,
                        8,8,10,1,
                        2,3,1,5};
        double[] C = new double[m * n];
        double alpha = 1;
        double beta = 0;
        JvBlas.dsymm(JvBlasLayout.ROW_MAJOR, JvBlasSide.RIGHT, JvBlasUplo.LOWER,
                m, n, alpha, A, LDA, B, LDB, beta, C, LDC);

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(C[j + i * n] + "\t");
            }
            System.out.println();
        }
    }

    @Test
    public void testsapxy(){
        double[] x = {1,2,3,4,5,6};
        double[] y = {2,3,4,5,6,7};
        double alpha = 10;
        JvBlas.daxpy(x.length, alpha, x, 1, y, 1);
        System.out.println(Arrays.toString(x));
        System.out.println(Arrays.toString(y));
    }

    @Test
    public void testdsyr(){
        double[] x = {1,2,3};
        double[] A = new double[x.length * x.length];
        int LDA = 9;
        JvBlas.dsyr(JvBlasLayout.ROW_MAJOR, JvBlasUplo.LOWER, x.length, 1, x, 1, A, LDA);
        System.out.println(Arrays.toString(A));

    }

    @Test
    public void testdspr(){
        double[] x = {1,2,3};
        double[] A = new double[(x.length * (x.length + 1)) / 2];
        JvBlas.dspr(JvBlasLayout.ROW_MAJOR, JvBlasUplo.UPPER, x.length, 1, x, 1, A);
        System.out.println(Arrays.toString(A));
    }
}
