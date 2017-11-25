package by.tr.web.util;

import by.tr.web.exception.UnexpectedError;
import by.tr.web.exception.WrongParameters;

import java.io.Serializable;
import java.util.Iterator;

public class MyArrayList<E> implements MyList<E>, Cloneable, Serializable, Iterable<E> {
    private int size;
    private int INITIAL_CAPACITY = 10;
    private Object[] array;


    public MyArrayList() {
        this.array = new Object[INITIAL_CAPACITY];
    }

    public MyArrayList(MyList<? extends E> o) {
        this();
        this.addAll(o);
    }

    public MyArrayList(int capacity) {
        if (capacity > 0) {
            this.array = new Object[capacity];
            INITIAL_CAPACITY = capacity;
        } else if (capacity < 0) {
            throw new WrongParameters("Illegal capacity value");
        }
    }


    @Override
    public Iterator<E> iterator() {
        return new IteratorImpl();
    }

    @Override
    public MyIterator<E> myListIterator() {
        return new MyArrayIter();
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    public boolean add(E element) {
        if (size >= INITIAL_CAPACITY) {
            expandArrayTo(size + 1);
        }
        array[size] = element;
        size++;

        return true;
    }

    @Override
    public boolean add(int index, E element) {
        if (index < 0) {
            throw new WrongParameters("Illegal index " + index);
        }
        if (index >= size) {
            add(element);
            return true;
        }
        if (size >= INITIAL_CAPACITY) {
            expandArrayTo(size + 1);
        }

        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = element;
        return true;
    }

    @Override
    public boolean addAll(MyList<? extends E> list) {
        int extraSize = list.size();
        expandArrayTo(extraSize + size);
        Object[] extraArray = list.toArray();
        System.arraycopy(extraArray, 0, array, size, extraSize);
        size += extraSize;
        return true;
    }

    @Override
    public void clear() {

        for (int index = 0; index < size; index++) {
            array[index] = null;
        }
        size = 0;
    }

    public boolean remove(Object o) {
        if (o == null) {
            for (int index = 0; index < size; index++)
                if (array[index] == null) {
                    removeFromArray(index);
                    return true;
                }
        } else {
            for (int index = 0; index < size; index++)
                if (o.equals(array[index])) {
                    removeFromArray(index);
                    return true;
                }
        }
        return false;
    }

    public E remove(int i) {
        if (i > size) {
            throw new IndexOutOfBoundsException("Index " + i + "is greater than " + size + " size");
        }
        E element = get(i);
        removeFromArray(i);
        return element;
    }

    @Override
    public boolean removeAll(MyList<? extends E> list) {
        Object[] listArray = list.toArray();
        int listSize = list.size();

        for (int removeEl = 0; removeEl < listSize; removeEl++) {
            Object elementToRemove = listArray[removeEl];
            remove(elementToRemove);
        }

        return true;
    }

    private void removeFromArray(int index) {
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(array, index + 1, array, index,
                    numMoved);
        }
        array[--size] = null;
    }

    public int indexOf(Object o) {
        if (o == null) {
            for (int i = 0; i < size; i++)
                if (array[i] == null)
                    return i;
        } else {
            for (int i = 0; i < size; i++)
                if (o.equals(array[i]))
                    return i;
        }
        return -1;
    }

    public Object[] toArray() {
        Object[] arrayForm = new Object[size];
        System.arraycopy(array, 0, arrayForm, 0, size);
        return arrayForm;
    }

    private void expandArrayTo(int newSize) {
        Object[] newArray = new Object[newSize];
        System.arraycopy(array, 0, newArray, 0, size);
        array = newArray;
    }

    public E get(int index) {
        if (index < 0) {
            throw new WrongParameters("Illegal index " + index);
        }
        if (index > size) {
            throw new IndexOutOfBoundsException("Index " + index + " is greater than " + size + " size");
        }
        return (E) array[index];
    }

    @Override
    public String toString() {
        if (size == 0) {
            return "[]";
        }
        if (size == 1) {
            return "[" + array[0] + "]";
        }
        StringBuilder builder = new StringBuilder("[");
        for (int i = 0; i < size - 1; i++) {
            builder.append(array[i].toString());
            builder.append(", ");
        }
        builder.append(array[size - 1]);
        builder.append("]");
        return builder.toString();
    }

    public MyArrayList clone() {
        MyArrayList clone;
        try {
            clone = (MyArrayList) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new UnexpectedError(e);
        }
        return clone;
    }
    private class IteratorImpl implements Iterator<E>{
        int current = 0;
        int size = MyArrayList.this.size;

        @Override
        public boolean hasNext() {
            return current < size;
        }

        @Override
        public E next() {
            if (hasNext()) {
                E element = get(current++);
                return element;
            }
            return null;
        }
    }
    private class MyArrayIter extends IteratorImpl implements MyIterator<E> {

        @Override
        public boolean hasPrevious() {
            return current != 0;
        }

        @Override
        public E previous() {
            if (hasPrevious()) {
                E element = get(--current);
                return element;
            }
            return null;
        }
    }
}
