package com.dasherwa.mineralgame.main.ui.dialoguebox;

import com.raylib.Raylib.Rectangle;

import java.text.DecimalFormat;

import com.dasherwa.mineralgame.main.Main;

public class DialogueBoxManager {

	DialogueBox not_enough_money,
				not_a_quarry,
				removed_money,
				max_quarry_attribute_level,
				max_quarry_tier;

	
	public DialogueBoxManager() {
		
		CreateBoxes();
		
	}
	
	@SuppressWarnings("resource")
	void CreateBoxes() {
		
		not_enough_money = new DialogueBox.Builder()
				.counter(0.0f)
				.text("Not Enough Money")
				.box(new Rectangle().height(16).width(100).x(8).y(Main.GAME_SCREEN_HEIGHT - 8 - 16))
				.show(false)
				.duration(2.0f)
				.build();
		
		not_a_quarry = new DialogueBox.Builder()
				.counter(0.0f)
				.text("Not A Quarry")
				.box(new Rectangle().height(16).width(75).x(8).y(Main.GAME_SCREEN_HEIGHT - 8 - 16))
				.show(false)
				.duration(2.0f)
				.build();
		
		removed_money = new DialogueBox.Builder()
				.counter(0.0f)
				.text("")
				.box(new Rectangle().height(16).width(75).x(8).y(Main.GAME_SCREEN_HEIGHT - 8 - 16))
				.show(false)
				.duration(2.0f)
				.build();
		
		max_quarry_attribute_level = new DialogueBox.Builder()
				.counter(0.0f)
				.text("Attribute Maxed Out")
				.box(new Rectangle().height(16).width(111).x(8).y(Main.GAME_SCREEN_HEIGHT - 8 - 16))
				.show(false)
				.duration(2.0f)
				.build();
		
		max_quarry_tier = new DialogueBox.Builder()
				.counter(0.0f)
				.text("Tier Maxed Out")
				.box(new Rectangle().height(16).width(111).x(8).y(Main.GAME_SCREEN_HEIGHT - 8 - 16))
				.show(false)
				.duration(2.0f)
				.build();
		
	}
	
	public void Draw() {
		DialogueBox.Draw(not_enough_money);
		DialogueBox.Draw(not_a_quarry);
		DialogueBox.Draw(removed_money);
		DialogueBox.Draw(max_quarry_attribute_level);
		DialogueBox.Draw(max_quarry_tier);
	}
	
	public void SetBoxShow(BoxType box_type) {
		
		switch (box_type) {

			case NOT_ENOUGH_MONEY:
				not_enough_money.SetShow(true);
				break;
				
			case NOT_A_QUARRY:
				not_a_quarry.SetShow(true);
				break;
				
			case REMOVED_MONEY:
				removed_money.SetShow(true);
				break;
				
			case MAX_QUARRY_ATTRIBUTE_LEVEL:
				max_quarry_attribute_level.SetShow(true);
				break;
				
			case MAX_QUARRY_TIER:
				max_quarry_tier.SetShow(true);
				break;

			default:
				break;

		}
		
	}
	
	public void SetRemovedMoneyBoxText(float removed_money_amount) {
		DecimalFormat decimal_format = new DecimalFormat("0.00");
		removed_money.SetText("-" + decimal_format.format(removed_money_amount));
	}
}
