package com.lgm.collider;

import com.lgm.model.GameObject;

public interface Collider<T,V> {
    boolean collideBetween(GameObject o1,GameObject o2);
    void setRectangleLocation(T t,V v);
}
