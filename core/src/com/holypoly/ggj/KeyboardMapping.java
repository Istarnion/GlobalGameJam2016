package com.holypoly.ggj;

import com.badlogic.gdx.Input.Keys;

/**
 *
 * @author istarnion
 */
public class KeyboardMapping {

    public int[] keys = new int[5];

    public static final int
            TURN_LEFT = 0,
            TURN_RIGHT = 1,
            ACCEL = 2,
            FIRE  = 3,
            PAUSE = 4;

    public KeyboardMapping(boolean playerOne) {
        keys[PAUSE] = Keys.ESCAPE;

        if(playerOne) {
            keys[TURN_LEFT] = Keys.A;
            keys[TURN_RIGHT] = Keys.D;
            keys[ACCEL] = Keys.W;
            keys[FIRE] = Keys.SPACE;
        }
        else {
            keys[TURN_LEFT] = Keys.LEFT;
            keys[TURN_RIGHT] = Keys.RIGHT;
            keys[ACCEL] = Keys.UP;
            keys[FIRE] = Keys.CONTROL_RIGHT;
        }
    }
}
