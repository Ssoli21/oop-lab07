package it.unibo.inner.impl;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import it.unibo.inner.api.IterableWithPolicy;
import it.unibo.inner.api.Predicate;

public class IterableWithPolicyImpl<T> implements IterableWithPolicy<T>{

    final private List<T> elements;
    private Predicate<T> filter;

    /**
     * Iterates over all the elements.
     * @param originalElements elements to iterate over
     */
    public IterableWithPolicyImpl(T[] originalElements) {
        this(originalElements, new Predicate<T>() {
            public boolean test(T elem){
                return true;
            }
        });
    }  

    /**
     * Iterates only over the elements that satisfy the filter
     * @param originalElements elements to iterate over
     * @param filter filter that is applied
     */
    public IterableWithPolicyImpl(T[] originalElements, Predicate<T> filter){
        this.elements = List.of(originalElements);
        this.filter = filter;
    }



    private class NewIterator implements Iterator<T> {
        private int current = 0;

        public boolean hasNext(){
            while (this.current < elements.size()){
                T elemToTest = elements.get(current);
                if(filter.test(elemToTest)){
                    return true;
                }
                current++;
            }
            return false;
        }

        public T next() {
            if (hasNext()){
                return elements.get(current++);
            }
            throw new NoSuchElementException();
        }
        
    }

    public Iterator<T> iterator() {
       return new NewIterator();
    }

    public void setIterationPolicy(Predicate<T> filter) {
       this.filter = filter;
    }

   
}
