package Iterator;

/**
 * @author:李罡毛
 * @date:2021/2/23 18:48
 */
public class Node<T> {

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
