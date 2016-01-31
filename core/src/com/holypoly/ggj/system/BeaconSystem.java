package com.holypoly.ggj.system;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.IntArray;
import com.holypoly.ggj.component.AnimationComponent;
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

        int state = 0;

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
                        if(curBeacon.captureState < 25) {
                            curBeacon.captured = 0;
                            AnimationComponent ac = ((GraphicsSystem)game.systems[game.GFX]).getComponent(curBeacon.entityID);

                            if(curBeacon.captureState < -25) {
                                curBeacon.captured = -1;

                                ac.animation = ac.animations.get("purple");
                            }
                            else {
                                ac.animation = ac.animations.get("neutral");
                            }
                        }
                        if(curBeacon.captureState < -100) curBeacon.captureState = -100;
					}
                    else {
						curBeacon.captureState += delta*20f;
                        if(curBeacon.captureState > -25) {
                            curBeacon.captured = 0;
                            AnimationComponent ac = ((GraphicsSystem)game.systems[game.GFX]).getComponent(curBeacon.entityID);

                            if(curBeacon.captureState > 25) {
                                curBeacon.captured = 1;

                                ac.animation = ac.animations.get("yellow");
                            }
                            else {
                                ac.animation = ac.animations.get("neutral");
                            }
                        }
                        if(curBeacon.captureState > 100) curBeacon.captureState = 100;
					}
				}
			}

            state += curBeacon.captured;
		}

        if(state > 0) {
            game.score.x -= state*delta*4;
        }
        else {
            game.score.y += state*delta*4;
        }
	}
}
