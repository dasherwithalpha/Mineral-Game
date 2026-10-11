package com.dasherwa.mineralgame.main.ui;

import static com.raylib.Raylib.*;

import java.text.DecimalFormat;

import com.dasherwa.mineralgame.gem.GemManager;
import com.dasherwa.mineralgame.main.Main;
import com.dasherwa.mineralgame.main.TextureManager;
import com.dasherwa.mineralgame.main.ui.dialoguebox.BoxType;
import com.dasherwa.mineralgame.tiles.TileData;
import com.dasherwa.mineralgame.world.WorldManager;
import com.dasherwa.utilities.romannumeral.RomanNumeral;

public class UI {

	public UIState ui_state;
	public SelectionType selection_type;
	
	public Vector2 selected_tile;
	public Vector2 selected_menu, selected_menu_draw, selected_sell, selected_quarry;
	public final Vector2 SELL, AREAS, QUARRY;
	public float[] mineralKG_counts;
	long balance;
	int quarry_count;
	
	DecimalFormat decimal_format, decimal_format2;	
	GemManager gem_manager;
	
	@SuppressWarnings("resource")
	public UI(){
		
		decimal_format = new DecimalFormat("0.00");
		decimal_format2 = new DecimalFormat("00000000000000");
		
		quarry_count = 0;
		
		gem_manager = new GemManager();
		
		ui_state = UIState.RESOURCES;
		selection_type = SelectionType.TILE;
		
		selected_tile = new Vector2().x(0).y(0);
		selected_menu = new Vector2().x(0).y(0);
		selected_menu_draw = new Vector2().x(0).y(0);
		selected_sell = new Vector2().x(0).y(0);
		selected_quarry = new Vector2().x(0).y(1);
		
		SELL = new Vector2().x(0).y(0);
		AREAS = new Vector2().x(0).y(1);
		QUARRY = new Vector2().x(1).y(0);
		
		balance = 1000;
		
		mineralKG_counts = new float[4];
		mineralKG_counts[0] = 0.0f;
		mineralKG_counts[1] = 0.0f;
		mineralKG_counts[2] = 0.0f;
		mineralKG_counts[3] = 0.0f;
	}
	
	public void Update() {
		
		
		switch(selection_type) {
			
			case TILE:
				
				SelectionTypeTileUpdate();
				
				break;
				
			case MENU:
				
				SelectionTypeMenuUpdate();
				
				break;
			
		}	
	}
	
