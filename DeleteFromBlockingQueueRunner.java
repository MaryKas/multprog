import java.util.AbstractCollection;
import java.util.concurrent.BlockingQueue;

/**
 * Created by mls on 09.01.16.
 */
public class DeleteFromBlockingQueueRunner extends QueueAbstractRunner {

    public DeleteFromBlockingQueueRunner(AbstractCollection queue, Integer count,int loopSize) {
        super(queue,count,loopSize);
    }

    @Override
    protected void addOrDeleteQueueElement(int element) {

            ((BlockingQueue)queue).poll();

    }

}
