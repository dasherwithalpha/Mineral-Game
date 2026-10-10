package com.dasherwa.mineralgame.world;

import com.FastNoiseLite.FastNoiseLite;
import com.FastNoiseLite.FastNoiseLite.FractalType;
import com.FastNoiseLite.FastNoiseLite.NoiseType;
import com.dasherwa.mineralgame.gem.Rarity;
import com.dasherwa.mineralgame.main.Main;
import com.dasherwa.mineralgame.main.ui.SelectionType;
import com.dasherwa.mineralgame.main.ui.UIState;
import com.dasherwa.mineralgame.main.ui.dialoguebox.DialogueBoxManager.BoxType;
import com.dasherwa.mineralgame.tiles.Tile;
import com.dasherwa.mineralgame.tiles.TileData;
import com.dasherwa.mineralgame.tiles.TileManager;
import com.dasherwa.mineralgame.tiles.TileType;

import static com.dasherwa.mineralgame.tiles.TileData.*;
import static com.raylib.Raylib.*;

public class WorldManager {

	FastNoiseLite noise;
	final int WORLD_WIDTH = 10;
	final int WORLD_HEIGHT = 6;
	public TileManager tile_manager;
	
	int noise_extend;
	
	static Tile[][] tiles;
	
	public WorldManager() {
		
//		tile_manager = new TileManager();
		
		tiles = new Tile[WORLD_WIDTH][WORLD_HEIGHT];
		
		noise_extend = 1;
		
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
		
		for (int i = 0; i < WORLD_WIDTH; i++) {
			for (int j = 0; j < WORLD_HEIGHT; j++) {
				
				tiles[i][j] = new Tile(TileManager.world_tiles[0]);
				
				float noise_value = (noise.GetNoise(i * noise_extend, j * noise_extend) + 1) / 2 * 15;
				
				System.out.println(noise_value);
				
				if(noise_value >= 0 && noise_value < 6) {
//					System.out.println("this value is between 0 and 6 (could be 0 as well)");
					tiles[i][j].SetRarity(Rarity.COMMON);
				}
				if(noise_value >= 6 && noise_value < 11) {
//					System.out.println("this value is between 6 and 11 (could be 6 as well)");
					tiles[i][j].SetRarity(Rarity.RARE);
				}
				if(noise_value >= 11 && noise_value < 13) {
//					System.out.println("this value is between 11 and 13 (could be 11 as well)");
					tiles[i][j].SetRarity(Rarity.EPIC);
				}
				if(noise_value >= 13 && noise_value < 15) {
//					System.out.println("this value is between 13 and 15 (could be 13 as well)");
					tiles[i][j].SetRarity(Rarity.LEGENDARY);
				}
				
			}
		}
		
		
		
	}
	
	public void DrawWorld() {
		
		for (int i = 0; i < WORLD_WIDTH; i++) {
			for (int j = 0; j < WORLD_HEIGHT; j++) {
				
				tiles[i][j].Draw(i * Main.TILE_SIZE, j * Main.TILE_SIZE + Main.SCREEN_BLACKNESS, Main.APAWHITE);
				
			}
		}
		
	}
	
	public void UpdateWorld() {
		
		int selected_tile_x = (int) Main.ui.selected_tile.x();
		int selected_tile_y = (int) Main.ui.selected_tile.y();
		
		if(IsKeyPressed(KEY_ENTER) && Main.ui.ui_state == UIState.RESOURCES && Main.ui.selection_type == SelectionType.TILE) {
			if (tiles[selected_tile_x][selected_tile_y].GetID() == 0) {
				
				if(Main.ui.GetTotalBalance(0) >= TileData.GetQuarryPrice(tiles[selected_tile_x][selected_tile_y], Main.ui.GetQuarryCount())) {
					
					Main.ui.SetTotalBalance(Main.ui.GetTotalBalance(0) - TileData.GetQuarryPrice(tiles[selected_tile_x][selected_tile_y], Main.ui.GetQuarryCount()));
					
					tiles[selected_tile_x][selected_tile_y] = TileData.PlaceQuarry(tiles[selected_tile_x][selected_tile_y]);
					System.out.println(TileData.GetQuarryPrice(tiles[selected_tile_x][selected_tile_y], Main.ui.GetQuarryCount()));
					Main.ui.IncreaseQuarryCountByOne();
					
				} else {
					System.out.println("not enough money bruh");
					Main.dialogue_box_manager.SetBoxShow(BoxType.NOT_ENOUGH_MONEY);
				}
				
				

			}
			
			
			
		}
		
		if(IsKeyPressed(KEY_ONE)) {
			if(tiles[selected_tile_x][selected_tile_y].GetID() >= 1 && tiles[selected_tile_x][selected_tile_y].GetID() <= 4) {
				
//				SetQuarryPower(tiles[selected_tile_x][selected_tile_y], GetQuarryPower(tiles[selected_tile_x][selected_tile_y]) + 1);
				IncreaseTileQuarryPower(selected_tile_x, selected_tile_y);
			}
		}
		
		if (IsKeyPressed(KEY_TWO)) {
			if (tiles[selected_tile_x][selected_tile_y].GetID() >= 1 && tiles[selected_tile_x][selected_tile_y].GetID() <= 4) {

//				SetQuarryFrequency(tiles[selected_tile_x][selected_tile_y], GetQuarryFrequency(tiles[selected_tile_x][selected_tile_y]) + 1);
				IncreaseTileQuarryFrequency(selected_tile_x, selected_tile_y);
			}
		}
		
		if(IsKeyPressed(KEY_A)) {
			System.out.println(GetQuarryPower(tiles[selected_tile_x][selected_tile_y]));
			System.out.println(GetQuarryFrequency(tiles[selected_tile_x][selected_tile_y]));
			System.out.println(tiles[selected_tile_x][selected_tile_y].hashCode());
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

	public Tile[][] GetWorldTiles(){
		return tiles;
	}

	public static void IncreaseTileQuarryPower(int tile_x, int tile_y) {
		SetQuarryPower(tiles[tile_x][tile_y], GetQuarryPower(tiles[tile_x][tile_y]) + 1);
	}
	
	public static void IncreaseTileQuarryFrequency(int tile_x, int tile_y) {
		SetQuarryFrequency(tiles[tile_x][tile_y], GetQuarryFrequency(tiles[tile_x][tile_y]) + 1);
	}
	
	public static int GetTileQuarryPower(int tile_x, int tile_y) {
		return GetQuarryPower(tiles[tile_x][tile_y]);
	}
	
	public static int GetTileQuarryFrequency(int tile_x, int tile_y) {
		return GetQuarryFrequency(tiles[tile_x][tile_y]);
	}
}
