package com.dasherwa.mineralgame.main;

public class Signal {

	boolean signal;
	
	public Signal() {
		signal = false;
	}
	
	public void Alert() {
		signal = true;
	}
	
	public void Reset() {
		signal = false;
	}
	
	public boolean IsAlerted() {
		return signal;
	}
	
}
