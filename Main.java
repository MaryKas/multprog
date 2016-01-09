import java.util.*;
import java.util.concurrent.*;

/**
 * Created by mls on 06.01.16.
 */
public class Main {

    static  int NTHREADS = 20;
    static final int SIZE = 100000;

    public static void main(String[] args) throws InterruptedException {
        long count= 0;
        AbstractCollection<String> queue;

        if (args.length>0){
            switch (args[0]){
                case "1":queue = new LinkedBlockingQueue<String>(NTHREADS*SIZE);break;
                case "2":queue = new ArrayBlockingQueue<String>(NTHREADS*SIZE);break;
                case "3":queue = new PriorityBlockingQueue<String>(NTHREADS*SIZE);break;
                case "4":queue = new PriorityQueue<String>(NTHREADS*SIZE);break;
                default: queue = new LinkedList<String>();
            }
            NTHREADS = Integer.parseInt(args[1]);
        }
        else {
            System.out.println("Not enough arguments");
            return;
        }

        ExecutorService executor = Executors.newFixedThreadPool(NTHREADS);

        List<Callable<Long>> threads = new ArrayList<Callable<Long>>();
        for (int i = 0; i < NTHREADS; i++) {
            Callable worker;
            switch (args[0]) {

                case "1":case "2":case "3":worker = new AddToBlockingQueueRunner(queue, i + 1, SIZE);break;
                default: worker = new AddToSimpleQueueRunner(queue,i+1,SIZE);
            }
            threads.add(worker);

        }
        count = executor.invokeAll(threads)
                .stream()
                .map(future -> {
                    try {
                        return future.get();
                    } catch (Exception e) {
                        e.printStackTrace();
                        return 0;
                    }
                })
                .reduce(0, (a, b) -> a.longValue() + b.longValue())
                .longValue();

        executor.shutdown();
        try {
            executor.awaitTermination(10,TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(count/NTHREADS);
        return;


    }
}


