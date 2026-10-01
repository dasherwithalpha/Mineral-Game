package com.dasherwa.mineralgame.main.ui;

import static com.raylib.Raylib.*;

import java.text.DecimalFormat;

import com.dasherwa.mineralgame.main.Main;
import com.dasherwa.mineralgame.main.TextureManager;

public class UI {

	UIState ui_state;
	
	public Vector2 selected_tile;
	public float[] mineralKG_counts = new float[4];
	
	DecimalFormat decimal_format = new DecimalFormat("0.00");	
	
	@SuppressWarnings("resource")
	public UI(){
		
		ui_state = UIState.RESOURCES;
		
		selected_tile = new Vector2().x(0).y(0);
		
		mineralKG_counts[0] = 0.0f;
		mineralKG_counts[1] = 0.0f;
		mineralKG_counts[2] = 0.0f;
		mineralKG_counts[3] = 0.0f;
	}
	
	public void Update() {
		if(IsKeyPressed(KEY_UP)) {
			selected_tile.y(selected_tile.y() - 1);
		}
		if(IsKeyPressed(KEY_DOWN)) {
			selected_tile.y(selected_tile.y() + 1);
		}
		if(IsKeyPressed(KEY_LEFT)) {
			selected_tile.x(selected_tile.x() - 1);
		}
		if(IsKeyPressed(KEY_RIGHT)) {
			selected_tile.x(selected_tile.x() + 1);
		}
	}
	
	public void Draw() {
		
		switch(ui_state) {
			
			case RESOURCES:
				
				DrawTexture(
						TextureManager.selected_tile,
						(int)selected_tile.x() * Main.TILE_SIZE,
						(int)selected_tile.y() * Main.TILE_SIZE + Main.SCREEN_BLACKNESS,
						Main.APAWHITE
						);
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
		}
		
		
	}
	
}
