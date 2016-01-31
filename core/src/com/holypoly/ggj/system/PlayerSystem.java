package com.holypoly.ggj.system;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.holypoly.ggj.component.InputState;
import com.holypoly.ggj.component.PlayerComponent;
import com.holypoly.ggj.screen.GameScreen;
import java.util.ArrayList;

/**
 *
 * @author istarnion
 */
public class PlayerSystem extends AbstractSystem {
	public ArrayList<PlayerComponent> pc;
	public float rotationSpeed;
	public float maxAngVel;
	public float moveSpeed;
	public float maxSpeed;

	public PlayerSystem(GameScreen game) {
		super(game);
		pc = new ArrayList<PlayerComponent>();
		rotationSpeed = 3000;
		maxAngVel = (float)(2*Math.PI);
		moveSpeed = 2000;
		maxSpeed = 250;
	}



	@Override
	public void update(float delta) {
        int playerNumber = 0;
		for(PlayerComponent p : pc){

			InputState is = ((InputSystem)game.systems[game.INPUT]).inputStates[playerNumber];

			Body b = game.getPhysicsSystem().getBody(p.entityID);
			b.applyTorque(is.turn*delta*rotationSpeed, true);
			if(Math.abs(b.getAngularVelocity()) > maxAngVel){
				if(b.getAngularVelocity() > 0){
					b.setAngularVelocity(maxAngVel);
				}else{
					b.setAngularVelocity(-maxAngVel);
				}
			}
			Vector2 v2 = new Vector2(0, 1);
			v2.rotateRad(b.getAngle());
			if(is.accel){
				b.applyForceToCenter(v2.scl(delta*moveSpeed), true);
			}
			v2.set(b.getLinearVelocity());
			if(v2.len() > maxSpeed){
				b.setLinearVelocity(v2.setLength(maxSpeed));
			}

            if(is.shoot && p.cooldown <= 0) {
                Vector2 v = b.getPosition();
                v2 = new Vector2(0, 1.5f);
                v2.setAngleRad(b.getAngle()+(float)Math.PI/2+MathUtils.random(-0.2f, 0.2f));

                v.add(v2);
                game.entityFactory.makeMissile(v.x, v.y, v2.nor(), (p.entityID&1)!=0);
                p.cooldown = p.delay;
            }
            p.cooldown -= delta;
            if(p.cooldown < 0) p.cooldown = 0;

            playerNumber++;
		}
	}
}
