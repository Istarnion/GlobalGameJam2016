package com.holypoly.ggj;

import com.badlogic.gdx.Game;
import com.holypoly.ggj.screen.GameScreen;

public class Main extends Game {

	@Override
	public void create () {
		super.setScreen(new GameScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}

    @Override
    public void dispose() {
        super.screen.dispose();
    }
}
