package com.dasherwa.mineralgame.world;

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
	
	public String GetPlanetAreaTileName(int planet, int area_x, int area_y, int tile_x, int tile_y) {
		return planets[planet].GetArea(area_x, area_y).GetTileName(tile_x, tile_y);
	}
}
