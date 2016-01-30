package com.holypoly.ggj.component;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;

/**
 *
 * @author istarnion
 */
public class PhysicsComponent extends Component {

    public BodyDef bodyDef;

    public FixtureDef fixtureDef;

    public Shape shape;

    public float radius, width, height;

    public PhysicsComponent(float x, float y, BodyType type, float radius) {
        bodyDef = new BodyDef();
        bodyDef.type = type;

        bodyDef.position.set(x, y);

        this.radius = radius;
        this.shape = Shape.CIRCLE;
    }

    public PhysicsComponent(float x, float y, BodyType type, float width, float height) {
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