	void SelectionTypeTileUpdate() {
		
		if(IsKeyPressed(KEY_UP) && selected_tile.y() > 0) {
			selected_tile.y(selected_tile.y() - 1);
		}
		if(IsKeyPressed(KEY_DOWN) && selected_tile.y() < 5) {
			selected_tile.y(selected_tile.y() + 1);
		}
		if(IsKeyPressed(KEY_LEFT) && selected_tile.x() > 0) {
			selected_tile.x(selected_tile.x() - 1);
		}
		if(IsKeyPressed(KEY_RIGHT) && selected_tile.x() < 9) {
			selected_tile.x(selected_tile.x() + 1);
		}
		
		if(IsKeyPressed(KEY_K)) {
			selection_type = SelectionType.MENU;
			selected_menu.x(0);
			selected_menu.y(0);
		}
		
	}
	void SelectionTypeMenuUpdate() {
		
		if(IsKeyPressed(KEY_K)) {
			selection_type = SelectionType.TILE;
			return;
		}
		
		if(ui_state == UIState.RESOURCES || ui_state == UIState.BALANCE || ui_state == UIState.OPTIONS) {
			if(IsKeyPressed(KEY_LEFT) && IsKeyDown(KEY_APOSTROPHE)) {
				ui_state = ui_state.Previous();
				System.out.println(ui_state.toString());
			}
			if(IsKeyPressed(KEY_RIGHT) && IsKeyDown(KEY_APOSTROPHE)) {
				ui_state = ui_state.Next();
				System.out.println(ui_state.toString());
			}
		}
				
		if(ui_state == UIState.OPTIONS) {
			
			if(IsKeyPressed(KEY_UP) && selected_menu.y() > 0) {
				selected_menu.y(selected_menu.y() - 1);
			}
			if(IsKeyPressed(KEY_DOWN) && selected_menu.y() < 1) {
				selected_menu.y(selected_menu.y() + 1);
			}
			if(IsKeyPressed(KEY_LEFT) && !IsKeyDown(KEY_APOSTROPHE) && selected_menu.x() > 0) {
				selected_menu.x(selected_menu.x() - 1);
			}
			if(IsKeyPressed(KEY_RIGHT) && !IsKeyDown(KEY_APOSTROPHE) && selected_menu.x() < 2) {
				selected_menu.x(selected_menu.x() + 1);
			}
			
			if (IsKeyPressed(KEY_ENTER)) {

				if (selected_menu.x() == SELL.x() && selected_menu.y() == SELL.y()) {
					ui_state = UIState.SELL;
					selected_sell.x(4);
					return;
				}
				if (selected_menu.x() == AREAS.x() && selected_menu.y() == AREAS.y()) {
					ui_state = UIState.AERAS;
					return;
				}
				if (selected_menu.x() == QUARRY.x() && selected_menu.y() == QUARRY.y()) {
					ui_state = UIState.QUARRY;
					selected_quarry.x(1);
					return;
				}
			}
			
			selected_menu_draw.x(selected_menu.x() * 48 + 11);
			selected_menu_draw.y(selected_menu.y() * 15 + 1);
			
		}
		
		if(ui_state == UIState.SELL) {
			
			if(IsKeyPressed(KEY_LEFT) && selected_sell.x() > 0) {
				selected_sell.x(selected_sell.x() - 1);
				
			}
			if(IsKeyPressed(KEY_RIGHT) && selected_sell.x() < 4) {
				selected_sell.x(selected_sell.x() + 1);
			}
			
			if(selected_sell.x() >= 0 && selected_sell.x() <= 3) {
				
				if(!IsKeyDown(KEY_APOSTROPHE)) {
					
					selected_menu_draw.x(selected_sell.x() * 30 + 1);
					selected_menu_draw.y(2);
					
					if(IsKeyPressed(KEY_ENTER)) {
						balance += Math.round(mineralKG_counts[(int)selected_sell.x()] * GemManager.GetGemValue((int)selected_sell.x()));
						mineralKG_counts[(int)selected_sell.x()] = 0;
						ui_state = UIState.BALANCE;
						System.out.println(balance);
					}
					
				}
				
				if(IsKeyDown(KEY_APOSTROPHE)) {
					
					selected_menu_draw.x(1);
					selected_menu_draw.y(2);
					
					if(IsKeyPressed(KEY_ENTER)) {
						for (int i = 0; i < 4; i++) {
							balance += Math.round(mineralKG_counts[i] * GemManager.GetGemValue(i));
							mineralKG_counts[i] = 0;
						}
						ui_state = UIState.BALANCE;
					}
					
				}
				
			}
			if(selected_sell.x() == 4) {
				selected_menu_draw.x(137);
				selected_menu_draw.y(20);
				
				if(IsKeyPressed(KEY_ENTER)) {
					ui_state = UIState.OPTIONS;
				}
			}
		}
		
		if(ui_state == UIState.QUARRY) {
			
			if(IsKeyPressed(KEY_LEFT)) {
				selected_quarry.x(0);
			}
			if(IsKeyPressed(KEY_RIGHT)) {
				selected_quarry.x(1);
			}
			if(IsKeyPressed(KEY_UP)) {
				selected_quarry.y(0);
			}
			if(IsKeyPressed(KEY_DOWN)) {
				selected_quarry.y(1);
			}
			
			if(IsKeyPressed(KEY_ENTER)) {
				
				if(		WorldManager.IsTileQuarry((int)selected_tile.x(), (int)selected_tile.y()) &&
						selected_quarry.x() == 0 &&
						selected_quarry.y() == 0 &&
						balance > TileData.GetQuarryUpgradePowerPrice(WorldManager.GetTile((int)selected_tile.x(), (int)selected_tile.y())) &&
						!WorldManager.IsTileQuarryPowerMaxed((int)selected_tile.x(), (int)selected_tile.y(), 10)
						)
				{
					balance -= TileData.GetQuarryUpgradePowerPrice(WorldManager.GetTile((int)selected_tile.x(), (int)selected_tile.y()));
					
					Main.dialogue_box_manager.SetRemovedMoneyBoxText(TileData.GetQuarryUpgradePowerPrice(WorldManager.GetTile((int)selected_tile.x(), (int)selected_tile.y())));
					Main.dialogue_box_manager.SetBoxShow(BoxType.REMOVED_MONEY);
					
					WorldManager.IncreaseTileQuarryPower((int)selected_tile.x(), (int)selected_tile.y(), 1);
					System.out.println(WorldManager.GetTileQuarryPower((int)selected_tile.x(), (int)selected_tile.y()));
				
				} else if (WorldManager.IsTileQuarryPowerMaxed((int)selected_tile.x(), (int)selected_tile.y(), 10) &&
						selected_quarry.x() == 0 &&
						selected_quarry.y() == 0
						)
				{
				
					Main.dialogue_box_manager.SetBoxShow(BoxType.MAX_QUARRY_ATTRIBUTE_LEVEL);
				
				}	else if(	!WorldManager.IsTileQuarry((int)selected_tile.x(), (int)selected_tile.y()) &&
							selected_quarry.x() == 0 &&
							selected_quarry.y() == 0
							)
				{	
						
					Main.dialogue_box_manager.SetBoxShow(BoxType.NOT_A_QUARRY);
					
				
				} else if(	balance < TileData.GetQuarryUpgradePowerPrice(WorldManager.GetTile((int)selected_tile.x(), (int)selected_tile.y())) &&
							selected_quarry.x() == 0 &&
							selected_quarry.y() == 0
							)
				{
					
					Main.dialogue_box_manager.SetBoxShow(BoxType.NOT_ENOUGH_MONEY);
					
				}
				
				if (	WorldManager.IsTileQuarry((int)selected_tile.x(), (int)selected_tile.y()) &&
						selected_quarry.x() == 0 &&
						selected_quarry.y() == 1 &&
						balance > TileData.GetQuarryUpgradeFrequencyPrice(WorldManager.GetTile((int)selected_tile.x(), (int)selected_tile.y())) &&
						!WorldManager.IsTileQuarryFrequencyMaxed((int)selected_tile.x(), (int)selected_tile.y(), 10)
						)
				{
					
					balance -= TileData.GetQuarryUpgradeFrequencyPrice(WorldManager.GetTile((int)selected_tile.x(), (int)selected_tile.y()));
					
					Main.dialogue_box_manager.SetRemovedMoneyBoxText(TileData.GetQuarryUpgradeFrequencyPrice(WorldManager.GetTile((int)selected_tile.x(), (int)selected_tile.y())));
					Main.dialogue_box_manager.SetBoxShow(BoxType.REMOVED_MONEY);
					
					WorldManager.IncreaseTileQuarryFrequency((int)selected_tile.x(), (int)selected_tile.y(), 1);
				
				}  else if (WorldManager.IsTileQuarryFrequencyMaxed((int)selected_tile.x(), (int)selected_tile.y(), 10) &&
						selected_quarry.x() == 0 &&
						selected_quarry.y() == 1
						)
						
				{
		
					Main.dialogue_box_manager.SetBoxShow(BoxType.MAX_QUARRY_ATTRIBUTE_LEVEL);

				}	else if(!WorldManager.IsTileQuarry((int)selected_tile.x(), (int)selected_tile.y()) &&
						selected_quarry.x() == 0 &&
						selected_quarry.y() == 1
						)
				{
					
					Main.dialogue_box_manager.SetBoxShow(BoxType.NOT_A_QUARRY);
					
				}  else if(	balance < TileData.GetQuarryUpgradeFrequencyPrice(WorldManager.GetTile((int)selected_tile.x(), (int)selected_tile.y())) &&
							selected_quarry.x() == 0 &&
							selected_quarry.y() == 1
							)
				{
					
					Main.dialogue_box_manager.SetBoxShow(BoxType.NOT_ENOUGH_MONEY);
					
				}
				
				if(selected_quarry.x() == 1) {
					ui_state = UIState.OPTIONS;
				}
				
			}
			
			
			if(selected_quarry.x() == 1) {
				selected_menu_draw.x(137);
				selected_menu_draw.y(20);
			}
			
			if(selected_quarry.x() == 0) {
				selected_menu_draw.x(34);
				selected_menu_draw.y(selected_quarry.y() * 16);
			}
		}
	
		if(ui_state == UIState.AERAS) {
			ui_state = UIState.OPTIONS;
		}

	}
	
