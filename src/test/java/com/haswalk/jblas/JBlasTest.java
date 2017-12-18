package com.haswalk.jblas;

import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.Arrays;

/**
 * Created by wangx on 2017/10/16.
 */
public class JBlasTest {

    @Test
    public void testddot() {
        double[] X = {1,2,3,4,5,6,7};
        double[] Y = {1,2,3,4,5,6,7};
        int n = X.length;
        double ddot = JBlas.ddot(n, X, 1, Y, 1);
        System.out.println(ddot);
    }

    @Test
    public void testdnrm2() {
        double[] x = {1,2,3,4};
        int n = x.length;
        double v = JBlas.dnrm2(n, x, 1);
        System.out.println(v);
    }

    @Test
    public void testdasum() {
        double[] x = {-1, 1, -10, 1, 2};
        int n = x.length;
        double dasum = JBlas.dasum(n, x, 1);
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
        JBlas.dgemm(JBlasLayout.ROW_MAJOR, JBlasTranspose.NO_TRANS, JBlasTranspose.NO_TRANS,
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
        JBlas.dsymm(JBlasLayout.ROW_MAJOR, JBlasSide.RIGHT, JBlasUplo.LOWER,
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
        JBlas.daxpy(x.length, alpha, x, 1, y, 1);
        System.out.println(Arrays.toString(x));
        System.out.println(Arrays.toString(y));
    }

    @Test
    public void testdsyr(){
        double[] x = {1,2,3};
        double[] A = new double[x.length * x.length];
        int LDA = 9;
        JBlas.dsyr(JBlasLayout.ROW_MAJOR, JBlasUplo.LOWER, x.length, 1, x, 1, A, LDA);
        System.out.println(Arrays.toString(A));

    }

    @Test
    public void testdspr(){
        double[] x = {1,2,3};
        double[] A = new double[(x.length * (x.length + 1)) / 2];
        JBlas.dspr(JBlasLayout.ROW_MAJOR, JBlasUplo.UPPER, x.length, 1, x, 1, A);
        System.out.println(Arrays.toString(A));
    }
}
