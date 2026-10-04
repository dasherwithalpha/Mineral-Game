package com.dasherwa.mineralgame.gem;

import com.dasherwa.mineralgame.main.Main;

public class GemManager {

	static Gem[] gems = new Gem[4];
	
	public GemManager() {
		gems[0] = new Gem("Brownite", Rarity.COMMON, Main.APABROWN, 4);
		gems[1] = new Gem("Grayite", Rarity.RARE, Main.APAGRAY, 4.8f);
		gems[2] = new Gem("Whiteoxide", Rarity.EPIC, Main.APAWHITE, 12);
		gems[3] = new Gem("Bluntric", Rarity.LEGENDARY, Main.APABLUE, 12);
	}
	
	public static float GetGemValue(int gem_ordinal) {
		return gems[gem_ordinal].value;
	}
	
}
