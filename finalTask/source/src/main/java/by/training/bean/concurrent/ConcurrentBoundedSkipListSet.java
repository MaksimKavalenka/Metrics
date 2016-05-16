package by.training.bean.concurrent;

import java.util.Collection;
import java.util.Comparator;
import java.util.SortedSet;
import java.util.concurrent.ConcurrentSkipListSet;

public class ConcurrentBoundedSkipListSet<E> extends ConcurrentSkipListSet<E> {

    private static final long serialVersionUID = 7653681659363720608L;

    private int               maxSize          = Integer.MAX_VALUE;

    public ConcurrentBoundedSkipListSet() {
        super();
    }

    public ConcurrentBoundedSkipListSet(final int maxSize) {
        super();
        setMaxSize(maxSize);
    }

    public ConcurrentBoundedSkipListSet(final int maxSize, final Collection<? extends E> c) {
        super(c);
        setMaxSize(maxSize);
    }

    public ConcurrentBoundedSkipListSet(final int maxSize, final Comparator<? super E> c) {
        super(c);
        setMaxSize(maxSize);
    }

    public ConcurrentBoundedSkipListSet(final int maxSize, final SortedSet<E> s) {
        super(s);
        setMaxSize(maxSize);
    }

    public int getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(final int max) {
        maxSize = max;
        adjust();
    }

    private void adjust() {
        while (maxSize < size()) {
            remove(first());
        }
    }

    @Override
    public boolean add(final E e) {
        boolean modified = super.add(e);
        adjust();
        return modified;
    }

    @Override
    public boolean addAll(final Collection<? extends E> c) {
        boolean modified = super.addAll(c);
        adjust();
        return modified;
    }

}
