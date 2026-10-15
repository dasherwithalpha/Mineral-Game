package com.dasherwa.mineralgame.main;

import static com.raylib.Raylib.*;

public class TextureManager {

	public static Texture
					//TILE TEXTURES
					dirt_texture,
					quarry_cycle_completed,
					quarry_brownite_01,
					quarry_brownite_02,
					quarry_brownite_03,
					quarry_brownite_04,
					quarry_grayite_01,
					quarry_grayite_02,
					quarry_grayite_03,
					quarry_grayite_04,
					quarry_whiteoxide_01,
					quarry_whiteoxide_02,
					quarry_whiteoxide_03,
					quarry_whiteoxide_04,
					quarry_bluntric_01,
					quarry_bluntric_02,
					quarry_bluntric_03,
					quarry_bluntric_04,
					//MENU TEXTURES
					selected_tile,
					black_bar,
					black_bar_options,
					black_bar_balance,
					selected_option,
					//SELL MENU TEXTURES
					black_bar_sell,
					sell_back_menu,
					sell_selected_mineral,
					sell_selected_mineral_all,
					//QUARRY MENU TEXTURES
					black_bar_quarry,
					quarry_selected_attribute,
					quarry_back_menu,
					selected_tile_notinmenu,
					quarry_00_tier_1,
					quarry_00_tier_2,
					quarry_00_tier_3,
					quarry_00_tier_4,
					quarry_tier_selected,
					//AERAS MENU TEXTURES
					black_bar_areas,
					areas_selected_area,
					areas_selled_area;
					
	
	public static void LoadTextures() {
		//TILE TEXTURES
		dirt_texture = LoadTexture("Resources/Textures/Tile/dirt.png");
		quarry_cycle_completed = LoadTexture("Resources/Textures/Tile/quarry-00-cycle-completed.png");
		quarry_brownite_01 = LoadTexture("Resources/Textures/Tile/quarry-01-1.png");
		quarry_brownite_02 = LoadTexture("Resources/Textures/Tile/quarry-01-2.png");
		quarry_brownite_03 = LoadTexture("Resources/Textures/Tile/quarry-01-3.png");
		quarry_brownite_04 = LoadTexture("Resources/Textures/Tile/quarry-01-4.png");
		quarry_grayite_01 = LoadTexture("Resources/Textures/Tile/quarry-02-1.png");
		quarry_grayite_02 = LoadTexture("Resources/Textures/Tile/quarry-02-2.png");
		quarry_grayite_03 = LoadTexture("Resources/Textures/Tile/quarry-02-3.png");
		quarry_grayite_04 = LoadTexture("Resources/Textures/Tile/quarry-02-4.png");
		quarry_whiteoxide_01 = LoadTexture("Resources/Textures/Tile/quarry-03-1.png");
		quarry_whiteoxide_02 = LoadTexture("Resources/Textures/Tile/quarry-03-2.png");
		quarry_whiteoxide_03 = LoadTexture("Resources/Textures/Tile/quarry-03-3.png");
		quarry_whiteoxide_04 = LoadTexture("Resources/Textures/Tile/quarry-03-4.png");
		quarry_bluntric_01 = LoadTexture("Resources/Textures/Tile/quarry-04-1.png");
		quarry_bluntric_02 = LoadTexture("Resources/Textures/Tile/quarry-04-2.png");
		quarry_bluntric_03 = LoadTexture("Resources/Textures/Tile/quarry-04-3.png");
		quarry_bluntric_04 = LoadTexture("Resources/Textures/Tile/quarry-04-4.png");
		//MENU TEXTURES
		selected_tile = LoadTexture("Resources/Textures/Menu/selected-tile.png");
		black_bar = LoadTexture("Resources/Textures/Menu/black_bar.png");
		black_bar_options = LoadTexture("Resources/Textures/Menu/black_bar_options.png");
		black_bar_balance = LoadTexture("Resources/Textures/Menu/black_bar_balance.png");
		selected_option = LoadTexture("Resources/Textures/Menu/selected_option.png");
		//SELL MENU TEXTURES
		black_bar_sell = LoadTexture("Resources/Textures/Menu/black_bar_sell.png");
		sell_back_menu = LoadTexture("Resources/Textures/Menu/sell_back_menu.png");
		sell_selected_mineral = LoadTexture("Resources/Textures/Menu/sell_selected_mineral.png");
		sell_selected_mineral_all = LoadTexture("Resources/Textures/Menu/sell_selected_mineral_all.png");
		//QUARRY MENU TEXTURES
		black_bar_quarry = LoadTexture("Resources/Textures/Menu/black_bar_quarry.png");
		quarry_selected_attribute = LoadTexture("Resources/Textures/Menu/quarry_selected_attribute.png");
		quarry_back_menu = LoadTexture("Resources/Textures/Menu/quarry_back_menu.png");
		selected_tile_notinmenu = LoadTexture("Resources/Textures/Menu/selected-tile_notinmenu.png");
		quarry_00_tier_1 = LoadTexture("Resources/Textures/Menu/quarry_00_tier_1.png");
		quarry_00_tier_2 = LoadTexture("Resources/Textures/Menu/quarry_00_tier_2.png");
		quarry_00_tier_3 = LoadTexture("Resources/Textures/Menu/quarry_00_tier_3.png");
		quarry_00_tier_4 = LoadTexture("Resources/Textures/Menu/quarry_00_tier_4.png");
		quarry_tier_selected = LoadTexture("Resources/Textures/Menu/quarry_tier_selected.png");
		//AREA MENU TEXTURES
		black_bar_areas = LoadTexture("Resources/Textures/Menu/black_bar_area.png");
		areas_selected_area = LoadTexture("Resources/Textures/Menu/areas_selected_area.png");
		areas_selled_area = LoadTexture("Resources/Textures/Menu/areas_selled_area.png");
	}
	
