package com.gcstudios.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.gcstudios.main.Game;

public class Wall extends Entity{
	
	public int life = 14;
	private BufferedImage sprite1;
	
	public Wall(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
		sprite1 = Game.spritesheet.getSprite(45, 31, 24, 16);
	}
	public void tick() {
		if(life == 0) {
			Game.walls.remove(this);
		}
	}
	public void render(Graphics g) {
		g.drawImage(sprite1, getX(),getY(),getWidth(),getHeight(),null);
	}

}
