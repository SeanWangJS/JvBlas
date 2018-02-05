package com.haswalk.jblas.benchmark;

import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

/**
 * Created by wangx on 2018/1/31.
 */
public class Main {

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(DgemmBenchmark.class.getSimpleName())
                .forks(1)
                .build();
        new Runner(opt).run();
    }

}
