package com.dasherwa.mineralgame.code.main;

import static com.raylib.Raylib.*;

public class TextureManager {

	public static Texture
					dirt_texture,
					quarry_brownite,
					quarry_grayite,
					quarry_whiteoxide,
					quarry_bluntric,
					selected_tile,
					black_bar;
	
	public static void LoadTextures() {
		dirt_texture = LoadTexture("src/com/dasherwa/mineralgame/resources/images/dirt.png");
		quarry_brownite = LoadTexture("src/com/dasherwa/mineralgame/resources/images/quarry-brownite.png");
		quarry_grayite = LoadTexture("src/com/dasherwa/mineralgame/resources/images/quarry-grayite.png");
		quarry_whiteoxide = LoadTexture("src/com/dasherwa/mineralgame/resources/images/quarry-whiteoxide.png");
		quarry_bluntric = LoadTexture("src/com/dasherwa/mineralgame/resources/images/quarry-bluntric.png");
		selected_tile = LoadTexture("src/com/dasherwa/mineralgame/resources/images/selected-tile.png");
		black_bar = LoadTexture("src/com/dasherwa/mineralgame/resources/images/black_bar.png");
	}
	
	public static void UnloadTextures() {
		UnloadTexture(dirt_texture);
		UnloadTexture(quarry_brownite);
		UnloadTexture(quarry_grayite);
		UnloadTexture(quarry_whiteoxide);
		UnloadTexture(quarry_bluntric);
		UnloadTexture(selected_tile);
		UnloadTexture(black_bar);
	}
	
}
