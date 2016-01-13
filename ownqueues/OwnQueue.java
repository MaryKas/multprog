package ownqueues;


public interface OwnQueue<T> {
     void put(T r);
     T poll();
}
