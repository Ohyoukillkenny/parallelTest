package utils;

import java.util.Iterator;

public interface Stream<A> {
    public Iterator<A> infinite();

    public Iterator<A> finite(long N);
}
