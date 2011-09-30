package com.twentysix20.util;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import com.twentysix20.util.RollbackQueue;
import com.twentysix20.util.exception.EmptyQueueException;

public class TestRollbackQueue extends TestCase {

    public static void main(String args[]) {
		junit.textui.TestRunner.run(TestRollbackQueue.class);
	}

    public void testEmpty() {
        RollbackQueue q = new RollbackQueue();
        try {
            Object o = q.pop();
            fail("Should have thrown EmptyQueueException.");
        } catch (EmptyQueueException eqe) {}
        try {
            q.rollback();
            fail("Should have thrown EmptyQueueException.");
        } catch (EmptyQueueException eqe) {}        
        try {
            q.peek();
            fail("Should have thrown EmptyQueueException.");
        } catch (EmptyQueueException eqe) {}        
        try {
            q.cachePeek();
            fail("Should have thrown EmptyQueueException.");
        } catch (EmptyQueueException eqe) {}        
    }
    
    public void testErrors() {
        try {
            RollbackQueue q = new RollbackQueue(-1);
            fail("Should have thrown IllegalArgumentException: Undo cache size must be 0 or higher.");
        } catch (IllegalArgumentException iae) {}
        RollbackQueue q = new RollbackQueue(2);
        Object[] oarray = {new Integer(1), new Integer(2), new Integer(3)};
        q.fill(oarray);
        Object o = q.pop();
        try {
            List os = q.pop(3);
            fail("Should have thrown IllegalArgumentException: Can't pop more than are in queue.");
        } catch (IllegalArgumentException iae) {}
        try {
            List os = q.pop(0);
            fail("Should have thrown IllegalArgumentException: Can't pop less than one.");
        } catch (IllegalArgumentException iae) {}
        try {
            q.rollback(3);
            fail("Should have thrown IllegalArgumentException: Can't rollback more than are in undo cache.");
        } catch (IllegalArgumentException iae) {}
        try {
            q.rollback(0);
            fail("Should have thrown IllegalArgumentException: Can't rollback less than one.");
        } catch (IllegalArgumentException iae) {}
        try {
            q.peek(2);
            fail("Should have thrown IllegalArgumentException: Can't peek past the end of the queue.");
        } catch (IllegalArgumentException iae) {}
        try {
            q.peek(-1);
            fail("Should have thrown IllegalArgumentException: Peek index must be 0 or greater.");
        } catch (IllegalArgumentException iae) {}
        try {
            q.fill((List)null);
            fail("Should have thrown IllegalArgumentException: Can't fill with null.");
        } catch (IllegalArgumentException iae) {}
        try {
            q.fill((Object[])null);
            fail("Should have thrown IllegalArgumentException: Can't fill with null.");
        } catch (IllegalArgumentException iae) {}
        try {
            q.fill((String)null);
            fail("Should have thrown IllegalArgumentException: Can't fill with null.");
        } catch (IllegalArgumentException iae) {}
        try {
            Object[] os2 = {};
            q.fill(os2);
            fail("Should have thrown IllegalArgumentException: Can't fill empty array.");
        } catch (IllegalArgumentException iae) {}
        try {
            q.fill(new ArrayList());
            fail("Should have thrown IllegalArgumentException: Can't fill empty list.");
        } catch (IllegalArgumentException iae) {}
    }
    
    public void testIsEmpty() {
        RollbackQueue q = new RollbackQueue(2);
        assertTrue(q.isEmpty());
        assertTrue(q.cacheEmpty());
        q.fill("one");
        assertFalse(q.isEmpty());
        assertTrue(q.cacheEmpty());
        Object o = q.pop();
        assertTrue(q.isEmpty());
        assertFalse(q.cacheEmpty());
    }
    
    public void testClear() {
        RollbackQueue q = new RollbackQueue(2);
        q.fill("one");
        q.fill("two");
        q.fill("three");
        q.fill("four");
        q.fill("five");
        List list = q.pop(2);
        assertEquals(3,q.size());
        assertEquals(2,q.cacheSize());
        q.clearCache();
        assertEquals(3,q.size());
        assertEquals(0,q.cacheSize());
        Object o = q.pop();
        q.clear();
        assertEquals(0,q.size());
        assertEquals(1,q.cacheSize());
    }
    
