package com.dasherwa.mineralgame.main;

import static com.raylib.Raylib.*;

public class TextureManager {

	public static Texture
					dirt_texture,
					quarry_brownite,
					quarry_grayite,
					quarry_whiteoxide,
					quarry_bluntric,
					selected_tile,
					black_bar,
					black_bar_options,
					black_bar_balance,
					selected_option,
					black_bar_sell,
					sell_back_menu,
					sell_selected_mineral,
					sell_selected_mineral_all,
					black_bar_quarry,
					quarry_selected_attribute,
					quarry_back_menu,
					selected_tile_notinmenu;
	
	public static void LoadTextures() {
		dirt_texture = LoadTexture("Resources/Textures/dirt.png");
		quarry_brownite = LoadTexture("Resources/Textures/quarry-brownite.png");
		quarry_grayite = LoadTexture("Resources/Textures/quarry-grayite.png");
		quarry_whiteoxide = LoadTexture("Resources/Textures/quarry-whiteoxide.png");
		quarry_bluntric = LoadTexture("Resources/Textures/quarry-bluntric.png");
		selected_tile = LoadTexture("Resources/Textures/selected-tile.png");
		black_bar = LoadTexture("Resources/Textures/black_bar.png");
		black_bar_options = LoadTexture("Resources/Textures/black_bar_options.png");
		black_bar_balance = LoadTexture("Resources/Textures/black_bar_balance.png");
		selected_option = LoadTexture("Resources/Textures/selected_option.png");
		black_bar_sell = LoadTexture("Resources/Textures/black_bar_sell.png");
		sell_back_menu = LoadTexture("Resources/Textures/sell_back_menu.png");
		sell_selected_mineral = LoadTexture("Resources/Textures/sell_selected_mineral.png");
		sell_selected_mineral_all = LoadTexture("Resources/Textures/sell_selected_mineral_all.png");
		black_bar_quarry = LoadTexture("Resources/Textures/black_bar_quarry.png");
		quarry_selected_attribute = LoadTexture("Resources/Textures/quarry_selected_attribute.png");
		quarry_back_menu = LoadTexture("Resources/Textures/quarry_back_menu.png");
		selected_tile_notinmenu = LoadTexture("Resources/Textures/selected-tile_notinmenu.png");
	}
	
	public static void UnloadTextures() {
		UnloadTexture(dirt_texture);
		UnloadTexture(quarry_brownite);
		UnloadTexture(quarry_grayite);
		UnloadTexture(quarry_whiteoxide);
		UnloadTexture(quarry_bluntric);
		UnloadTexture(selected_tile);
		UnloadTexture(black_bar);
		UnloadTexture(black_bar_options);
		UnloadTexture(black_bar_balance);
		UnloadTexture(selected_option);
		UnloadTexture(black_bar_sell);
		UnloadTexture(sell_back_menu);
		UnloadTexture(sell_selected_mineral);
		UnloadTexture(sell_selected_mineral_all);
		UnloadTexture(black_bar_quarry);
		UnloadTexture(quarry_selected_attribute);
		UnloadTexture(quarry_back_menu);
		UnloadTexture(selected_tile_notinmenu);
	}
	
}
