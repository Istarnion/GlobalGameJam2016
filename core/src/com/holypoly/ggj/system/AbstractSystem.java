package com.holypoly.ggj.system;

import com.holypoly.ggj.screen.GameScreen;

/**
 *
 * @author istarnion
 */
public abstract class AbstractSystem {

    protected GameScreen game;

    public AbstractSystem(GameScreen game) {
        this.game = game;
    }

    public abstract void update(float delta);

}
