package com.dasherwa.mineralgame.tiles;

public class TileData {

	int quarry_power;
	int quarry_frequency;
	float quarry_counter;
	int quarry_tier;
	
	public TileData() {}
	
	TileData(TileData tile_data){
		this.quarry_power = tile_data.quarry_power;
		this.quarry_frequency = tile_data.quarry_frequency;
		this.quarry_counter = tile_data.quarry_counter;
		this.quarry_tier = tile_data.quarry_tier;
	}
	
	//TASK SPECIFIC METHODS
	public static float CalculatePeriodFromFrequency(float frequency) {
		return 1 / frequency;
	}
	
	public static float KGPerPower(Tile tile) {
		return (float) ((9 + (GetQuarryTier(tile) - 1) * 2) * Math.pow(1.15f, GetQuarryPower(tile) - 1));
	}
	
	public static float FrequencyLevelToPeriod(int frequency_level) {
		return 5 / (float)frequency_level;
	}
	
	public static float GetQuarryPrice(Tile tile, int quarry_count) {
		
		float pow = 1.1f;
		
		switch (tile.GetRarity()) {

			case COMMON:
				return (float) (1000 * Math.pow(pow, quarry_count));

			case RARE:
				return (float) (1200 * Math.pow(pow, quarry_count));

			case EPIC:
				return (float) (3000 * Math.pow(pow, quarry_count));

			case LEGENDARY:
				return (float) (3000 * Math.pow(pow, quarry_count));

			default:
				return 0;

		}
	}
	
	public static float GetQuarryTierPrice(int tier) {
		return 10000 * (tier + 1);
	}
	
	public static float GetQuarryUpgradePowerPrice(Tile tile) {
		return (float) (500 * Math.pow(1.1f, (GetQuarryPower(tile) - 1)));
	}
	
	public static float GetQuarryUpgradeFrequencyPrice(Tile tile) {
		return (float) (500 * Math.pow(1.1f, (GetQuarryFrequency(tile) - 1)));
	}
	
	// SETTERS
	public static void SetQuarryPower(Tile tile, int power) {
		tile.tile_data.quarry_power = power;
	}

	public static void SetQuarryFrequency(Tile tile, int frequency) {
		tile.tile_data.quarry_frequency = frequency;
	}

	public static void SetQuarryCounter(Tile tile, float counter) {
		tile.tile_data.quarry_counter = counter;
	}
	
	public static void SetQuarryTier(Tile tile, int tier) {
		tile.tile_data.quarry_tier = tier;
	}

	// GETTERS
	public static int GetQuarryPower(Tile tile) {
		return tile.tile_data.quarry_power;
	}

	public static int GetQuarryFrequency(Tile tile) {
		return tile.tile_data.quarry_frequency;
	}

	public static float GetQuarryCounter(Tile tile) {
		return tile.tile_data.quarry_counter;
	}
	
	public static int GetQuarryTier(Tile tile) {
		return tile.tile_data.quarry_tier;
	}

}
