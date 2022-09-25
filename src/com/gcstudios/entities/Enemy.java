package com.gcstudios.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.gcstudios.main.Game;

public class Enemy extends Entity{
	
	
	private int timeToAdvance = 240;
	private int timeSprite = 0;
	private int spriteCur = 0;
	private int localTime = 0;
	private int showDeath = 15;
	private BufferedImage deathSprite;
	private BufferedImage sprite0;
	private boolean death;
	private int value;
	private boolean endEnemy;

	public Enemy(double x, double y, int width, int height, double speed, BufferedImage sprite1,BufferedImage sprite2,int enemyLvl) {
		super(x, y, width, height, speed, null);
		sprite = Game.spritesheet.getSprite(1 + (18 * enemyLvl), 1, 16, 8);
		sprite0 = Game.spritesheet.getSprite(1 + (18 * enemyLvl), 11, 16, 8);
		deathSprite = Game.spritesheet.getSprite(55, 1, 16, 8);
		value = enemyLvl;
	}
	
	public void tick() {
		if(Game.gameState == -1) {
			localTime++;
			timeSprite++;
			//Enemy move
			if(timeToAdvance - (Game.enemyRow.size*6) < localTime) {
				localTime = 0;
				y +=10;
			}
			//Enemy shoot
			if(Game.rand.nextInt(10000) > 9960) {
				EnemyBullet eb = new EnemyBullet(getX()+8,getY(),3,8,0,null);
				Game.entities.add(eb);
			}
			if(isColidding(this,Game.player) && !death) {
				Game.player.life--;
				death = true;
			}
			for(int i = 0; i < Game.walls.size();i++) {
				if(isColidding(this,Game.walls.get(i)) && !death) {
					Game.walls.get(i).life--;
					death = true;
				}
			}
			if(this.getY() > Game.HEIGHT) {
				death = true;
				Game.player.life--;
			}
			//Enemy anymation
			if(death) {
				showDeath--;
			}
			if(timeToAdvance/10 == timeSprite) {
				if(spriteCur == 0) {
					spriteCur = 1;
				}else {
					spriteCur = 0;
				}
				timeSprite = 0;
			}
			if(showDeath == 0) {
				Game.enemyRow.size++;
				Game.score += 20 * value;
				endEnemy = true;
			}
		}
	}
	public void render(Graphics g) {
		if(!death) {
			if(spriteCur == 0) {
				g.drawImage(sprite, this.getX(),this.getY(),this.getWidth(),this.getHeight(),null);
			}else {
				g.drawImage(sprite0, this.getX(),this.getY(),this.getWidth(),this.getHeight(),null);
			}
		}if(death) {
			g.drawImage(deathSprite, this.getX(),this.getY(),this.getWidth(),this.getHeight(),null);
		}
	}

	public int getTimeToAdvance() {
		return timeToAdvance;
	}

	public void setTimeToAdvance(int timeToAdvance) {
		this.timeToAdvance = timeToAdvance;
	}

	public int getTimeSprite() {
		return timeSprite;
	}

	public void setTimeSprite(int timeSprite) {
		this.timeSprite = timeSprite;
	}

	public int getSpriteCur() {
		return spriteCur;
	}

	public void setSpriteCur(int spriteCur) {
		this.spriteCur = spriteCur;
	}

	public int getLocalTime() {
		return localTime;
	}

	public void setLocalTime(int localTime) {
		this.localTime = localTime;
	}

	public int getShowDeath() {
		return showDeath;
	}

	public void setShowDeath(int showDeath) {
		this.showDeath = showDeath;
	}

	public BufferedImage getDeathSprite() {
		return deathSprite;
	}

	public void setDeathSprite(BufferedImage deathSprite) {
		this.deathSprite = deathSprite;
	}

	public BufferedImage getSprite0() {
		return sprite0;
	}

	public void setSprite0(BufferedImage sprite0) {
		this.sprite0 = sprite0;
	}

	public boolean isDeath() {
		return death;
	}

	public void setDeath(boolean death) {
		this.death = death;
	}

	public boolean isEndEnemy() {
		return endEnemy;
	}

	public void setEndEnemy(boolean endEnemy) {
		this.endEnemy = endEnemy;
	}
	
	
}
