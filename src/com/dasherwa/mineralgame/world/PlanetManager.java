package com.dasherwa.mineralgame.world;

import com.dasherwa.mineralgame.gem.Rarity;
import com.dasherwa.mineralgame.world.planets.ApaPlanet;
import com.raylib.Raylib.Color;
import static com.raylib.Raylib.*;

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
	
	public Color GetPlanetMineralColour(int planet_ordinal, Rarity rarity) {
		return planets[planet_ordinal].GetColor(rarity);
	}
	
	public String GetPlanetMineralName(int planet_ordinal, Rarity rarity) {
		return planets[planet_ordinal].GetMineralName(rarity);
	}
	
	public void UnloadTextures() {
		for (int i = 0; i < planets.length; i++) {
			if(planets[i] != null) {
				UnloadTexture(planets[i].quarry_body);
				UnloadTexture(planets[i].quarry_mineral);
				UnloadTexture(planets[i].quarry_rope);
			}
		}
	}
}
