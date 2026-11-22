package com.dasherwa.mineralgame.mineral;

import static com.raylib.Raylib.*;

public class Mineral {
	String name;
	Rarity rarity;
	Color colour;
	float value;
	boolean use_bright;
	
	public Mineral(String name, Rarity rarity, Color colour, float value) {
		
		this.name = name;
		this.rarity = rarity;
		this.colour = colour;
		this.value = value;
		
	}
	
	public float GetMineralValue() {
		return value;
	}
	public String GetMineralString() {
		return name;
	}
	
	public void UseBright() {
		use_bright = true;
	}
	
	public boolean GetUseBright() {
		return use_bright;
	}
}
