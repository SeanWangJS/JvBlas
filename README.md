# JvBlas

## Introduction

JvBlas is a lightweight Java&trade; binding for BLAS library API based on Intel&reg; Math Kernel Library.

## Usage

For users, One need to first install [Intel&reg; Math Kernel Library](https://software.intel.com/en-us/mkl), and then add the paths of  "include" and "lib" folders in the MKL to System Environment Variable PATH.

For developers, the .h .c and .o files are in the .\native\src folder, to generate the header file, use
```
javac .\src\main\java\com\haswalk\jblas\JBlas.java -h .\lib
```
And then edit com_haswalk_JBlas.c file to implement new functions. Finally, use the following command to generate dynamic link library.
```
gcc -c .\native\src\com_haswalk_jblas_JBlas.c -o .\native\src\com_haswalk_jblas_JBlas.o -I "%MKL_ROOT%\include" -I "%JAVA_HOME%\include" -I "%JAVA_HOME%\include\win32"
gcc -shared -o .\native\lib\com_haswalk_jblas_JBlas.dll .\native\src\com_haswalk_jblas_JBlas.o -L "%MKL_ROOT%\lib\intel64_win" -lmkl_rt
```
