package com.lgm.strategy;

import com.lgm.facade.GameModel;
import com.lgm.model.Tank;

import java.io.Serializable;

public interface FireStrategy extends Serializable {
    void fire(Tank tank);

    void setGameModel(GameModel gameModel);
}
