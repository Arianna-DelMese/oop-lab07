package it.unibo.inner.impl;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import javax.annotation.processing.Filer;

import it.unibo.inner.api.IterableWithPolicy;
import it.unibo.inner.api.Predicate;

public final class IterableWithPolicyImpl<T> implements IterableWithPolicy<T> {

    private final List<T> elements;
    private Predicate<T> filter;

    public IterableWithPolicyImpl(final T[] elements) {
        this(elements, new Predicate<>() {
                @Override
                public boolean test(T elem) {
                    return true;
                }
            }
        );
    }

    public IterableWithPolicyImpl(final T[] elements, final Predicate<T> filter) {
        this.elements = List.of(elements);
        this.filter = filter;
    }

    public Iterator<T> iterator() {
        return new InnerIterator();
    }

    @Override 
    public void setIterationPolicy(final Predicate<T> filter) {
        this.filter = filter;
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
            while (current < elements.size()){
                final T elem = elements.get(current);
                if (filter.test(elem)) {
                    return true;
                }
                current++;
            }
            return false;
        }

        @Override 
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
