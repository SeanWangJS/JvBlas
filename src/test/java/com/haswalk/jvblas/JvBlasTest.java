package com.haswalk.jvblas;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;
/**
 * Created by wangx on 2017/10/16.
 */
public class JvBlasTest {

    // level-1
    @Test
    public void drotg() {
        double a = 0.51;
        double b = -1.78;

        double[] result = JvBlas.drotg(a, b);
        System.out.println(Arrays.toString(result));
    }

    @Test
    public void drotmg() {
        double dd1 = -0.12;
        double dd2 = 0.76;
        double dx1 = 0.94;
        double dy1 = 0.31;
        double[] result = JvBlas.drotmg(dd1, dd2, dx1, dy1);
        System.out.println(Arrays.toString(result));
    }

    @Test
    public void drot() {

        int n = 4;//             :Values of N
        int incX= 1, incY= 2;//           :Values of INCX, INCY
        double c=0.52, s=  -1.77;//   :Values of C, S
        double[] x={1.0,2.0,3.0,4.0};//     :Values of vector X
        double[] y = {-3.7,0.5,-4.1,0.5,7.7,0.5,6.2};//     :Values of vector Y
        JvBlas.drot(n, x, incX, y, incY, s, c);
        System.out.println(Arrays.toString(x));
        System.out.println(Arrays.toString(y));

    }

    @Test
    public void drotm() {
        int n = 5;//          :Values of N
        int incX = 1, incY =  1;//        :Values of INCX, INCY
        double[] dParams={-1.0,  0.0,  0.0,  0.0,  0.0};// :Values of vector PARAM
        double[] dx = {1.0, 2.0,  3.0,  4.0,  5.0};//     :Values of vector X
        double[] dy = {1.0,  1.0,  1.0,  1.0,  1.0};//     :Values of vector Y

        JvBlas.drotm(n, dx, incX, dy, incY, dParams);
        System.out.println(Arrays.toString(dx));
        System.out.println(Arrays.toString(dy));

    }

    @Test
    public void dsawp() {

        int n = 5;
        int incX = 1, incY = 1;
        double[] dx = {5,4,3,2,1};
        double[] dy = {1,2,3,4,5};
        JvBlas.dswap(n, dx, incX, dy, incY);
        System.out.println(Arrays.toString(dx));
        System.out.println(Arrays.toString(dy));

    }

    @Test
    public void dscal() {

        int n = 5;
        int incX = 1;
        double[] dx = {1,2,3,4,5};
        double da = 0.2;

        JvBlas.dscal(n, da, dx, incX);
        assertArrayEquals(dx, new double[]{0.2, 0.4, 0.6, 0.8, 1}, 0.01);

    }

    @Test
    public void daxpy() {

        int n = 5;
        double alpha = 0.2;
        double[] x = {1, 2, 3, 4,5};
        int incX = 1;
        double[] y = {2,3,4,5,6};
        int incY = 1;

        JvBlas.daxpy(n, alpha, x, incX, y, incY);

        assertArrayEquals(y, new double[]{2.2, 3.4, 4.6, 5.8, 7}, 0.01);
    }

    @Test
    public void ddot() {
        double[] X = {1,2,3};
        double[] Y = {4,5,6};
        int n = X.length;
        double ddot = JvBlas.ddot(n, X, 1, Y, 1);
        assertEquals(ddot, 32, 0.01);
    }

    @Test
    public void dnrm2() {

        int n = 4;
        double[] X = {1,2,3,4};
        int incX = 1;
        double result = JvBlas.dnrm2(n, X, incX);
        assertEquals(result, Math.sqrt(1*1+ 2*2+ 3*3+ 4*4), 0.01);

    }

    @Test
    public void dasum() {

    }

    @Test
    public void dgemv() {

        int m = 4, n = 5;//           :Values of M, N
        int incX = -1, incY =  1;//        :Values of INCX, INCY
        double alpha = 0.56, beta= 1.0;//      :Values of ALPHA, BETA
        int trans =112, layout= 101;//       :Values of TRANS, LAYOUT
        double[] x = {1.0,   2.0,   3.0,   4.0};//     :Values of vector X
        double[] y = {1.0,   1.0,   1.0,   1.0,   1.0};//     :Values of vector Y
        double[] A = {-1.3,   2.3,   3.7,   4.3 ,  5.9,
                -1.8,   2.8,   3.2,   4.6,   5.7,
        1.1,   2.2,   3.0 ,  4.5 ,  5.4,
        1.9,   2.8 ,  3.4,   4.2  , 5.1};//    :Values of array A

        JvBlas.dgemv(layout, trans, m, n, alpha, A, 5, x, incX, beta, y, incY);

        System.out.println(Arrays.toString(y));
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
