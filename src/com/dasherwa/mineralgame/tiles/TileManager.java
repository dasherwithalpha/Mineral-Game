package com.dasherwa.mineralgame.tiles;

import static com.dasherwa.mineralgame.tiles.TileData.*;

import com.dasherwa.mineralgame.main.TextureManager;
import com.dasherwa.mineralgame.mineral.Rarity;

public class TileManager {

	public static Tile[] world_tiles;
	private final int TILE_COUNT = 2;
	
	public TileManager() {
		
		world_tiles = new Tile[TILE_COUNT];
		
		Tile mineral_tile = new Tile();
		mineral_tile.SetName("Mineral");
		mineral_tile.SetRarity(Rarity.COMMON);
		mineral_tile.SetTextureMineral(TextureManager.mineral);
		mineral_tile.SetType(TileType.REGULAR);
		mineral_tile.SetID(0);
		
		Tile quarry = new Tile();
		quarry.SetName("Quarry");
		quarry.SetRarity(Rarity.COMMON);
		quarry.SetTextureBase(TextureManager.quarry_body);
		quarry.SetTextureMineral(TextureManager.quarry_mineral);
		quarry.SetType(TileType.QUARRY);
		quarry.SetID(1);
		SetQuarryPower(quarry, 1);
		SetQuarryFrequency(quarry, 1);
		SetQuarryCounter(quarry, 0.0f);
		SetQuarryTier(quarry, 1);

		
		
		world_tiles[0] = mineral_tile;
		world_tiles[1] = quarry;
		
	}
	
	public static Tile QuarryFactory(String name, Rarity rarity) {
		world_tiles[1].SetTextureBase(TextureManager.quarry_body, TextureManager.quarry_body_bright);
		world_tiles[1].SetTextureMineral(TextureManager.quarry_mineral, TextureManager.quarry_mineral_bright);
		
		return new Tile(world_tiles[1], name + " Quarry", rarity);
	}
	
}
