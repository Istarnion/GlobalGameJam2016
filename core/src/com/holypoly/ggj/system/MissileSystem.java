package com.holypoly.ggj.system;

import com.holypoly.ggj.component.MissileComponent;
import com.holypoly.ggj.screen.GameScreen;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author istarnion
 */
public class MissileSystem extends AbstractSystem {

    public List<MissileComponent> missiles;

    public MissileSystem(GameScreen game) {
        super(game);

        missiles = new ArrayList<MissileComponent>();
    }

    @Override
    public void update(float delta) {
        Iterator<MissileComponent> it = missiles.iterator();
        MissileComponent mc = null;
        while(it.hasNext()) {
            mc = it.next();

            mc.lifetime -= delta;
            if(mc.lifetime <= 0) {
                removeMissile(mc.entityID);
                it.remove();
            }
        }
    }

    private void removeMissile(int entityID) {
        game.getPhysicsSystem().removeEntity(entityID);
        ((GraphicsSystem)game.systems[game.GFX]).removeAnimation(entityID);
    }

}
