package it.unibo.inner.impl;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import it.unibo.inner.api.IterableWithPolicy;
import it.unibo.inner.api.Predicate;

public final class IterableWithPolicyImpl<T> implements IterableWithPolicy<T> {

    private final List<T> elements;

    public IterableWithPolicyImpl(final T[] elements) {
        this.elements = List.of(elements);
    }

    public Iterator<T> iterator() {
        return new InnerIterator();
    }

    @Override 
    public void setIterationPolicy(Predicate<T> filter) {

    }

    private class InnerIterator implements Iterator<T> {
        
        private int current = 0;

        @Override 
        public T next() {
            if (hasNext()) {
                return elements.get(current);
            }
            throw new NoSuchElementException();
        }

        @Override 
        public boolean hasNext() {
            if (current < elements.size()){
                return true;
            }
            return false;
        }

        @Override 
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
