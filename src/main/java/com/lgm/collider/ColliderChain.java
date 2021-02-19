package com.lgm.collider;

import com.lgm.model.GameObject;

import java.util.LinkedList;
import java.util.List;

public class ColliderChain implements Collider{

    private List<Collider> colliders = new LinkedList<>();

    public void add(Collider collider){
        colliders.add(collider);
    }

    @Override
    public boolean collideBetween(GameObject o1, GameObject o2) {

        for (int i = 0; i < colliders.size(); i++) {
            if (!colliders.get(i).collideBetween(o1,o2)) break;
        }
        return false;
    }

    @Override
    public void setRectangleLocation(Object o, Object o2) {
        return;
    }
}
