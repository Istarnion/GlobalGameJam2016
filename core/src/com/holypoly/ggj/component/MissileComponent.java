package com.holypoly.ggj.component;

/**
 *
 * @author istarnion
 */
public class MissileComponent extends AbstractComponent {

    public float missileSpeed = 100;

    public float lifetime = 0.3f;

    public MissileComponent(int entityID) {
        super(entityID);
    }

}
