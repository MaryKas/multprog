import ownqueues.BDEQueue;
import ownqueues.OwnArrayBlockingQueue;
import queuerunners.AddToConcurrentQueueRunner;
import queuerunners.AddToSimpleQueueRunner;
import queuerunners.DeleteFromConcurrentQueueRunner;
import queuerunners.DeleteFromSimpleQueueRunner;

import java.util.*;
import java.util.concurrent.*;


public class Main {

    static  int NTHREADS = 3;
    static final int SIZE = 100000;
    static String operationType="add";
    static String resultMeasure = "latency";


    public static void main(String[] args) throws InterruptedException {
        long count= 0;
        AbstractCollection<String> queue;

        if (args.length>0){
            NTHREADS = Integer.parseInt(args[1]);
            switch (args[0]){
                case "1":queue = new LinkedBlockingQueue<String>(NTHREADS*SIZE);break;
                case "2":queue = new ArrayBlockingQueue<String>(NTHREADS*SIZE);break;
                case "3":queue = new PriorityBlockingQueue<String>(NTHREADS*SIZE);break;
                case "4":queue = new PriorityQueue<String>(NTHREADS*SIZE);break;
                case "5":queue = new OwnArrayBlockingQueue<String>(NTHREADS*SIZE);break;
                case "6":queue = new BDEQueue<String>(NTHREADS*SIZE);break;
                default: queue = new LinkedList<String>();
            }

            operationType = args[2];
            if (operationType.equals("pop")){
                queue.addAll(Collections.nCopies(NTHREADS*SIZE,"Some String"));
            }
            resultMeasure = args[3];
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

                case "1":case "2":case "3":case "5":case "6":
                    if(operationType.equals("add"))
                        worker = new AddToConcurrentQueueRunner(queue, i + 1, SIZE, resultMeasure);
                    else
                        worker = new DeleteFromConcurrentQueueRunner(queue, i + 1, SIZE, resultMeasure);
                        break;
                default:
                    if(operationType.equals("add"))
                        worker = new AddToSimpleQueueRunner(queue,i+1,SIZE, resultMeasure);
                    else
                        worker = new DeleteFromSimpleQueueRunner(queue, i + 1, SIZE, resultMeasure);
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


