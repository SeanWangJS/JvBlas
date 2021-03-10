
#include <stdlib.h>
#include <jni.h>
#include <assert.h>
#include <mkl_cblas.h>


#ifndef _Included_com_haswalk_jvblas_JvBlas
#define _Included_com_haswalk_jvblas_JvBlas
#ifdef __cplusplus
extern "C" {
#endif

/********************************************************************************/
/**********************************Leve 1****************************************/
/********************************************************************************/


JNIEXPORT jdoubleArray JNICALL Java_com_haswalk_jvblas_JvBlas_drotg
  (JNIEnv * env, jclass clazz, jdouble da, jdouble db) {

     double cda = (double)da;
     double cdb = (double)db;
     double cc;
     double cs;

     cblas_drotg(&cda, &cdb, &cc, &cs);
     jdoubleArray result;
     result=(*env) -> NewDoubleArray(env, 4);
     if(result == NULL) {
        return NULL;
     }

     jdouble temp[4] = {cda, cdb, cc, cs};
     (*env) -> SetDoubleArrayRegion(env, result, 0, 4, temp);
     return result;

  }


JNIEXPORT jdoubleArray JNICALL Java_com_haswalk_jvblas_JvBlas_drotmg
  (JNIEnv * env, jclass clazz, jdouble jdd1, jdouble jdd2, jdouble jdx1, jdouble jdy1) {

      double dd1 = (double)jdd1;
      double dd2 = (double)jdd2;
      double dx1 = (double)jdx1;
      double dy1 = (double)jdy1;

      double params[5] = {0.0, 0.0, 0.0, 0.0, 0.0};
      cblas_drotmg(&dd1, &dd2, &dx1, dy1, params);

      jdoubleArray result;
      result=(*env) -> NewDoubleArray(env, 5);
      if(result == NULL) {
         return NULL;
      }

      (*env) -> SetDoubleArrayRegion(env, result, 0, 5, params);
      return result;
  }


JNIEXPORT void JNICALL Java_com_haswalk_jvblas_JvBlas_drot
  (JNIEnv * env, jclass clazz, jint n, jdoubleArray dx, jint incX, jdoubleArray dy, jint incY, jdouble c, jdouble s) {
    jdouble *dxElems, *dyElems;
    dxElems = (*env) -> GetDoubleArrayElements(env, dx, NULL);
    dyElems = (*env) -> GetDoubleArrayElements(env, dy, NULL);

    assert(dxElems && dyElems);

    cblas_drot((int)n, dxElems, (int)incX, dyElems, (int)incY, (double)c, (double)s);

    (*env) -> ReleaseDoubleArrayElements(env, dx, dxElems, 0);
    (*env) -> ReleaseDoubleArrayElements(env, dy, dyElems, 0);
    
  }


JNIEXPORT void JNICALL Java_com_haswalk_jvblas_JvBlas_drotm
  (JNIEnv * env, jclass clazz, jint n, jdoubleArray dx, jint incX, jdoubleArray dy, jint incY, jdoubleArray dParams) {

    jdouble *dxElems, *dyElems, *dParamsElems;
    dxElems = (*env) -> GetDoubleArrayElements(env, dx, NULL);
    dyElems = (*env) -> GetDoubleArrayElements(env, dy, NULL);
    dParamsElems = (*env) -> GetDoubleArrayElements(env, dParams, NULL);

    assert(dxElems && dyElems && dParamsElems);

    cblas_drotm((int)n, dxElems, (int)incX, dyElems, (int)incY, dParamsElems);

    (*env) -> ReleaseDoubleArrayElements(env, dx, dxElems, 0);
    (*env) -> ReleaseDoubleArrayElements(env, dy, dyElems, 0);
    (*env) -> ReleaseDoubleArrayElements(env, dParams, dParamsElems, JNI_ABORT);

  }


JNIEXPORT void JNICALL Java_com_haswalk_jvblas_JvBlas_dswap
  (JNIEnv * env, jclass clazz, jint n, jdoubleArray dx, jint incX, jdoubleArray dy, jint incY) {

    jdouble *dxElems, *dyElems;
    dxElems = (*env) -> GetDoubleArrayElements(env, dx, NULL);
    dyElems=  (*env) -> GetDoubleArrayElements(env, dy, NULL);

    assert(dxElems && dyElems);

    cblas_dswap((int)n, dxElems, (int)incX, dyElems, (int)incY);

    (*env) -> ReleaseDoubleArrayElements(env, dx, dxElems, 0);
    (*env) -> ReleaseDoubleArrayElements(env, dy, dyElems, 0);

  }


JNIEXPORT void JNICALL Java_com_haswalk_jvblas_JvBlas_dscal
  (JNIEnv * env, jclass clazz, jint n, jdouble da, jdoubleArray dx, jint incX) {

    jdouble *dxElems = (*env) -> GetDoubleArrayElements(env, dx, NULL);
    assert(dxElems);

    cblas_dscal((int)n, (double) da, dxElems, (int)incX);

    (*env) -> ReleaseDoubleArrayElements(env, dx, dxElems, 0);

  }


JNIEXPORT void JNICALL Java_com_haswalk_jvblas_JvBlas_daxpy
  (JNIEnv * env, jclass clazz, jint n, jdouble alpha, jdoubleArray X, jint incX, jdoubleArray Y, jint incY){
       jdouble *xElems, *yElems;
       xElems = (*env) -> GetDoubleArrayElements(env, X, NULL);
       yElems = (*env) -> GetDoubleArrayElements(env, Y, NULL);
       assert(xElems && yElems);
       cblas_daxpy((int)n, (double) alpha, xElems, (int)incX, yElems, (int)incY);

       (*env) -> ReleaseDoubleArrayElements(env, X, xElems, JNI_ABORT);
       (*env) -> ReleaseDoubleArrayElements(env, Y, yElems, 0);
  }


JNIEXPORT jdouble JNICALL Java_com_haswalk_jvblas_JvBlas_ddot
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


JNIEXPORT jdouble JNICALL Java_com_haswalk_jvblas_JvBlas_dnrm2
  (JNIEnv * env, jclass clazz, jint n, jdoubleArray X, jint incX){
	  jdouble *xElems = (*env) -> GetDoubleArrayElements(env, X, NULL);
	  assert(xElems);
	  
	  double result = cblas_dnrm2((int)n, xElems, (int) incX);
	  (*env) -> ReleaseDoubleArrayElements(env, X, xElems, JNI_ABORT);
	  
	  return result;
  }


JNIEXPORT jdouble JNICALL Java_com_haswalk_jvblas_JvBlas_dasum
  (JNIEnv * env, jclass clazz, jint n, jdoubleArray X, jint incX){
	  jdouble *xElems = (*env) -> GetDoubleArrayElements(env, X, NULL);
	  assert(xElems);

	  double result = cblas_dasum((int)n, xElems, (int) incX);
	  (*env) -> ReleaseDoubleArrayElements(env, X, xElems, JNI_ABORT);

	  return result;

  }


JNIEXPORT jint JNICALL Java_com_haswalk_jvblas_JvBlas_idamax
  (JNIEnv * env, jclass clazz, jint n, jdoubleArray dx, jint incX) {

    jdouble *dxElems = (*env) -> GetDoubleArrayElements(env, dx, NULL);
    assert(dxElems);

    cblas_idamax((int)n, dxElems, (int)incX);

    (*env) -> ReleaseDoubleArrayElements(env, dx, dxElems, 0);
  }

/********************************************************************************/
/*********************************Level 2****************************************/
/********************************************************************************/


JNIEXPORT void JNICALL Java_com_haswalk_jvblas_JvBlas_dgemv
  (JNIEnv * env, jclass clazz, jint layout, jint trans, jint m, jint n, jdouble alpha, jdoubleArray A, jint LDA, jdoubleArray X, jint incX, jdouble beta, jdoubleArray Y, jint incY) {

    jdouble *aElems = (*env) -> GetDoubleArrayElements(env, A, NULL);
    jdouble *xElems = (*env) -> GetDoubleArrayElements(env, X, NULL);
    jdouble *yElems = (*env) -> GetDoubleArrayElements(env, Y, NULL);

    assert(aElems && xElems && yElems);

    cblas_dgemv(layout, trans, m, n, alpha, aElems, LDA, xElems, incX, beta, yElems, incY);

    (*env) -> ReleaseDoubleArrayElements(env, A, aElems, JNI_ABORT);
    (*env) -> ReleaseDoubleArrayElements(env, X, xElems, JNI_ABORT);
    (*env) -> ReleaseDoubleArrayElements(env, Y, yElems, 0);

  }


JNIEXPORT void JNICALL Java_com_haswalk_jvblas_JvBlas_dgbmv
  (JNIEnv * env, jclass clazz, jint layout, jint trans, jint m, jint n, jint kl, jint ku, jdouble alpha, jdoubleArray A, jint LDA, jdoubleArray X, jint incX, jdouble beta, jdoubleArray Y, jint incY) {
    jdouble *aElems = (*env) -> GetDoubleArrayElements(env, A, NULL);
    jdouble *xElems = (*env) -> GetDoubleArrayElements(env, X, NULL);
    jdouble *yElems = (*env) -> GetDoubleArrayElements(env, Y, NULL);

    assert(aElems && xElems && yElems);

    cblas_dgbmv(layout, trans, m, n,kl, ku, alpha, aElems, LDA, xElems, incX, beta, yElems, incY);

    (*env) -> ReleaseDoubleArrayElements(env, A, aElems, JNI_ABORT);
    (*env) -> ReleaseDoubleArrayElements(env, X, xElems, JNI_ABORT);
    (*env) -> ReleaseDoubleArrayElements(env, Y, yElems, 0);
}

JNIEXPORT void JNICALL Java_com_haswalk_jvblas_JvBlas_dsymv
  (JNIEnv * env, jclass clazz, jint layout, jint uplo, jint n, jdouble alpha, jdoubleArray A, jint LDA, jdoubleArray X, jint incX, jdouble beta, jdoubleArray Y, jint incY) {

    jdouble *aElems = (*env) -> GetDoubleArrayElements(env, A, NULL);
    jdouble *xElems = (*env) -> GetDoubleArrayElements(env, X, NULL);
    jdouble *yElems = (*env) -> GetDoubleArrayElements(env, Y, NULL);

    assert(aElems && xElems && yElems);

    cblas_dsymv(layout, uplo, n, alpha, aElems, LDA, xElems, incX, beta, yElems, incY);

    (*env) -> ReleaseDoubleArrayElements(env, A, aElems, JNI_ABORT);
    (*env) -> ReleaseDoubleArrayElements(env, X, xElems, JNI_ABORT);
    (*env) -> ReleaseDoubleArrayElements(env, Y, yElems, 0);

}


JNIEXPORT void JNICALL Java_com_haswalk_jvblas_JvBlas_dsbmv
  (JNIEnv * env, jclass clazz, jint layout, jint uplo, jint n, jint k, jdouble alpha, jdoubleArray A, jint LDA, jdoubleArray X, jint incX, jdouble beta, jdoubleArray Y, jint incY) {

    jdouble *aElems = (*env) -> GetDoubleArrayElements(env, A, NULL);
    jdouble *xElems = (*env) -> GetDoubleArrayElements(env, X, NULL);
    jdouble *yElems = (*env) -> GetDoubleArrayElements(env, Y, NULL);

    assert(aElems && xElems && yElems);

    cblas_dsbmv(layout, uplo, n, k, alpha, aElems, LDA, xElems, incX, beta, yElems, incY);

    (*env) -> ReleaseDoubleArrayElements(env, A, aElems, JNI_ABORT);
    (*env) -> ReleaseDoubleArrayElements(env, X, xElems, JNI_ABORT);
    (*env) -> ReleaseDoubleArrayElements(env, Y, yElems, 0);

  }


JNIEXPORT void JNICALL Java_com_haswalk_jvblas_JvBlas_dspmv
  (JNIEnv * env, jclass clazz, jint layout, jint uplo, jint n, jdouble alpha, jdoubleArray AP, jdoubleArray X, jint incX, jdouble beta, jdoubleArray Y, jint incY) {

    jdouble *aElems = (*env) -> GetDoubleArrayElements(env, AP, NULL);
    jdouble *xElems = (*env) -> GetDoubleArrayElements(env, X, NULL);
    jdouble *yElems = (*env) -> GetDoubleArrayElements(env, Y, NULL);

    assert(aElems && xElems && yElems);

    cblas_dspmv(layout, uplo, n, alpha, aElems, xElems, incX, beta, yElems, incY);

    (*env) -> ReleaseDoubleArrayElements(env, AP, aElems, JNI_ABORT);
    (*env) -> ReleaseDoubleArrayElements(env, X, xElems, JNI_ABORT);
    (*env) -> ReleaseDoubleArrayElements(env, Y, yElems, 0);
  }


JNIEXPORT void JNICALL Java_com_haswalk_jvblas_JvBlas_dtrmv
  (JNIEnv * env, jclass clazz, jint layout, jint trans, jint uplo, jint diag, jint n, jdoubleArray A, jint LDA, jdoubleArray X, jint incX) {

    jdouble *aElems = (*env) -> GetDoubleArrayElements(env, A, NULL);
    jdouble *xElems = (*env) -> GetDoubleArrayElements(env, X, NULL);

    assert(aElems && xElems);

    cblas_dtrmv(layout, uplo, trans, diag, n, aElems, LDA, xElems, incX);

    (*env) -> ReleaseDoubleArrayElements(env, A, aElems, JNI_ABORT);
    (*env) -> ReleaseDoubleArrayElements(env, X, xElems, 0);

  }


JNIEXPORT void JNICALL Java_com_haswalk_jvblas_JvBlas_dtbmv
  (JNIEnv * env, jclass clazz, jint layout, jint uplo, jint trans, jint diag, jint n, jint k, jdoubleArray A, jint LDA, jdoubleArray X, jint incX) {

    jdouble *aElems = (*env) -> GetDoubleArrayElements(env, A, NULL);
    jdouble *xElems = (*env) -> GetDoubleArrayElements(env, X, NULL);

    assert(aElems && xElems);

    cblas_dtbmv(layout, uplo, trans, diag, n, k, aElems, LDA, xElems, incX);

    (*env) -> ReleaseDoubleArrayElements(env, A, aElems, JNI_ABORT);
    (*env) -> ReleaseDoubleArrayElements(env, X, xElems, 0);
  }


JNIEXPORT void JNICALL Java_com_haswalk_jvblas_JvBlas_dtpmv
  (JNIEnv * env, jclass clazz, jint layout, jint trans, jint uplo, jint diag, jint n, jdoubleArray AP, jdoubleArray X, jint incX) {

    jdouble *aElems = (*env) -> GetDoubleArrayElements(env, AP, NULL);
    jdouble *xElems = (*env) -> GetDoubleArrayElements(env, X, NULL);

    assert(aElems && xElems);

    cblas_dtpmv(layout, uplo, trans, diag, n, aElems, xElems, incX);

    (*env) -> ReleaseDoubleArrayElements(env, AP, aElems, JNI_ABORT);
    (*env) -> ReleaseDoubleArrayElements(env, X, xElems, 0);
  }


JNIEXPORT void JNICALL Java_com_haswalk_jvblas_JvBlas_dtrsv
  (JNIEnv * env, jclass clazz, jint layout, jint uplo, jint trans, jint diag, jint n, jdoubleArray A, jint LDA, jdoubleArray X, jint incX) {
    jdouble *aElems = (*env) -> GetDoubleArrayElements(env, A, NULL);
    jdouble *xElems = (*env) -> GetDoubleArrayElements(env, X, NULL);

    assert(aElems && xElems);

    cblas_dtrsv(layout, uplo, trans, diag, n, aElems, LDA, xElems, incX);

    (*env) -> ReleaseDoubleArrayElements(env, A, aElems, JNI_ABORT);
    (*env) -> ReleaseDoubleArrayElements(env, X, xElems, 0);
 }

JNIEXPORT void JNICALL Java_com_haswalk_jvblas_JvBlas_dtbsv
  (JNIEnv * env, jclass clazz, jint layout, jint uplo, jint trans, jint diag, jint n, jint k, jdoubleArray A, jint LDA, jdoubleArray X, jint incX) {
    jdouble *aElems = (*env) -> GetDoubleArrayElements(env, A, NULL);
    jdouble *xElems = (*env) -> GetDoubleArrayElements(env, X, NULL);

    assert(aElems && xElems);

    cblas_dtbsv(layout, uplo, trans, diag, n, k, aElems, LDA, xElems, incX);

    (*env) -> ReleaseDoubleArrayElements(env, A, aElems, JNI_ABORT);
    (*env) -> ReleaseDoubleArrayElements(env, X, xElems, 0);
  }

JNIEXPORT void JNICALL Java_com_haswalk_jvblas_JvBlas_dtpsv
  (JNIEnv * env, jclass clazz, jint layout, jint uplo, jint trans, jint diag, jint n, jdoubleArray AP, jdoubleArray X, jint incX) {
    jdouble *aElems = (*env) -> GetDoubleArrayElements(env, AP, NULL);
    jdouble *xElems = (*env) -> GetDoubleArrayElements(env, X, NULL);

    assert(aElems && xElems);

    cblas_dtpsv(layout, uplo, trans, diag, n, aElems, xElems, incX);

    (*env) -> ReleaseDoubleArrayElements(env, AP, aElems, JNI_ABORT);
    (*env) -> ReleaseDoubleArrayElements(env, X, xElems, 0);
  }

JNIEXPORT void JNICALL Java_com_haswalk_jvblas_JvBlas_dger
  (JNIEnv * env, jclass clazz, jint layout, jint m, jint n, jdouble alpha, jdoubleArray X, jint incX, jdoubleArray Y, jint incY, jdoubleArray A, jint LDA) {

    jdouble *aElems = (*env) -> GetDoubleArrayElements(env, A, NULL);
    jdouble *xElems = (*env) -> GetDoubleArrayElements(env, X, NULL);
    jdouble *yElems = (*env) -> GetDoubleArrayElements(env, Y, NULL);

    assert(aElems && xElems && yElems);

    cblas_dger(layout, m, n, alpha, xElems, incX, yElems, incY, aElems, LDA);

    (*env) -> ReleaseDoubleArrayElements(env, A, aElems, 0);
    (*env) -> ReleaseDoubleArrayElements(env, X, xElems, JNI_ABORT);
    (*env) -> ReleaseDoubleArrayElements(env, Y, yElems, JNI_ABORT);
  }

  
JNIEXPORT jdouble JNICALL Java_com_haswalk_jvblas_JvBlas_dsyr
    (JNIEnv * env, jclass clazz, jint layout, jint uplo, jint n, jdouble alpha, jdoubleArray X, jint incX, jdoubleArray A, jint LDA) {
      jdouble *xElems = (*env) -> GetDoubleArrayElements(env, X, NULL);
      jdouble *aElems = (*env) -> GetDoubleArrayElements(env, A, NULL);
      assert(xElems && aElems);

      cblas_dsyr((int)layout, (int)uplo, (int)n, (double) alpha, xElems, (int)incX, aElems, LDA);
      (*env) -> ReleaseDoubleArrayElements(env, A, aElems, 0);
      (*env) -> ReleaseDoubleArrayElements(env, X, xElems, JNI_ABORT);

  }


JNIEXPORT jdouble JNICALL Java_com_haswalk_jvblas_JvBlas_dspr
  (JNIEnv * env, jclass clazz, jint layout, jint uplo, jint n, jdouble alpha, jdoubleArray X, jint incX, jdoubleArray Ap){
        jdouble *xElems = (*env) -> GetDoubleArrayElements(env, X, NULL);
        jdouble *aElems = (*env) -> GetDoubleArrayElements(env, Ap, NULL);
        assert(xElems && aElems);

        cblas_dspr((int)layout, (int)uplo, (int)n, (double) alpha, xElems, (int)incX, aElems);
        (*env) -> ReleaseDoubleArrayElements(env, Ap, aElems, 0);
        (*env) -> ReleaseDoubleArrayElements(env, X, xElems, JNI_ABORT);
  }

JNIEXPORT void JNICALL Java_com_haswalk_jvblas_JvBlas_dsyr2
  (JNIEnv * env, jclass clazz, jint layout, jint uplo, jint n, jdouble alpha, jdoubleArray X, jint incX, jdoubleArray Y, jint incY, jdoubleArray A, jint LDA) {
    jdouble *aElems = (*env) -> GetDoubleArrayElements(env, A, NULL);
    jdouble *xElems = (*env) -> GetDoubleArrayElements(env, X, NULL);
    jdouble *yElems = (*env) -> GetDoubleArrayElements(env, Y, NULL);

    assert(aElems && xElems && yElems);

    cblas_dsyr2(layout, uplo, n, alpha, xElems, incX, yElems, incY, aElems, LDA);

    (*env) -> ReleaseDoubleArrayElements(env, A, aElems, 0);
    (*env) -> ReleaseDoubleArrayElements(env, X, xElems, JNI_ABORT);
    (*env) -> ReleaseDoubleArrayElements(env, Y, yElems, JNI_ABORT);
  }

JNIEXPORT void JNICALL Java_com_haswalk_jvblas_JvBlas_dspr2
  (JNIEnv * env, jclass clazz, jint layout, jint uplo, jint n, jdouble alpha, jdoubleArray X, jint incX, jdoubleArray Y, jint incY, jdoubleArray AP) {
    jdouble *aElems = (*env) -> GetDoubleArrayElements(env, AP, NULL);
    jdouble *xElems = (*env) -> GetDoubleArrayElements(env, X, NULL);
    jdouble *yElems = (*env) -> GetDoubleArrayElements(env, Y, NULL);

    assert(aElems && xElems && yElems);

    cblas_dspr2(layout, uplo, n, alpha, xElems, incY, yElems, incY, aElems);

    (*env) -> ReleaseDoubleArrayElements(env, AP, aElems, 0);
    (*env) -> ReleaseDoubleArrayElements(env, X, xElems, JNI_ABORT);
    (*env) -> ReleaseDoubleArrayElements(env, Y, yElems, JNI_ABORT);
  }

/********************************************************************************/
/*********************************Level 2****************************************/
/********************************************************************************/


JNIEXPORT void JNICALL Java_com_haswalk_jvblas_JvBlas_dgemm
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

 JNIEXPORT void JNICALL Java_com_haswalk_jvblas_JvBlas_dsymm
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

 JNIEXPORT void JNICALL Java_com_haswalk_jvblas_JvBlas_dsyrk
   (JNIEnv * env, jclass clazz, jint layout, jint uplo, jint trans, jint n, jint k, jdouble alpha, jdoubleArray A, jint LDA, jdouble beta, jdoubleArray C, jint LDC) {

     jdouble *aElems = (*env) -> GetDoubleArrayElements(env, A, NULL);
     jdouble *cElems = (*env) -> GetDoubleArrayElements(env, C, NULL);

     assert(aElems && cElems);

     cblas_dsyrk(layout, uplo, trans, n, k, alpha, aElems, LDA, beta, cElems, LDC);

     (*env) -> ReleaseDoubleArrayElements(env, A, aElems, JNI_ABORT);
     (*env) -> ReleaseDoubleArrayElements(env, C, cElems, 0);
     
   }


 JNIEXPORT void JNICALL Java_com_haswalk_jvblas_JvBlas_dsyr2k
   (JNIEnv * env, jclass clazz, jint layout, jint uplo, jint trans, jint n, jint k, jdouble alpha, jdoubleArray A, jint LDA, jdoubleArray B, jint LDB, jdouble beta, jdoubleArray C, jint LDC) {

     jdouble *aElems = (*env) -> GetDoubleArrayElements(env, A, NULL);
     jdouble *bElems = (*env) -> GetDoubleArrayElements(env, B, NULL);
     jdouble *cElems = (*env) -> GetDoubleArrayElements(env, C, NULL);

     assert(aElems && bElems && cElems);

     cblas_dsyr2k(layout, uplo, trans, n, k, alpha, aElems, LDA, bElems, LDB, beta, cElems, LDC);

     (*env) -> ReleaseDoubleArrayElements(env, A, aElems, JNI_ABORT);
     (*env) -> ReleaseDoubleArrayElements(env, A, bElems, JNI_ABORT);
     (*env) -> ReleaseDoubleArrayElements(env, C, cElems, 0);

   }


 JNIEXPORT void JNICALL Java_com_haswalk_jvblas_JvBlas_dtrmm
   (JNIEnv * env, jclass clazz, jint layout, jint side, jint uplo, jint trans, jint diag, jint m, jint n, jdouble alpha, jdoubleArray A, jint LDA, jdoubleArray B, jint LDB) {

     jdouble *aElems = (*env) -> GetDoubleArrayElements(env, A, NULL);
     jdouble *bElems = (*env) -> GetDoubleArrayElements(env, B, NULL);

     assert(aElems && bElems);

     cblas_dtrmm(layout, side, uplo, trans, diag, m, n, alpha, aElems, LDA, bElems, LDB);

     (*env) -> ReleaseDoubleArrayElements(env, A, aElems, JNI_ABORT);
     (*env) -> ReleaseDoubleArrayElements(env, B, bElems, 0);

   }


 JNIEXPORT void JNICALL Java_com_haswalk_jvblas_JvBlas_dtrsm
   (JNIEnv * env, jclass clazz, jint layout, jint side, jint uplo, jint trans, jint diag, jint m, jint n, jdouble alpha, jdoubleArray A, jint LDA, jdoubleArray B, jint LDB) {

     jdouble *aElems = (*env) -> GetDoubleArrayElements(env, A, NULL);
     jdouble *bElems = (*env) -> GetDoubleArrayElements(env, B, NULL);

     assert(aElems && bElems);

     cblas_dtrsm(layout, side, uplo, trans, diag, m, n, alpha, aElems, LDA, bElems, LDB);

     (*env) -> ReleaseDoubleArrayElements(env, A, aElems, JNI_ABORT);
     (*env) -> ReleaseDoubleArrayElements(env, B, bElems, 0);

   }





#ifdef __cplusplus
}
#endif
#endif
