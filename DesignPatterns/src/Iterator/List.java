package Iterator;

/**
 * @param <E>
 * List接口
 */
public interface List<E> extends Collection{
    List<E> add(Object e); //父类接口有此方法，子类接口不必重写
    E get(int i);
}
