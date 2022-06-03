import java.util.Iterator;

// new from line 27 to 52
public class LinkedList<T> implements List<T>, Iterable<T> {
    static class Node<T> {
        public T payload;
        public Node<T> next;
        public Node<T> previous;

        public Node(T element) {
            payload = element;
            next = null;
            previous = null;
        }
    }

    private Node<T> head;
    private Node<T> tail;
    private int size;

    public LinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    static class LinkedListIterator<T> implements Iterator<T> {

        private Node<T> currentNode;
        private final Node<T> tail;
        public LinkedListIterator(Node<T> head, Node<T> tail) {
            this.currentNode = head;
            this.tail = tail;
        }

        @Override
        public boolean hasNext() {
            return currentNode != tail.next;
        }

        @Override
        public T next() {
            Node<T> node = currentNode;
            currentNode = currentNode.next;
            return node.payload;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedList.LinkedListIterator<>(head, tail);
    }
    @Override
    public void add(T element) {
        Node<T> node = new Node<>(element);

        if (head == null) {
            head = node;
        } else {
            tail.next = node;
            node.previous = tail;
        }

        tail = node;
        ++size;
    }

    @Override
    public void put(T element, int position) {
        if (position < 0 || position >= size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        Node<T> node = new Node<>(element);
        if (size == 1) {
            node.next = head;
            head = node;
        } else {
            if (position == size - 1) {
                node.previous = tail.previous;
                tail.previous.next = node;
                node.next = tail;
            } else if (position == 0) {
                head.previous = node;
                node.next = head;
                head = node;
            } else {
                Node<T> targetNode = head;
                for (int i = 0; i < position; ++i) {
                    targetNode = targetNode.next;
                }
                Node<T> prevNode = targetNode.previous;

                node.previous = prevNode;
                prevNode.next = node;

                node.next = targetNode;
                targetNode.previous = node;
            }
        }
        ++size;
    }

    @Override
    public void remove(int position) {
        if (position < 0 || position >= size) {
            throw new IndexOutOfBoundsException();
        }

        if (size == 1) {
            head = null;
            tail = null;
        } else {
            Node<T> node = head;
            for (var i = 0; i < position; i++) {
                node = node.next;
            }
            Node<T> previousNode = node.previous;
            Node<T> nextNode = node.next;

            if (previousNode == null) {
                head = nextNode;
                head.previous = null;
            } else if (nextNode == null) {
                tail = previousNode;
                tail.next = null;
            } else {
                previousNode.next = nextNode;
                nextNode.previous = previousNode;
            }
        }
        --size;
    }

    @Override
    public int find(T element) {
        Node<T> node = head;

        for (int i = 0; i < size; ++i) {
            if (node.payload == element) {
                return i;
            }
            node = node.next;
        }

        return -1;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        Node<T> node = head;
        for (int i = 0; i < index; ++i) {
            node = node.next;
        }
        return node.payload;
    }

    @Override
    public void print() {
        Node<T> node = head;
        for (int i = 0; i < size; ++i) {
            System.out.println(node.payload);
            node = node.next;
        }
    }

}
