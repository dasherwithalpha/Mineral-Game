package com.dasherwa.mineralgame.main.ui.dialoguebox;

import com.raylib.Raylib.Rectangle;

public class DialogueBox {

	private float counter;
	private String text;
	private Rectangle box;
	private boolean show;
	private float duration;
	
	DialogueBox(Builder builder){
		
		this.counter = builder.counter;
		this.text = builder.text;
		this.box = builder.box;
		this.show = builder.show;
		this.duration = builder.duration;
		
	}
	
	// SETTERS
	public void SetCounter(float counter) {
		this.counter = counter;
	}

	public void SetText(String text) {
		this.text = text;
	}

	public void SetBox(Rectangle box) {
		this.box = box;
	}

	public void SetShow(boolean show) {
		this.show = show;
	}
	
	public void SetDuration(float duration) {
		this.duration = duration;
	}

	// GETTERS
	public float GetCounter() {
		return counter;
	}

	public String GetText() {
		return text;
	}

	public Rectangle GetBox() {
		return box;
	}

	public boolean GetShow() {
		return show;
	}
	
	public float GetDuration() {
		return duration;
	}
	
	public static class Builder {
		
		private float counter;
		private String text;
		private Rectangle box;
		private boolean show;
		private float duration;
		
		public Builder counter(float counter) {
			this.counter = counter;
			return this;
		}
		
		public Builder text(String text) {
			this.text = text;
			return this;
		}
		
		public Builder box(Rectangle box) {
			this.box = box;
			return this;
		}
		
		public Builder show(boolean show) {
			this.show = show;
			return this;
		}
		
		public Builder duration(float duration) {
			this.duration = duration;
			return this;
		}
		
		public DialogueBox build() {
			return new DialogueBox(this);
		}
		
	}
}
