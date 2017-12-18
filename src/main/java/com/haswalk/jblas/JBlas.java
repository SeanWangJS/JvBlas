package com.haswalk.jblas;

//import org.intellij.lang.annotations.MagicConstant;

import java.io.File;
import java.lang.reflect.Field;

/**
 * Created by wangx on 2017/10/10.
 */
public class JBlas {

    static{
        System.setProperty("java.library.path", "C:\\Users\\wangx\\OneDrive\\workspace\\ideaw\\jblas\\lib");
        Field sysPaths = null;
        try {
            sysPaths = ClassLoader.class.getDeclaredField("sys_paths");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        sysPaths.setAccessible(true);
        try {
            sysPaths.set(null, null);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        System.loadLibrary("com_haswalk_jblas_JBlas");
    }

    /* level 1 */

    /**
     * constant alpha times a vector x plus a vector y
     * @param n define the number of product
     * @param incX index increment of array x
     * @param incY index increment of array y
     * */
    public static native void daxpy(int n, double alpha, double[] x, int incX, double[] y, int incY);

    /**
     * Forms the dot product of two vectors
     * @param n define the number of product
     * @param incX index increment of array x
     * @param incY index increment of array y
     * @return the dot product value
     * */
    public static native double ddot(int n, double[] x, int incX, double[] y, int incY);

    /**
     * Returns the Euclidean norm of a vector
     * @param n the number of element
     * @param incX the index increment of array x
     * @return Euclidean norm
     * */
    public static native double dnrm2(int n, double[] x, int incX);

    /**
     * Take the sum of the absolute values
     * @param n the number of element to take sum
     * @param incX the index increment of array x
     * @return the sum value
     * */
    public static native double dasum(int n, double[] x, int incX);

    /* level 2 */

    /**
     * Performs one of the matrix-vector operations
     *     y := alpha * A * x + beta * y
     *     or
     *     y := alpha * A**T * x + beta * y
     * where alpha and beta are scalars, x and y are vectors and A is an m by n matrix.
     * @param layout array memory layout: 101 = Row Major (C style), 102 = Col Major (Fortran style)
     * */
    public static native double dgemv(
//            @MagicConstant(intValues = {JBlasLayout.ROW_MAJOR, JBlasLayout.COL_MAJOR})
                    int layout,
//            @MagicConstant(intValues = {JBlasTranspose.NO_TRANS, JBlasTranspose.TRANS})
                    int transA, int m, int n, double alpha, double[] A, int LDA, double[] X, int incX, double beta, double[] Y, int incY);

    /**
     * performs the symmetric rank 1 operation
     *     A := alpha*x*x**T + A,
     *  where alpha is a real scalar, x is an n element vector and A is an
     *  n by n symmetric matrix.
     * */
    public static native double dsyr(int layout, int uplo, int n, double alpha, double[] X, int incX, double[] A, int LDA);

    /**
     * performs the symmetric rank 1 operation
     *     A := alpha*x*x**T + A,
     *  where alpha is a real scalar, x is an n element vector and A is an
     *  n by n symmetric matrix, supplied in packed form.
     * */
    public static native double dspr(int layout, int uplo, int n, double alpha, double[] X, int incX, double[] Ap);

    /** level 3 **/

    /**
     * Performs the matrix-matrix operations
     *     C := alpha * op(A) * op(B) + beta * C
     * where op(X) is one of op(X) = X or op(X) = X**T
     * alpha and beta are scalars, and A, B and C are matrices,
     * with op(A) an m × k matrix, op(B) a k × n matrix and C an m × n matrix.
     * @param layout array memory layout: 101 = Row Major (C style), 102 = Col Major (Fortran style)
     * */
    public static native void dgemm(
//            @MagicConstant(intValues = {JBlasLayout.ROW_MAJOR, JBlasLayout.COL_MAJOR})
                    int layout,
//            @MagicConstant(intValues = {JBlasTranspose.NO_TRANS, JBlasTranspose.TRANS})
                    int transA,
//            @MagicConstant(intValues = {JBlasTranspose.NO_TRANS, JBlasTranspose.TRANS})
                    int transB, int m, int n, int k, double alpha, double[] A, int LDA, double[] B, int LDB, double beta, double[] C, int LDC);

    /**
     * Performs the matrix-matrix operations
     *     C := alpha*A*B + beta*C,
     *     or
     *     C := alpha*B*A + beta*C,
     * where alpha and beta are scalars, A is a symmetric matrix and  B and C are  m by n matrices.
     * @param layout array memory layout: 101 = Row Major (C style), 102 = Col Major (Fortran style)
     * @param side define the matrix is at the left or right side of B
     * @param uplo define A is lower symmetric or upper symmetric
     * */
    public static native void dsymm(
//            @MagicConstant(intValues = {JBlasLayout.ROW_MAJOR, JBlasLayout.COL_MAJOR})
                    int layout,
//            @MagicConstant(intValues = {JBlasSide.LEFT, JBlasSide.RIGHT})
                    int side,
//            @MagicConstant(intValues = {JBlasUplo.UPPER, JBlasUplo.LOWER})
                    int uplo, int m, int n, double alpha, double[] A, int LDA, double[] B, int LDB, double beta, double[] C, int LDC);

}



