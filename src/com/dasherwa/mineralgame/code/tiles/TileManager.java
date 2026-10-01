package com.dasherwa.mineralgame.code.tiles;

import com.dasherwa.mineralgame.code.gem.Rarity;
import com.dasherwa.mineralgame.code.main.TextureManager;

public class TileManager {

	public Tile[] world_tiles;
	private final int TILE_COUNT = 5;
	
	public TileManager() {
		
		world_tiles = new Tile[TILE_COUNT];
		
		Tile dirt_tile = new Tile();
		dirt_tile.SetName("Dirt");
		dirt_tile.SetRarity(Rarity.COMMON);
		dirt_tile.SetTexture(TextureManager.dirt_texture);
		dirt_tile.SetType(TileType.REGULAR);
		dirt_tile.SetID(0);
		
		Tile quarry_brownite = new Tile();
		quarry_brownite.SetName("Brownite Quarry");
		quarry_brownite.SetRarity(Rarity.COMMON);
		quarry_brownite.SetTexture(TextureManager.quarry_brownite);
		quarry_brownite.SetType(TileType.QUARRY);
		quarry_brownite.SetID(1);
		TileData.SetQuarryPower(quarry_brownite, 1);
		TileData.SetQuarryFrequency(quarry_brownite, 1);
		TileData.SetQuarryCounter(quarry_brownite, 0.0f);
		
		Tile quarry_grayite = new Tile();
		quarry_grayite.SetName("Grayite Quarry");
		quarry_grayite.SetRarity(Rarity.RARE);
		quarry_grayite.SetTexture(TextureManager.quarry_grayite);
		quarry_grayite.SetType(TileType.QUARRY);
		quarry_grayite.SetID(2);
		TileData.SetQuarryPower(quarry_grayite, 1);
		TileData.SetQuarryFrequency(quarry_grayite, 1);
		TileData.SetQuarryCounter(quarry_grayite, 0.0f);
		
		Tile quarry_whiteoxide = new Tile();
		quarry_whiteoxide.SetName("Whiteoxide Quarry");
		quarry_whiteoxide.SetRarity(Rarity.EPIC);
		quarry_whiteoxide.SetTexture(TextureManager.quarry_whiteoxide);
		quarry_whiteoxide.SetType(TileType.QUARRY);
		quarry_whiteoxide.SetID(3);
		TileData.SetQuarryPower(quarry_whiteoxide, 1);
		TileData.SetQuarryFrequency(quarry_whiteoxide, 1);
		TileData.SetQuarryCounter(quarry_whiteoxide, 0.0f);
		
		Tile quarry_bluntric = new Tile();
		quarry_bluntric.SetName("Bluntric Quarry");
		quarry_bluntric.SetRarity(Rarity.LEGENDARY);
		quarry_bluntric.SetTexture(TextureManager.quarry_bluntric);
		quarry_bluntric.SetType(TileType.QUARRY);
		quarry_bluntric.SetID(4);
		TileData.SetQuarryPower(quarry_bluntric, 1);
		TileData.SetQuarryFrequency(quarry_bluntric, 1);
		TileData.SetQuarryCounter(quarry_bluntric, 0.0f);
		
		
		world_tiles[0] = dirt_tile;
		world_tiles[1] = quarry_brownite;
		world_tiles[2] = quarry_grayite;
		world_tiles[3] = quarry_whiteoxide;
		world_tiles[4] = quarry_bluntric;
		
	}
	
}
