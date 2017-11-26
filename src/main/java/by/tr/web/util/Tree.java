package by.tr.web.util;

import java.util.Iterator;

public interface Tree<E> extends Iterable<E>{
    void setRoot(E root);

    MyList<E> getChildList(E node);

    MyList<E> toLevelOrderList();

    MyList<E> toLeftRightList();
    MyList<E> toRightLeftList();


    MyList<E> toList();

    Iterator<E> rightLeftIterator();

    Iterator<E> leftRightIterator();
    Iterator<E> levelOrderIterator();

    boolean contains(E node);

    boolean add(E node);

    boolean remove(E node);

    void clear();
}
