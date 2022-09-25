package com.gcstudios.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.gcstudios.main.Game;

public class EnemyBullet extends Entity{
	
	private BufferedImage[] sprite1;
	private int timeShow = 5;
	private boolean explodes = false;
	private int timeAni = 5,curAni = 0,timeCurAni;
	
	public EnemyBullet(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
		sprite1 = new BufferedImage[5];
		for(int i = 0 ; i < 4;i++)
			sprite1[i] = Game.spritesheet.getSprite(21 + (i*5), 21, 3, 8);
		sprite1[4] = Game.spritesheet.getSprite(61, 21, 6, 8);
	}
	
	public void tick() {
		if(!explodes) {
			setY(getY()+2);
			timeCurAni++;
		}
		if(this.getY() == Game.HEIGHT - 8) {
			explodes = true;
		}
		if(timeCurAni == timeAni) {
			timeCurAni = 0;
			if(curAni == 4) {
				curAni = 0;
			}else {
				curAni++;
			}
		}
		
		if(isColidding(this,Game.player) && !explodes) {
			explodes = true;
			Game.player.life--;
		}
		for(int i = 0; i < Game.walls.size();i++) {
				if(isColidding(this,Game.walls.get(i)) && !explodes) {
						Game.walls.get(i).life--;
						explodes = true;
				}
		}
		if(explodes)
			timeShow--;
		if(timeShow == 0)
			Game.entities.remove(this);
	}
	
	public void render(Graphics g) {
		if(!explodes) {
			g.drawImage(sprite1[curAni], getX(), getY(), getWidth(),getHeight(),null);
		}else {
			g.drawImage(sprite1[4], getX(), getY(), 6,8,null);
		}
	}
}
