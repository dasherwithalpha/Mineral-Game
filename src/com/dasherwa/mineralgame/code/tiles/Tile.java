package com.dasherwa.mineralgame.code.tiles;

import static com.raylib.Raylib.*;

import com.dasherwa.mineralgame.code.gem.Rarity;

public class Tile{

	String name;
	int id;
	TileType type;
	Rarity rarity;
	Texture texture;
	TileData tile_data;
	
	public Tile() {
		tile_data = new TileData();
	}
	public Tile(Tile tile) {
		
		this.name = tile.name;
		this.type = tile.type;
		this.rarity = tile.rarity;
		this.texture = tile.texture;
		this.id = tile.id;
		this.tile_data = new TileData(tile.tile_data);
		
	}
	
	//TASK SPECIFIC METHODS
	public void Draw(int x, int y, Color colour) {
		DrawTexture(texture, x, y, colour);
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
	
	public void SetTexture(Texture texture) {
		this.texture = texture;
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
}
