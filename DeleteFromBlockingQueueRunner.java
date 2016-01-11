import java.util.AbstractCollection;
import java.util.concurrent.BlockingQueue;

public class DeleteFromBlockingQueueRunner extends QueueAbstractRunner {

    public DeleteFromBlockingQueueRunner(AbstractCollection queue, Integer count,int loopSize,String resultMeasure) {
        super(queue,count,loopSize,resultMeasure);
    }

    @Override
    protected void addOrDeleteQueueElement(int element) {
        if (queue.getClass()==OwnArrayBlockingQueue.class)
            ((OwnArrayBlockingQueue)queue).poll();
        else
            ((BlockingQueue)queue).poll();


    }

}
