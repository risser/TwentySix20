package com.twentysix20.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.ConcurrentModificationException;

import junit.framework.TestCase;

import com.twentysix20.util.StringList;

public class TestStringList extends TestCase {

    public static void main(String args[]) {
		junit.textui.TestRunner.run(TestStringList.class);
	}
    
    public void testCollectionConstructor() {
        ArrayList list = new ArrayList();
        list.add("one");
        list.add("two");
        StringList s = new StringList(list);
        assertEquals(2,s.size());
        assertEquals("one",s.get(0));
        assertEquals("two",s.get(1));
    }
    public void testArrayConstructor() {
        String[] list = {"one","two"};
        StringList s = new StringList(list);
        assertEquals(2,s.size());
        assertEquals("one",s.get(0));
        assertEquals("two",s.get(1));
    }
    public void testMixedCollectionConstructor() {
        ArrayList list = new ArrayList();
        list.add("one");
        list.add(new StringBuffer("two"));
        StringList s = new StringList(list,true);
        assertEquals(2,s.size());
        assertEquals("one",s.get(0));
        assertEquals("two",s.get(1));
    }
    public void testBadCollectionConstructor() {
        ArrayList list = new ArrayList();
        list.add("one");
        list.add(new StringBuffer("two"));
        try {
            Collection c = null;
            StringList s = new StringList(c);
            fail("Should have thrown exception: null collection");
        } catch (IllegalArgumentException iae) {}
        try {
            String[] a = null;
            StringList s = new StringList(a);
            fail("Should have thrown exception: null collection");
        } catch (IllegalArgumentException iae) {}
        try {
            StringList s = new StringList(list);
            fail("Should have thrown exception: bad collection");
        } catch (IllegalArgumentException iae) {}
        try {
            StringList s = new StringList(list,false);
            fail("Should have thrown exception: bad collection");
        } catch (IllegalArgumentException iae) {}
    }
    
