package com.holypoly.ggj.component;

/**
 *
 * @author istarnion
 */
public class InputState {

    public float turn;

    public boolean shoot;

    public boolean accel;


    @Override
    public String toString(){
    	return turn + ", " + shoot +", " + accel;
    }
}
