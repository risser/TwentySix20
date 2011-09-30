/*
 * RollbackQueue.java
 *
 * Created on August 24, 2004, 10:47 PM
 */

package com.twentysix20.util;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.twentysix20.util.exception.EmptyQueueException;

/**
 *
 * @author  tpnr007
 */
public class RollbackQueue {
    
    LinkedList main = new LinkedList();
    LinkedList undo = new LinkedList();
    int cacheSize = 0;
    
    public RollbackQueue(int csize) {
        if (csize < 0) throw new IllegalArgumentException("Initial cache size must be greater than zero.");
        cacheSize = csize;
    }
    public RollbackQueue() {
        this(0);
    }
    
    public Object peek() {
        if (isEmpty()) throw new EmptyQueueException();
        return main.getFirst();
    }
    public Object peek(int index) {
        if (isEmpty()) throw new EmptyQueueException();
        if (index >= main.size()) throw new IllegalArgumentException("Peek index ("+index+") must be less than queue size ("+main.size()+").");
        if (index < 0) throw new IllegalArgumentException("Peek index must be zero or greater.");
        return main.get(index);
    }
    public Object cachePeek() {
        if (cacheEmpty()) throw new EmptyQueueException();
        return undo.getFirst();
    }
    
    public Object pop() {
        if (isEmpty()) throw new EmptyQueueException();
        Object o = main.removeFirst();
        pushCache(o);
        return o;
    }
    public List pop(int times) {
        if (times > main.size()) throw new IllegalArgumentException("Can't pop more items ("+times+") than there are in the queue ("+main.size()+").");
        if (times < 1) throw new IllegalArgumentException("Can't pop fewer than one item.");
        List list = new ArrayList();
        for (int i = 0; i < times; i++)
            list.add(pop());
        return list;
    }
    
    public void rollback() {
        if (cacheEmpty()) throw new EmptyQueueException();
        main.addFirst(undo.removeFirst());
    }
    public void rollback(int times) {
        if (times > undo.size()) throw new IllegalArgumentException("Can't rollback more items ("+times+") than there are in the cache ("+undo.size()+").");
        if (times < 1) throw new IllegalArgumentException("Can't rollback fewer than one item.");
        for (int i = 0; i < times; i++)
            rollback();
    }
    
    public void fill(Object[] os) {
        if (os == null) throw new IllegalArgumentException("Can't fill with null.");
        if (os.length == 0) throw new IllegalArgumentException("Can't fill with empty array.");
        for (int i = 0; i < os.length; i++)
            main.addLast(os[i]);
    }
    public void fill(List os) {
        if (os == null) throw new IllegalArgumentException("Can't fill with null.");
        if (os.isEmpty()) throw new IllegalArgumentException("Can't fill with empty List.");
        main.addAll(main.size(), os);
    }
    public void fill(Object o) {
        if (o == null) throw new IllegalArgumentException("Can't fill with null.");
        main.addLast(o);
    }
    
    public void clear() {
        main.clear();
    }
    public void clearCache() {
        undo.clear();
    }

    public boolean isEmpty() {
        return (main.size() == 0);
    }
    public boolean cacheEmpty() {
        return (undo.size() == 0);
    }
    
    public int size() {
        return main.size();
    }
    public int cacheSize() {
        return undo.size();
    }

    protected void pushCache(Object o) {
        undo.addFirst(o);
        trimCache();
    }
    protected void trimCache() {
        while (undo.size() > cacheSize)
            undo.removeLast();
    }
}
