package queuerunners;

import java.util.AbstractCollection;
import java.util.LinkedList;
import java.util.PriorityQueue;


public class DeleteFromSimpleQueueRunner extends QueueAbstractRunner {


    public DeleteFromSimpleQueueRunner(AbstractCollection queue, Integer count, int loopSize, String resultMeasure) {
        super(queue,count,loopSize,resultMeasure);
    }

    @Override
    protected void addOrDeleteQueueElement(int element) {
        synchronized (queue) {
            if (queue.getClass()==LinkedList.class)
                ((LinkedList)queue).poll();
            if (queue.getClass()==PriorityQueue.class)
                ((PriorityQueue)queue).poll();
        }
    }

}