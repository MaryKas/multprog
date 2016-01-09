import java.util.AbstractCollection;


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

