package com.holypoly.ggj;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.holypoly.ggj.component.AnimationComponent;
import com.holypoly.ggj.component.BeaconComponent;
import com.holypoly.ggj.component.CameraComponent;
import com.holypoly.ggj.component.PhysicsComponent;
import com.holypoly.ggj.component.PlayerComponent;
import com.holypoly.ggj.screen.GameScreen;
import com.holypoly.ggj.system.BeaconSystem;
import com.holypoly.ggj.system.CameraSystem;
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

    private BeaconSystem beaconSystem;

    private CameraSystem cameraSystem;

    private AnimationFactory animFactory;
    
    private int entityCount = 0;

    public EntityFactory(GameScreen game) {
        this.game = game;

        animFactory = new AnimationFactory(game.assets);
        
        gfxSystem = (GraphicsSystem)game.systems[game.GFX];
        physSystem = game.getPhysicsSystem();
        playerSystem = (PlayerSystem)game.systems[game.PLAYER];
        beaconSystem = (BeaconSystem)game.systems[game.BEACON];
        cameraSystem = (CameraSystem)game.systems[game.CAMERA];
    }

    public int makePlayer(float x, float y, boolean playerOne) {
        AnimationComponent animComp = new AnimationComponent(entityCount);
        
        if(playerOne) {
            animComp.animations.put("flying", animFactory.getPurplePlayer());
        }
        else {
            animComp.animations.put("flying", animFactory.getYellowPlayer());
        }
        
        animComp.animation = animComp.animations.get("flying");
        
        gfxSystem.animations.add(animComp);
        physSystem.addComponent(new PhysicsComponent(entityCount, x, y, BodyDef.BodyType.DynamicBody, 1));
        playerSystem.pc.add(new PlayerComponent(entityCount));

        return entityCount++;
    }

    public int makeBeacon(float x, float y) {
        AnimationComponent animComp = new AnimationComponent(entityCount);
        animComp.animations.put("neutral", animFactory.getNeutralTorch());
        animComp.animations.put("yellow", animFactory.getYellowTorch());
        animComp.animations.put("purple", animFactory.getPurpleTorch());
        animComp.animation = animComp.animations.get("neutral");
        gfxSystem.animations.add(animComp);
        physSystem.addComponent(new PhysicsComponent(entityCount, x, y, BodyDef.BodyType.StaticBody, 1));
        beaconSystem.addBeaconComponent(new BeaconComponent(entityCount));

        return entityCount++;
    }

    public int makeCamera(int targetId, int cameraId) {

        cameraSystem.addCameraComponent(new CameraComponent(entityCount, targetId, cameraId));

        return entityCount++;
    }
}
