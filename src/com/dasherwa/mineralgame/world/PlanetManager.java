package com.dasherwa.mineralgame.world;

import com.FastNoiseLite.FastNoiseLite;
import com.dasherwa.mineralgame.world.planets.ApaPlanet;

public class PlanetManager {

	Planet[] planets = new Planet[8];
	
	public PlanetManager() {
		planets[0] = new ApaPlanet();
	}
	
	public Planet GetPlanet(int index) {
		return planets[index];
	}
	public float GetPlanetGemValue(int planet_ordinal, int gem_ordinal) {
		return planets[planet_ordinal].gems[gem_ordinal].GetGemValue();
	}
}
