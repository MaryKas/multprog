package ownqueues;

import java.util.AbstractCollection;

/**
 * Created by mls on 13.01.16.
 */
public interface OwnQueue<T> {
     void put(T r);
     T poll();

}
