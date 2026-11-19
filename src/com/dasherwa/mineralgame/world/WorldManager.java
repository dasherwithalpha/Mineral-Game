package com.dasherwa.mineralgame.world;

import com.dasherwa.mineralgame.gem.Rarity;
import com.dasherwa.mineralgame.main.Main;
import com.dasherwa.mineralgame.main.Signal;
import com.dasherwa.mineralgame.main.TextureManager;
import com.dasherwa.mineralgame.main.ui.SelectionType;
import com.dasherwa.mineralgame.main.ui.UIState;
import com.dasherwa.mineralgame.main.ui.dialoguebox.BoxType;
import com.dasherwa.mineralgame.tiles.Tile;
import com.dasherwa.mineralgame.tiles.TileData;
import com.dasherwa.mineralgame.tiles.TileManager;
import com.dasherwa.mineralgame.tiles.TileType;

import static com.dasherwa.mineralgame.tiles.TileData.*;
import static com.raylib.Raylib.*;

public class WorldManager {

	static final int WORLD_WIDTH = 10;
	static final int WORLD_HEIGHT = 6;
	
	static Tile[][] tiles;
	
	public static int current_planet_ordinal;

	public static Vector2 current_chunk;
	
	boolean every_quarry_max_tier;
	
	public static Signal area_change_signal;
	
	public WorldManager() {
		
		current_chunk = new Vector2();
		current_chunk.x(0);
		current_chunk.y(0);
		
		tiles = new Tile[WORLD_WIDTH][WORLD_HEIGHT];
		tiles = Main.planet_manager.GetPlanet(current_planet_ordinal).GetTilesFromAnArea(0, 0);
		TextureManager.SwapQuarryTexturesWith(
				Main.planet_manager.planets[current_planet_ordinal].quarry_body,
				Main.planet_manager.planets[current_planet_ordinal].quarry_body_bright,
				Main.planet_manager.planets[current_planet_ordinal].quarry_mineral,
				Main.planet_manager.planets[current_planet_ordinal].quarry_mineral_bright,
				Main.planet_manager.planets[current_planet_ordinal].quarry_rope
				);
		
		every_quarry_max_tier = false;
		
		area_change_signal = new Signal();
	}
	
	public void DrawWorld() {
		
		for (int i = 0; i < WORLD_WIDTH; i++) {
			for (int j = 0; j < WORLD_HEIGHT; j++) {
				
				DrawTexture(
						TextureManager.dirt_texture,
						i * Main.TILE_SIZE,
						j * Main.TILE_SIZE + Main.SCREEN_BLACKNESS,
						Main.planet_manager.GetPlanetMineralColour(current_planet_ordinal, Rarity.COMMON));
				
				tiles[i][j].Draw(
						i * Main.TILE_SIZE,
						j * Main.TILE_SIZE + Main.SCREEN_BLACKNESS,
						Main.planet_manager.GetPlanetMineralColour(current_planet_ordinal, tiles[i][j].GetRarity()));
				
			}
		}
		
	}
	
	public void UpdateWorld() {
		
		int selected_tile_x = (int) Main.ui.selected_tile.x();
		int selected_tile_y = (int) Main.ui.selected_tile.y();
		
		if(area_change_signal.IsAlerted()) {
			tiles = Main.planet_manager.GetPlanet(current_planet_ordinal).GetArea(current_chunk).GetTiles();
			area_change_signal.Reset();
		}
		
		if(IsCurrentAreaFinished()) {
			return;
		}
		
		if(IsKeyPressed(KEY_ENTER) && Main.ui.ui_state == UIState.RESOURCES && Main.ui.selection_type == SelectionType.TILE) {
			if (tiles[selected_tile_x][selected_tile_y].GetID() == 0) {
				
				if(Main.ui.GetTotalBalance() >= TileData.GetQuarryPrice(tiles[selected_tile_x][selected_tile_y], Main.ui.GetQuarryCount())) {
					
					Main.ui.SetTotalBalance(Main.ui.GetTotalBalance() - (long)TileData.GetQuarryPrice(tiles[selected_tile_x][selected_tile_y], Main.ui.GetQuarryCount()));
					
					Main.dialogue_box_manager.SetRemovedMoneyBoxText(TileData.GetQuarryPrice(tiles[selected_tile_x][selected_tile_y], Main.ui.GetQuarryCount()));
					Main.dialogue_box_manager.SetBoxShow(BoxType.REMOVED_MONEY);
					
					tiles[selected_tile_x][selected_tile_y] = TileManager.QuarryFactory(Main.planet_manager.GetPlanetMineralName(current_planet_ordinal, tiles[selected_tile_x][selected_tile_y].GetRarity()), tiles[selected_tile_x][selected_tile_y].GetRarity());
					System.out.println(TileData.GetQuarryPrice(tiles[selected_tile_x][selected_tile_y], Main.ui.GetQuarryCount()));
					Main.ui.IncreaseQuarryCountByOne();
					
				} else {
					Main.dialogue_box_manager.SetBoxShow(BoxType.NOT_ENOUGH_MONEY);
				}
			}
		}
		
		every_quarry_max_tier = IsEveryQuarryMaxTier();
		if(every_quarry_max_tier) {
			SetCurrentAreaFinished();
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
								Main.ui.mineralKG_counts[0] += KGPerPower(tiles[i][j]);
								break;

							case RARE:
								Main.ui.mineralKG_counts[1] += KGPerPower(tiles[i][j]);
								break;

							case EPIC:
								Main.ui.mineralKG_counts[2] += KGPerPower(tiles[i][j]);
								break;

							case LEGENDARY:
								Main.ui.mineralKG_counts[3] += KGPerPower(tiles[i][j]);
								break;

							default:
								break;

						}
						
						SetQuarryCounter(tiles[i][j], 0.0f);
					
					}
					
