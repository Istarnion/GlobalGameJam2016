package com.holypoly.ggj.system;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector2;
import com.holypoly.ggj.component.BeaconComponent;
import com.holypoly.ggj.component.PlayerComponent;
import com.holypoly.ggj.screen.GameScreen;

/**
 *
 * @author istarnion
 */
public class BeaconSystem extends AbstractSystem {

	ArrayList<BeaconComponent> bc;
	ArrayList<PlayerComponent> pc;
	
	public BeaconSystem(GameScreen game) {
		super(game);
		bc = new ArrayList<BeaconComponent>();
	}

	public void addBeaconComponent(BeaconComponent beaconComp){
		bc.add(beaconComp);
	}
	
	@Override
	public void update(float delta) {
		float time = 0;
		
		for(int i = 0; i < bc.size(); i++){
			BeaconComponent curBeacon = bc.get(i);
			Vector2 beaconPos = game.getPhysicsSystem().getEntityPos(curBeacon.entityID);
			
			for(int j = 0; j < pc.size(); j++){
				PlayerComponent curPlayer = pc.get(j);
				Vector2 playerPos = game.getPhysicsSystem().getEntityPos(curPlayer.entityID);
				
				
				if(beaconPos.dst2(playerPos) <= curBeacon.entityID){
					time += delta;
					int score = (int)time*2;
					if(pc.get(j).team == -1){
						score = -score;
						curBeacon.captureState -= score;
					}else{
						curBeacon.captureState += score;
					}
				}
			}
		}
	}

}
