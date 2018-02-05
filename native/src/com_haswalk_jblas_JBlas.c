
#include <jni.h>
#include <assert.h>
#include "mkl_cblas.h"

#ifndef _Included_com_haswalk_jblas_JBlas
#define _Included_com_haswalk_jblas_JBlas
#ifdef __cplusplus
extern "C" {
#endif

/*
 * Class:     com_haswalk_jblas_JBlas
 * Method:    daxpy
 * Signature: (ID[DI[DI)V
 */
JNIEXPORT void JNICALL Java_com_haswalk_jblas_JBlas_daxpy
  (JNIEnv * env, jclass clazz, jint n, jdouble alpha, jdoubleArray X, jint incX, jdoubleArray Y, jint incY){
       jdouble *xElems, *yElems;
       xElems = (*env) -> GetDoubleArrayElements(env, X, NULL);
       yElems = (*env) -> GetDoubleArrayElements(env, Y, NULL);
       assert(xElems && yElems);
       cblas_daxpy((int)n, (double) alpha, xElems, (int)incX, yElems, (int)incY);

       (*env) -> ReleaseDoubleArrayElements(env, X, xElems, JNI_ABORT);
       (*env) -> ReleaseDoubleArrayElements(env, Y, yElems, 0);
  }

/*
 * Class:     com_haswalk_jblas_JBlas
 * Method:    ddot
 * Signature: (I[DI[DI)V
 */
JNIEXPORT jdouble JNICALL Java_com_haswalk_jblas_JBlas_ddot
  (JNIEnv * env, jclass clazz, jint n, jdoubleArray X, jint incX, jdoubleArray Y, jint incY) {
	  jdouble *xElems, *yElems;
	  xElems = (*env) -> GetDoubleArrayElements(env, X, NULL);
	  yElems = (*env) -> GetDoubleArrayElements(env, Y, NULL);
	  assert(xElems && yElems);
	  
	  double result = cblas_ddot((int)n, xElems, (int) incX, yElems, (int) incY);
	  (*env) -> ReleaseDoubleArrayElements(env, X, xElems, JNI_ABORT);
	  (*env) -> ReleaseDoubleArrayElements(env, Y, yElems, JNI_ABORT);
	  
	  return result;
  }

/*
 * Class:     com_haswalk_jblas_JBlas
 * Method:    dnrm2
 * Signature: (I[DI)D
 */
JNIEXPORT jdouble JNICALL Java_com_haswalk_jblas_JBlas_dnrm2
  (JNIEnv * env, jclass clazz, jint n, jdoubleArray X, jint incX){
	  jdouble *xElems = (*env) -> GetDoubleArrayElements(env, X, NULL);
	  assert(xElems);
	  
	  double result = cblas_dnrm2((int)n, xElems, (int) incX);
	  (*env) -> ReleaseDoubleArrayElements(env, X, xElems, JNI_ABORT);
	  
	  return result;
  }
  
  /*
 * Class:     com_haswalk_jblas_JBlas
 * Method:    dasum
 * Signature: (I[DI)D
 */
JNIEXPORT jdouble JNICALL Java_com_haswalk_jblas_JBlas_dasum
  (JNIEnv * env, jclass clazz, jint n, jdoubleArray X, jint incX){
	  jdouble *xElems = (*env) -> GetDoubleArrayElements(env, X, NULL);
	  assert(xElems);
	  
	  double result = cblas_dasum((int)n, xElems, (int) incX);
	  (*env) -> ReleaseDoubleArrayElements(env, X, xElems, JNI_ABORT);
	  
	  return result;
	  
  }

  /*
   * Class:     com_haswalk_jblas_JBlas
   * Method:    dspr
   * Signature: (IIID[DI[DI)D
   */
  JNIEXPORT jdouble JNICALL Java_com_haswalk_jblas_JBlas_dsyr
    (JNIEnv * env, jclass clazz, jint layout, jint uplo, jint n, jdouble alpha, jdoubleArray X, jint incX, jdoubleArray A, jint LDA) {
      jdouble *xElems = (*env) -> GetDoubleArrayElements(env, X, NULL);
      jdouble *aElems = (*env) -> GetDoubleArrayElements(env, A, NULL);
      assert(xElems && aElems);

      cblas_dsyr((int)layout, (int)uplo, (int)n, (double) alpha, xElems, (int)incX, aElems, LDA);
      (*env) -> ReleaseDoubleArrayElements(env, A, aElems, 0);
      (*env) -> ReleaseDoubleArrayElements(env, X, xElems, JNI_ABORT);

  }

/*
 * Class:     com_haswalk_jblas_JBlas
 * Method:    dspr
 * Signature: (IIID[DI[D)D
 */
JNIEXPORT jdouble JNICALL Java_com_haswalk_jblas_JBlas_dspr
  (JNIEnv * env, jclass clazz, jint layout, jint uplo, jint n, jdouble alpha, jdoubleArray X, jint incX, jdoubleArray Ap){
        jdouble *xElems = (*env) -> GetDoubleArrayElements(env, X, NULL);
        jdouble *aElems = (*env) -> GetDoubleArrayElements(env, Ap, NULL);
        assert(xElems && aElems);

        cblas_dspr((int)layout, (int)uplo, (int)n, (double) alpha, xElems, (int)incX, aElems);
        (*env) -> ReleaseDoubleArrayElements(env, Ap, aElems, 0);
        (*env) -> ReleaseDoubleArrayElements(env, X, xElems, JNI_ABORT);}

/*
 * Class:     com_haswalk_jblas_JBlas
 * Method:    dgemm
 * Signature: (IIIIIID[DI[DID[DI)V
 */
JNIEXPORT void JNICALL Java_com_haswalk_jblas_JBlas_dgemm
  (JNIEnv * env, jclass clazz, jint order, jint transA, jint transB, jint m, jint n, jint k, 
  jdouble alpha, jdoubleArray A, jint LDA, jdoubleArray B, jint LDB, jdouble beta, jdoubleArray C, jint LDC){
	  
	jdouble *aElems, *bElems, *cElems;
	aElems = (*env) -> GetDoubleArrayElements(env, A, NULL);
	bElems = (*env) -> GetDoubleArrayElements(env, B, NULL);
	cElems = (*env) -> GetDoubleArrayElements(env, C, NULL);
	assert(aElems && bElems && cElems);
	
	cblas_dgemm((CBLAS_ORDER) order, (CBLAS_TRANSPOSE) transA, 
	(CBLAS_TRANSPOSE) transB, (int)m, (int)n, (int)k, alpha, aElems, (int)LDA, bElems, (int)LDB, beta, cElems, (int)LDC);
	(*env) -> ReleaseDoubleArrayElements(env, C, cElems, 0);
	(*env) -> ReleaseDoubleArrayElements(env, B, bElems, JNI_ABORT);
	(*env) -> ReleaseDoubleArrayElements(env, A, aElems, JNI_ABORT);

  }
  
/*
 * Class:     com_haswalk_jblas_JBlas
 * Method:    dsymm
 * Signature: (IIIIID[DI[DID[DI)V
 */
JNIEXPORT void JNICALL Java_com_haswalk_jblas_JBlas_dsymm
  (JNIEnv * env, jclass clazz, jint order, jint side, jint uplo, jint m, jint n, jdouble alpha, 
  jdoubleArray A, jint LDA, jdoubleArray B, jint LDB, jdouble beta, jdoubleArray C, jint LDC) {
	  
	jdouble *aElems, *bElems, *cElems;
	aElems = (*env) -> GetDoubleArrayElements(env, A, NULL);
	bElems = (*env) -> GetDoubleArrayElements(env, B, NULL);
	cElems = (*env) -> GetDoubleArrayElements(env, C, NULL);
	assert(aElems && bElems && cElems);
	
	cblas_dsymm((CBLAS_ORDER) order, (CBLAS_SIDE) side, (CBLAS_UPLO) uplo, (int) m, (int) n, alpha, aElems, (int) LDA,
	bElems, (int) LDB, beta, cElems, (int) LDC);
	
	(*env) -> ReleaseDoubleArrayElements(env, C, cElems, 0);
	(*env) -> ReleaseDoubleArrayElements(env, B, bElems, JNI_ABORT);
	(*env) -> ReleaseDoubleArrayElements(env, A, aElems, JNI_ABORT);

  }


#ifdef __cplusplus
}
#endif
#endif
