package com.holypoly.ggj;

import com.badlogic.gdx.Input.Keys;

/**
 *
 * @author istarnion
 */
public class KeyboardMapping {

    public int turnLeft, turnRight, accel, fire, pause;

    public KeyboardMapping(boolean playerOne) {
        pause = Keys.ESCAPE;

        if(playerOne) {
            turnLeft = Keys.A;
            turnRight = Keys.D;
            accel = Keys.W;
            fire = Keys.SPACE;
        }
        else {
            turnLeft = Keys.LEFT;
            turnRight = Keys.RIGHT;
            accel = Keys.UP;
            fire = Keys.CONTROL_RIGHT;
        }
    }
}
