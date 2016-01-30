package com.holypoly.ggj.screen;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.holypoly.ggj.Main;

/**
 *
 * @author istarnion
 */
public class MenuScreen extends AbstractScreen {

    private SpriteBatch spriteBatch;

    public MenuScreen(Main main) {
        super(main);

        spriteBatch = new SpriteBatch();
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        spriteBatch.begin();
        
        spriteBatch.end();
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
    }

}
