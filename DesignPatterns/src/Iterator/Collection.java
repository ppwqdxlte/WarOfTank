package Iterator;

/**
 * @param <E>
 * 集合接口
 */
public interface Collection<E> {
    Collection<E> add(E o);
    int size();
    Iterator iterator();
}
