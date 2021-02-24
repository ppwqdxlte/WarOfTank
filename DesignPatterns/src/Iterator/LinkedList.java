package Iterator;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author:李罡毛
 * @date:2021/2/23 16:04
 */
public class LinkedList implements List{

    private Node head;
    private Node tail;
    private AtomicInteger count = new AtomicInteger(0);

    @Override
    public List add(Object o) {
        if (o == null) return this;
        if (head == null && tail == null) {
           head = tail = new Node();
           head.setValue(o);
           count.getAndIncrement();
           return this;
        }
        Node oldTail = tail;
        Node newTail = new Node();
        newTail.setValue(o);
        tail = newTail;
        oldTail.setNext(tail);
        tail.setPrevious(oldTail);
        count.getAndIncrement();
        return this;
    }

    @Override
    public Object get(int i) {
        if (count.intValue()<=i) throw new IndexOutOfBoundsException("下标越界了你不知道");
        Node nn = head;//临时变量
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
        Node h = head;
        for (int i = 0; i < count.intValue(); i++) {
            stringBuffer.append(h.getValue().toString());
            h = h.getNext();
            if (i!=count.intValue()-1) stringBuffer.append(",");
        }
        stringBuffer.append("}");
        return stringBuffer.toString();
    }
}
