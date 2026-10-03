package com.dasherwa.mineralgame.main.ui;

public enum UIState {

	RESOURCES, BALANCE, OPTIONS, QUARRY, SELL, AERAS;
	
	public UIState Next() {
		
		UIState[] values = UIState.values();
		int next_ordinal = (this.ordinal() + 1) % 3;
		return values[next_ordinal];
		
	}
	
	public UIState Previous() {
		UIState[] values = UIState.values();
		int previous_ordinal;

		if (this.ordinal() == 0) {
			previous_ordinal = 2;
		} else {
			previous_ordinal = (this.ordinal() - 1) % 3;
		}
		
		return values[previous_ordinal];
	}
	
}
