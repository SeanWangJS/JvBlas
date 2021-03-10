package com.haswalk.jvblas.benchmark;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.nio.channels.Channel;
import java.nio.channels.SocketChannel;
import java.util.concurrent.TimeUnit;

/**
 * Created by wangx on 2018/2/5.
 */

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Thread)
public class DgemmBenchmark_java {
    private double[] A, B, C;
    private int m, n, k;
    private int LDA, LDB, LDC;
    public DgemmBenchmark_java() {
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
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                double v = 0;
                for (int l = 0; l < k; l++) {
                    v += A[i * k + l] * B[l * n + j];
                }
                C[i * n + j] = v;
            }
        }
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(DgemmBenchmark_java.class.getSimpleName())
                .warmupIterations(8)
                .measurementIterations(5)
                .forks(1)
                .build();
        new Runner(opt).run();
    }
}
