package by.tr.web.util;

import by.tr.web.exception.NoSuchElement;
import by.tr.web.exception.WrongParameters;

import java.util.Iterator;

public class MyLinkedList<E> implements MyList<E> {
    private int size;
    private Node<E> first;
    private Node<E> last;

    private static class Node<E> {
        E element;
        Node<E> next;
        Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next) {
            this.element = element;
            this.next = next;
            this.prev = prev;
        }

        Node(Node<E> copy) {
            this.element = copy.element;
            this.next = copy.next;
            this.prev = copy.prev;
        }

    }

    public MyLinkedList() {
    }

    public MyLinkedList(MyList<? extends E> list) {
        this();
        addAll(list);
    }

    @Override
    public MyIterator<E> myListIterator() {
        return new LinkedListIter<E>();
    }


    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        Node<E> current = first;

        while (current != null) {
            Object element = current.element;
            if (element.equals(o)) {
                return true;
            }
            current = current.next;
        }

        return false;
    }

    @Override
    public boolean add(E element) {
        if (first == null && last == null) {
            addInitial(element);
        } else {
            insertEnd(element);
        }
        return true;
    }

    private void addInitial(E element) {
        first = last = new Node<>(null, element, null);
        size++;
    }

    @Override
    public boolean add(int index, E element) {
        if (index < 0) {
            throw new WrongParameters("Illegal index " + index);
        }
        if (first == null && last == null) {
            addInitial(element);
            return true;
        }
        if (index == 0) {
            insertBeginning(element);
            return true;
        }
        if (index == size) {
            insertEnd(element);
            return true;
        }
        insertTo(index, element);

        return true;
    }

    @Override
    public boolean addAll(MyList<? extends E> list) {
        for (E element : list) {
            if (first == null && last == null) {
                addInitial(element);
            } else {
                insertEnd(element);
            }
        }
        return false;
    }

    private void insertBeginning(E e) {
        Node<E> f = first;
        Node<E> newNode = new Node<>(null, e, f);
        first = newNode;
        if (f != null) {
            f.prev = newNode;
        }
        size++;
    }

    private void insertEnd(E e) {
        Node<E> l = last;
        Node<E> newNode = new Node<>(l, e, null);
        last = newNode;
        if (l != null) {
            l.next = newNode;
        }
        size++;
    }

    private void insertTo(int index, E e) {
        Node<E> pivot = getNode(index);
        Node<E> pivotPrev = pivot.prev;

        Node<E> newNode = new Node<>(pivotPrev, e, pivot);
        pivotPrev.next = newNode;
        pivot.prev = newNode;

        size++;
    }

    @Override
    public void clear() {
        Node<E> current = first;

        while (current != null) {
            current.element = null;
            current.prev = null;

            current = current.next;
        }
        first = last = null;
        size = 0;
    }

    @Override
    public boolean remove(Object o) {
        Node<E> current = first;

        while (current != null) {
            Object element = current.element;
            if (element.equals(o)) {
                size--;
                return unlink(current);
            }
            current = current.next;
        }

        return false;
    }

    @Override
    public E remove(int index) {
        if (index < 0) {
            throw new WrongParameters("Invalid index " + index);
        }
        Node<E> node = getNode(index);
        E element = node.element;
        unlink(node);
        size--;
        return element;
    }

    @Override
    public boolean removeAll(MyList<? extends E> list) {
        for (E element : list) {
            remove(element);
        }
        return true;
    }

    private boolean unlink(Node<E> node) {
        if (node == first) {
            return unlinkFirst();
        }
        if (node == last) {
            return unlinkLast();
        }
        Node<E> prevNode = node.prev;
        Node<E> nextNode = node.next;

        prevNode.next = nextNode;
        nextNode.prev = prevNode;

        nullNode(node);
        return true;
    }

    private boolean unlinkLast() {

        Node<E> penultimate = last.prev;
        penultimate.next = null;
        last = penultimate;
        return true;
    }

    private boolean unlinkFirst() {

        Node<E> second = first.next;
        second.prev = null;
        first = second;
        return true;
    }

    private void nullNode(Node<E> node) {
        node.element = null;
        node.next = null;
        node.prev = null;
    }

    @Override
    public E get(int index) {
        Node<E> node = getNode(index);
        return node.element;
    }

    private Node<E> getNode(int index) {
        int counter = 0;
        Node<E> current = first;

        while (current != null) {
            if (counter == index) {
                return current;
            }
            counter++;
            current = current.next;
        }

        throw new NoSuchElement("At index " + index);
    }

    @Override
    public int indexOf(Object o) {
        int counter = 0;
        Node<E> current = new Node<>(first);
        while (current != null) {
            if (current.element.equals(o)) {
                return counter;
            }
            counter++;
            current = current.next;
        }
        return -1;
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];
        for (int index = 0; index < size; index++) {
            array[index] = get(index);
        }
        return array;
    }

    @Override
    public Iterator<E> iterator() {
        return new IteratorImpl<>();
    }

    private class IteratorImpl<E> implements Iterator<E> {
        Node cursor;

        IteratorImpl() {
            cursor = first;
        }

        @Override
        public boolean hasNext() {
            return cursor != null && cursor.next != null;
        }

        @Override
        public E next() {
            if (hasNext()) {
                E element = (E) cursor.element;
                cursor = cursor.next;
                return element;
            } else throw new NoSuchElement();
        }
    }



    @Override
    public String toString() {
        if (size == 0) {
            return Constant.EMPTY_LIST;
        }
        StringBuilder builder = new StringBuilder(Constant.LIST_START);

        Node<E> current = first;
        if (current.next == null) {
            builder.append(current.element.toString());
        } else {
            writeElements(builder, current);
        }
        builder.append(Constant.LIST_END);
        return builder.toString();
    }

    private void writeElements(StringBuilder builder, Node<E> current) {
        while (current != null) {
            builder.append(current.element.toString());

            if (!isLast(current)) {
                builder.append(Constant.SEPARATOR);
            }
            current = current.next;
        }
    }

    private boolean isLast(Node<E> node) {
        return last == node;
    }

    private class LinkedListIter<E> extends IteratorImpl<E> implements MyIterator<E> {

        LinkedListIter() {
            super();
        }

        @Override
        public boolean hasPrevious() {
            return cursor != null && cursor.prev != null;
        }

        @Override
        public E previous() {
            if (hasPrevious()) {
                E element = (E) cursor.element;
                cursor = cursor.prev;
                return element;
            } else throw new NoSuchElement();
        }
    }
}
