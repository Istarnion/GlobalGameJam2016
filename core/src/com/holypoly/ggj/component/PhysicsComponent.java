package com.holypoly.ggj.component;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;

/**
 *
 * @author istarnion
 */
public class PhysicsComponent extends AbstractComponent {

    public BodyDef bodyDef;

    public FixtureDef fixtureDef;

    public Shape shape;

    public float radius, width, height;

    public PhysicsComponent(int entityID, float x, float y, BodyType type, float radius) {
        super(entityID);
    	bodyDef = new BodyDef();
        bodyDef.type = type;

        bodyDef.position.set(x, y);

        this.radius = radius;
        this.shape = Shape.CIRCLE;
    }

    public PhysicsComponent(int entityID, float x, float y, BodyType type, float width, float height) {
    	super(entityID);
    	bodyDef = new BodyDef();
        bodyDef.type = type;

        bodyDef.position.set(x, y);

        this.width = width;
        this.height = height;
        this.shape = Shape.RECTANGLE;
    }

    public enum Shape {
        CIRCLE,
        RECTANGLE
    }
}
