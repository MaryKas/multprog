import java.util.AbstractCollection;
import java.util.concurrent.BlockingQueue;

public class DeleteFromBlockingQueueRunner extends QueueAbstractRunner {

    public DeleteFromBlockingQueueRunner(AbstractCollection queue, Integer count,int loopSize) {
        super(queue,count,loopSize);
    }

    @Override
    protected void addOrDeleteQueueElement(int element) {

            ((BlockingQueue)queue).poll();

    }

}
