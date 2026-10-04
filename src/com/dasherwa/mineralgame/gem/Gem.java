package com.dasherwa.mineralgame.gem;

import static com.raylib.Raylib.*;

public class Gem {
	String name;
	Rarity rarity;
	Color colour;
	float value;
	
	public Gem(String name, Rarity rarity, Color colour, float value) {
		
		this.name = name;
		this.rarity = rarity;
		this.colour = colour;
		this.value = value;
		
	}
}
