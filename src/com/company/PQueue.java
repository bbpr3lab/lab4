package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;

public class PQueue<T extends Comparable<T>> implements Iterable<T> {
    ArrayList<T> list;

    public PQueue() {
        list = new ArrayList<>();
    }

    void clear() {
        list.clear();
    }

    int size() {
        return list.size();
    }

    void push(T t) {
        list.add(t);
    }

    T pop() {
        if (size() == 0) {
            throw new EmptyQueueException();
        }
        T max = Collections.max(list);
        list.remove(max);
        return max;
    }

    T top() {
        if (size() == 0) {
            throw new EmptyQueueException();
        }
        return Collections.max(list);
    }

    @Override
    public Iterator<T> iterator() {
        ArrayList<T> sortedCopy = new ArrayList<T>(list);
        Collections.sort(sortedCopy);
        Collections.reverse(sortedCopy);
        return sortedCopy.iterator();
    }
}