    public void testBadCapacity() {
        try {
            StringList s = new StringList(-1);
            fail("Should have thrown exception: bad capacity");
        } catch (IllegalArgumentException iae) {}            
        try {
            StringList s = new StringList();
            s.ensureCapacity(-1);
            fail("Should have thrown exception: bad capacity");
        } catch (IllegalArgumentException iae) {}            
    }
    public void testIsEmpty() {
        StringList s = new StringList();
        assertTrue(s.isEmpty());
        String[] a = {"one","two"};
        s = new StringList(a);
        assertFalse(s.isEmpty());
    }
    public void testSize() {
        StringList s = new StringList();
        assertEquals(0,s.size());
        String[] a = {"one","two"};
        s = new StringList(a);
        assertEquals(2,s.size());
    }
    public void testContains() {
        String[] a = {"one","two"};
        StringList s = new StringList(a);
        assertTrue(s.contains("two"));
        assertFalse(s.contains("three"));
        assertFalse(s.contains(null));
    }
    public void testContainsNull() {
        String[] a = {"one",null,"two"};
        StringList s = new StringList(a);
        assertTrue(s.contains("two"));
        assertFalse(s.contains("three"));
        assertTrue(s.contains(null));
    }
    public void testIndexOf() {
        String[] a = {"one",null,"two","three","two",null,"one"};
        StringList s = new StringList(a);
        assertEquals(0,s.indexOf("one"));
        assertEquals(1,s.indexOf(null));
        assertEquals(2,s.indexOf("two"));
    }
    public void testLastIndexOf() {
        String[] a = {"one",null,"two","three","two",null,"one"};
        StringList s = new StringList(a);
        assertEquals(6,s.lastIndexOf("one"));
        assertEquals(5,s.lastIndexOf(null));
        assertEquals(4,s.lastIndexOf("two"));
    }
    public void testContainsIndexOfEmpty() {
        StringList s = new StringList();
        assertFalse(s.contains("two"));
        assertEquals(-1,s.indexOf("two"));
        assertEquals(-1,s.lastIndexOf("two"));
    }
    public void testClone() {
        String[] a = {"one","two"};
        StringList s = new StringList(a);
        StringList s1 = (StringList)s.clone();
        assertFalse(s == s1);
    }
    public void testEquals() {
        String[] a = {"111","222"};
        StringList s = new StringList(a);
        StringList s1 = new StringList();
        s1.add("111");
        s1.add("2"+(2*11));
        assertTrue(s.equals(s1));
        s1.remove(1);
        assertFalse(s.equals(s1));
        s1.add("22");
        assertFalse(s.equals(s1));
    }
    public void testEqualsEmpty() {
        StringList s = new StringList();
        StringList s1 = new StringList();
        assertTrue(s.equals(s1));
    }
    public void testToArray() {
        StringList s = new StringList();
        s.add("one");
        s.add("two");
        String[] a = s.toStringArray();
        assertEquals(2,a.length);
        assertEquals("one",a[0]);
        assertEquals("two",a[1]);
    }
    public void testToArrayEmpty() {
        StringList s = new StringList();
        String[] a = s.toStringArray();
        assertEquals(0,a.length);
    }
    public void testBadGet() {
        StringList s1 = new StringList();
        try {
            s1.get(0);
            fail("Should have thrown exception: index out of bounds");
        } catch (IndexOutOfBoundsException ioobe) {}            
        String[] a = {"111","222"};
        StringList s = new StringList(a);
        try {
            s.get(-1);
            fail("Should have thrown exception: array index out of bounds");
        } catch (IndexOutOfBoundsException aioobe) {}            
        try {
            s.get(2);
            fail("Should have thrown exception: index out of bounds");
        } catch (IndexOutOfBoundsException ioobe) {}            
   }
    public void testGet() {
        String[] a = {"111","222"};
        StringList s = new StringList(a);
        assertEquals("111",s.get(0));
        assertEquals("222",s.get(1));
    }
    public void testBadSet() {
        StringList s1 = new StringList();
        try {
            s1.set(0,"sss");
            fail("Should have thrown exception: index out of bounds");
        } catch (IndexOutOfBoundsException ioobe) {}            
        String[] a = {"111","222"};
        StringList s = new StringList(a);
        try {
            s.set(-1,"000");
            fail("Should have thrown exception: array index out of bounds");
        } catch (IndexOutOfBoundsException aioobe) {}            
        try {
            s.set(2,"000");
            fail("Should have thrown exception: index out of bounds");
        } catch (IndexOutOfBoundsException ioobe) {}            
    }
    public void testSet() {
        String[] a = {"111","222"};
        StringList s = new StringList(a);
        s.set(0,"000");
        assertEquals("000",s.get(0));
        assertEquals("222",s.get(1));
        s.set(1,null);
        assertEquals("000",s.get(0));
        assertEquals(null,s.get(1));
    }
    public void testAdd() {
        StringList s = new StringList();
        assertEquals(0,s.size());
        s.add("111");
        assertEquals(1,s.size());
        assertEquals("111",s.get(0));
        s.add(null);
        assertEquals(2,s.size());
        assertEquals(null,s.get(1));
        s.add("222");
        assertEquals(3,s.size());
        assertEquals("222",s.get(2));
    }
    public void testAddIndex() {
        String[] a = {"111","222"};
        StringList s = new StringList(a);
        assertEquals(2,s.size());
        s.add(2,"xxx");
        assertEquals(3,s.size());
        assertEquals("111",s.get(0));
        assertEquals("222",s.get(1));
        assertEquals("xxx",s.get(2));
        s.add(2,"yyy");
        assertEquals(4,s.size());
        assertEquals("111",s.get(0));
        assertEquals("222",s.get(1));
        assertEquals("yyy",s.get(2));
        assertEquals("xxx",s.get(3));
        s.add(0,"zzz");
        assertEquals(5,s.size());
        assertEquals("zzz",s.get(0));
        assertEquals("111",s.get(1));
        assertEquals("222",s.get(2));
        assertEquals("yyy",s.get(3));
        assertEquals("xxx",s.get(4));
    }
    public void testBadAddIndex() {
        String[] a = {"111","222"};
        StringList s = new StringList(a);
        try {
            s.add(-1,"000");
            fail("Should have thrown exception: array index out of bounds");
        } catch (IndexOutOfBoundsException ioobe) {}            
        try {
            s.add(3,"000");
            fail("Should have thrown exception: index out of bounds");
        } catch (IndexOutOfBoundsException ioobe) {}            
    }
    public void testBadRemove() {
        String[] a = {"111","222"};
        StringList s = new StringList(a);
        try {
            s.remove(-1);
            fail("Should have thrown exception: array index out of bounds");
        } catch (IndexOutOfBoundsException ioobe) {}            
        try {
            s.remove(2);
            fail("Should have thrown exception: index out of bounds");
        } catch (IndexOutOfBoundsException ioobe) {}            
    }
    public void testRemove() {
        String[] a = {"111","222","333","444","555"};
        StringList s = new StringList(a);
        assertEquals(5,s.size());
        assertEquals("111",s.remove(0));
        assertEquals(4,s.size());
        assertEquals("222",s.get(0));
        assertEquals("333",s.get(1));
        assertEquals("444",s.get(2));
        assertEquals("555",s.get(3));
        assertEquals("555",s.remove(3));
        assertEquals(3,s.size());
        assertEquals("222",s.get(0));
        assertEquals("333",s.get(1));
        assertEquals("444",s.get(2));
        assertEquals("333",s.remove(1));
        assertEquals(2,s.size());
        assertEquals("222",s.get(0));
        assertEquals("444",s.get(1));
    }
    public void testClear() {
        String[] a = {"111","222","333","444","555"};
        StringList s = new StringList(a);
        assertEquals(5,s.size());
        s.clear();
        assertEquals(0,s.size());
        s.clear();
        assertEquals(0,s.size());
    }
    public void testToString() {
        String[] a = {"111","222","333","444","555"};
        StringList s = new StringList();
        assertEquals("",s.toString());
        assertEquals("",s.toString(","));
        s = new StringList(a);
        assertEquals("111 222 333 444 555",s.toString());
        assertEquals("111,222,333,444,555",s.toString(","));
    }
    public void testAddAllArray() {
        String[] a = {"111","222"};
        String[] b = {"333","444"};
        String[] c = {"xxx","yyy"};
        StringList s = new StringList();
        assertEquals(0,s.size());
        s.addAll(a);
        assertEquals(2,s.size());
        assertEquals("111",s.get(0));
        assertEquals("222",s.get(1));
        s.addAll(b);
        assertEquals(4,s.size());
        assertEquals("111",s.get(0));
        assertEquals("222",s.get(1));
        assertEquals("333",s.get(2));
        assertEquals("444",s.get(3));
        s.addAll(4,c);
        assertEquals(6,s.size());
        assertEquals("111",s.get(0));
        assertEquals("222",s.get(1));
        assertEquals("333",s.get(2));
        assertEquals("444",s.get(3));
        assertEquals("xxx",s.get(4));
        assertEquals("yyy",s.get(5));
        s.addAll(0,c);
        assertEquals(8,s.size());
        assertEquals("xxx",s.get(0));
        assertEquals("yyy",s.get(1));
        assertEquals("111",s.get(2));
        assertEquals("222",s.get(3));
        assertEquals("333",s.get(4));
        assertEquals("444",s.get(5));
        assertEquals("xxx",s.get(6));
        assertEquals("yyy",s.get(7));
        s.addAll(7,c);
        assertEquals(10,s.size());
        assertEquals("xxx",s.get(0));
        assertEquals("yyy",s.get(1));
        assertEquals("111",s.get(2));
        assertEquals("222",s.get(3));
        assertEquals("333",s.get(4));
        assertEquals("444",s.get(5));
        assertEquals("xxx",s.get(6));
        assertEquals("xxx",s.get(7));
        assertEquals("yyy",s.get(8));
        assertEquals("yyy",s.get(9));
        s = new StringList();
        s.addAll(0,a);
        assertEquals(2,s.size());
        assertEquals("111",s.get(0));
        assertEquals("222",s.get(1));
    }
    public void testAddAllCollection() {
        ArrayList a = new ArrayList();
        ArrayList b = new ArrayList();
        ArrayList c = new ArrayList();
        a.add("111");
        a.add("222");
        b.add("333");
        b.add("444");
        c.add("xxx");
        c.add("yyy");
        StringList s = new StringList();
        assertEquals(0,s.size());
        s.addAll(a);
        assertEquals(2,s.size());
        assertEquals("111",s.get(0));
        assertEquals("222",s.get(1));
        s.addAll(b);
        assertEquals(4,s.size());
        assertEquals("111",s.get(0));
        assertEquals("222",s.get(1));
        assertEquals("333",s.get(2));
        assertEquals("444",s.get(3));
        s.addAll(4,c);
        assertEquals(6,s.size());
        assertEquals("111",s.get(0));
        assertEquals("222",s.get(1));
        assertEquals("333",s.get(2));
        assertEquals("444",s.get(3));
        assertEquals("xxx",s.get(4));
        assertEquals("yyy",s.get(5));
        s.addAll(0,c);
        assertEquals(8,s.size());
        assertEquals("xxx",s.get(0));
        assertEquals("yyy",s.get(1));
        assertEquals("111",s.get(2));
        assertEquals("222",s.get(3));
        assertEquals("333",s.get(4));
        assertEquals("444",s.get(5));
        assertEquals("xxx",s.get(6));
        assertEquals("yyy",s.get(7));
        s.addAll(7,c);
        assertEquals(10,s.size());
        assertEquals("xxx",s.get(0));
        assertEquals("yyy",s.get(1));
        assertEquals("111",s.get(2));
        assertEquals("222",s.get(3));
        assertEquals("333",s.get(4));
        assertEquals("444",s.get(5));
        assertEquals("xxx",s.get(6));
        assertEquals("xxx",s.get(7));
        assertEquals("yyy",s.get(8));
        assertEquals("yyy",s.get(9));
        s = new StringList();
        s.addAll(0,a);
        assertEquals(2,s.size());
        assertEquals("111",s.get(0));
        assertEquals("222",s.get(1));
    }
    
