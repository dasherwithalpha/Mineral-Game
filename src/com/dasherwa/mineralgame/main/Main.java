package com.dasherwa.mineralgame.main;

import static com.raylib.Raylib.*;

import com.dasherwa.mineralgame.drpc.DRPC;
import com.dasherwa.mineralgame.main.ui.UI;
import com.dasherwa.mineralgame.main.ui.dialoguebox.DialogueBoxManager;
import com.dasherwa.mineralgame.tiles.TileManager;
import com.dasherwa.mineralgame.world.WorldManager;

import static com.raylib.Colors.*;

import com.raylib.Helpers;

public class Main {

	public static final int TILE_SIZE = 16;
	public static final int TILE_COUNT_HORIZONTAL = 10;
	public static final int TILE_COUNT_VERTICAL = 8;
	public static final int SCREEN_BLACKNESS = 2 * TILE_SIZE;
	public static final int GAME_SCREEN_HEIGHT = TILE_SIZE * TILE_COUNT_VERTICAL;
	public static final int GAME_SCREEN_WIDHT = TILE_SIZE * TILE_COUNT_HORIZONTAL;
	
	public static final Color APABROWN = Helpers.newColor(119, 80, 39, 255);
	public static final Color APABLUE = Helpers.newColor(182, 185, 244, 255);
	public static final Color APAWHITE = Helpers.newColor(255, 255, 255, 255);
	public static final Color APAGRAY = Helpers.newColor(75, 75, 75, 255);
	
	public static UI ui;
	public static DialogueBoxManager dialogue_box_manager;
	static DRPC discord_rpc;
	
	static final int SCREEN_MULTIPLIER = 6;
	
	
	
	public static void main(String[] args) {
		InitWindow(
				TILE_SIZE * TILE_COUNT_HORIZONTAL * SCREEN_MULTIPLIER,
				TILE_SIZE * TILE_COUNT_VERTICAL * SCREEN_MULTIPLIER,
				"Mineral Game"
				);
		
		SetTargetFPS(60);
		
		@SuppressWarnings("resource")
		Camera2D camera2d = new Camera2D().zoom(SCREEN_MULTIPLIER);
		
		TextureManager.LoadTextures();
		@SuppressWarnings("unused")
		TileManager tile_manager = new TileManager();
		WorldManager world_manager = new WorldManager();
		
		dialogue_box_manager = new DialogueBoxManager();
		
		ui = new UI();
		
		discord_rpc = new DRPC();
		discord_rpc.InitDiscordRPC();
		
		while(!WindowShouldClose()) {
			
			ui.Update();
			world_manager.UpdateWorld();
			
			BeginDrawing();
			BeginMode2D(camera2d);
			ClearBackground(BLACK);
			world_manager.DrawWorld();
			ui.Draw();
			dialogue_box_manager.Draw();
			EndMode2D();
			EndDrawing();
			
			discord_rpc.UpdateDiscordRPC();
			
			
		}
		
		discord_rpc.EndDiscordRPC();
		
		TextureManager.UnloadTextures();
		camera2d.close();
		CloseWindow();
		
	}

}
