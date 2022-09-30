import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

public class Main {

    public static void main(String[] args) {

        Long startTime = System.currentTimeMillis();
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);
        List<Future<Integer>> resultList = new ArrayList<>();

        Random random =new Random();
        for (int i = 0; i < 10; i++) {
            int number = random.nextInt(10);
            FactorialCalculator calculator = new FactorialCalculator(number);
            Future<Integer> result = executor.submit(calculator);
            resultList.add(result);
        }

        try {
            while (executor.getCompletedTaskCount() < resultList.size()){
                System.out.printf("\nCompleted Number of Thread: %d\n", executor.getCompletedTaskCount());
                for (int i = 0; i < resultList.size(); i++) {
                    Future<Integer> result = resultList.get(i);
                    System.out.printf("No %d Thread: yes or no completed: %s\n", i, result.isDone());
                }
                Thread.sleep(50);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("All Thread Completed!");

        try {
            for (int i = 0; i < resultList.size(); i++) {
                Future<Integer> result = resultList.get(i);
                Integer number = null;
                number = result.get();
                System.out.printf("No %d Thread Result is: %d\n", i, number);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        executor.shutdown();
        Long endTime = System.currentTimeMillis();
        System.out.println("Processing Time: " + (endTime - startTime));

    }

}