    public void testAddAllMixedCollection() {
        ArrayList list = new ArrayList();
        list.add("one");
        list.add(new StringBuffer("two"));
        StringList s = new StringList();
        s.addAll(list,true);
        assertEquals(2,s.size());
        assertEquals("one",s.get(0));
        assertEquals("two",s.get(1));
        list.set(1, new StringBuffer("three"));
        s.addAll(0,list,true);
        assertEquals(4,s.size());
        assertEquals("one",s.get(0));
        assertEquals("three",s.get(1));
        assertEquals("one",s.get(2));
        assertEquals("two",s.get(3));
        list.set(1, new StringBuffer("four"));
        s.addAll(1,list,true);
        assertEquals(6,s.size());
        assertEquals("one",s.get(0));
        assertEquals("one",s.get(1));
        assertEquals("four",s.get(2));
        assertEquals("three",s.get(3));
        assertEquals("one",s.get(4));
        assertEquals("two",s.get(5));
        list.set(1, new StringBuffer("five"));
        s.addAll(6,list,true);
        assertEquals(8,s.size());
        assertEquals("one",s.get(0));
        assertEquals("one",s.get(1));
        assertEquals("four",s.get(2));
        assertEquals("three",s.get(3));
        assertEquals("one",s.get(4));
        assertEquals("two",s.get(5));
        assertEquals("one",s.get(6));
        assertEquals("five",s.get(7));        
    }
    public void testAddAllBadCollection() {
        String[] ss = {"xxx","ttt"};
        StringList s = new StringList(ss);
        ArrayList list = new ArrayList();
        list.add("one");
        list.add(new StringBuffer("two"));
        try {
            Collection c = null;
            s.addAll(c);
            fail("Should have thrown exception: null collection");
        } catch (IllegalArgumentException iae) {}
        try {
            String[] a = null;
            s.addAll(a);
            fail("Should have thrown exception: null collection");
        } catch (IllegalArgumentException iae) {}
        try {
            s.addAll(list);
            fail("Should have thrown exception: bad collection");
        } catch (IllegalArgumentException iae) {}
        try {
            s.addAll(list,false);
            fail("Should have thrown exception: bad collection");
        } catch (IllegalArgumentException iae) {}
        try {
            Collection c = null;
            s.addAll(1,c);
            fail("Should have thrown exception: null collection");
        } catch (IllegalArgumentException iae) {}
        try {
            String[] a = null;
            s.addAll(1,a);
            fail("Should have thrown exception: null collection");
        } catch (IllegalArgumentException iae) {}
        try {
            s.addAll(1,list);
            fail("Should have thrown exception: bad collection");
        } catch (IllegalArgumentException iae) {}
        try {
            s.addAll(1,list,false);
            fail("Should have thrown exception: bad collection");
        } catch (IllegalArgumentException iae) {}
    }
    public void testIterator() {
        StringList a = new StringList();
        a.add("111");
        a.add("222");
        a.add("333");
        a.add("444");
        a.add("xxx");
        a.add("yyy");
        StringList.StringListIterator itr = a.stringIterator();
        assertEquals("111", itr.nextString());
        assertEquals("222", itr.nextString());
        assertEquals("333", itr.nextString());
        assertEquals("444", itr.nextString());
        assertEquals("xxx", itr.nextString());
        assertEquals("yyy", itr.nextString());
    }
    public void testConcurrentIterator() {
        StringList a = new StringList();
        a.add("111");
        a.add("222");
        a.add("333");
        a.add("444");
        a.add("xxx");
        a.add("yyy");
        StringList.StringListIterator itr = a.stringIterator();
        assertEquals("111", itr.nextString());
        assertEquals("222", itr.nextString());
        assertEquals("333", itr.nextString());
        a.add("zzz");
        try {
            assertEquals("444", itr.nextString());
            fail("Should have thrown ConcurrentModificationException."); 
        } catch (ConcurrentModificationException cme) {
            // good.
        }
    }

}