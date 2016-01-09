import java.util.*;
import java.util.concurrent.*;


public class Main {

    static  int NTHREADS = 3;
    static final int SIZE = 1500000;
    static String operationType="add";
    static String resultMeasure = "latency";


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
            operationType = args[2];
            if (operationType=="pop"){
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

                case "1":case "2":case "3":
                    if(operationType=="add")
                        worker = new AddToBlockingQueueRunner(queue, i + 1, SIZE, resultMeasure);
                    else
                        worker = new DeleteFromBlockingQueueRunner(queue, i + 1, SIZE, resultMeasure);
                        break;
                default:
                    if(operationType=="add")
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


