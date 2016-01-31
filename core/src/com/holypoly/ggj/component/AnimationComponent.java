package com.holypoly.ggj.component;

import com.badlogic.gdx.graphics.g2d.Animation;
import java.util.HashMap;

/**
 *
 * @author istarnion
 */
public class AnimationComponent extends AbstractComponent {

    public HashMap<String, Animation> animations;
    
    public Animation animation = null;
    
    public float animTime;
    
    public AnimationComponent(int entityID) {
        super(entityID);
        
        animations = new HashMap<String, Animation>();
    }

}
