package com.dasherwa.mineralgame.main.ui;

import static com.raylib.Raylib.*;
import static com.raylib.Colors.WHITE;
import static com.raylib.Colors.BLACK;
import static com.raylib.Colors.GRAY;

import java.text.DecimalFormat;

import com.dasherwa.mineralgame.main.Main;
import com.dasherwa.mineralgame.main.TextureManager;
import com.dasherwa.mineralgame.main.ui.dialoguebox.BoxType;
import com.dasherwa.mineralgame.tiles.TileData;
import com.dasherwa.mineralgame.world.Areas;
import com.dasherwa.mineralgame.world.WorldManager;
import com.dasherwa.utilities.romannumeral.RomanNumeral;
import com.raylib.Helpers;

public class UI {

	public UIState ui_state;
	public SelectionType selection_type;

	public Vector2 selected_tile;
	public Vector2 selected_menu, selected_menu_draw, selected_sell, selected_quarry;
	public final Vector2 SELL, AREAS, QUARRY, EXIT;
	public float[] mineralKG_counts;
	long balance;
	int quarry_count;

	DecimalFormat decimal_format, decimal_format2;

	Color LESSWHITE;

	@SuppressWarnings("resource")
	public UI() {

		LESSWHITE = Helpers.newColor(215, 218, 234, 255);

		decimal_format = new DecimalFormat("0");
		decimal_format2 = new DecimalFormat("00000000000000");

		quarry_count = 0;

		ui_state = UIState.RESOURCES;
		selection_type = SelectionType.TILE;

		selected_tile = new Vector2().x(0).y(0);
		selected_menu = new Vector2().x(0).y(0);
		selected_menu_draw = new Vector2().x(0).y(0);
		selected_sell = new Vector2().x(0).y(0);
		selected_quarry = new Vector2().x(0).y(1);

		SELL = new Vector2().x(0).y(0);
		AREAS = new Vector2().x(0).y(1);
		QUARRY = new Vector2().x(1).y(0);
		EXIT = new Vector2().x(2).y(1);

		balance = 3000;

		mineralKG_counts = new float[4];
		mineralKG_counts[0] = 0.0f;
		mineralKG_counts[1] = 0.0f;
		mineralKG_counts[2] = 0.0f;
		mineralKG_counts[3] = 0.0f;
	}

	public void Update() {

		switch (selection_type) {

			case TILE:

				SelectionTypeTileUpdate();

				break;

			case MENU:

				SelectionTypeMenuUpdate();

				break;

		}
	}

	void SelectionTypeTileUpdate() {

		if (IsKeyPressed(KEY_UP) && selected_tile.y() > 0) {
			selected_tile.y(selected_tile.y() - 1);
		}
		if (IsKeyPressed(KEY_DOWN) && selected_tile.y() < 5) {
			selected_tile.y(selected_tile.y() + 1);
		}
		if (IsKeyPressed(KEY_LEFT) && selected_tile.x() > 0) {
			selected_tile.x(selected_tile.x() - 1);
		}
		if (IsKeyPressed(KEY_RIGHT) && selected_tile.x() < 9) {
			selected_tile.x(selected_tile.x() + 1);
		}

		if (IsKeyPressed(KEY_K)) {
			selection_type = SelectionType.MENU;
			selected_menu.x(0);
			selected_menu.y(0);
		}

	}

