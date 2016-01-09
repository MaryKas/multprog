import java.util.AbstractCollection;
import java.util.concurrent.BlockingQueue;

/**
 * Created by mls on 08.01.16.
 */
public class AddToBlockingQueueRunner extends QueueAbstractRunner {

    public AddToBlockingQueueRunner(AbstractCollection queue, Integer count,int loopSize) {
       super(queue,count,loopSize);
    }

    @Override
    protected void addOrDeleteQueueElement(int element) {
        try {
            ((BlockingQueue)queue).put("added element "+element);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
