package com.dasherwa.mineralgame.main.ui;

import static com.raylib.Raylib.*;

import java.text.DecimalFormat;

import com.dasherwa.mineralgame.main.Main;
import com.dasherwa.mineralgame.main.TextureManager;

public class UI {

	public UIState ui_state;
	public SelectionType selection_type;
	
	public Vector2 selected_tile;
	public Vector2 selected_menu, selected_menu_draw;
	public final Vector2 SELL, AREAS, QUARRY;
	public float[] mineralKG_counts;
	int balance;
	
	DecimalFormat decimal_format, decimal_format2;	
	
	@SuppressWarnings("resource")
	public UI(){
		
		decimal_format = new DecimalFormat("0.00");
		decimal_format2 = new DecimalFormat("00000000000000");
		
		ui_state = UIState.RESOURCES;
		selection_type = SelectionType.TILE;
		
		selected_tile = new Vector2().x(0).y(0);
		selected_menu = new Vector2().x(0).y(0);
		selected_menu_draw = new Vector2().x(0).y(0);
		
		SELL = new Vector2().x(0).y(0);
		AREAS = new Vector2().x(0).y(1);
		QUARRY = new Vector2().x(1).y(0);
		
		balance = 0;
		
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
			System.out.println(selection_type.toString());
		}
		
	}
	void SelectionTypeMenuUpdate() {
		
		if(IsKeyPressed(KEY_LEFT) && IsKeyDown(KEY_APOSTROPHE)) {
			ui_state = ui_state.Previous();
			System.out.println(ui_state.toString());
		}
		if(IsKeyPressed(KEY_RIGHT) && IsKeyDown(KEY_APOSTROPHE)) {
			ui_state = ui_state.Next();
			System.out.println(ui_state.toString());
		}
		
		if(IsKeyPressed(KEY_K)) {
			
			selection_type = SelectionType.TILE;
			System.out.println(selection_type.toString());
			
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
			
			selected_menu_draw.x(selected_menu.x() * 48 + 11);
			selected_menu_draw.y(selected_menu.y() * 15 + 1);
			
			if(IsKeyPressed(KEY_ENTER)) {
				
				if(selected_menu.x() == SELL.x() && selected_menu.y() == SELL.y()) {
					ui_state = UIState.SELL;
				}
				if(selected_menu.x() == AREAS.x() && selected_menu.y() == AREAS.y()) {
					ui_state = UIState.AERAS;
				}
				if (selected_menu.x() == QUARRY.x() && selected_menu.y() == QUARRY.y()) {
					ui_state = UIState.QUARRY;
				}
			}
			
		}
		
		if(ui_state == UIState.SELL) {
			if(IsKeyPressed(KEY_APOSTROPHE)) {
				ui_state = UIState.OPTIONS;
			}
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
				break;
				
			case SELL:
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
	
}
