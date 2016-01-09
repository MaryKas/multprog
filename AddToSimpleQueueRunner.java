import java.util.AbstractCollection;

/**
 * Created by mls on 08.01.16.
 */
public class AddToSimpleQueueRunner extends QueueAbstractRunner {




        public AddToSimpleQueueRunner(AbstractCollection queue, Integer count,int loopSize) {
            super(queue,count,loopSize);
        }

        @Override
        protected void addOrDeleteQueueElement(int element) {
                synchronized (queue) {
                        queue.add("added element " + element);
                }
        }

}

