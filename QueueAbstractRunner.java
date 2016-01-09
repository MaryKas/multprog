import java.util.AbstractCollection;
import java.util.concurrent.Callable;


public abstract class QueueAbstractRunner implements Callable<Long> {

    private final int loopSize;
    private final String resultMeasure;
    private Integer count;
    protected AbstractCollection <String> queue;

    private long resultTime;

    public QueueAbstractRunner(AbstractCollection queue, Integer count, int loopSize, String resultMeasure) {
        this.queue = queue;
        this.count=count;
        this.loopSize=loopSize;
        this.resultMeasure= resultMeasure;
    }

    abstract protected void addOrDeleteQueueElement(int element);

    @Override
    public Long call() {
        if (resultMeasure.equals("latency"))
            return estimateLatency();
        else
            return estimateThroughput();
    }


    public Long estimateLatency(){
        long startTime = System.nanoTime();
        for (int j=0;j<loopSize;j++) {
            addOrDeleteQueueElement(count);
        }
        resultTime = System.nanoTime() - startTime;
        return resultTime;
    }

    public Long estimateThroughput(){
        long counter = 0;
        long startTime = System.nanoTime();
        while(System.nanoTime()-startTime<100000000){
            counter++;
            addOrDeleteQueueElement(count);
        }
        return counter;
    }
}
