package queuerunners;

import java.util.AbstractCollection;
import java.util.concurrent.BlockingQueue;

public class DeleteFromConcurrentQueueRunner extends QueueAbstractRunner {

    public DeleteFromConcurrentQueueRunner(AbstractCollection queue, Integer count, int loopSize, String resultMeasure) {
        super(queue,count,loopSize,resultMeasure);
    }

    @Override
    protected void addOrDeleteQueueElement(int element) {
        if  (queue.getClass().getInterfaces()[0]== OwnQueue.class)
            ((OwnQueue)queue).poll();
        else
            ((BlockingQueue)queue).poll();


    }

}
