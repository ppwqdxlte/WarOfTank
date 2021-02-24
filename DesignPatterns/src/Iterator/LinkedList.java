package Iterator;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author:李罡毛
 * @date:2021/2/23 16:04
 */
public class LinkedList<T> implements List{

    private Node<T> head;
    private Node<T> tail;
    private AtomicInteger count = new AtomicInteger(0);

    @Override
    public List<T> add(Object o) {
        if (o == null) return this;
        if (head == null && tail == null) {
           head = tail = new Node();
           head.setValue((T) o);
           count.getAndIncrement();
           return this;
        }
        Node<T> oldTail = tail;
        Node<T> newTail = new Node();
        newTail.setValue((T) o);
        tail = newTail;
        oldTail.setNext(tail);
        tail.setPrevious(oldTail);
        count.getAndIncrement();
        return this;
    }

    @Override
    public int size() {
        return count.intValue();
    }

    @Override
    public Iterator iterator() {
        return new LinkedListIterator();
    }

    @Override
    public T get(int i) {
        if (count.intValue()<=i) throw new IndexOutOfBoundsException("下标越界了你不知道");
        Node<T> nn = head;//临时变量
        if (i== 0)return head.getValue();
        for (int j = 0; j < i; j++) {
            if (nn.getNext()!=null) {
                nn = nn.getNext();
            }
        }
        return nn.getValue();
    }

    @Override
    public String toString() {
        StringBuffer stringBuffer= new StringBuffer("LinkedList={");
        Node<T> h = head;
        for (int i = 0; i < count.intValue(); i++) {
            stringBuffer.append(h.getValue().toString());
            h = h.getNext();
            if (i!=count.intValue()-1) stringBuffer.append(",");
        }
        stringBuffer.append("}");
        return stringBuffer.toString();
    }

    private class LinkedListIterator implements Iterator{

        private int index = -1;

        @Override
        public boolean hasNext() {
            return index<count.intValue()-1;
        }

        @Override
        public Object next() {
            if (index == -1) {
                index++;
                return head.getValue();
            }
            return get(++index);
        }
    }

    private static class Node<T> {

        T value;
        Node<T> next;
        Node<T> previous;

        public void setValue(T value) {
            this.value = value;
        }

        public T getValue() {
            return value;
        }

        public Node<T> getNext() {
            return next;
        }

        public void setNext(Node<T> next) {
            this.next = next;
        }

        public Node<T> getPrevious() {
            return previous;
        }

        public void setPrevious(Node<T> previous) {
            this.previous = previous;
        }
    }
}
