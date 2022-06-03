import java.util.Iterator;

// new from line 26 to 52
@SuppressWarnings("unchecked")
public class ArrayList<T> implements List<T>, Iterable<T> {
    private T[] arr;
    private int size = 0;
    public int capacity;
    private final int DEFAULT_ARRAYLIST_SIZE = 1000;

    public ArrayList() {
        this.arr = (T[]) new Object[DEFAULT_ARRAYLIST_SIZE];
        this.capacity = DEFAULT_ARRAYLIST_SIZE;
    }

    public ArrayList(int n) {
        this.size = n;
        if (n <= DEFAULT_ARRAYLIST_SIZE) {
            n = DEFAULT_ARRAYLIST_SIZE;
        } else {
            n += DEFAULT_ARRAYLIST_SIZE;
        }
        this.capacity = n;
        this.arr = (T[]) new Object[n];
    }

    static class ArrayListIterator<T> implements Iterator<T> {

        private int currentPosition;
        private final int size;
        private final ArrayList<T> iterableArrayList;

        public ArrayListIterator(ArrayList<T> iterableArrayList, int size) {
            this.iterableArrayList = iterableArrayList;
            this.size = size;
            this.currentPosition = 0;
        }

        @Override
        public boolean hasNext() {
            return currentPosition != size;
        }

        @Override
        public T next() {
            return iterableArrayList.get(currentPosition++);
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayListIterator<>(this, this.size);
    }

    @Override
    public void add(T element) {
        if (size < capacity) {
            arr[size++] = element;
            return;
        }
        T[] newArr = (T[]) new Object[capacity * 2];
        for (int i = 0; i < size; ++i) {
            newArr[i] = arr[i];
        }
        arr = newArr;
        arr[size++] = element;
        capacity *= 2;
    }

    @Override
    public void put(T element, int position) {
        if (position < 0 || position >= size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        if (size < capacity) {
            for (int i = size - 1; i >= position; --i) {
                arr[i + 1] = arr[i];
            }
        } else {
            T[] newArr = (T[]) new Object[capacity * 2];
            for (int i = 0; i < position; ++i) {
                newArr[i] = arr[i];
            }
            for (int i = position; i < size; ++i) {
                newArr[i + 1] = arr[i];
            }
            arr = newArr;
            capacity *= 2;
        }
        arr[position] = element;
        ++size;
    }

    @Override
    public void remove(int position) {
        if (position < 0 || position >= size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        T[] newArr = (T[]) new Object[size];
        for (int i = 0; i < position; ++i) {
            newArr[i] = arr[i];
        }
        for (int i = position; i < size - 1; ++i) {
            newArr[i] = arr[i + 1];
        }
        arr = newArr;
        --size;
    }

    @Override
    public int find(T element) {
        for (int i = 0; i < size; ++i) {
            if (arr[i] == element) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return arr[index];
    }

    @Override
    public void print() {
        for (int i = 0; i < size; ++i) {
            System.out.println(arr[i]);
        }
    }
}