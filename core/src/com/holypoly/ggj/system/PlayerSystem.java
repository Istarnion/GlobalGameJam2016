package com.holypoly.ggj.system;

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
		for(PlayerComponent p : pc){
			InputState is = ((InputSystem)game.systems[game.INPUT]).inputStates[0];
            
			Body b = game.getPhysicsSystem().getBody(p.entityID);
			b.applyTorque(is.turn*delta*rotationSpeed, true);
			if(Math.abs(b.getAngularVelocity()) > maxAngVel){
				if(b.getAngularVelocity() > 0){
					b.setAngularVelocity(maxAngVel);
				}else{
					b.setAngularVelocity(-maxAngVel);
				}
			}
			Vector2 v2 = new Vector2(1, 0);
			v2.rotateRad(b.getAngle());
			if(is.accel){
				b.applyForceToCenter(v2.scl(delta*moveSpeed), true);
			}
			v2.set(b.getLinearVelocity());
			if(v2.len() > maxSpeed){
				b.setLinearVelocity(v2.setLength(maxSpeed));
			}
		}

	}


}
