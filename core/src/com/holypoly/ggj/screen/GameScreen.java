package com.holypoly.ggj.screen;

import com.holypoly.ggj.EntityFactory;
import com.holypoly.ggj.Main;
import com.holypoly.ggj.system.AbstractSystem;
import com.holypoly.ggj.system.GraphicsSystem;
import com.holypoly.ggj.system.InputSystem;
import com.holypoly.ggj.system.PhysicsSystem;
import com.holypoly.ggj.system.PlayerSystem;

/**
 *
 * @author istarnion
 */
public class GameScreen extends AbstractScreen {

    /** System ID **/
    public final int
            INPUT = 0,
            GFX = 1,
            PHYS = 2,
            PLAYER = 3;
    		

    public AbstractSystem[] systems = {
        new InputSystem(this),
        new GraphicsSystem(this),
        new PhysicsSystem(this),
        new PlayerSystem(this)
    };

    public EntityFactory entityFactory;

    public GameScreen(Main main) {
        super(main);

        entityFactory = new EntityFactory(this);

        entityFactory.makeTest(0, 0);
    }

    @Override
    public void show() {
        System.out.println("show");
    }

    @Override
    public void render(float delta) {
        for(AbstractSystem s : systems) {
            s.update(delta);
        }
    }

    @Override
    public void resize(int width, int height) {
        ((GraphicsSystem)systems[GFX]).resize(width, height);
    }

    @Override
    public void pause() {
        System.out.println("pause");
    }

    @Override
    public void resume() {
        System.out.println("resume");
    }

    @Override
    public void hide() {
        System.out.println("hide");
    }

    @Override
    public void dispose() {
        System.out.println("dispose");
    }

    public PhysicsSystem getPhysicsSystem() {
        return (PhysicsSystem)systems[PHYS];
    }
}
