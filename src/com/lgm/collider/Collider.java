package com.lgm.collider;

import com.lgm.model.GameObject;

import java.io.Serializable;

public interface Collider<T,V> extends Serializable {
    boolean collideBetween(GameObject o1, GameObject o2);
    void setRectangleLocation(T t, V v);
}
