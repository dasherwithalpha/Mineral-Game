package com.dasherwa.mineralgame.code.gem;

import static com.raylib.Raylib.*;

public class Gem {
	String name;
	Rarity rarity;
	Color colour;
	int value;
	float kilogram;
	
	public Gem(String name, Rarity rarity, Color colour, int value, float kilogram) {
		
		this.name = name;
		this.rarity = rarity;
		this.colour = colour;
		this.value = value;
		this.kilogram = kilogram;
		
	}
}
