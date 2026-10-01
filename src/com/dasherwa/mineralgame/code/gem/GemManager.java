package com.dasherwa.mineralgame.code.gem;

import com.dasherwa.mineralgame.code.main.Main;

public class GemManager {

	Gem[] gems = new Gem[4];
	
	public GemManager() {
		gems[0] = new Gem("Brownite", Rarity.COMMON, Main.APABROWN, 10, 0.0f);
		gems[1] = new Gem("Grayite", Rarity.RARE, Main.APAGRAY, 12, 0.0f);
		gems[2] = new Gem("Whiteoxide", Rarity.EPIC, Main.APAWHITE, 30, 0.0f);
		gems[3] = new Gem("Bluntric", Rarity.LEGENDARY, Main.APABLUE, 30, 0.0f);
	}
	
}
