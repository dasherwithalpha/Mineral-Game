package com.dasherwa.mineralgame.main.ui.dialoguebox;

import com.raylib.Raylib.Rectangle;

import java.text.DecimalFormat;

import com.dasherwa.mineralgame.main.Main;

public class DialogueBoxManager {

	DialogueBox not_enough_money;
	DialogueBox not_a_quarry;
	DialogueBox removed_money;
	
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
		
	}
	
	public void DrawBox(BoxType box_type) {
		
		switch (box_type) {

			case NOT_ENOUGH_MONEY:		
				DialogueBox.Draw(not_enough_money);
				break;
				
			case NOT_A_QUARRY:
				DialogueBox.Draw(not_a_quarry);
				break;
				
			case REMOVED_MONEY:
				DialogueBox.Draw(removed_money);
				break;

			default:
				break;

		}
		
	}

	public void Draw() {
		DrawBox(BoxType.NOT_ENOUGH_MONEY);
		DrawBox(BoxType.NOT_A_QUARRY);
		DrawBox(BoxType.REMOVED_MONEY);
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

			default:
				break;

		}
		
	}
	
	public void SetRemovedMoneyBoxText(float removed_money_amount) {
		DecimalFormat decimal_format = new DecimalFormat("0.00");
		removed_money.SetText("-" + decimal_format.format(removed_money_amount));
	}
}
