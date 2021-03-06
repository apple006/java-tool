package org.osgl.util;

import java.util.NoSuchElementException;

/**
 * Created with IntelliJ IDEA.
 * User: luog
 * Date: 27/10/13
 * Time: 10:51 AM
 * To change this template use File | Settings | File Templates.
 */
abstract class CursorBase<T> implements C.List.Cursor<T> {
    private boolean defined_ = false;
    private T cur_;

    private C.List.Cursor<T> cur(T t) {
        cur_ = t;
        defined_ = true;
        return this;
    }

    @Override
    public final boolean isDefined() {
        return defined_;
    }


    protected abstract T next();

    @Override
    public C.List.Cursor<T> forward() {
        cur(next());
        return this;
    }

    protected abstract T previous();

    @Override
    public C.List.Cursor<T> backward() {
        cur(previous());
        return this;
    }

    @Override
    public T get() throws NoSuchElementException {
        if (!defined_) {
            throw new NoSuchElementException();
        }
        return cur_;
    }

    /**
     * Update the current element with new value {@code t}
     * @param t the new value of the current element
     */
    protected abstract void update(T t);

    @Override
    public C.List.Cursor<T> set(T t) throws IndexOutOfBoundsException, NullPointerException {
        update(t);
        return this;
    }

    protected abstract void remove();

    @Override
    public C.List.Cursor<T> drop() throws NoSuchElementException {
        remove();
        cur(next());
        return this;
    }

    /**
     * Insert an element before this cursor
     * @param t the element to be inserted
     */
    protected abstract void add(T t);

    @Override
    public C.List.Cursor<T> prepend(T t) throws IndexOutOfBoundsException {
        previous();
        add(t);
        next();
        return this;
    }

    @Override
    public C.List.Cursor<T> append(T t) {
        add(t);
        previous();
        return this;
    }

    public String toString() {
        return get() + "@" + index();
    }

}
