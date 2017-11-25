package by.tr.web.util;

import java.util.Iterator;

public interface MyList<E> {

    Iterator<E> iterator();
    MyIterator<E> myListIterator();
    int size();

    boolean isEmpty();

    boolean contains(Object o);

    boolean add(E element);

    boolean add(int index, E element);

    boolean addAll(MyList<? extends E> list);

    void clear();

    boolean remove(Object o);

    E remove(int index);

    boolean removeAll(MyList<? extends E> list);

    E get(int index);

    int indexOf(Object o);

    Object[] toArray();
}
