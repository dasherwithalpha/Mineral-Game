package com.dasherwa.mineralgame.tiles;

import static com.raylib.Raylib.*;
import static com.raylib.Colors.GREEN;
import static com.raylib.Colors.WHITE;

import com.dasherwa.mineralgame.gem.Rarity;
import com.dasherwa.mineralgame.main.TextureManager;

public class Tile{

	String name;
	int id;
	TileType type;
	Rarity rarity;
	Texture texture_base;
	Texture texture_base_bright;
	Texture texture_mineral;
	Texture texture_mineral_bright;
	TileData tile_data;
	
	public Tile() {
		tile_data = new TileData();
	}
	public Tile(Tile tile) {
		
		this.name = tile.name;
		this.type = tile.type;
		this.rarity = tile.rarity;
		this.texture_base = tile.texture_base;
		this.texture_base_bright = tile.texture_base_bright;
		this.texture_mineral = tile.texture_mineral;
		this.texture_mineral_bright = tile.texture_mineral_bright;
		this.id = tile.id;
		this.tile_data = new TileData(tile.tile_data);
		
	}
	public Tile(Tile tile, String name, Rarity rarity) {
		
		this.name = name;
		this.type = tile.type;
		this.rarity = rarity;
		this.texture_base = tile.texture_base;
		this.texture_base_bright = tile.texture_base_bright;
		this.texture_mineral = tile.texture_mineral;
		this.texture_mineral_bright = tile.texture_mineral_bright;
		this.id = tile.id;
		this.tile_data = new TileData(tile.tile_data);
		
	}
	
	//TASK SPECIFIC METHODS
	public void Draw(int x, int y, Color colour) {
		
		if(texture_base_bright != null) {
			DrawTexture(texture_base_bright, x, y, ColorBrightness(colour, 0.5f));
		}
		
		if(texture_base != null) {
			DrawTexture(texture_base, x, y, colour);
		}
		
		if(texture_mineral_bright != null) {
			DrawTexture(texture_mineral_bright, x, y, ColorBrightness(colour, 0.5f));
		}
		
		if(texture_mineral != null) {
			DrawTexture(texture_mineral, x, y, colour);
		}
		
		if(type == TileType.QUARRY) {
			if(tile_data.quarry_counter > TileData.FrequencyLevelToPeriod(TileData.GetQuarryFrequency(this)) - 0.1f) {
				DrawTexture(TextureManager.quarry_mineral_bright, x, y, ColorBrightness(GREEN, 0.5f));
				DrawTexture(TextureManager.quarry_mineral, x, y, GREEN);
			}
			DrawTexture(TextureManager.quarry_rope, x, y, WHITE);
		}
	};
	
	//SETTERS
	public void SetName(String name) {
		this.name = name;
	}
	
	public void SetType(TileType type) {
		this.type = type;
	}
	
	public void SetRarity(Rarity rarity) {
		this.rarity = rarity;
	}
	
	public void SetTextureBase(Texture texture) {
		this.texture_base = texture;
	}
	
	public void SetTextureBase(Texture texture, Texture bright) {
		this.texture_base = texture;
		this.texture_base_bright = bright;
	}
	
	public void SetTextureMineral(Texture texture) {
		this.texture_mineral = texture;
	}
	
	public void SetTextureMineral(Texture texture, Texture bright) {
		this.texture_mineral = texture;
		this.texture_mineral_bright = bright;
	}
	
	public void SetID(int id) {
		this.id = id;
	}
	
	//GETTERS
	public Rarity GetRarity() {
		return this.rarity;
	}
	
	public int GetID() {
		return this.id;
	}
	
	public TileType GetType() {
		return this.type;
	}
	
	public String GetName() {
		return this.name;
	}
	
	public Texture GetTextureBase() {
		return this.texture_base;
	}
	
	public Texture GetTextureMineral() {
		return this.texture_mineral;
	}
}
