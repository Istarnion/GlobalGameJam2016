package com.holypoly.ggj.system;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.IntArray;
import com.holypoly.ggj.component.BeaconComponent;
import com.holypoly.ggj.screen.GameScreen;
import java.util.ArrayList;

/**
 *
 * @author istarnion
 */
public class BeaconSystem extends AbstractSystem {

	ArrayList<BeaconComponent> bc;

	public BeaconSystem(GameScreen game) {
		super(game);
		bc = new ArrayList<BeaconComponent>();
	}

	public void addBeaconComponent(BeaconComponent beaconComp) {
		bc.add(beaconComp);
	}

	@Override
	public void update(float delta) {

        IntArray players = game.players;

		for(int i = 0; i < bc.size(); i++) {
			BeaconComponent curBeacon = bc.get(i);
			Vector2 beaconPos = game.getPhysicsSystem().getEntityPos(curBeacon.entityID);

			for(int j=0; j<players.size; j++) {
				Vector2 playerPos = game.getPhysicsSystem().getEntityPos(players.get(j));


				if(beaconPos.dst2(playerPos) <= curBeacon.capRad) {
					//time += delta;
					//int score = (int)time*2;
					if((players.get(j) & 1) == 0) {
						curBeacon.captureState -= delta*20f;
                        if(curBeacon.captureState < 25) curBeacon.caputred = 0;
                        if(curBeacon.captureState < -25) curBeacon.caputred = -1;
                        if(curBeacon.captureState < -100) curBeacon.captureState = -100;
					}
                    else {
						curBeacon.captureState += delta*20f;
                        if(curBeacon.captureState > -25) curBeacon.caputred = 0;
                        if(curBeacon.captureState > 25) curBeacon.caputred = 1;
                        if(curBeacon.captureState > 100) curBeacon.captureState = 100;
					}
				}
			}
		}


	}

}
