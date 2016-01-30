package com.holypoly.ggj.system;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.holypoly.ggj.component.CameraComponent;
import com.holypoly.ggj.screen.GameScreen;
import java.util.ArrayList;

/**
 *
 * @author istarnion
 */
public class CameraSystem extends AbstractSystem {

    private ArrayList<CameraComponent> camComponents;

    public CameraSystem(GameScreen game) {
        super(game);

        camComponents = new ArrayList<CameraComponent>();
    }

    @Override
    public void update(float delta) {

        for(CameraComponent cc : camComponents) {
            Vector2 targetPos = game.getPhysicsSystem().getEntityPos(cc.targetID);
            OrthographicCamera cam = ((GraphicsSystem)game.systems[game.GFX]).cameras[cc.cameraID];

            cam.translate(targetPos.x-cam.position.x, targetPos.y-cam.position.y);
        }

    }

    public void addCameraComponent(CameraComponent comp) {
        camComponents.add(comp);
    }

}
