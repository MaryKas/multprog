package ownqueues;

import java.util.AbstractCollection;
import java.util.Iterator;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by mls on 11.01.16.
 */
public class OwnArrayBlockingQueue<T> extends AbstractCollection<T> implements OwnQueue<T> {

    private Object[] array;
    private int top;
    private int bottom;

    Lock lock;


    public OwnArrayBlockingQueue(int capacity){
        array = new Object[2*capacity];
        lock = new ReentrantLock();
        top = 0;
        bottom = 0;
    }


    public void put(T element){
        lock.lock();
        try{
            if(bottom>array.length/2+top) {
                System.out.println(bottom +" "+ top +  " " + array.length);
                throw new ArrayIndexOutOfBoundsException();
            }
            array[bottom] = element;
            bottom++;
        }
        finally{
            lock.unlock();
        }

    }

    @SuppressWarnings("unchecked")
    public T poll(){
        T result;
        lock.lock();
        try{
            if(top>=bottom)
                throw new ArrayIndexOutOfBoundsException();
            result = (T) array[top];
            top++;
            if (top==array.length/2)
                moveArray();
        }
        finally{
            lock.unlock();
        }
        return result;
    }

    private void moveArray(){
        Object[] array2 = new Object[array.length];
        for(int i=0;i<bottom-top;i++){
            array2[i]=array[i+top];
        }
        bottom -= top;
        top=0;
        array=array2;
    }


    @Override
    public Iterator<T> iterator() {
        return null;
    }

    @Override
    public int size() {
        return array.length/2;
    }
}
