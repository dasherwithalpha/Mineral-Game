package com.dasherwa.mineralgame.main.ui.dialoguebox;

import com.raylib.Raylib.Rectangle;
import static com.raylib.Raylib.*;
import static com.raylib.Colors.*;

import com.dasherwa.mineralgame.main.Main;

public class DialogueBoxManager {

	DialogueBox not_enough_money_dialogue_box;
	
	public DialogueBoxManager() {
		
		CreateBoxes();
		
	}
	
	@SuppressWarnings("resource")
	void CreateBoxes() {
		
		not_enough_money_dialogue_box = new DialogueBox.Builder()
				.counter(0.0f)
				.text("Not Enough Money")
				.box(new Rectangle().height(16).width(100).x(8).y(Main.GAME_SCREEN_HEIGHT - 8 - 16))
				.show(false)
				.duration(2.0f)
				.build();
		
	}
	
	public void DrawBox(BoxType box_type) {
		
		switch (box_type) {

			case NOT_ENOUGH_MONEY:
				
				if(not_enough_money_dialogue_box.GetShow()) {
					
					not_enough_money_dialogue_box.SetCounter(
							not_enough_money_dialogue_box.GetCounter() + GetFrameTime()
							);
					
					if(not_enough_money_dialogue_box.GetCounter() < not_enough_money_dialogue_box.GetDuration()) {
						DrawRectangleRec(not_enough_money_dialogue_box.GetBox(), BLACK);
						DrawRectangleLinesEx(not_enough_money_dialogue_box.GetBox(), 1.0f, Main.APAWHITE);
						DrawText(
								"Not Enough Money",
								(int)not_enough_money_dialogue_box.GetBox().x() + 3,
								(int) not_enough_money_dialogue_box.GetBox().y() + 3,
								3,
								Main.APAWHITE
								);
					}
					if(not_enough_money_dialogue_box.GetCounter() > not_enough_money_dialogue_box.GetDuration()) {
						not_enough_money_dialogue_box.SetShow(false);
						not_enough_money_dialogue_box.SetCounter(0.0f);
					}
					
				}
				
				
				
				break;

			default:
				break;

		}
		
	}
	public void Draw() {
		DrawBox(BoxType.NOT_ENOUGH_MONEY);
	}
	
	public void SetBoxShow(BoxType box_type) {
		
		switch (box_type) {

			case NOT_ENOUGH_MONEY:
				not_enough_money_dialogue_box.SetShow(true);
				break;

			default:
				break;

		}
		
	}
	
	public enum BoxType{
		
		NOT_ENOUGH_MONEY;
		
	}
	
}
