package com.dasherwa.mineralgame.world.planets;

import com.FastNoiseLite.FastNoiseLite;
import com.FastNoiseLite.FastNoiseLite.FractalType;
import com.FastNoiseLite.FastNoiseLite.NoiseType;
import com.dasherwa.mineralgame.gem.Gem;
import com.dasherwa.mineralgame.gem.Rarity;
import com.dasherwa.mineralgame.world.Areas;
import com.dasherwa.mineralgame.world.Planet;
import com.raylib.Helpers;

public class ApaPlanet extends Planet{

	@Override
	public void InitNoise() {
		noise = new FastNoiseLite();
		noise.SetNoiseType(NoiseType.OpenSimplex2);
		noise.SetSeed(10);
		noise.SetFrequency(0.09f);
		noise.SetFractalType(FractalType.PingPong);
		noise.SetFractalOctaves(4);
		noise.SetFractalLacunarity(0.2f);
		noise.SetFractalGain(0.21f);
		noise.SetFractalWeightedStrength(7.47f);
		noise.SetFractalPingPongStrength(2);
		
		noise_extend = 1;
	}

	@Override
	public void InitAreas() {
		areas = new Areas();
		CreatePlanetAreas();
		
	}

	@Override
	public void InitColours() {
		colour_common = Helpers.newColor(119, 80, 39, 255);
		colour_rare = Helpers.newColor(75, 75, 75, 255);
		colour_epic = Helpers.newColor(255, 255, 255, 255);
		colour_legendary = Helpers.newColor(182, 185, 244, 255);
		
	}

	@Override
	public void InitGems() {
		gems = new Gem[4];
		gems[0] = new Gem("Brownite", Rarity.COMMON, colour_common, 4);
		gems[1] = new Gem("Grayite", Rarity.RARE, colour_rare, 4.8f);
		gems[2] = new Gem("Whiteoxide", Rarity.EPIC, colour_epic, 12);
		gems[3] = new Gem("Bluntric", Rarity.LEGENDARY, colour_legendary, 12);
		
	}

}
