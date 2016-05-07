package by.training.bean.set;

import java.util.Collection;
import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

public class BoundedTreeSet<E> extends TreeSet<E> {

    private static final long serialVersionUID = 7653681659363720608L;

    private int               maxSize          = Integer.MAX_VALUE;

    public BoundedTreeSet() {
        super();
    }

    public BoundedTreeSet(final int maxSize) {
        super();
        this.setMaxSize(maxSize);
    }

    public BoundedTreeSet(final int maxSize, final Collection<? extends E> c) {
        super(c);
        this.setMaxSize(maxSize);
    }

    public BoundedTreeSet(final int maxSize, final Comparator<? super E> c) {
        super(c);
        this.setMaxSize(maxSize);
    }

    public BoundedTreeSet(final int maxSize, final SortedSet<E> s) {
        super(s);
        this.setMaxSize(maxSize);
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
    public boolean add(final E item) {
        if (!super.contains(item)) {
            boolean out = super.add(item);
            adjust();
            return out;
        }
        return false;
    }

    @Override
    public boolean addAll(final Collection<? extends E> c) {
        TreeSet<E> set = new TreeSet<>();
        for (E e : c) {
            if (!super.contains(e)) {
                set.add(e);
            }
        }
        boolean out = super.addAll(set);
        adjust();
        return out;
    }

}
