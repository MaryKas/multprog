import java.util.AbstractCollection;
import java.util.concurrent.Callable;

/**
 * Created by mls on 08.01.16.
 */
public abstract class AddToQueueAbstractRunner implements Callable<Long> {

    private final int loopSize;
    private Integer count;
    protected AbstractCollection <String> queue;

    private long resultTime;

    public AddToQueueAbstractRunner(AbstractCollection queue, Integer count,int loopSize) {
        this.queue = queue;
        this.count=count;
        this.loopSize=loopSize;
    }

    abstract protected void addElementToQueue(int element);

    @Override
    public Long call() {
        long startTime = System.nanoTime();
        //System.out.println("Started "+ count);
        for (int j=0;j<loopSize;j++) {
                addElementToQueue(count);
        }
                resultTime = System.nanoTime() - startTime;
                //System.out.println(count + " Time = [" + resultTime + "] " );
                return resultTime;
            }
        }
