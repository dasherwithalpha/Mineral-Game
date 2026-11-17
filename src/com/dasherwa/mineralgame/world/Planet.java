package com.dasherwa.mineralgame.world;

import com.FastNoiseLite.FastNoiseLite;
import com.dasherwa.mineralgame.gem.Gem;
import com.dasherwa.mineralgame.gem.Rarity;
import com.dasherwa.mineralgame.main.TextureManager;
import com.dasherwa.mineralgame.tiles.Tile;
import com.dasherwa.mineralgame.tiles.TileManager;
import com.raylib.Raylib.Color;
import com.raylib.Raylib.Vector2;
import static com.dasherwa.mineralgame.world.WorldManager.WORLD_HEIGHT;
import static com.dasherwa.mineralgame.world.WorldManager.WORLD_WIDTH;

public abstract class Planet {

	protected Color colour_common;
	protected Color colour_rare;
	protected Color colour_epic;
	protected Color colour_legendary;
	
	protected Areas areas;
	
	protected FastNoiseLite noise;
	
	protected int noise_extend;
	
	public Gem[] gems;
	
	public Planet() {
		InitNoise();
		InitColours();
		InitGems();
		InitAreas();
	}
	
	public abstract void InitNoise();
	public abstract void InitAreas();
	public abstract void InitColours();
	public abstract void InitGems();
	
	public void CreatePlanetAreas() {
		for(int i=0;i<Areas.HORIZONTAL_AREA_COUNT; i++) {
			for(int j=0;j<Areas.VERTICAL_AREA_COUNT;j++) {
				areas.area[i][j] = new Area();
				areas.area[i][j].SetIsAreaFinished(false);
				areas.area[i][j].SetPosition(i, j);
				areas.area[i][j].SetTiles(CreateTiles(noise, noise_extend, areas.area[i][j].GetPosition()));
			}
		}
	}
	
	Tile[][] CreateTiles(FastNoiseLite noise, int noise_extend, Vector2 chunk){
			
			Tile[][] new_tiles = new Tile[WORLD_WIDTH][WORLD_HEIGHT];
			
			for (int i = 0; i < WORLD_WIDTH; i++) {
				for (int j = 0; j < WORLD_HEIGHT; j++) {
					
					new_tiles[i][j] = new Tile(TileManager.world_tiles[0]);
					
					float noise_value = (noise.GetNoise((i + chunk.x() * WORLD_WIDTH) * noise_extend, (j + chunk.y() * WORLD_HEIGHT) * noise_extend) + 1) / 2 * 15;
					System.out.println(noise_value);
					if(noise_value >= 0 && noise_value < 6) {
	//					System.out.println("this value is between 0 and 6 (could be 0 as well)");
						new_tiles[i][j].SetRarity(Rarity.COMMON);
						new_tiles[i][j].SetTextureMineral(TextureManager.dirt_00_mineral_1);
					}
					if(noise_value >= 6 && noise_value < 11) {
	//					System.out.println("this value is between 6 and 11 (could be 6 as well)");
						new_tiles[i][j].SetRarity(Rarity.RARE);
						new_tiles[i][j].SetTextureMineral(TextureManager.dirt_00_mineral_2);
					}
					if(noise_value >= 11 && noise_value < 13) {
	//					System.out.println("this value is between 11 and 13 (could be 11 as well)");
						new_tiles[i][j].SetRarity(Rarity.EPIC);
						new_tiles[i][j].SetTextureMineral(TextureManager.dirt_00_mineral_3);
					}
					if(noise_value >= 13 && noise_value < 15) {
	//					System.out.println("this value is between 13 and 15 (could be 13 as well)");
						new_tiles[i][j].SetRarity(Rarity.LEGENDARY);
						new_tiles[i][j].SetTextureMineral(TextureManager.dirt_00_mineral_4);
					}
					
				}
			}
			
			return new_tiles;
		}
	
	public Area GetArea(int x, int y) {
		return areas.GetArea(x, y);
	}
	
	public Area GetArea(Vector2 pos) {
		return areas.GetArea(pos);
	}
	
	public Tile[][] GetTilesFromAnArea(int x, int y){
		return areas.area[x][y].GetTiles();
	}
	
	public FastNoiseLite GetNoise() {
		return noise;
	}
	
}
