package com.dasherwa.mineralgame.code.tiles;

import static com.raylib.Raylib.*;

import com.dasherwa.mineralgame.code.gem.Rarity;

public class Tile{

	String name;
	int id;
	TileType type;
	Rarity rarity;
	Texture texture;
	
	public Tile() {}
	public Tile(Tile tile) {
		
		this.name = tile.name;
		this.type = tile.type;
		this.rarity = tile.rarity;
		this.texture = tile.texture;
		this.id = tile.id;
		
	}
	
	public void Draw(int x, int y, Color colour) {
		DrawTexture(texture, x, y, colour);
	};
	
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
