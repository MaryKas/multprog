import java.util.AbstractCollection;
import java.util.concurrent.BlockingQueue;

public class DeleteFromBlockingQueueRunner extends QueueAbstractRunner {

    public DeleteFromBlockingQueueRunner(AbstractCollection queue, Integer count,int loopSize,String resultMeasure) {
        super(queue,count,loopSize,resultMeasure);
    }

    @Override
    protected void addOrDeleteQueueElement(int element) {

            ((BlockingQueue)queue).poll();

    }

}
