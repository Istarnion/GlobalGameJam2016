package com.holypoly.ggj.component;

/**
 *
 * @author istarnion
 */
public class BeaconComponent extends Component{
	
	//Radius squared
	public double capRad;
	
	//3 values, -1 for one team, 0 if uncaptured and 1 for the other team
	public int caputred; 
	
	
	public int captureState;
	
}
