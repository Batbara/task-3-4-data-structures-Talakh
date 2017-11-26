package by.tr.web.util;

import java.util.Iterator;

public interface MyIterator<E> extends Iterator<E> {

    boolean hasPrevious();

    E previous();

}
