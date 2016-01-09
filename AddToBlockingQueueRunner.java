import java.util.AbstractCollection;
import java.util.concurrent.BlockingQueue;

/**
 * Created by mls on 08.01.16.
 */
public class AddToBlockingQueueRunner extends AddToQueueAbstractRunner {

    public AddToBlockingQueueRunner(AbstractCollection queue, Integer count,int loopSize) {
       super(queue,count,loopSize);
    }

    @Override
    protected void addElementToQueue(int element) {
        try {
            ((BlockingQueue)queue).put("added element "+element);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
