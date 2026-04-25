package ro.ase.acs.multitheading;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {
        int[] values = new int[200_000_000];

        for(int i=0; i<values.length; i++){
            values[i] = i + 1;
        }

        long startTime = System.currentTimeMillis();

        long sum = 0;
        for(int i=0; i<values.length; i++){
            sum += values[i];
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Single thread sum = " + sum + " computed in " + (endTime - startTime) + " ms");

        final int NB_OF_THREADS = 4;

        startTime = System.currentTimeMillis();
        sum = 0;
        SummingThread[] threads = new SummingThread[NB_OF_THREADS];
        for(int i=0; i<NB_OF_THREADS; i++){
            threads[i] = new SummingThread(values, (values.length / NB_OF_THREADS) * i,
                    values.length / NB_OF_THREADS * (i+1));
        }
        for(int i=0; i<NB_OF_THREADS; i++){
            threads[i].start();
        }
        for(int i=0; i<NB_OF_THREADS; i++){
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            sum += threads[i].getSum();
        }

        endTime = System.currentTimeMillis();
        System.out.println("Thread array sum = " + sum + " computed in " + (endTime - startTime) + " ms");

        startTime = System.currentTimeMillis();
        sum = 0;

        ExecutorService executor = Executors.newFixedThreadPool(NB_OF_THREADS);
        threads = new SummingThread[NB_OF_THREADS];
        for(int i=0; i<NB_OF_THREADS; i++){
            threads[i] = new SummingThread(values, (values.length / NB_OF_THREADS) * i,
                    values.length / NB_OF_THREADS * (i+1));
        }
        for(int i=0; i<NB_OF_THREADS; i++){
            executor.submit(threads[i]);
        }
        executor.shutdown();
        try {
            executor.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for(int i=0; i<NB_OF_THREADS; i++){
            sum += threads[i].getSum();
        }

        endTime = System.currentTimeMillis();
        System.out.println("Threadpool sum = " + sum + " computed in " + (endTime - startTime) + " ms");

        startTime = System.currentTimeMillis();
        sum = 0;

        List<Future<Long>> results = new ArrayList<>();
        executor = Executors.newFixedThreadPool(NB_OF_THREADS);
        for(int i = 0; i <  NB_OF_THREADS; i++){
            Future<Long> result = executor.submit(new SummingCallable(values,
                    values.length / NB_OF_THREADS * i,
                    values.length / NB_OF_THREADS * (i+1)));
            results.add(result);
        }
        executor.shutdown();
        try {
            executor.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for(Future<Long> result : results){
            try {
                sum += result.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        endTime = System.currentTimeMillis();
        System.out.println("Callable sum = " + sum + " computed in " + (endTime - startTime) + " ms");
    }
}
