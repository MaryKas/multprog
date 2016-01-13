package queuerunners;

import java.util.AbstractCollection;
import java.util.concurrent.BlockingQueue;


public class AddToConcurrentQueueRunner extends QueueAbstractRunner {

    public AddToConcurrentQueueRunner(AbstractCollection queue, Integer count, int loopSize, String resultMeasure) {
       super(queue,count,loopSize,resultMeasure);
    }

    @Override
    protected void addOrDeleteQueueElement(int element) {
        try {
            if (queue.getClass().getInterfaces()[0]== OwnQueue.class)
                ((OwnQueue)queue).put("added element "+element);
            else
                ((BlockingQueue)queue).put("added element "+element);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