					SetQuarryCounter(tiles[i][j], GetQuarryCounter(tiles[i][j]) + GetFrameTime());
				}
			}
		}
		
	}

	public Tile[][] GetWorldTiles(){
		return tiles;
	}

	public static void IncreaseTileQuarryPower(int tile_x, int tile_y, int factor) {
		SetQuarryPower(tiles[tile_x][tile_y], GetQuarryPower(tiles[tile_x][tile_y]) + factor);
	}
	
	public static void IncreaseTileQuarryFrequency(int tile_x, int tile_y, int factor) {
		SetQuarryFrequency(tiles[tile_x][tile_y], GetQuarryFrequency(tiles[tile_x][tile_y]) + factor);
	}
	
	public static void IncreaseTileQuarryTier(int tile_x, int tile_y) {
		SetQuarryTier(tiles[tile_x][tile_y], GetQuarryTier(tiles[tile_x][tile_y]) + 1);
	}
	
	public static boolean IsTileQuarryPowerMaxed(int tile_x, int tile_y, int max) {
		if(GetQuarryPower(tiles[tile_x][tile_y]) < max) {
			return false;
		} else {
			return true;
		}
	}
	
	public static boolean IsTileQuarryFrequencyMaxed(int tile_x, int tile_y, int max) {
		if(GetQuarryFrequency(tiles[tile_x][tile_y]) < max) {
			return false;
		} else {
			return true;
		}
	}
	
	public static int GetTileQuarryPower(int tile_x, int tile_y) {
		return GetQuarryPower(tiles[tile_x][tile_y]);
	}
	
	public static int GetTileQuarryFrequency(int tile_x, int tile_y) {
		return GetQuarryFrequency(tiles[tile_x][tile_y]);
	}
	
	public static Tile GetTile(int x, int y) {
		return tiles[x][y];
	}
	
	public static boolean IsTileQuarry(int x, int y) {
		
		if(tiles[x][y].GetID() >= 1 && tiles[x][y].GetID() <= 4) {
			return true;
		} else {
			return false;
		}
		
	}

	static boolean IsEveryQuarryMaxTier() {
		
		boolean result = true;
		
		for (int i = 0; i < WORLD_WIDTH; i++) {
			for (int j = 0; j < WORLD_HEIGHT; j++) {
				
				if(TileData.GetQuarryTier(tiles[i][j]) != 3) {
					result = false;
				}
				
			}
		}
		
		return result;
	}
	
	static void SetCurrentAreaFinished() {
		
		Main.planet_manager.GetPlanet(current_planet_ordinal).GetArea(current_chunk).SetIsAreaFinished(true);
		
	}
	
	public static boolean IsCurrentAreaFinished() {
		
		return Main.planet_manager.GetPlanet(current_planet_ordinal).GetArea(current_chunk).GetIsAreaFinished();
		
	}
	
	public static Color GetRarityColorOfTile(int x, int y) {
		return Main.planet_manager.GetPlanetMineralColour(current_planet_ordinal, tiles[x][y].GetRarity());
	}
}
