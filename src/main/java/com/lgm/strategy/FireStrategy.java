package com.lgm.strategy;

import com.lgm.model.Tank;

@FunctionalInterface
public interface FireStrategy {
    void fire(Tank tank);
}
