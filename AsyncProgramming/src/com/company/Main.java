package com.company;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class Factorial implements Callable<Long>
{
    private Integer num;

    public Factorial(Integer num) {
        this.num = num;
    }
    //@Override
    public Long call() throws Exception {
        Long result= Long.valueOf(1);
        for (int i = 1; i <= num; i++) {
           result *= i;
        }
        return result;
    }
}

public class Main {

    public static void main(String[] args) {
	// write your code here
        int cores = Runtime.getRuntime().availableProcessors();
        ExecutorService service = Executors.newFixedThreadPool(cores);
        List<Future<Long>> allFutures = new ArrayList<>();

        for (int i = 15; i >= 1; i--) {
            Factorial fact = new Factorial(i);
            Future<Long> future = service.submit(fact);
            allFutures.add(future);
        }

        for (int i = 0; i < 15; i++) {
            Future<Long> future = allFutures.get(i);
            try
            {
                Long result = future.get();
                System.out.println(result);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}
