package com.holypoly.ggj;

import com.holypoly.ggj.component.AnimationComponent;
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

    public int makeTest() {
        gfxSystem.animations.add(new AnimationComponent(entityCount));

        return entityCount++;
    }
}
