package com.gcstudios.entities;

import java.awt.Graphics;
import java.util.ArrayList;

import com.gcstudios.main.Game;

public class EnemyRow {
	
	ArrayList<Enemy> enemiesLvl1;
	ArrayList<Enemy> enemiesLvl12;
	ArrayList<Enemy> enemiesLvl2;
	ArrayList<Enemy> enemiesLvl3;
	private int lastDeadEnemy = -1;
	public int size = 0;
	private boolean movingRight = true;
	
	public EnemyRow() {

		this.enemiesLvl1 = creatEnemyRow(0,0);
		this.enemiesLvl12 = creatEnemyRow(0,1);
		this.enemiesLvl2 = creatEnemyRow(1,2);
		this.enemiesLvl3 = creatEnemyRow(2,3);
	}

	public void tick() {
		if(Game.gameState == -1) {
			for(int i = 0; i < enemiesLvl1.size(); i++) {
				enemiesLvl1.get(i).tick();
				if(enemiesLvl1.get(i).isEndEnemy()) {
					enemiesLvl1.remove(i);
					lastDeadEnemy = i;
				}
			}
			for(int i = 0; i < enemiesLvl12.size(); i++) {
				enemiesLvl12.get(i).tick();
				if(enemiesLvl12.get(i).isEndEnemy()) {
					enemiesLvl12.remove(i);
					lastDeadEnemy = i;
				}
			}
			for(int i = 0; i < enemiesLvl2.size(); i++) {
				enemiesLvl2.get(i).tick();
				if(enemiesLvl2.get(i).isEndEnemy()) {
					enemiesLvl2.remove(i);
					lastDeadEnemy = i;
				}
			}
			for(int i = 0; i < enemiesLvl3.size(); i++) {
				enemiesLvl3.get(i).tick();
				if(enemiesLvl3.get(i).isEndEnemy()) {
					enemiesLvl3.remove(i);
					lastDeadEnemy = i;
				}
			}
			if(!enemiesLvl3.isEmpty()) {
				moveRow(enemiesLvl3);
			}else if(!enemiesLvl2.isEmpty()) {
				moveRow(enemiesLvl2);
			}else if(!enemiesLvl12.isEmpty()) {
				moveRow(enemiesLvl12);
			}else if(!enemiesLvl1.isEmpty()) {
				moveRow(enemiesLvl1);
			}
		}
		if(enemiesLvl3.isEmpty() && enemiesLvl2.isEmpty() && enemiesLvl1.isEmpty() &&enemiesLvl12.isEmpty()) {
			Game.gameState = 1;//you win
		}
	}
	
	public void moveRow(ArrayList<Enemy> en) {
		if(en.size() > 0) {
			if(movingRight) {
				for(int i = 0; i < en.size(); i++) {
					en.get(i).x += 0.2 + size/15;
					if(en.get(en.size()-1).x > Game.WIDTH -16) {
						movingRight = false;
					}
				}
			}
			if(!movingRight) {
				for(int i = 0; i < en.size(); i++) {
					en.get(i).x -= 0.2 + size/15;
					if(en.get(i).x < 3) {
						movingRight = true;
					}
				}
			}
		}
	}
	
	public void render(Graphics g) {
		for(int i = 0; i < enemiesLvl1.size(); i++) {
			enemiesLvl1.get(i).render(g);
		}
		for(int i = 0; i < enemiesLvl12.size(); i++) {
			enemiesLvl12.get(i).render(g);
		}
		for(int i = 0; i < enemiesLvl2.size(); i++) {
			enemiesLvl2.get(i).render(g);
		}
		for(int i = 0; i < enemiesLvl3.size(); i++) {
			enemiesLvl3.get(i).render(g);
		}
	}
	

	public ArrayList<Enemy> creatEnemyRow(int enemyLvl,int altura){
		ArrayList<Enemy> enemy = new ArrayList<Enemy>();
		for(int pos = 2; pos + 16< Game.WIDTH; pos += 16 ) {
			Enemy en = new Enemy(pos,2+ (10*altura),16,8,0,null,null,enemyLvl);
			enemy.add(en);
		}
		return enemy;
	}
}
