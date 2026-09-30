package com.dasherwa.mineralgame.code.world;

import com.FastNoiseLite.FastNoiseLite;
import com.FastNoiseLite.FastNoiseLite.NoiseType;
import com.dasherwa.mineralgame.code.gem.Rarity;
import com.dasherwa.mineralgame.code.main.Main;
import com.dasherwa.mineralgame.code.tiles.Tile;
import com.dasherwa.mineralgame.code.tiles.TileManager;
import com.dasherwa.mineralgame.code.tiles.TileType;

import static com.raylib.Raylib.*;

public class WorldManager {

	FastNoiseLite noise;
	final int WORLD_WIDTH = 10;
	final int WORLD_HEIGHT = 6;
	TileManager tile_manager;
	
	static Tile[][] tiles;
	
	float counter;
	
	public WorldManager() {
		
		counter = 0.0f;
		
		tile_manager = new TileManager();
		
		tiles = new Tile[WORLD_WIDTH][WORLD_HEIGHT];
		
		noise = new FastNoiseLite();
		noise.SetNoiseType(NoiseType.OpenSimplex2);
		noise.SetSeed(10);
		noise.SetFrequency(0.09f);
		
		for (int i = 0; i < WORLD_WIDTH; i++) {
			for (int j = 0; j < WORLD_HEIGHT; j++) {
				
				tiles[i][j] = new Tile(tile_manager.world_tiles[0]);
				
				float noise_value = (noise.GetNoise(i, j) + 1) / 2 * 15;
				
				System.out.println(noise_value);
				
				if(noise_value >= 0 && noise_value < 6) {
					System.out.println("this value is between 0 and 6 (could be 0 as well)");
					tiles[i][j].SetRarity(Rarity.COMMON);
				}
				if(noise_value >= 6 && noise_value < 11) {
					System.out.println("this value is between 6 and 11 (could be 6 as well)");
					tiles[i][j].SetRarity(Rarity.RARE);
				}
				if(noise_value >= 11 && noise_value < 13) {
					System.out.println("this value is between 11 and 13 (could be 11 as well)");
					tiles[i][j].SetRarity(Rarity.EPIC);
				}
				if(noise_value >= 13 && noise_value < 15) {
					System.out.println("this value is between 13 and 15 (could be 13 as well)");
					tiles[i][j].SetRarity(Rarity.LEGENDARY);
				}
				
				System.out.println(tiles[i][j].hashCode());
				System.out.println(tiles[i][j].GetID());
				
			}
		}
		
		
		
	}
	
	public void DrawWorld() {
		
		for (int i = 0; i < WORLD_WIDTH; i++) {
			for (int j = 0; j < WORLD_HEIGHT; j++) {
				
				tiles[i][j].Draw(i * Main.TILE_SIZE, j * Main.TILE_SIZE + Main.SCREEN_BLACKNESS, Main.APAWHITE);
				
//				switch (tiles[i][j].GetRarity()){
//				
//				case COMMON:
//					tiles[i][j].Draw(i * Main.TILE_SIZE, j * Main.TILE_SIZE + Main.SCREEN_BLACKNESS, Main.APABROWN);
//					break;
//				
//				case RARE:
//					tiles[i][j].Draw(i * Main.TILE_SIZE, j * Main.TILE_SIZE + Main.SCREEN_BLACKNESS, Main.APAGRAY);
//					break;
//					
//				case EPIC:
//					tiles[i][j].Draw(i * Main.TILE_SIZE, j * Main.TILE_SIZE + Main.SCREEN_BLACKNESS, Main.APAWHITE);
//					break;
//					
//				case LEGENDARY:
//					tiles[i][j].Draw(i * Main.TILE_SIZE, j * Main.TILE_SIZE + Main.SCREEN_BLACKNESS, Main.APABLUE);
//					break;
//				
//				}
				
				
			}
		}
		
	}
	
	public void UpdateWorld() {
		
		if(IsKeyPressed(KEY_ENTER)) {
			
			switch (tiles[(int)Main.ui.selected_tile.x()][(int)Main.ui.selected_tile.y()].GetRarity()) {
			
			case COMMON:
				tiles[(int)Main.ui.selected_tile.x()][(int)Main.ui.selected_tile.y()] = new Tile(tile_manager.world_tiles[1]);
				break;
			
			case RARE:
				tiles[(int)Main.ui.selected_tile.x()][(int)Main.ui.selected_tile.y()] = new Tile(tile_manager.world_tiles[2]);
				break;
				
			case EPIC:
				tiles[(int)Main.ui.selected_tile.x()][(int)Main.ui.selected_tile.y()] = new Tile(tile_manager.world_tiles[3]);
				break;
				
			case LEGENDARY:
				tiles[(int)Main.ui.selected_tile.x()][(int)Main.ui.selected_tile.y()] = new Tile(tile_manager.world_tiles[4]);
				break;
				
			default:
				break;
			}
			
			
			
		}
		
		counter = counter + GetFrameTime();
		
		
		if(counter >= 1.0f) {
			System.out.println(counter);
			CollectMineral();
			System.out.println("Brownite KG: " + Main.ui.mineralKG_counts[0]);
			System.out.println("Grayite KG: " + Main.ui.mineralKG_counts[1]);
			System.out.println("Whiteoxide KG: " + Main.ui.mineralKG_counts[2]);
			System.out.println("Bluntric KG: " + Main.ui.mineralKG_counts[3]);
			
			counter = 0.0f;
		}
		
	}
	
	void CollectMineral() {
		
		for (int i = 0; i < WORLD_WIDTH; i++) {
			for (int j = 0; j < WORLD_HEIGHT; j++) {
				if(tiles[i][j].GetType() == TileType.QUARRY) {
					switch(tiles[i][j].GetRarity()) {
					
					case COMMON:
						Main.ui.mineralKG_counts[0] += 1.0f;
						break;
						
					case RARE:
						Main.ui.mineralKG_counts[1] += 1.0f;
						break;
						
					case EPIC:
						Main.ui.mineralKG_counts[2] += 1.0f;
						break;
						
					case LEGENDARY:
						Main.ui.mineralKG_counts[3] += 1.0f;
						break;
						
					default:
						break;
						
					
					}
				}
			}
		}
		
	}
	
}
