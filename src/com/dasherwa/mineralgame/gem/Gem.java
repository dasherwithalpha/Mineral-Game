package com.dasherwa.mineralgame.gem;

import static com.raylib.Raylib.*;

public class Gem {
	String name;
	Rarity rarity;
	Color colour;
	float value;
	boolean use_bright;
	
	public Gem(String name, Rarity rarity, Color colour, float value) {
		
		this.name = name;
		this.rarity = rarity;
		this.colour = colour;
		this.value = value;
		
	}
	
	public float GetGemValue() {
		return value;
	}
	public String GetGemName() {
		return name;
	}
	
	public void UseBright() {
		use_bright = true;
	}
	
	public boolean GetUseBright() {
		return use_bright;
	}
}
