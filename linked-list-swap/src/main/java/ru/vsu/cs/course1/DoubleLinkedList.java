package ru.vsu.cs.course1;

public class DoubleLinkedList<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size;

    public DoubleLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public void swapPairs() {
        if (head == null || head.next == null) {
            return; // Список пустой или содержит только один элемент
        }

        Node<T> current = head;
        head = head.next; // Новый head после первого обмена

        while (current != null && current.next != null) {
            Node<T> first = current;
            Node<T> second = current.next;
            Node<T> nextPair = second.next;

            // Обмен указателей
            first.next = nextPair;
            second.next = first;
            second.prev = first.prev;
            first.prev = second;

            // Обновление указателя prev следующего узла
            if (nextPair != null) {
                nextPair.prev = first;
            }

            // Обновление указателя next предыдущего узла
            if (second.prev != null) {
                second.prev.next = second;
            }

            current = nextPair;
        }

        // Обновление tail
        if (current != null) {
            tail = current;
        } else {
            tail = head;
            while (tail.next != null) {
                tail = tail.next;
            }
        }
    }

    public void addLast(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data cannot be null");
        }
        Node<T> newNode = new Node<>(data);
        if (head == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
    }

    public void addFirst(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data cannot be null");
        }
        Node<T> newNode = new Node<>(data);
        if (head == null) {
            head = tail = newNode;
        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
        size++;
    }

    public void removeLast() {
        if (tail == null) {
            throw new IllegalStateException("The list is empty");
        }
        if (head == tail) {
            head = tail = null;
        } else {
            tail = tail.prev;
            tail.next = null;
        }
        size--;
    }

    public void removeFirst() {
        if (head == null) {
            throw new IllegalStateException("The list is empty");
        }
        if (head == tail) {
            head = tail = null;
        } else {
            head = head.next;
            head.prev = null;
        }
        size--;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.data;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder("[");
        Node<T> current = head;
        while (current != null) {
            sb.append(current.data);
            if (current.next != null) {
                sb.append(", ");
            }
            current = current.next;
        }
        sb.append("]");
        return sb.toString();
    }
}