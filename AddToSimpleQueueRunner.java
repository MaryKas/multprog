import java.util.AbstractCollection;


public class AddToSimpleQueueRunner extends QueueAbstractRunner {

        public AddToSimpleQueueRunner(AbstractCollection queue, Integer count,int loopSize, String resultMeasure) {
            super(queue,count,loopSize,resultMeasure);
        }

        @Override
        protected void addOrDeleteQueueElement(int element) {
                synchronized (queue) {
                        queue.add("added element " + element);
                }
        }

}

