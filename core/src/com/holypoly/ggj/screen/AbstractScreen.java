package com.holypoly.ggj.screen;

import com.badlogic.gdx.Screen;
import com.holypoly.ggj.Main;

/**
 *
 * @author istarnion
 */
public abstract class AbstractScreen implements Screen {

    protected Main main;

    public AbstractScreen(Main main) {
        this.main = main;
    }
}
