package com.lgm.strategy;

import com.lgm.model.Tank;

import java.io.Serializable;

@FunctionalInterface
public interface FireStrategy extends Serializable {
    void fire(Tank tank);
}
