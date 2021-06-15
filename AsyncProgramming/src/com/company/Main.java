package com.company;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

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

    public void FutureImp()
    {
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

    public static void main(String[] args) {
	// write your code here

        /*supplyAsync(): It complete its job asynchronously. The result of supplier is run by a task from ForkJoinPool.commonPool() as default. The supplyAsync() method returns CompletableFuture on which we can apply other methods.
        thenApply(): The method accepts function as an arguments. It returns a new CompletableStage when this stage completes normally. The new stage use as the argument to the supplied function.
        join(): the method returns the result value when complete. It also throws a CompletionException (unchecked exception) if completed exceptionally.*/

        MainController.getData();
        MainController.showData();
    }
}
