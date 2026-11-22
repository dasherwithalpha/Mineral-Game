package com.dasherwa.mineralgame.world;

import com.dasherwa.mineralgame.tiles.Tile;
import com.raylib.Raylib.Vector2;

public class Areas {

	public static final int HORIZONTAL_AREA_COUNT = 13;
	public static final int VERTICAL_AREA_COUNT = 7;
	
	Area[][] area = new Area[HORIZONTAL_AREA_COUNT][HORIZONTAL_AREA_COUNT];
	
	
	public Areas() {}
	
	public Area GetArea(int x, int y) {
		return area[x][y];	
	}
	public Area GetArea(Vector2 pos) {
		return area[(int)pos.x()][(int)pos.y()];	
	}
	
	public void SetArea(Area area, int x, int y) {
		this.area[x][y] = area;
	}
	
	public boolean GetAreaFinished(Vector2 pos) {
		return area[(int)pos.x()][(int)pos.y()].GetIsAreaFinished();
	}
	
	public boolean GetAreaFinished(int x, int y) {
		return area[x][y].GetIsAreaFinished();
	}
	
	public void SetAreaFinished(int x, int y, boolean area_finished) {
		area[x][y].SetIsAreaFinished(area_finished);
	}
}

class Area {
	
	private Vector2 position;
	private Tile[][] tiles;
	private boolean is_area_finished;
	
	public Area() {
		position = new Vector2();
	}
	
	public String GetTileName(int x, int y) {
		return tiles[x][y].GetName();
	}
	
	//GETTERS
	public Tile[][] GetTiles(){
		return tiles;
	}
	
	public Tile GetTile(int x, int y) {
		return tiles[x][y];
	}
	
	public boolean GetIsAreaFinished() {
		return is_area_finished;
	}
	
	public Vector2 GetPosition() {
		return position;
	}
	
	//SETTERS
	public void SetTiles(Tile[][] tiles) {
		this.tiles = tiles;
	}
	
	public void SetIsAreaFinished(boolean is_area_finished) {
		this.is_area_finished = is_area_finished;
	}
	
	public void SetPosition(int x, int y) {
		this.position.x(x);
		this.position.y(y);
	}
	
}