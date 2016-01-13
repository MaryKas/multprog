package ownqueues;

import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicStampedReference;

public class BDEQueue<T> extends AbstractCollection<T> implements OwnQueue<T> {

    Object[] tasks;
    volatile int bottom;
    AtomicStampedReference<Integer> top;

    public BDEQueue(int capacity) {
        tasks = new Object[capacity];
        top = new AtomicStampedReference<Integer>(0, 0);
        bottom = 0;
    }


    public void put(T r){
            tasks[bottom] = r;
            bottom++;
    }

    @Override
    public boolean addAll(Collection<? extends T> collection){
        for (T element: collection){
            put(element);
        }
        return true;

    }

    @Override
    public Iterator iterator() {
        return null;
    }

    @Override
    public int size() {
        return tasks.length;
    }



    @SuppressWarnings("unchecked")
    public T poll() {
        while(true) {
            int[] stamp = new int[1];

            int oldTop = top.get(stamp), newTop = oldTop + 1;
            int oldStamp = stamp[0], newStamp = oldStamp + 1;
            if (bottom <= oldTop)
                return null;
            T r = (T)tasks[oldTop];
            if (top.compareAndSet(oldTop, newTop, oldStamp, newStamp))
                return r;
        }
    }

}