	public static void UnloadTextures() {
		//TILE TEXTURES
		UnloadTexture(dirt_texture);
		UnloadTexture(quarry_cycle_completed);
		UnloadTexture(quarry_brownite_01);
		UnloadTexture(quarry_brownite_02);
		UnloadTexture(quarry_brownite_03);
		UnloadTexture(quarry_brownite_04);
		UnloadTexture(quarry_grayite_01);
		UnloadTexture(quarry_grayite_02);
		UnloadTexture(quarry_grayite_03);
		UnloadTexture(quarry_grayite_04);
		UnloadTexture(quarry_whiteoxide_01);
		UnloadTexture(quarry_whiteoxide_02);
		UnloadTexture(quarry_whiteoxide_03);
		UnloadTexture(quarry_whiteoxide_04);
		UnloadTexture(quarry_bluntric_01);
		UnloadTexture(quarry_bluntric_02);
		UnloadTexture(quarry_bluntric_03);
		UnloadTexture(quarry_bluntric_04);
		//MENU TEXTURES
		UnloadTexture(selected_tile);
		UnloadTexture(black_bar);
		UnloadTexture(black_bar_options);
		UnloadTexture(black_bar_balance);
		UnloadTexture(selected_option);
		//SELL MENU TEXTURES
		UnloadTexture(black_bar_sell);
		UnloadTexture(sell_back_menu);
		UnloadTexture(sell_selected_mineral);
		UnloadTexture(sell_selected_mineral_all);
		//QUARRY MENU TEXTURES
		UnloadTexture(black_bar_quarry);
		UnloadTexture(quarry_selected_attribute);
		UnloadTexture(quarry_back_menu);
		UnloadTexture(selected_tile_notinmenu);
		UnloadTexture(quarry_00_tier_1);
		UnloadTexture(quarry_00_tier_2);
		UnloadTexture(quarry_00_tier_3);
		UnloadTexture(quarry_00_tier_4);
		UnloadTexture(quarry_tier_selected);
		//AREAS MENU TEXTURES
		UnloadTexture(black_bar_areas);
		UnloadTexture(areas_selected_area);
		UnloadTexture(areas_selled_area);
	}
	
}
