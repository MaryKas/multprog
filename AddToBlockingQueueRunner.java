import java.util.AbstractCollection;
import java.util.concurrent.BlockingQueue;


public class AddToBlockingQueueRunner extends QueueAbstractRunner {

    public AddToBlockingQueueRunner(AbstractCollection queue, Integer count,int loopSize, String resultMeasure) {
       super(queue,count,loopSize,resultMeasure);
    }

    @Override
    protected void addOrDeleteQueueElement(int element) {
        try {
            if (queue.getClass()==OwnArrayBlockingQueue.class)
                ((OwnArrayBlockingQueue)queue).put("added element "+element);
            else
                ((BlockingQueue)queue).put("added element "+element);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
