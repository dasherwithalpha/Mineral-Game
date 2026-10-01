package com.dasherwa.mineralgame.code.world;

import com.FastNoiseLite.FastNoiseLite;
import com.FastNoiseLite.FastNoiseLite.NoiseType;
import com.dasherwa.mineralgame.code.gem.Rarity;
import com.dasherwa.mineralgame.code.main.Main;
import com.dasherwa.mineralgame.code.tiles.Tile;
import static com.dasherwa.mineralgame.code.tiles.TileData.*;
import com.dasherwa.mineralgame.code.tiles.TileManager;
import com.dasherwa.mineralgame.code.tiles.TileType;

import static com.raylib.Raylib.*;

public class WorldManager {

	FastNoiseLite noise;
	final int WORLD_WIDTH = 10;
	final int WORLD_HEIGHT = 6;
	TileManager tile_manager;
	
	static Tile[][] tiles;
	
	public WorldManager() {
		
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
			if (tiles[(int) Main.ui.selected_tile.x()][(int) Main.ui.selected_tile.y()].GetID() == 0) {
				switch (tiles[(int) Main.ui.selected_tile.x()][(int) Main.ui.selected_tile.y()].GetRarity()) {

					case COMMON:
						tiles[(int) Main.ui.selected_tile.x()][(int) Main.ui.selected_tile.y()] = new Tile(
								tile_manager.world_tiles[1]);
						break;

					case RARE:
						tiles[(int) Main.ui.selected_tile.x()][(int) Main.ui.selected_tile.y()] = new Tile(
								tile_manager.world_tiles[2]);
						break;

					case EPIC:
						tiles[(int) Main.ui.selected_tile.x()][(int) Main.ui.selected_tile.y()] = new Tile(
								tile_manager.world_tiles[3]);
						break;

					case LEGENDARY:
						tiles[(int) Main.ui.selected_tile.x()][(int) Main.ui.selected_tile.y()] = new Tile(
								tile_manager.world_tiles[4]);
						break;

					default:
						break;
						
				}

			}
			
			
			
		}
		
		if(IsKeyPressed(KEY_ONE)) {
			if(tiles[(int) Main.ui.selected_tile.x()][(int) Main.ui.selected_tile.y()].GetID() >= 1 && tiles[(int) Main.ui.selected_tile.x()][(int) Main.ui.selected_tile.y()].GetID() <= 4) {
				
				SetQuarryPower(
						tiles[(int) Main.ui.selected_tile.x()][(int) Main.ui.selected_tile.y()],
						GetQuarryPower(tiles[(int) Main.ui.selected_tile.x()][(int) Main.ui.selected_tile.y()]) + 1);
				
			}
		}
		
		if (IsKeyPressed(KEY_TWO)) {
			if (tiles[(int) Main.ui.selected_tile.x()][(int) Main.ui.selected_tile.y()].GetID() >= 1 && tiles[(int) Main.ui.selected_tile.x()][(int) Main.ui.selected_tile.y()].GetID() <= 4) {

				SetQuarryFrequency(tiles[(int) Main.ui.selected_tile.x()][(int) Main.ui.selected_tile.y()],
						GetQuarryFrequency(tiles[(int) Main.ui.selected_tile.x()][(int) Main.ui.selected_tile.y()])
								+ 1);

			}
		}
		
		if(IsKeyPressed(KEY_A)) {
			System.out.println(GetQuarryPower(tiles[(int) Main.ui.selected_tile.x()][(int) Main.ui.selected_tile.y()]));
			System.out.println(GetQuarryFrequency(tiles[(int) Main.ui.selected_tile.x()][(int) Main.ui.selected_tile.y()]));
			System.out.println(tiles[(int) Main.ui.selected_tile.x()][(int) Main.ui.selected_tile.y()].hashCode());
		}
		
		CollectMineral();
		
	}
	
	void CollectMineral() {
		
		for (int i = 0; i < WORLD_WIDTH; i++) {
			for (int j = 0; j < WORLD_HEIGHT; j++) {
				if(tiles[i][j].GetType() == TileType.QUARRY) {
					
					if(GetQuarryCounter(tiles[i][j]) >= FrequencyLevelToPeriod(GetQuarryFrequency(tiles[i][j]))) {
					
						switch (tiles[i][j].GetRarity()) {

							case COMMON:
								Main.ui.mineralKG_counts[0] += KGPerPower(GetQuarryPower(tiles[i][j]));
								break;

							case RARE:
								Main.ui.mineralKG_counts[1] += KGPerPower(GetQuarryPower(tiles[i][j]));
								break;

							case EPIC:
								Main.ui.mineralKG_counts[2] += KGPerPower(GetQuarryPower(tiles[i][j]));
								break;

							case LEGENDARY:
								Main.ui.mineralKG_counts[3] += KGPerPower(GetQuarryPower(tiles[i][j]));
								break;

							default:
								break;

						}
						
						SetQuarryCounter(tiles[i][j], 0.0f);
					
					}
					
					SetQuarryCounter(tiles[i][j], GetQuarryCounter(tiles[i][j]) + GetFrameTime());
					//System.out.println(TileData.GetQuarryCounter(tiles[i][j]));
				}
			}
		}
		
	}

}
