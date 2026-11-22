package com.dasherwa.mineralgame.main;

import static com.raylib.Raylib.*;

public class TextureManager {

	public static Texture
					//TILE TEXTURES
					dirt_texture,
					mineral,
					quarry_body,
					quarry_body_bright,
					quarry_mineral,
					quarry_mineral_bright,
					quarry_rope,
					//MENU TEXTURES
					selected_tile,
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
					quarry_tier_selected,
					//AERAS MENU TEXTURES
					black_bar_areas;
	
	public static void LoadTextures() {
		//TILE TEXTURES
		dirt_texture = LoadTexture("Resources/Textures/Tile/dirt.png");
		mineral = LoadTexture("Resources/Textures/Tile/mineral.png");
		//MENU TEXTURES
		selected_tile = LoadTexture("Resources/Textures/Menu/selected-tile.png");
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
		quarry_tier_selected = LoadTexture("Resources/Textures/Menu/quarry_tier_selected.png");
		//AREA MENU TEXTURES
		black_bar_areas = LoadTexture("Resources/Textures/Menu/black_bar_areas.png");
	}
	
	public static void UnloadTextures() {
		//TILE TEXTURES
		UnloadTexture(dirt_texture);
		UnloadTexture(mineral);
		UnloadTexture(quarry_body);
		UnloadTexture(quarry_body_bright);
		UnloadTexture(quarry_mineral);
		UnloadTexture(quarry_mineral_bright);
		UnloadTexture(quarry_rope);
		//MENU TEXTURES
		UnloadTexture(selected_tile);
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
		UnloadTexture(quarry_tier_selected);
		//AREAS MENU TEXTURES
		UnloadTexture(black_bar_areas);
	}
	
	public static void SwapQuarryTexturesWith(Texture quarry_body, Texture quarry_body_bright, Texture quarry_mineral, Texture quarry_mineral_bright, Texture quarry_rope) {
		TextureManager.quarry_body = quarry_body;
		TextureManager.quarry_body_bright = quarry_body_bright;
		TextureManager.quarry_mineral = quarry_mineral;
		TextureManager.quarry_mineral_bright = quarry_mineral_bright;
		TextureManager.quarry_rope = quarry_rope;
	}
	
}