	void SelectionTypeMenuUpdate() {

		if (IsKeyPressed(KEY_K)) {
			selection_type = SelectionType.TILE;
			return;
		}

		if (ui_state == UIState.RESOURCES || ui_state == UIState.BALANCE || ui_state == UIState.OPTIONS) {
			if (IsKeyPressed(KEY_LEFT) && IsKeyDown(KEY_APOSTROPHE)) {
				ui_state = ui_state.Previous();
				System.out.println(ui_state.toString());
			}
			if (IsKeyPressed(KEY_RIGHT) && IsKeyDown(KEY_APOSTROPHE)) {
				ui_state = ui_state.Next();
				System.out.println(ui_state.toString());
			}
		}

		if (ui_state == UIState.OPTIONS) {

			if (IsKeyPressed(KEY_UP) && selected_menu.y() > 0) {
				selected_menu.y(selected_menu.y() - 1);
			}
			if (IsKeyPressed(KEY_DOWN) && selected_menu.y() < 1) {
				selected_menu.y(selected_menu.y() + 1);
			}
			if (IsKeyPressed(KEY_LEFT) && !IsKeyDown(KEY_APOSTROPHE) && selected_menu.x() > 0) {
				selected_menu.x(selected_menu.x() - 1);
			}
			if (IsKeyPressed(KEY_RIGHT) && !IsKeyDown(KEY_APOSTROPHE) && selected_menu.x() < 2) {
				selected_menu.x(selected_menu.x() + 1);
			}

			if (IsKeyPressed(KEY_ENTER)) {

				if (selected_menu.x() == SELL.x() && selected_menu.y() == SELL.y()) {
					ui_state = UIState.SELL;
					selected_sell.x(4);
					return;
				}
				if (selected_menu.x() == AREAS.x() && selected_menu.y() == AREAS.y()) {
					ui_state = UIState.AERAS;
					return;
				}
				if (selected_menu.x() == QUARRY.x() && selected_menu.y() == QUARRY.y()) {
					ui_state = UIState.QUARRY;
					selected_quarry.x(1);
					return;
				}
				if (selected_menu.x() == EXIT.x() && selected_menu.y() == EXIT.y()) {
					Main.should_exit = true;
				}
			}

			selected_menu_draw.x(selected_menu.x() * 48 + 11);
			selected_menu_draw.y(selected_menu.y() * 15 + 1);

		}

		if (ui_state == UIState.SELL) {

			if (IsKeyPressed(KEY_LEFT) && selected_sell.x() > 0) {
				selected_sell.x(selected_sell.x() - 1);

			}
			if (IsKeyPressed(KEY_RIGHT) && selected_sell.x() < 4) {
				selected_sell.x(selected_sell.x() + 1);
			}

			if (selected_sell.x() >= 0 && selected_sell.x() <= 3) {

				if (!IsKeyDown(KEY_APOSTROPHE)) {

					selected_menu_draw.x(selected_sell.x() * 30 + 1);
					selected_menu_draw.y(2);

					if (IsKeyPressed(KEY_ENTER)) {
						balance += Math.round(mineralKG_counts[(int) selected_sell.x()] * Main.planet_manager
								.GetPlanetMineralValue(WorldManager.current_planet_ordinal, (int) selected_sell.x()));
						mineralKG_counts[(int) selected_sell.x()] = 0;
						ui_state = UIState.BALANCE;
						System.out.println(balance);
					}

				}

				if (IsKeyDown(KEY_APOSTROPHE)) {

					selected_menu_draw.x(1);
					selected_menu_draw.y(2);

					if (IsKeyPressed(KEY_ENTER)) {
						for (int i = 0; i < 4; i++) {
							balance += Math.round(mineralKG_counts[i] * Main.planet_manager
									.GetPlanetMineralValue(WorldManager.current_planet_ordinal, i));
							mineralKG_counts[i] = 0;
						}
						ui_state = UIState.BALANCE;
					}

				}

			}
			if (selected_sell.x() == 4) {
				selected_menu_draw.x(137);
				selected_menu_draw.y(20);

				if (IsKeyPressed(KEY_ENTER)) {
					ui_state = UIState.OPTIONS;
				}
			}
		}

		if (ui_state == UIState.QUARRY) {

			if (IsKeyPressed(KEY_LEFT) && selected_quarry.x() > -1) {
				selected_quarry.x(selected_quarry.x() - 1);
			}
			if (IsKeyPressed(KEY_RIGHT) && selected_quarry.x() < 1) {
				selected_quarry.x(selected_quarry.x() + 1);
			}
			if (IsKeyPressed(KEY_UP)) {
				selected_quarry.y(0);
			}
			if (IsKeyPressed(KEY_DOWN)) {
				selected_quarry.y(1);
			}

			if (IsKeyPressed(KEY_ENTER)) {

				if (selected_quarry.x() == 0 && selected_quarry.y() == 0) {

					if (WorldManager.IsTileQuarry((int) selected_tile.x(), (int) selected_tile.y())
							&& balance >= TileData.GetQuarryUpgradePowerPrice(
									WorldManager.GetTile((int) selected_tile.x(), (int) selected_tile.y()))
							&& !WorldManager.IsTileQuarryPowerMaxed((int) selected_tile.x(), (int) selected_tile.y(),
									10)) {

						balance -= TileData.GetQuarryUpgradePowerPrice(
								WorldManager.GetTile((int) selected_tile.x(), (int) selected_tile.y()));

						Main.dialogue_box_manager.SetRemovedMoneyBoxText(TileData.GetQuarryUpgradePowerPrice(
								WorldManager.GetTile((int) selected_tile.x(), (int) selected_tile.y())));
						Main.dialogue_box_manager.SetBoxShow(BoxType.REMOVED_MONEY);

						WorldManager.IncreaseTileQuarryPower((int) selected_tile.x(), (int) selected_tile.y(), 1);
						System.out.println(
								WorldManager.GetTileQuarryPower((int) selected_tile.x(), (int) selected_tile.y()));

					} else if (WorldManager.IsTileQuarryPowerMaxed((int) selected_tile.x(), (int) selected_tile.y(),
							10)) {

						Main.dialogue_box_manager.SetBoxShow(BoxType.MAX_QUARRY_ATTRIBUTE_LEVEL);

					} else if (!WorldManager.IsTileQuarry((int) selected_tile.x(), (int) selected_tile.y())) {

						Main.dialogue_box_manager.SetBoxShow(BoxType.NOT_A_QUARRY);

					} else if (balance < TileData.GetQuarryUpgradePowerPrice(
							WorldManager.GetTile((int) selected_tile.x(), (int) selected_tile.y()))) {

						Main.dialogue_box_manager.SetBoxShow(BoxType.NOT_ENOUGH_MONEY);

					}

				}

				if (selected_quarry.x() == 0 && selected_quarry.y() == 1) {

					if (WorldManager.IsTileQuarry((int) selected_tile.x(), (int) selected_tile.y())
							&& balance >= TileData.GetQuarryUpgradeFrequencyPrice(
									WorldManager.GetTile((int) selected_tile.x(), (int) selected_tile.y()))
							&& !WorldManager.IsTileQuarryFrequencyMaxed((int) selected_tile.x(),
									(int) selected_tile.y(), 10)) {

						balance -= TileData.GetQuarryUpgradeFrequencyPrice(
								WorldManager.GetTile((int) selected_tile.x(), (int) selected_tile.y()));

						Main.dialogue_box_manager.SetRemovedMoneyBoxText(TileData.GetQuarryUpgradeFrequencyPrice(
								WorldManager.GetTile((int) selected_tile.x(), (int) selected_tile.y())));
						Main.dialogue_box_manager.SetBoxShow(BoxType.REMOVED_MONEY);

						WorldManager.IncreaseTileQuarryFrequency((int) selected_tile.x(), (int) selected_tile.y(), 1);

					} else if (WorldManager.IsTileQuarryFrequencyMaxed((int) selected_tile.x(), (int) selected_tile.y(),
							10)) {

						Main.dialogue_box_manager.SetBoxShow(BoxType.MAX_QUARRY_ATTRIBUTE_LEVEL);

					} else if (!WorldManager.IsTileQuarry((int) selected_tile.x(), (int) selected_tile.y())) {

						Main.dialogue_box_manager.SetBoxShow(BoxType.NOT_A_QUARRY);

					} else if (balance < TileData.GetQuarryUpgradeFrequencyPrice(
							WorldManager.GetTile((int) selected_tile.x(), (int) selected_tile.y()))) {

						Main.dialogue_box_manager.SetBoxShow(BoxType.NOT_ENOUGH_MONEY);

					}
				}

				if (selected_quarry.x() == -1) {
					if (WorldManager.IsTileQuarry((int) selected_tile.x(), (int) selected_tile.y())) {

						if (TileData.GetQuarryTier(
								WorldManager.GetTile((int) selected_tile.x(), (int) selected_tile.y())) < 4) {

							if (balance >= TileData.GetQuarryTierPrice(TileData.GetQuarryTier(
									WorldManager.GetTile((int) selected_tile.x(), (int) selected_tile.y())))) {

								balance -= TileData.GetQuarryTierPrice(TileData.GetQuarryTier(
										WorldManager.GetTile((int) selected_tile.x(), (int) selected_tile.y())));

								Main.dialogue_box_manager.SetRemovedMoneyBoxText(
										TileData.GetQuarryTierPrice(TileData.GetQuarryTier(WorldManager
												.GetTile((int) selected_tile.x(), (int) selected_tile.y()))));

								WorldManager.IncreaseTileQuarryTier((int) selected_tile.x(), (int) selected_tile.y());

								Main.dialogue_box_manager.SetBoxShow(BoxType.REMOVED_MONEY);

							} else {
								Main.dialogue_box_manager.SetBoxShow(BoxType.NOT_ENOUGH_MONEY);
							}

						} else {
							Main.dialogue_box_manager.SetBoxShow(BoxType.MAX_QUARRY_TIER);
						}

					} else {
						Main.dialogue_box_manager.SetBoxShow(BoxType.NOT_A_QUARRY);
					}

				}

				if (selected_quarry.x() == 1) {
					ui_state = UIState.OPTIONS;
				}

			}

			if (selected_quarry.x() == 1) {
				selected_menu_draw.x(137);
				selected_menu_draw.y(20);
			}

			if (selected_quarry.x() == 0) {
				selected_menu_draw.x(34);
				selected_menu_draw.y(selected_quarry.y() * 16);
			}

			if (selected_quarry.x() == -1) {
				selected_menu_draw.x(7);
				selected_menu_draw.y(7);
			}
		}

		if (ui_state == UIState.AERAS) {

			if (IsKeyPressed(KEY_RIGHT)) {
				if (WorldManager.current_chunk.x() < Areas.HORIZONTAL_AREA_COUNT - 1) {
					WorldManager.current_chunk.x(WorldManager.current_chunk.x() + 1);
					WorldManager.area_change_signal.Alert();
				}

			}

			if (IsKeyPressed(KEY_LEFT)) {
				if (WorldManager.current_chunk.x() > 0) {
					WorldManager.current_chunk.x(WorldManager.current_chunk.x() - 1);
					WorldManager.area_change_signal.Alert();
				}
			}

			if (IsKeyPressed(KEY_UP)) {
				if (WorldManager.current_chunk.y() > 0) {
					WorldManager.current_chunk.y(WorldManager.current_chunk.y() - 1);
					WorldManager.area_change_signal.Alert();
				}
			}

			if (IsKeyPressed(KEY_DOWN)) {
				if (WorldManager.current_chunk.y() < Areas.VERTICAL_AREA_COUNT - 1) {
					WorldManager.current_chunk.y(WorldManager.current_chunk.y() + 1);
					WorldManager.area_change_signal.Alert();
				}

			}
			
			if (IsKeyDown(KEY_APOSTROPHE)) {
				
				selected_menu_draw.x(137);
				selected_menu_draw.y(20);
				
				if(IsKeyPressed(KEY_ENTER)) {
					ui_state = UIState.OPTIONS;
					return;
				}
			}

		}

	}

