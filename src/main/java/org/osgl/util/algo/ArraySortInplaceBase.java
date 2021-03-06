package org.osgl.util.algo;

import org.osgl.$;
import org.osgl.exception.NotAppliedException;

import java.util.Comparator;

/**
 * Created with IntelliJ IDEA.
 * User: luog
 * Date: 12/11/13
 * Time: 11:09 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class ArraySortInplaceBase<T> implements ArraySort<T> {

    @Override
    public final T[] apply(T[] ts, Integer from, Integer to, Comparator<T> comp) throws NotAppliedException, $.Break {
        return sort(ts, from, to, comp);
    }

    protected abstract T[] sort0(T[] ts, int from, int to, Comparator<T> comp);

    public final T[] sort(T[] ts, int from, int to, Comparator<T> comp) {
        Util.checkIndex(ts, from, to);
        if (null == comp) {
            comp = $.F.NATURAL_ORDER;
        }
        if (to == from) {
            return ts;
        }
        if (to < from) {
            return sort0(ts, to, from, comp);
        } else {
            return sort0(ts, from, to, comp);
        }
    }

}
