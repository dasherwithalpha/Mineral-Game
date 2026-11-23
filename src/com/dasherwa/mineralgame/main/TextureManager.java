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
					selected_tile_notinmenu,
					balance_money,
					selected_option,
					button_back,
					button_back_selected,
					//SELL MENU TEXTURES
					sell_selected_mineral,
					sell_selected_mineral_all,
					//QUARRY MENU TEXTURES
					quarry_selected_attribute,
					quarry_tier_selected,
					quarry_upgrade_frequency,
					quarry_upgrade_power,
					quarry_upgrade_tree;
	
	public static Texture[] texts = new Texture[6];
	
	public static void LoadTextures() {
		//TILE TEXTURES
		dirt_texture = LoadTexture("Resources/Textures/Tile/dirt.png");
		mineral = LoadTexture("Resources/Textures/Tile/mineral.png");
		//MENU TEXTURES
		texts[0] = LoadTexture("Resources/Textures/Menu/text_SELL.png");
		texts[1] = LoadTexture("Resources/Textures/Menu/text_QUARRY.png");
		texts[2] = LoadTexture("Resources/Textures/Menu/text_SETTINGS.png");
		texts[3] = LoadTexture("Resources/Textures/Menu/text_AREAS.png");
		texts[4] = LoadTexture("Resources/Textures/Menu/text_PLANET.png");
		texts[5] = LoadTexture("Resources/Textures/Menu/text_EXIT.png");
		selected_tile = LoadTexture("Resources/Textures/Menu/selected-tile.png");
		balance_money = LoadTexture("Resources/Textures/Menu/balance_money.png");
		selected_option = LoadTexture("Resources/Textures/Menu/selected_option.png");
		button_back = LoadTexture("Resources/Textures/Menu/button_back.png");
		button_back_selected = LoadTexture("Resources/Textures/Menu/button_back_selected.png");
		//SELL MENU TEXTURES
		sell_selected_mineral = LoadTexture("Resources/Textures/Menu/sell_selected_mineral.png");
		sell_selected_mineral_all = LoadTexture("Resources/Textures/Menu/sell_selected_mineral_all.png");
		//QUARRY MENU TEXTURES
		quarry_selected_attribute = LoadTexture("Resources/Textures/Menu/quarry_selected_attribute.png");
		selected_tile_notinmenu = LoadTexture("Resources/Textures/Menu/selected-tile_notinmenu.png");
		quarry_tier_selected = LoadTexture("Resources/Textures/Menu/quarry_tier_selected.png");
		quarry_upgrade_power = LoadTexture("Resources/Textures/Menu/quarry_upgrade_power.png");
		quarry_upgrade_frequency = LoadTexture("Resources/Textures/Menu/quarry_upgrade_frequency.png");
		quarry_upgrade_tree = LoadTexture("Resources/Textures/Menu/quarry_upgrade_tree.png");
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
		for(int i = 0; i<6; i++) {
			UnloadTexture(texts[i]);
		}
		UnloadTexture(selected_tile);
		UnloadTexture(balance_money);
		UnloadTexture(selected_option);
		UnloadTexture(button_back);
		UnloadTexture(button_back_selected);
		//SELL MENU TEXTURES
		UnloadTexture(sell_selected_mineral);
		UnloadTexture(sell_selected_mineral_all);
		//QUARRY MENU TEXTURES
		UnloadTexture(quarry_selected_attribute);
		UnloadTexture(selected_tile_notinmenu);
		UnloadTexture(quarry_tier_selected);
		UnloadTexture(quarry_upgrade_power);
		UnloadTexture(quarry_upgrade_frequency);
		UnloadTexture(quarry_upgrade_tree);
	}
	
	public static void SwapQuarryTexturesWith(Texture quarry_body, Texture quarry_body_bright, Texture quarry_mineral, Texture quarry_mineral_bright, Texture quarry_rope) {
		TextureManager.quarry_body = quarry_body;
		TextureManager.quarry_body_bright = quarry_body_bright;
		TextureManager.quarry_mineral = quarry_mineral;
		TextureManager.quarry_mineral_bright = quarry_mineral_bright;
		TextureManager.quarry_rope = quarry_rope;
	}
	
}
