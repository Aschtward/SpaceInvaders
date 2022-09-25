package com.gcstudios.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.gcstudios.main.Game;


public class Player extends Entity{
	
	public boolean right,left;
	public int life = 6;
	
	
	public Player(int x, int y, int width, int height,double speed,BufferedImage sprite) {
		super(x, y, width, height,speed,sprite);
		this.sprite = Game.spritesheet.getSprite(1, 49, 16, 8);
		this.y = Game.HEIGHT - 10;
		this.x = 2;
	}
	
	public void tick(){
		if(Game.gameState == -1) {
			if(right && x < Game.WIDTH - 18) {
				x +=1;
			}
			if(left  && x > 2) {
				x -=1;
			}	
		}
		if(life == 0) {
			Game.gameState = 0;//you lose
		}
	}
	
	public void render(Graphics g) {
		g.drawImage(sprite, (int)this.x ,(int)this.y ,16,8,null);
	}

}
