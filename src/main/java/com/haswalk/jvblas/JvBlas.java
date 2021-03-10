package com.haswalk.jvblas;

import org.intellij.lang.annotations.MagicConstant;

/**
 * Created by wangx on 2017/10/10.
 */
public class JvBlas {

    static{

//        System.setProperty("java.library.path", "C:\\Users\\wangx\\OneDrive\\workspace\\ideaw\\jblas\\native\\lib");
//        System.out.println(System.getProperty("user.dir"));
//        System.setProperty("java.library.path", System.getProperty("user.dir")+"\\jblas\\native\\lib");
//        Field sysPaths = null;
//        try {
//            sysPaths = ClassLoader.class.getDeclaredField("sys_paths");
//        } catch (NoSuchFieldException e) {
//            e.printStackTrace();
//        }
//        sysPaths.setAccessible(true);
//        try {
//            sysPaths.set(null, null);
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }
//        System.loadLibrary("com_haswalk_jvblas_JvBlas");

        System.load(System.getProperty("user.dir")+"\\native\\lib\\com_haswalk_jvblas_JvBlas.dll");
    }

    /*================================ level 1 =======================================*/

    /**
     * Construct Givens rotation
     * @param da
     * @param db
     * @return [r, z, c, s]
     * */
    public static native double[] drotg(double da, double db);

    /**
     * Construct the mofified Givens transformation
     * @param dd1
     * @param dd2
     * @param dx1
     * @param dx2
     * @return params
     * */
    public static native double[] drotmg(double dd1, double dd2, double dx1, double dy1);

    /**
     * Applies a plane rotation
     * @param
     * */
    public static native void drot(int n, double[] dx, int incX, double[] dy, int incY, double c, double s);

    /**
     * Apply the modified Givens transformation
     * */
    public static native void drotm(int n, double[] dx, int incX, double[] dy, int incY, double[] dParams);

    /**
     * Interchange two vectors
     * @param n Number of elements in input vector
     *
     * */
    public static native void dswap(int n, double[] dx, int incX, double[] dy, int incY);

    /**
     * Scales a vector by a constant
     * @param n number of elements in input vector
     * @param da specifies the scalar alpha
     *
     * */
    public static native void dscal(int n, double da, double[] dx, int incX);

    /**
     * Copies a vector to another one
     * */
    public static native void dcopy(int n, double[] dx, int incX, double[] dy, int incY);

    /**
     * Perform the operation:
     *      Y := alpha * X + Y
     * @param n number of elements in input vector
     * @param incX storage spacing between elements of x
     * @param incY storage spacing between elements of y
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
     * Compute the inner product of two vectors with extended
     * precision accumulation and result
     * */
    public static native double dsdot(int n, double[] sx, int incX, double[] sy, int incY);

    /**
     * Returns the Euclidean norm of a vector
     * @param n the number of element
     * @param incX the index increment of array x
     * @return Euclidean norm
     * */
    public static native double dnrm2(int n, double[] x, int incX);

    /**
     * Takes the sum of the absolute values.
     * @param n number of elements in input vectors
     * @param x double precious array
     * @param incX storage spacing between elements of x
     * */
    public static native double dasum(int n, double[] x, int incX);

    /**
     * Finds the index of the first element having maximum absolute value
     * */
    public static native int idamax(int n, double[] dx, int incX);

    /*================================ level 2 =======================================*/

    /**
     * Performs one of the matrix-vector operations
     *     y := alpha * A * x + beta * y
     *     or
     *     y := alpha * A**T * x + beta * y
     * where alpha and beta are scalars, x and y are vectors and A is an m by n matrix.
     * @param layout array memory layout: 101 = Row Major (C style), 102 = Col Major (Fortran style)
     * */
    public static native void dgemv(
            @MagicConstant(intValues = {JvBlasLayout.ROW_MAJOR, JvBlasLayout.COL_MAJOR})
                    int layout,
            @MagicConstant(intValues = {JvBlasTranspose.NO_TRANS, JvBlasTranspose.TRANS})
                    int transA, int m, int n, double alpha,
                    double[] A, int LDA, double[] X, int incX, double beta, double[] Y, int incY);

    /**
     * Performs one of the matrix-vector operations
     *
     *     y := alpha*A*x + beta*y,   or   y := alpha*A**T*x + beta*y,
     *
     *  where alpha and beta are scalars, x and y are vectors and A is an
     *  m by n band matrix, with kl sub-diagonals and ku super-diagonals
     * */
    public static native void dgbmv(int layout, int trans, int m, int n, int kl, int ku, double alpha, double[] A, int LDA, double[] X, int incX, double beta, double[] Y, int incY);

    /**
     * Performs the matrix-vector  operation
     *
     *     y := alpha*A*x + beta*y,
     *
     *  where alpha and beta are scalars, x and y are n element vectors and
     *  A is an n by n symmetric matrix
     * */
    public static native void dsymv(int layout, int uplo, int n, double alpha, double[] A, int LDA, double[] X, int incX, double beta, double[] Y, int incY);

    /**
     *  Performs the matrix-vector  operation
     *
     *     y := alpha*A*x + beta*y,
     *
     *  where alpha and beta are scalars, x and y are n element vectors and
     *  A is an n by n symmetric band matrix, with k super-diagonals.
     * */
    public static native void dsbmv(int layout, int uplo, int n, int k, double alpha, double[] A, int LDA, double[] X, int incX, double beta, double[] Y, int incY);

    /**
     * Performs the matrix-vector operation
     *
     *     y := alpha*A*x + beta*y,
     *
     *  where alpha and beta are scalars, x and y are n element vectors and
     *  A is an n by n symmetric matrix, supplied in packed form.
     * */
    public static native void dspmv(int layout, int uplo, int n, double alpha, double[] AP, double[] X, int incX, double beta, double[] Y, int incY);

    /**
     * Performs one of the matrix-vector operations
     *
     *     x := A*x,   or   x := A**T*x,
     *
     *  where x is an n element vector and  A is an n by n unit, or non-unit,
     *  upper or lower triangular matrix.
     * */
    public static native void dtrmv(int layout, int uplo, int trans, int diag, int n, double[] A, int LDA, double[] X, int incX);

    /**
     * Performs one of the matrix-vector operations
     *
     *     x := A*x,   or   x := A**T*x,
     *
     *  where x is an n element vector and  A is an n by n unit, or non-unit,
     *  upper or lower triangular band matrix, with ( k + 1 ) diagonals.
     * */
    public static native void dtbmv(int layout, int uplo, int trans, int diag, int n, int k, double[] A, int LDA, double[] X, int incX);

    /**
     * Performs one of the matrix-vector operations
     *
     *     x := A*x,   or   x := A**T*x,
     *
     *  where x is an n element vector and  A is an n by n unit, or non-unit,
     *  upper or lower triangular matrix, supplied in packed form.
     * */
    public static native void dtpmv(int layout, int uplo, int trans, int diag, int n, double[] AP, double[] X, int incX);

    /**
     * Solves one of the systems of equations
     *
     *     A*x = b,   or   A**T*x = b,
     *
     *  where b and x are n element vectors and A is an n by n unit, or
     *  non-unit, upper or lower triangular matrix.
     *
     *  No test for singularity or near-singularity is included in this
     *  routine. Such tests must be performed before calling this routine.
     * */
    public static native void dtrsv(int layout, int uplo, int trans, int diag, int n, double[] A, int LDA, double[] X, int incX);

    /**
     * Solves one of the systems of equations
     *
     *     A*x = b,   or   A**T*x = b,
     *
     *  where b and x are n element vectors and A is an n by n unit, or
     *  non-unit, upper or lower triangular band matrix, with ( k + 1 )
     *  diagonals.
     *
     *  No test for singularity or near-singularity is included in this
     *  routine. Such tests must be performed before calling this routine.
     * */
    public static native void dtbsv(int layout, int uplo, int trans, int diag, int n, int k, double[] A, int LDA, double[] X, int incX);

    /**
     * Solves one of the systems of equations
     *
     *     A*x = b,   or   A**T*x = b,
     *
     *  where b and x are n element vectors and A is an n by n unit, or
     *  non-unit, upper or lower triangular matrix, supplied in packed form.
     *
     *  No test for singularity or near-singularity is included in this
     *  routine. Such tests must be performed before calling this routine.
     * */
    public static native void dtpsv(int layout, int uplo, int trans, int diag, int n, double[] AP, double[] X, int incX);

    /**
     * Performs the rank 1 operation
     *
     *     A := alpha*x*y**T + A,
     *
     *  where alpha is a scalar, x is an m element vector, y is an n element
     *  vector and A is an m by n matrix.
     * */
    public static native void dger(int layout, int m, int n, double alpha, double[] X, int incX, double[] Y, int incY, double[] A, int LDA);

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

    /**
     * performs the symmetric rank 2 operation
     *
     *     A := alpha*x*y**T + alpha*y*x**T + A,
     *
     *  where alpha is a scalar, x and y are n element vectors and A is an n
     *  by n symmetric matrix.
     * */
    public static native void dsyr2(int layout, int uplo, int n, double alpha, double[] X, int incX, double[] Y, int incY, double[] A, int LDA);

    /**
     * Performs the symmetric rank 2 operation
     *
     *     A := alpha*x*y**T + alpha*y*x**T + A,
     *
     *  where alpha is a scalar, x and y are n element vectors and A is an
     *  n by n symmetric matrix, supplied in packed form.
     * */
    public static native void dspr2(int layout, int uplo, int n, double alpha, double[] X, int incX, double[] Y, int incY, double[] AP);

    /*================================ level 3 =======================================*/

    /**
     * Performs the matrix-matrix operations
     *     C := alpha * op(A) * op(B) + beta * C
     * where op(X) is one of op(X) = X or op(X) = X**T
     * alpha and beta are scalars, and A, B and C are matrices,
     * with op(A) an m × k matrix, op(B) a k × n matrix and C an m × n matrix.
     * @param layout array memory layout: 101 = Row Major (C style), 102 = Col Major (Fortran style)
     * */
    public static native void dgemm(
//            @MagicConstant(intValues = {JvBlasLayout.ROW_MAJOR, JvBlasLayout.COL_MAJOR})
                    int layout,
//            @MagicConstant(intValues = {JvBlasTranspose.NO_TRANS, JvBlasTranspose.TRANS})
                    int transA,
//            @MagicConstant(intValues = {JvBlasTranspose.NO_TRANS, JvBlasTranspose.TRANS})
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
//            @MagicConstant(intValues = {JvBlasLayout.ROW_MAJOR, JvBlasLayout.COL_MAJOR})
                    int layout,
//            @MagicConstant(intValues = {JvBlasSide.LEFT, JvBlasSide.RIGHT})
                    int side,
//            @MagicConstant(intValues = {JvBlasUplo.UPPER, JvBlasUplo.LOWER})
                    int uplo, int m, int n, double alpha, double[] A, int LDA, double[] B, int LDB, double beta, double[] C, int LDC);

    /**
     *  Performs one of the symmetric rank k operations
     *
     *     C := alpha*A*A**T + beta*C,
     *
     *  or
     *
     *     C := alpha*A**T*A + beta*C,
     *
     *  where  alpha and beta  are scalars, C is an  n by n  symmetric matrix
     *  and  A  is an  n by k  matrix in the first case and a  k by n  matrix
     *  in the second case.
     * */
    public static native void dsyrk(int layout, int uplo, int trans, int n, int k, double alpha, double[] A, int LDA, double beta, double[] C, int LDC);


    /**
     * Performs one of the symmetric rank 2k operations
     *
     *     C := alpha*A*B**T + alpha*B*A**T + beta*C,
     *
     *  or
     *
     *     C := alpha*A**T*B + alpha*B**T*A + beta*C,
     *
     *  where  alpha and beta  are scalars, C is an  n by n  symmetric matrix
     *  and  A and B  are  n by k  matrices  in the  first  case  and  k by n
     *  matrices in the second case.
     * */
    public static native void dsyr2k(int layout, int uplo, int trans, int n, int k, double alpha, double[] A, int LDA, double[] B, int LDB, double beta, double[] C, int LDC);

    /**
     * Performs one of the matrix-matrix operations
     *
     *     B := alpha*op( A )*B,   or   B := alpha*B*op( A ),
     *
     *  where  alpha  is a scalar,  B  is an m by n matrix,  A  is a unit, or
     *  non-unit,  upper or lower triangular matrix  and  op( A )  is one  of
     *
     *     op( A ) = A   or   op( A ) = A**T.
     * */
    public static native void dtrmm(int layout, int side, int uplo, int trans, int diag, int m, int n, double alpha, double[] A, int LDA, double[] B, int LDB);

    /**
     * Solves one of the matrix equations
     *
     *     op( A )*X = alpha*B,   or   X*op( A ) = alpha*B,
     *
     *  where alpha is a scalar, X and B are m by n matrices, A is a unit, or
     *  non-unit,  upper or lower triangular matrix  and  op( A )  is one  of
     *
     *     op( A ) = A   or   op( A ) = A**T.
     *
     *  The matrix X is overwritten on B.
     * */
    public static native void dtrsm(int layout, int side, int uplo, int trans, int diag, int m, int n, double alpha, double[] A, int LDA, double[] B, int LDB);
}



