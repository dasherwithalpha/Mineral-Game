package com.dasherwa.mineralgame.main;

import static com.raylib.Raylib.*;

import com.dasherwa.mineralgame.drpc.DRPC;
import com.dasherwa.mineralgame.main.ui.UI;
import com.dasherwa.mineralgame.main.ui.dialoguebox.DialogueBoxManager;
import com.dasherwa.mineralgame.tiles.TileManager;
import com.dasherwa.mineralgame.world.PlanetManager;
import com.dasherwa.mineralgame.world.WorldManager;
import com.dasherwa.utilities.version.Version;

import static com.dasherwa.utilities.version.Version.ReleaseIdentifier.*;

import static com.raylib.Colors.*;

public class Main {

	public static final int TILE_SIZE = 16;
	public static final int TILE_COUNT_HORIZONTAL = 10;
	public static final int TILE_COUNT_VERTICAL = 8;
	public static final int SCREEN_BLACKNESS = 2 * TILE_SIZE;
	public static final int GAME_SCREEN_HEIGHT = TILE_SIZE * TILE_COUNT_VERTICAL;
	public static final int GAME_SCREEN_WIDHT = TILE_SIZE * TILE_COUNT_HORIZONTAL;
	
	public static UI ui;
	public static DialogueBoxManager dialogue_box_manager;
	public static boolean should_exit;
	public static PlanetManager planet_manager;
	
	static DRPC discord_rpc;
	static boolean discord_rpc_on;
	static Version version;
	
	static final int SCREEN_MULTIPLIER = 7;
	
	static final int MAJOR = 2;
	static final int MINOR = 0;
	static final int PATCH = 0;
	static final Version.ReleaseIdentifier RELEASE_IDENTIFIER = ALPHA;
	static final String BUILD = "0006-20261120";
	
	
	
	public static void main(String[] args) {
		
		if(args.length == 0) {
			discord_rpc_on = false;
		} else {
			discord_rpc_on = true;
		}
		
		version = new Version.Builder()
				.major(MAJOR)
				.minor(MINOR)
				.patch(PATCH)
				.release(RELEASE_IDENTIFIER)
				.buildver(BUILD)
				.build();
		
		InitWindow(
				TILE_SIZE * TILE_COUNT_HORIZONTAL * SCREEN_MULTIPLIER,
				TILE_SIZE * TILE_COUNT_VERTICAL * SCREEN_MULTIPLIER,
				"Mineral Game " + version.GetVersionName(1)
				);
		
		SetTargetFPS(60);
		SetExitKey(KEY_NULL);
		
		@SuppressWarnings("resource")
		Camera2D camera2d = new Camera2D().zoom(SCREEN_MULTIPLIER);
		
		TextureManager.LoadTextures();
		@SuppressWarnings("unused")
		TileManager tile_manager = new TileManager();
		planet_manager = new PlanetManager();
		WorldManager world_manager = new WorldManager();
		
		
		dialogue_box_manager = new DialogueBoxManager();
		
		ui = new UI();
		
		if(discord_rpc_on) {
			discord_rpc = new DRPC(args[0]);
			discord_rpc.InitDiscordRPC();
		}
		
		
		while(!should_exit) {
			
			if(WindowShouldClose()) {
				should_exit = true;
			}
			
			ui.Update();
			world_manager.UpdateWorld();
			
//			if(IsKeyPressed(KEY_A)) {
//				WorldManager.SaveTilesToAreas();
//			}
//			if(IsKeyPressed(KEY_S)) {
//				WorldManager.LoadTilesFromAreas();
//			}
			System.out.println(planet_manager.GetPlanetAreaTileName(0, 0, 0, 0, 1));
			BeginDrawing();
			BeginMode2D(camera2d);
			ClearBackground(BLACK);
			world_manager.DrawWorld();
			ui.Draw();
			dialogue_box_manager.Draw();
			EndMode2D();
			EndDrawing();
			
			if(discord_rpc_on) {
				discord_rpc.UpdateDiscordRPC();
			}
		}
		
		if(discord_rpc_on) {
			discord_rpc.EndDiscordRPC();
		}
		
		TextureManager.UnloadTextures();
		planet_manager.UnloadTextures();
		camera2d.close();
		CloseWindow();
		
	}

}
