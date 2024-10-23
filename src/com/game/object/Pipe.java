package com.game.object;

import java.awt.Graphics;
import java.awt.Rectangle;

import com.game.object.util.ObjectId;

public class Pipe extends GameObject {
	private boolean enterable;
	
	public Pipe(int x, int y, int width, int height, int scale, boolean enterable) {
		super(x, y, ObjectId.Pipe, width, height, scale);
		this.enterable = enterable;
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int) getX(), (int) getY(), (int) getWidth(), (int) getHeight());
	}

}
