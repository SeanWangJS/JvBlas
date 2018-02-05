package com.haswalk.jblas.benchmark;

import org.openjdk.jmh.annotations.Benchmark;

/**
 * Created by wangx on 2018/1/31.
 */
public class JMHSample_01 {

    @Benchmark
    public void run() {
        for (int i = 0; i < 100000; i++) {
            double k = i * 1000;
        }

    }

}
