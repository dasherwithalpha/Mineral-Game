package com.dasherwa.mineralgame.code.tiles;

public class TileData {

	int quarry_power;
	int quarry_frequency;
	float quarry_counter;
	
	//TASK SPECIFIC METHODS
	public static float CalculatePeriodFromFrequency(float frequency) {
		return 1 / frequency;
	}
	
	public static float KGPerPower(int power_level) {
		return (float) (10 * Math.pow(1.15f, power_level - 1));
	}
	
	public static float FrequencyLevelToPeriod(int frequency_level) {
		return 5 / (float)frequency_level;
	}

	//SETTERS
	public static void SetQuarryPower(Tile tile, int power) {
		tile.tile_data.quarry_power = power;
	}

	public static void SetQuarryFrequency(Tile tile, int frequency) {
		tile.tile_data.quarry_frequency = frequency;
	}
	
	public static void SetQuarryCounter(Tile tile, float counter) {
		tile.tile_data.quarry_counter = counter;
	}
	
	//GETTERS
	public static int GetQuarryPower(Tile tile) {
		return tile.tile_data.quarry_power;
	}
	
	public static int GetQuarryFrequency(Tile tile) {
		return tile.tile_data.quarry_frequency;
	}
	
	public static float GetQuarryCounter(Tile tile) {
		return tile.tile_data.quarry_counter;
	}
}
