package com.dasherwa.mineralgame.world.planets;

import com.FastNoiseLite.FastNoiseLite;
import com.FastNoiseLite.FastNoiseLite.FractalType;
import com.FastNoiseLite.FastNoiseLite.NoiseType;
import com.dasherwa.mineralgame.mineral.Mineral;
import com.dasherwa.mineralgame.mineral.Rarity;
import com.dasherwa.mineralgame.world.Areas;
import com.dasherwa.mineralgame.world.Planet;
import com.raylib.Helpers;

import static com.raylib.Raylib.*;

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
//		areas.SetAreaFinished(0, 0, true);
//		areas.SetAreaFinished(5, 3, true);
	}

	@Override
	public void InitColours() {
		colour_dirt = Helpers.newColor(119, 80, 39, 255);
		colour_common = Helpers.newColor(119, 80, 39, 255);
		colour_rare = Helpers.newColor(75, 75, 75, 255);
		colour_epic = Helpers.newColor(215, 218, 234, 255);
		colour_legendary = Helpers.newColor(182, 185, 244, 255);
		colour_common_bright = Helpers.newColor(178, 119, 58, 255);
		colour_rare_bright = Helpers.newColor(112, 112, 112, 255);
		colour_epic_bright = Helpers.newColor(255, 255, 255, 255);
		colour_legendary_bright = Helpers.newColor(218, 219, 249, 255);
//		colour_common = Helpers.newColor(255, 0, 0, 255);
//		colour_rare = Helpers.newColor(0, 255, 0, 255);
//		colour_epic = Helpers.newColor(0, 0, 255, 255);
//		colour_legendary = Helpers.newColor(255, 255, 255, 255);
	}

	@Override
	public void InitMinerals() {
		minerals = new Mineral[4];
		minerals[0] = new Mineral("Brownite", Rarity.COMMON, colour_common, 4);
		minerals[1] = new Mineral("Grayite", Rarity.RARE, colour_rare, 4.8f);
		minerals[2] = new Mineral("Whiteoxide", Rarity.EPIC, colour_epic, 12);
		minerals[3] = new Mineral("Bluntric", Rarity.LEGENDARY, colour_legendary, 12);
		
		minerals[0].UseBright();
		minerals[2].UseBright();
	}

	@Override
	public void InitTextures() {
		quarry_body = LoadTexture("Resources/Textures/Tile/apa_quarry_body.png");
		quarry_body_bright = LoadTexture("Resources/Textures/Tile/apa_quarry_body_bright.png");
		quarry_mineral = LoadTexture("Resources/Textures/Tile/apa_quarry_mineral.png");
		quarry_mineral_bright = LoadTexture("Resources/Textures/Tile/apa_quarry_mineral_bright.png");
		quarry_rope = LoadTexture("Resources/Textures/Tile/apa_quarry_rope.png");
//		quarry_body = LoadTexture("Resources/Textures/Tile/lavre_quarry_body.png");
//		quarry_body_bright = LoadTexture("Resources/Textures/Tile/lavre_quarry_body_bright.png");
//		quarry_mineral = LoadTexture("Resources/Textures/Tile/lavre_quarry_mineral.png");
//		quarry_mineral_bright = LoadTexture("Resources/Textures/Tile/lavre_quarry_mineral_bright.png");
	}

}
