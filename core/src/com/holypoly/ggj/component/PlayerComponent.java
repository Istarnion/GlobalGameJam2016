package com.holypoly.ggj.component;

public class PlayerComponent extends AbstractComponent{

	public int team;
	public int maxHealth;
	public int currentHealth;
	public int score;

    public float cooldown = 0;

    public float delay = 0.1f;

	public PlayerComponent(int entityID) {
		super(entityID);
	}


}
