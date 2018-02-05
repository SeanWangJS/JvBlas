package com.haswalk.jvblas.benchmark;

import com.haswalk.jvblas.JvBlas;
import com.haswalk.jvblas.JvBlasLayout;
import com.haswalk.jvblas.JvBlasTranspose;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Thread)
public class DgemmBenchmark {

    private double[] A, B, C;
    private int m, n, k;
    private int LDA, LDB, LDC;
    public DgemmBenchmark() {
        m = 2000;
        n = 200;
        k = 1000;
        LDA = k;
        LDB = n;
        LDC = n;

        A = new double[m * k];
        B = new double[k * n];
        for(int i = 0; i < m * LDA; i++) {
            A[i] = i+1;
        }
        for(int i = 0; i < k*LDB; i++) {
            B[i] = -(i-1);
        }

        C = new double[m * n];
    }

    @Benchmark
    public void run() {
        double alpha = 1;
        double beta = 0;
        JvBlas.dgemm(JvBlasLayout.ROW_MAJOR, JvBlasTranspose.NO_TRANS, JvBlasTranspose.NO_TRANS,
                m, n, k, alpha, A, LDA, B, LDB, beta, C, LDC);
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(DgemmBenchmark.class.getSimpleName())
                .forks(1)
                .build();
        new Runner(opt).run();
    }
}