	public void Draw() {
		
		switch(ui_state) {
			
			case RESOURCES:
				DrawTexture(TextureManager.black_bar, 0, 0, Main.APAWHITE);
				DrawText(decimal_format.format(mineralKG_counts[2]), 20, 18, 13, Main.APAWHITE);
				DrawText(decimal_format.format(mineralKG_counts[3]), 20, 3, 13, Main.APAWHITE);
				DrawText(decimal_format.format(mineralKG_counts[0]), 100, 18, 13, Main.APAWHITE);
				DrawText(decimal_format.format(mineralKG_counts[1]), 100, 3, 13, Main.APAWHITE);
				break;
				
			case BALANCE:
				DrawTexture(TextureManager.black_bar_balance, 0, 0, Main.APAWHITE);
				DrawText(decimal_format2.format(balance), 53, 10, 13, Main.APAWHITE);
				break;
				
			case OPTIONS:
				DrawTexture(TextureManager.black_bar_options, 0, 0, Main.APAWHITE);
				DrawTexture(TextureManager.selected_option, (int)selected_menu_draw.x(), (int)selected_menu_draw.y(), Main.APAWHITE);
				break;
				
			case QUARRY:
				DrawTexture(TextureManager.black_bar_quarry, 0, 0, Main.APAWHITE);
				DrawText(
						"" + RomanNumeral.GetRomanNumeral(WorldManager.GetTileQuarryPower((int)selected_tile.x(), (int)selected_tile.y())),
						55,
						1,
						16,
						Main.APAWHITE
						);
				DrawText(
						"" + RomanNumeral.GetRomanNumeral(WorldManager.GetTileQuarryFrequency((int)selected_tile.x(), (int)selected_tile.y())),
						55,
						17,
						16,
						Main.APAWHITE
						);
				
				DrawTexture(
						TextureManager.selected_tile_notinmenu,
						(int)selected_tile.x() * Main.TILE_SIZE,
						(int)selected_tile.y() * Main.TILE_SIZE + Main.SCREEN_BLACKNESS,
						Main.APAWHITE
						);
				
				if(selected_quarry.x() == 1) {
					DrawTexture(TextureManager.quarry_back_menu, (int)selected_menu_draw.x(), (int)selected_menu_draw.y(), Main.APAWHITE);
				}
				
				if(selected_quarry.x() == 0) {
					DrawTexture(TextureManager.quarry_selected_attribute, (int)selected_menu_draw.x(), (int)selected_menu_draw.y(), Main.APAWHITE);
				}
				
				break;
				
			case SELL:
				DrawTexture(TextureManager.black_bar_sell, 0, 0, Main.APAWHITE);
				
				if(selected_sell.x() >= 0 && selected_sell.x() <= 3) {
					
					if(!IsKeyDown(KEY_APOSTROPHE)) {
						DrawTexture(TextureManager.sell_selected_mineral, (int)selected_menu_draw.x(), (int)selected_menu_draw.y(), Main.APAWHITE);
					} else {
						DrawTexture(TextureManager.sell_selected_mineral_all, (int)selected_menu_draw.x(), (int)selected_menu_draw.y(), Main.APAWHITE);
					}
				}
				if(selected_sell.x() == 4) {
					DrawTexture(TextureManager.sell_back_menu, (int)selected_menu_draw.x(), (int)selected_menu_draw.y(), Main.APAWHITE);
				}
				break;
				
			case AERAS:
				break;
		}
		
		switch(selection_type) {
			
			case TILE:
				DrawTexture(
						TextureManager.selected_tile,
						(int)selected_tile.x() * Main.TILE_SIZE,
						(int)selected_tile.y() * Main.TILE_SIZE + Main.SCREEN_BLACKNESS,
						Main.APAWHITE
						);
				break;
				
			case MENU:
				break;
		}
		
		
	}
	
	public long GetTotalBalance() {
		return balance;
	}
	
	public void SetTotalBalance(long balance) {
		this.balance = balance;
	}
	
	public void IncreaseQuarryCountByOne() {
		quarry_count += 1;
	}
	
	public int GetQuarryCount() {
		return quarry_count;
	}
}
