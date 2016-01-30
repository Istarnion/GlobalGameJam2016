package com.holypoly.ggj;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.holypoly.ggj.component.AnimationComponent;
import com.holypoly.ggj.component.PhysicsComponent;
import com.holypoly.ggj.screen.GameScreen;
import com.holypoly.ggj.system.GraphicsSystem;
import com.holypoly.ggj.system.PhysicsSystem;

/**
 *
 * @author istarnion
 */
public class EntityFactory {

    private GameScreen game;

    private GraphicsSystem gfxSystem;

    private PhysicsSystem physSystem;

    private int entityCount = 0;

    public EntityFactory(GameScreen game) {
        this.game = game;

        gfxSystem = (GraphicsSystem)game.systems[game.GFX];
        physSystem = game.getPhysicsSystem();
    }

    public int makeTest(float x, float y) {
        gfxSystem.animations.add(new AnimationComponent(entityCount));
        physSystem.addComponent(new PhysicsComponent(x, y, BodyDef.BodyType.StaticBody, 1));

        return entityCount++;
    }
}
