package com.holypoly.ggj;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.math.Vector2;
import com.holypoly.ggj.screen.GameScreen;
import com.holypoly.ggj.screen.ScoreScreen;

public class Main extends Game {

	@Override
	public void create () {
        GameScreen gs = new GameScreen(this);
        while(!gs.assets.update());

		super.setScreen(gs);
	}

	@Override
	public void render () {
		super.render();
	}

    public void gotoScoreScreen(Vector2 score) {
        super.screen.dispose();
        super.setScreen(new ScoreScreen(this, score));
    }

    @Override
    public void dispose() {
        super.screen.dispose();
    }
}
