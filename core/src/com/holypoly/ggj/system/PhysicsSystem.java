package com.holypoly.ggj.system;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.holypoly.ggj.screen.GameScreen;

/**
 *
 * @author istarnion
 */
public class PhysicsSystem extends AbstractSystem {

    private World world;

    public PhysicsSystem(GameScreen game) {
        super(game);
    }

    public Vector2 getEntityPos(int entity) {
        return null;
    }

    @Override
    public void update(float delta) {
    }
}