	public void Draw() {

		switch (ui_state) {

			case RESOURCES:
				int k = 3;
				for (int i = 0; i < 2; i++) {
					for (int j = 0; j < 2; j++) {
						DrawRectangle(80 * i + 3, 15 * j + 3, 11, 11,
								Main.planet_manager.GetPlanet(WorldManager.current_planet_ordinal).GetColor(k));
						DrawRectangle(80 * i + 16, 15 * j + 5, 2, 2, WHITE);
						DrawRectangle(80 * i + 16, 15 * j + 10, 2, 2, WHITE);
						DrawText(decimal_format.format(mineralKG_counts[3 - k]) + " KG", 80 * (1 - i) + 20,
								15 * (1 - j) + 3, 13, WHITE);
						k--;
					}
				}
				break;

			case BALANCE:
				DrawTexture(TextureManager.balance_money, 3, 4, WHITE);
				DrawText(decimal_format2.format(balance), 53, 10, 13, WHITE);
				break;

			case OPTIONS:
				for (int i = 0; i < 3; i++) {
					DrawWithBox(TextureManager.texts[i], 48 * i + 13, 3);
					DrawWithBox(TextureManager.texts[i+3], 48 * i + 13, 18);
				}

				DrawTexture(TextureManager.selected_option, (int) selected_menu_draw.x(), (int) selected_menu_draw.y(), WHITE);
				break;

			case QUARRY:
//				DrawTexture(TextureManager.black_bar_quarry, 0, 0, WHITE);
				DrawTexture(TextureManager.texts[1], 121, 9, WHITE);
				DrawWithBox(TextureManager.button_back, 139, 22);
				DrawWithBox(TextureManager.quarry_upgrade_power, 36, 2);
				DrawWithBox(TextureManager.quarry_upgrade_frequency, 36, 18);
				DrawTexture(TextureManager.quarry_upgrade_tree, 25, 6, WHITE);
				for(int i = 0; i<2; i++) {
					DrawRectangle(51, 16 * i + 5, 2, 2, WHITE);
					DrawRectangle(51, 16 * i + 10, 2, 2, WHITE);
				}
				DrawText(
						"" + RomanNumeral.GetRomanNumeral(
								WorldManager.GetTileQuarryPower((int) selected_tile.x(), (int) selected_tile.y())),
						55, 1, 16, WHITE);
				DrawText(
						"" + RomanNumeral.GetRomanNumeral(
								WorldManager.GetTileQuarryFrequency((int) selected_tile.x(), (int) selected_tile.y())),
						55, 17, 16, WHITE);

				if (WorldManager.IsTileQuarry((int) selected_tile.x(), (int) selected_tile.y())) {

					if (TextureManager.quarry_body_bright != null) {
						DrawTexture(TextureManager.quarry_body_bright, 8, 7,
								Main.planet_manager.GetPlanetMineralColourBright(WorldManager.current_planet_ordinal,
										TileData.GetQuarryTier(
												WorldManager.GetTile((int) selected_tile.x(), (int) selected_tile.y()))
												- 1));
					}

					if (TextureManager.quarry_body != null) {
						DrawTexture(TextureManager.quarry_body, 8, 7,
								Main.planet_manager.GetPlanetMineralColour(WorldManager.current_planet_ordinal,
										TileData.GetQuarryTier(
												WorldManager.GetTile((int) selected_tile.x(), (int) selected_tile.y()))
												- 1));
					}

				} else {

					if (TextureManager.quarry_body_bright != null) {
						DrawTexture(TextureManager.quarry_body_bright, 8, 7, WHITE);
					}

					if (TextureManager.quarry_body != null) {
						DrawTexture(TextureManager.quarry_body, 8, 7, LESSWHITE);
					}
				}

				if (TextureManager.quarry_mineral != null) {
					DrawTexture(TextureManager.quarry_mineral, 8, 7, LESSWHITE);
				}

				if (TextureManager.quarry_mineral_bright != null) {
					DrawTexture(TextureManager.quarry_mineral_bright, 8, 7, WHITE);
				}

				if (selected_quarry.x() == 1) {
					DrawTexture(TextureManager.button_back_selected, (int) selected_menu_draw.x(),
							(int) selected_menu_draw.y(), WHITE);
				}

				if (selected_quarry.x() == 0) {
					DrawTexture(TextureManager.quarry_selected_attribute, (int) selected_menu_draw.x(),
							(int) selected_menu_draw.y(), WHITE);
				}
				if (selected_quarry.x() == -1) {
					DrawTexture(TextureManager.quarry_tier_selected, (int) selected_menu_draw.x(),
							(int) selected_menu_draw.y(), WHITE);
				}

				break;

			case SELL:
//				DrawTexture(TextureManager.black_bar_sell, 0, 0, WHITE);
				DrawTexture(TextureManager.texts[0], 121, 9, WHITE);
				DrawWithBox(TextureManager.button_back, 139, 22);
				for (int i = 0; i < 4; i++) {
					DrawRectangle(30 * i + 3, 4, 24, 24,
							Main.planet_manager.GetPlanet(WorldManager.current_planet_ordinal).GetColor(i));
				}

				if (selected_sell.x() >= 0 && selected_sell.x() <= 3) {

					if (!IsKeyDown(KEY_APOSTROPHE)) {
						DrawTexture(TextureManager.sell_selected_mineral, (int) selected_menu_draw.x(),
								(int) selected_menu_draw.y(), WHITE);
					} else {
						DrawTexture(TextureManager.sell_selected_mineral_all, (int) selected_menu_draw.x(),
								(int) selected_menu_draw.y(), WHITE);
					}
				}
				if (selected_sell.x() == 4) {
					DrawTexture(TextureManager.button_back_selected, (int) selected_menu_draw.x(),
							(int) selected_menu_draw.y(), WHITE);
				}
				break;

			case AERAS:
//				DrawRectangle(2,2, Areas.HORIZONTAL_AREA_COUNT * 4, Areas.VERTICAL_AREA_COUNT * 4, GRAY);
				DrawTexture(TextureManager.texts[3], 121, 9, WHITE);
				DrawWithBox(TextureManager.button_back, 139, 22);
				for (int i = 0; i < Areas.HORIZONTAL_AREA_COUNT; i++) {
					for (int j = 0; j < Areas.VERTICAL_AREA_COUNT; j++) {
						DrawRectangle(i * 4 + 2, j * 4 + 2, 3, 3, GRAY);
						if (Main.planet_manager.GetPlanet(WorldManager.current_planet_ordinal).GetAreaFinished(i, j)) {
							DrawRectangle(i * 4 + 2, j * 4 + 2, 3, 3,
									Main.planet_manager.GetPlanetMineralColour(WorldManager.current_planet_ordinal, 0));
						}

					}
				}
				DrawRectangle((int) WorldManager.current_chunk.x() * 4 + 2,
						(int) WorldManager.current_chunk.y() * 4 + 2,
						3, 3,
						Main.planet_manager.GetPlanetMineralColour(WorldManager.current_planet_ordinal, 3));
				
				if(IsKeyDown(KEY_APOSTROPHE)) {
					DrawTexture(TextureManager.button_back_selected, (int) selected_menu_draw.x(),
							(int) selected_menu_draw.y(), WHITE);
				}
				
				break;
		}

		switch (selection_type) {

			case TILE:
				DrawTexture(TextureManager.selected_tile, (int) selected_tile.x() * Main.TILE_SIZE,
						(int) selected_tile.y() * Main.TILE_SIZE + Main.SCREEN_BLACKNESS, WHITE);
				break;

			case MENU:
				DrawTexture(TextureManager.selected_tile_notinmenu, (int) selected_tile.x() * Main.TILE_SIZE,
						(int) selected_tile.y() * Main.TILE_SIZE + Main.SCREEN_BLACKNESS, WHITE);
				break;
		}

	}
	
	static void DrawWithBox(Texture texture, int x, int y){
		DrawRectangle(x, y, texture.width(), texture.height(), WHITE);
		DrawTexture(texture, x, y, BLACK);
	}

	public long GetTotalBalance() {
		return balance;
	}

	public void SetTotalBalance(long balance) {
		this.balance = balance;
	}

	public void IncreaseQuarryCountByOne() {
		quarry_count += 1;
	}

	public int GetQuarryCount() {
		return quarry_count;
	}
}
