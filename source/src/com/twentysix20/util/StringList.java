/*
 * StringList.java
 *
 * Created on September 4, 2004, 8:26 AM
 */

package com.twentysix20.util;

import java.util.*;

/**
 *
 * @author  Peter Risser
 */

public class StringList extends AbstractCollection<String> implements Collection<String>, Cloneable {
    private ArrayList<String> list = new ArrayList<String>();
    protected transient int modCount = 0;
    
    public StringList (String[] strings) {
        if (strings == null) throw new IllegalArgumentException("Can't create a StringList with a null array.");
        list = new ArrayList<String>(strings.length);
        addAll(strings);
    }
    
    public StringList(int initialCapacity) {
        if (initialCapacity < 0) throw new IllegalArgumentException("Initial capacity must be zero or greater.");
        list = new ArrayList<String>(initialCapacity);
    }

    public StringList() {}

    public StringList(Collection<String> c, boolean convert) {
        if (c == null) throw new IllegalArgumentException("Can't create a StringList with a null Collection.");
        list = new ArrayList<String>(c.size());
        addAll(c,convert);
    }
    public StringList(Collection<String> c) {
        this(c,false);
    }

    public void trimToSize() {
        modCount++;
        list.trimToSize();
    }

    public void ensureCapacity(int minCapacity) {
        if (minCapacity < 0) throw new IllegalArgumentException("Capacity must be zero or greater.");
        modCount++;
        list.ensureCapacity(minCapacity);
    }

    public int size() {
        return list.size();
    }

    public boolean isEmpty() {
    	return list.isEmpty();
    }

    public boolean contains(String elem) {
        return list.contains(elem);
    }

    public int indexOf(String elem) {
        return list.indexOf(elem);
    }

    public int lastIndexOf(Object elem) {
        return list.lastIndexOf(elem);
    }

    public String[] toStringArray() {
        return (String[])list.toArray(new String[list.size()]);
    }

    public String get(int index) {
        return (String)list.get(index);
    }

    public String set(int index, String element) {
        modCount++;
    	return (String)list.set(index,element);
    }

    public boolean add(String o) {
        modCount++;
        return list.add(o);
    }

    public void add(int index, String element) {
        modCount++;
        list.add(index, element);
    }

    public String remove(int index) {
        modCount++;
        return (String)list.remove(index);
    }

    public void clear() {
        modCount++;
        list.clear();
    }

    public boolean addAll(String[] strings) {
        modCount++;
        if (strings == null) throw new IllegalArgumentException("Collection cannot be null.");
        for (int i = 0; i < strings.length; i++)
            list.add(strings[i]);
        return (strings.length != 0);
    }
    public boolean addAll(int index, String[] strings) {
        modCount++;
        if (strings == null) throw new IllegalArgumentException("Collection cannot be null.");
        for (int i = 0; i < strings.length; i++)
            list.add(index + i,strings[i]);
        return (strings.length != 0);
    }
    public boolean addAll(Collection c, boolean convert) {
        return addAll(list.size(),c,convert);
    }
    public boolean addAll(Collection c) {
        return addAll(c,false);
    }
    public boolean addAll(int index, Collection c) {
        return addAll(index, c,  false);
    }
    public boolean addAll(int index, Collection c, boolean convert) {
        modCount++;
        if (c == null) throw new IllegalArgumentException("Collection cannot be null.");
        if (c.isEmpty()) return false;
        ArrayList newList = new ArrayList(c);
        for (int i = 0; i < newList.size(); i++)
            if (!(newList.get(i) instanceof String))
                if (convert)
                    newList.set(i,newList.get(i).toString());
                else
                    throw new IllegalArgumentException("Item "+i+" ("+newList.get(i)+") in the collection is not a String. (Actual class: "+newList.get(i).getClass().getName()+")");
        list.addAll(index, newList);
        return true;
    }

    public String toString(String starter, String ender) {
        StringBuffer buf = new StringBuffer("");
        Iterator iter = list.iterator();
        while (iter.hasNext())
            buf.append(starter).append(iter.next().toString()).append(ender);
        return buf.toString();
    }
    
    public String toString(String divider) {
        StringBuffer buf = new StringBuffer("");
        Iterator iter = list.iterator();
        while (iter.hasNext())
            buf.append(iter.next().toString()).append(iter.hasNext() ? divider : "");
        return buf.toString();
    }
    
    public String toString() {
        return toString(" ");
    }
    
    public boolean equals(StringList slist) {
        if (size() != slist.size()) return false;
        for (int i = 0; i < size(); i++)
            if (!list.get(i).equals(slist.get(i)))
                return false;
        return true;
    }
    
    public StringList sortedCopy() {
        String[] a = this.toStringArray();
        Arrays.sort(a);
        return new StringList(a);
    }

    public Object clone() {
        // since strings are immutable, and the constructor dumps the collection
        // into it's own personal array, this works.
        return new StringList(list);
    }

    public Iterator iterator() {
        return new StringListItr(0);
    }

    public StringListIterator stringIterator() {
        return new StringListItr(0);
    }
    
    public interface StringListIterator extends ListIterator {
        public String nextString();
    }

    private class StringListItr implements StringListIterator {
        /**
         * Index of element to be returned by subsequent call to next.
         */
        int cursor = 0;

        /**
         * Index of element returned by most recent call to next or previous.
         * Reset to -1 if this element is deleted by a call to remove.
         */
        int lastRet = -1;

        /**
         * The modCount value that the iterator believes that the backing List
         * should have. If this expectation is violated, the iterator has
         * detected concurrent modification.
         */
        int expectedModCount = modCount;

        public boolean hasNext() {
            return cursor != size();
        }

        public Object next() {
            try {
                Object next = get(cursor);
                checkForComodification();
                lastRet = cursor++;
                return next;
            }
            catch (IndexOutOfBoundsException e) {
                checkForComodification();
                throw new NoSuchElementException();
            }
        }

        public String nextString() {
            return (String)next();
        }

        public void remove() {
            if (lastRet == -1) throw new IllegalStateException();
            checkForComodification();

            try {
                StringList.this.remove(lastRet);
                if (lastRet < cursor) cursor--;
                lastRet = -1;
                expectedModCount = modCount;
            }
            catch (IndexOutOfBoundsException e) {
                throw new ConcurrentModificationException();
            }
        }

        final void checkForComodification() {
            if (modCount != expectedModCount) throw new ConcurrentModificationException();
        }

        StringListItr(int index) {
            cursor = index;
        }

        public boolean hasPrevious() {
            return cursor != 0;
        }

        public Object previous() {
            try {
                int i = cursor - 1;
                Object previous = get(i);
                checkForComodification();
                lastRet = cursor = i;
                return previous;
            }
            catch (IndexOutOfBoundsException e) {
                checkForComodification();
                throw new NoSuchElementException();
            }
        }

        public int nextIndex() {
            return cursor;
        }

        public int previousIndex() {
            return cursor - 1;
        }

        public void set(String o) {
            if (lastRet == -1) throw new IllegalStateException();
            checkForComodification();

            try {
                StringList.this.set(lastRet, o);
                expectedModCount = modCount;
            }
            catch (IndexOutOfBoundsException e) {
                throw new ConcurrentModificationException();
            }
        }

        public void add(String o) {
            checkForComodification();

            try {
                StringList.this.add(cursor++, o);
                lastRet = -1;
                expectedModCount = modCount;
            }
            catch (IndexOutOfBoundsException e) {
                throw new ConcurrentModificationException();
            }
        }

        public void set(Object o) {
            set(o.toString());            
        }

        public void add(Object o) {
            add(o.toString());            
        }
    }
}

