package com.haswalk.jvblas.benchmark;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Thread)
public class ParrallelBenchmark {

    class SumTask extends RecursiveTask<Double> {

        private final int threshold = 1000000;

        private double[] arr;
        private int lb;
        private int rb;

        public SumTask(double[] arr, int lb, int rb) {
            this.arr = arr;
            this.lb = lb;
            this.rb = rb;
        }

        @Override
        protected Double compute() {

            double sum = 0;
            if (rb - lb <= threshold) {
                for (int i = lb; i < rb; i++) {
                    sum += arr[i];
                }
                return sum;
            }

            int mid = (lb + rb) >>> 1;
            SumTask sumTask1 = new SumTask(arr, lb, mid);
            SumTask sumTask2 = new SumTask(arr, mid, rb);

            sumTask1.fork();
            sumTask2.fork();

            sum = sumTask1.join() + sumTask2.join();

            return sum;
        }
    }

    private double[] array = new double[10000000];
    public ParrallelBenchmark() {
        Random random = new Random();
        random.setSeed(100);
        for(int i = 0, len = array.length; i < len; i++) {
            array[i] = random.nextDouble() * 100;
        }

    }

    @Benchmark
    public void run() throws ExecutionException, InterruptedException {
        SumTask sumTask = new SumTask(array, 0, array.length);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        forkJoinPool.submit(sumTask);
        Double result = sumTask.get();
        System.out.println("result: " + result);
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
