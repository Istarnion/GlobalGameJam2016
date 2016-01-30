package com.holypoly.ggj.component;

public class PlayerComponent extends Component{

	public int team;
	public int maxHealth;
	public int currentHealth;
	public int score;

	public PlayerComponent(int entityID) {
		super(entityID);
	}
	
	
}
