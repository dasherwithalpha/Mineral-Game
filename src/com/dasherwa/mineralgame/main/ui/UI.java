package com.dasherwa.mineralgame.main.ui;

import static com.raylib.Raylib.*;

import java.text.DecimalFormat;

import com.dasherwa.mineralgame.main.Main;
import com.dasherwa.mineralgame.main.TextureManager;

public class UI {

	UIState ui_state;
	SelectionType selection_type;
	
	public Vector2 selected_tile;
	public float[] mineralKG_counts = new float[4];
	
	DecimalFormat decimal_format = new DecimalFormat("0.00");	
	
	@SuppressWarnings("resource")
	public UI(){
		
		ui_state = UIState.RESOURCES;
		selection_type = SelectionType.TILE;
		
		selected_tile = new Vector2().x(0).y(0);
		
		mineralKG_counts[0] = 0.0f;
		mineralKG_counts[1] = 0.0f;
		mineralKG_counts[2] = 0.0f;
		mineralKG_counts[3] = 0.0f;
	}
	
	public void Update() {
		
		if(selection_type == SelectionType.TILE){
			
			if(IsKeyPressed(KEY_UP)) {
				
				if(selected_tile.y() == 0) {
					
					selection_type = SelectionType.MENU;
					
				} else {
					
					selected_tile.y(selected_tile.y() - 1);
					
				}
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
			
		}
		
		if(selection_type == SelectionType.MENU) {
			
			if(IsKeyPressed(KEY_DOWN)) {
				
				selection_type = SelectionType.TILE;
				
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
				break;
				
			case OPTIONS:
				break;
				
			case QUARRY:
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