    public void testSizes() {
        RollbackQueue q = new RollbackQueue(2);
        assertEquals(0,q.size());
        assertEquals(0,q.cacheSize());
        Object[] os = {new Integer(1), new Integer(2), new Integer(3)};
        q.fill(os);
        assertEquals(3,q.size());
        assertEquals(0,q.cacheSize());
        Object o = q.pop();
        assertEquals(2,q.size());
        assertEquals(1,q.cacheSize());
        List list = q.pop(2);
        assertEquals(0,q.size());
        assertEquals(2,q.cacheSize());
        q.rollback();
        assertEquals(1,q.size());
        assertEquals(1,q.cacheSize());
        q.rollback(1);
        assertEquals(2,q.size());
        assertEquals(0,q.cacheSize());
    }

    public void testFillItem() {
        RollbackQueue q = new RollbackQueue(2);
        q.fill("one");
        q.fill("two");
        assertEquals("one",q.peek().toString());
        q.fill("three");
        q.fill("four");
        q.fill("five");
        q.pop(3);
        assertEquals("four",q.peek().toString());
    }
    
    public void testFillArray() {
        String[] ss = {"one","two","three"};
        RollbackQueue q = new RollbackQueue(2);
        q.fill(ss);
        assertEquals("one",q.peek().toString());        
        String[] s2 = {"four"};
        q.fill(s2);
        q.pop(3);
        assertEquals("four",q.peek().toString());        
    }
    
    public void testFillList() {
        ArrayList list = new ArrayList();
        list.add("one");
        list.add("two");
        RollbackQueue q = new RollbackQueue(2);
        q.fill(list);
        assertEquals("one",q.peek().toString());        
        ArrayList list2 = new ArrayList();
        list2.add("three");
        list2.add("four");
        q.fill(list2);
        q.pop(2);
        assertEquals("three",q.peek().toString());        
    }
    
    public void testPop() {
        RollbackQueue q = new RollbackQueue(2);
        q.fill("one");
        q.fill("two");
        q.fill("three");
        assertEquals("one",q.peek().toString());
        Object o = q.pop();
        assertEquals("one",o.toString());
        assertEquals("two",q.peek().toString());
        o = q.pop();
        assertEquals("two",o.toString());
        assertEquals("three",q.peek().toString());
    }
    
    public void testPops() {
        RollbackQueue q = new RollbackQueue(2);
        q.fill("one");
        q.fill("two");
        q.fill("three");
        q.fill("four");
        assertEquals("one",q.peek().toString());
        List list = q.pop(2);
        assertEquals("one",list.get(0).toString());
        assertEquals("two",list.get(1).toString());
        assertEquals("three",q.peek().toString());
        list = q.pop(1);
        assertEquals("three",list.get(0).toString());
        assertEquals("four",q.peek().toString());
    }

    public void testRollback() {
        RollbackQueue q = new RollbackQueue(4);
        q.fill("one");
        q.fill("two");
        q.fill("three");
        q.fill("four");
        q.fill("five");
        q.fill("six");
        q.fill("seven");
        q.fill("eight");
        List list = q.pop(3);
        assertEquals("four",q.peek().toString());
        assertEquals("three",q.cachePeek().toString());
        q.rollback();
        assertEquals("three",q.peek().toString());
        assertEquals("two",q.cachePeek().toString());
        list = q.pop(2);
        assertEquals("five",q.peek().toString());
        assertEquals("four",q.cachePeek().toString());
        q.rollback(3);
        assertEquals("two",q.peek().toString());
        assertEquals("one",q.cachePeek().toString());
    }
    
    public void testPeek() {
        RollbackQueue q = new RollbackQueue(4);
        q.fill("one");
        q.fill("two");
        q.fill("three");
        q.fill("four");
        q.fill("five");
        q.fill("six");
        q.fill("seven");
        q.fill("eight");
        Object o = q.pop();
        assertEquals("two",q.peek().toString());
        assertEquals("one",q.cachePeek().toString());
        assertEquals("two",q.peek(0).toString());
        assertEquals("three",q.peek(1).toString());
        assertEquals("eight",q.peek(6).toString());
    }
}