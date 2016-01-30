package com.holypoly.ggj.system;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.ControllerListener;
import com.badlogic.gdx.controllers.PovDirection;
import com.badlogic.gdx.math.Vector3;
import com.holypoly.ggj.KeyboardMapping;
import com.holypoly.ggj.component.InputState;
import com.holypoly.ggj.screen.GameScreen;

/**
 *
 * @author istarnion
 */
public class InputSystem extends AbstractSystem implements ControllerListener{

    public static final int
            LEFT_KEYBOARD = 0,
            RIGHT_KEYBOARD = 1,
            CONTROLLER_0 = 2,
            CONTROLLER_1 = 3,
            CONTROLLER_2 = 4,
            CONTROLLER_3 = 5;

    public InputState[] inputStates;

    public int[] inputDevices = {
        LEFT_KEYBOARD,
        RIGHT_KEYBOARD,
        CONTROLLER_0,
        CONTROLLER_1
    };

    public KeyboardMapping
            left = new KeyboardMapping(false),
            right = new KeyboardMapping(true);

    public InputSystem(GameScreen game) {
        super(game);
        this.inputStates = new InputState[4];
        for(int i = 0; i<inputStates.length; i++) {
            inputStates[i] = new InputState();
        }
    }

    @Override
    public void update(float delta) {

        for(int i=0; i<inputDevices.length; i++) {
            switch(inputDevices[i]) {
                case LEFT_KEYBOARD:
                    inputStates[i].accel = Gdx.input.isKeyPressed(left.accel);
                    inputStates[i].turn = 0;
                    inputStates[i].turn += (Gdx.input.isKeyPressed(left.turnLeft)?1:0);
                    inputStates[i].turn += (Gdx.input.isKeyPressed(left.turnRight)?-1:0);
                    inputStates[i].shoot = Gdx.input.isKeyPressed(left.fire);
                    break;
                case RIGHT_KEYBOARD:
                    inputStates[i].accel = Gdx.input.isKeyPressed(right.accel);
                    inputStates[i].turn = 0;
                    inputStates[i].turn += (Gdx.input.isKeyPressed(right.turnLeft)?1:0);
                    inputStates[i].turn += (Gdx.input.isKeyPressed(right.turnRight)?-1:0);
                    inputStates[i].shoot = Gdx.input.isKeyPressed(right.fire);
                    break;
                default:
                    // TODO: Add controller support
                    break;

            }
        }

    }

    @Override
    public void connected(Controller controller) {

    }

    @Override
    public void disconnected(Controller controller) {
    }

    @Override
    public boolean buttonDown(Controller controller, int buttonCode) {

        return true;
    }

    @Override
    public boolean buttonUp(Controller controller, int buttonCode) {
        return true;
    }

    @Override
    public boolean axisMoved(Controller controller, int axisCode, float value) {
        return true;
    }

    @Override
    public boolean povMoved(Controller controller, int povCode, PovDirection value) {
        return true;
    }

    @Override
    public boolean xSliderMoved(Controller controller, int sliderCode, boolean value) {
        return true;
    }

    @Override
    public boolean ySliderMoved(Controller controller, int sliderCode, boolean value) {
        return true;
    }

    @Override
    public boolean accelerometerMoved(Controller controller, int accelerometerCode, Vector3 value) {
        return true;
    }

}
