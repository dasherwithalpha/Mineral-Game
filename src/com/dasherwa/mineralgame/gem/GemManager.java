package com.dasherwa.mineralgame.gem;

public class GemManager {

	static Gem[] gems = new Gem[4];
	
	public GemManager() {
//		gems[0] = Main.planet_manager.GetPlanet(0).gem_common;
//		gems[1] = Main.planet_manager.GetPlanet(0).gem_rare;
//		gems[2] = Main.planet_manager.GetPlanet(0).gem_epic;
//		gems[3] = Main.planet_manager.GetPlanet(0).gem_legendary;
	}
	
	public static float GetGemValue(int gem_ordinal) {
		return gems[gem_ordinal].value;
	}
	
}
