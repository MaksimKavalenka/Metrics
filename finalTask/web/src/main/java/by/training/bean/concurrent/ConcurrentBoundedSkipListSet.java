package by.training.bean.concurrent;

import java.util.Collection;
import java.util.Comparator;
import java.util.SortedSet;
import java.util.concurrent.ConcurrentSkipListSet;

public class ConcurrentBoundedSkipListSet<E> extends ConcurrentSkipListSet<E> {

    private static final long serialVersionUID = 7653681659363720608L;

    private long              maxSize          = Long.MAX_VALUE;

    public ConcurrentBoundedSkipListSet() {
        super();
    }

    public ConcurrentBoundedSkipListSet(final long maxSize) {
        super();
        setMaxSize(maxSize);
    }

    public ConcurrentBoundedSkipListSet(final long maxSize, final Collection<? extends E> c) {
        super(c);
        setMaxSize(maxSize);
    }

    public ConcurrentBoundedSkipListSet(final long maxSize, final Comparator<? super E> c) {
        super(c);
        setMaxSize(maxSize);
    }

    public ConcurrentBoundedSkipListSet(final long maxSize, final SortedSet<E> s) {
        super(s);
        setMaxSize(maxSize);
    }

    public long getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(final long max) {
        maxSize = max;
        adjust();
    }

    private synchronized void adjust() {
        while (maxSize < size()) {
            pollFirst();
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
