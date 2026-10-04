package Java5.src.collection;

import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

public class CustomArrayList<E> extends AbstractList<E> implements RandomAccess {

    private static final int DEFAULT_CAPACITY = 10;

    private Object[] elements;
    private int size;

    public CustomArrayList() {
        this.elements = new Object[DEFAULT_CAPACITY];
    }

    public CustomArrayList(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("initialCapacity must be >= 0");
        }

        this.elements = new Object[initialCapacity];
    }

    public CustomArrayList(Collection<? extends E> source) {
        this(source.size());
        addAll(source);
    }

    @Override
    public E get(int index) {
        checkIndex(index);
        return elementAt(index);
    }

    @Override
    public E set(int index, E element) {
        checkIndex(index);

        E oldValue = elementAt(index);
        elements[index] = element;

        return oldValue;
    }

    @Override
    public boolean add(E element) {
        ensureCapacity(size + 1);

        elements[size++] = element;
        modCount++;

        return true;
    }

    @Override
    public void add(int index, E element) {
        checkPosition(index);
        ensureCapacity(size + 1);

        System.arraycopy(
                elements,
                index,
                elements,
                index + 1,
                size - index
        );

        elements[index] = element;
        size++;
        modCount++;
    }
    @Override
    public E remove(int index) {
        checkIndex(index);

        E oldValue = elementAt(index);

        int moved = size - index - 1;

        if (moved > 0) {
            System.arraycopy(
                    elements,
                    index + 1,
                    elements,
                    index,
                    moved
            );
        }

        elements[--size] = null;
        modCount++;

        return oldValue;
    }

    @Override
    public void clear() {
        Arrays.fill(elements, 0, size, null);
        size = 0;
        modCount++;
    }

    @Override
    public int size() {
        return size;
    }

    private void ensureCapacity(int requiredCapacity) {
        if (requiredCapacity <= elements.length) {
            return;
        }

        int current = elements.length;

        int grown = current == 0
                ? DEFAULT_CAPACITY
                : current + (current >> 1);

        elements = Arrays.copyOf(
                elements,
                Math.max(grown, requiredCapacity)
        );
    }

    @SuppressWarnings("unchecked")
    private E elementAt(int index) {
        return (E) elements[index];
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(
                    "index=" + index + ", size=" + size
            );
        }
    }

    private void checkPosition(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException(
                    "index=" + index + ", size=" + size
            );
        }
    }
}