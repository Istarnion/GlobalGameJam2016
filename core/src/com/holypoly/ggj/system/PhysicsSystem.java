package com.holypoly.ggj.system;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.IntMap;
import com.holypoly.ggj.component.PhysicsComponent;
import com.holypoly.ggj.screen.GameScreen;

/**
 *
 * @author istarnion
 */
public class PhysicsSystem extends AbstractSystem {

    private World world;

    private float accumulator;

    private final float TIME_STEP = 0.01666666f;

    private IntMap<Body> bodies;

    private IntMap<PhysicsComponent> components;

    public PhysicsSystem(GameScreen game) {
        super(game);

        components = new IntMap<PhysicsComponent>();

        Box2D.init();
        world = new World(Vector2.Zero, true);

        bodies = new IntMap<Body>();
    }

    @Override
    public void update(float delta) {

        float frameTime = Math.min(delta, 0.25f);
        accumulator += frameTime;
        while (accumulator >= TIME_STEP) {
            world.step(TIME_STEP, 6, 2);
            accumulator -= TIME_STEP;
        }
    }

    public void addComponent(PhysicsComponent comp) {
        components.put(comp.entityID, comp);

        Body b = world.createBody(comp.bodyDef);
        b.setAngularDamping(0.95f);
        b.setLinearDamping(0.85f);
        FixtureDef fixtureDef = new FixtureDef();

        Shape shape = null;
        if(comp.shape == PhysicsComponent.Shape.CIRCLE) {
            shape = new CircleShape();
            ((CircleShape)shape).setRadius(comp.radius);
        }
        else {
            shape = new PolygonShape();
            ((PolygonShape)shape).setAsBox(comp.width, comp.height);
        }

        fixtureDef.shape = shape;
        fixtureDef.density = 1.0f;
        fixtureDef.friction = 0.5f;
        fixtureDef.restitution = 0.3f;

        b.createFixture(fixtureDef);

        bodies.put(comp.entityID, b);

        shape.dispose();
    }
    
    public Body getBody(int entityID){
    	return bodies.get(entityID);
    }

    public Vector2 getEntityPos(int entity) {
        if(bodies.get(entity) != null) {
            return bodies.get(entity).getPosition();
        }
        return null;
    }

    public Vector2 getEntityDimensions(int entity) {
        PhysicsComponent comp = components.get(entity);
        if(comp != null) {
            comp = components.get(entity);
            if(comp.shape == PhysicsComponent.Shape.CIRCLE) {
                float r = comp.radius*2;
                return new Vector2(r, r);
            }
            else {
                return new Vector2(comp.width, comp.height);
            }
        }
        return null;
    }

    public World getWorld() {
        return world;
    }
}
