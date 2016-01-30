package com.holypoly.ggj;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.holypoly.ggj.component.AnimationComponent;
import com.holypoly.ggj.component.PhysicsComponent;
import com.holypoly.ggj.component.PlayerComponent;
import com.holypoly.ggj.screen.GameScreen;
import com.holypoly.ggj.system.GraphicsSystem;
import com.holypoly.ggj.system.PhysicsSystem;
import com.holypoly.ggj.system.PlayerSystem;

/**
 *
 * @author istarnion
 */
public class EntityFactory {

    private GameScreen game;

    private GraphicsSystem gfxSystem;

    private PhysicsSystem physSystem;
    
    private PlayerSystem playerSystem;

    private int entityCount = 0;

    public EntityFactory(GameScreen game) {
        this.game = game;

        gfxSystem = (GraphicsSystem)game.systems[game.GFX];
        physSystem = game.getPhysicsSystem();
        playerSystem = (PlayerSystem)game.systems[game.PLAYER];
    }

    public int makeTest(float x, float y) {
        gfxSystem.animations.add(new AnimationComponent(entityCount));
        physSystem.addComponent(new PhysicsComponent(entityCount, x, y, BodyDef.BodyType.DynamicBody, 1));
        playerSystem.pc.add(new PlayerComponent(entityCount));
        
        return entityCount++;
    }
}
