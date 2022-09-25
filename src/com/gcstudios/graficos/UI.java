package com.gcstudios.graficos;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.gcstudios.entities.Player;
import com.gcstudios.main.Game;
import com.gcstudios.world.World;

public class UI {
	
	public void tick() {
	}

	public void render(Graphics g) {
		if(Game.gameState == 0) {
			g.setColor(Color.white);
			g.setFont(new Font("Arial",30,30));
			g.drawString("Voce perdeu", ((Game.WIDTH * Game.SCALE)/2) - 80, (Game.HEIGHT * Game.SCALE)/2);
		}
		if(Game.gameState == 1) {
			g.setColor(Color.white);
			g.setFont(new Font("Arial",30,30));
			g.drawString("Voce ganhou", ((Game.WIDTH * Game.SCALE)/2) - 80, (Game.HEIGHT * Game.SCALE)/2);
		}
		
		g.setColor(Color.white);
		g.setFont(new Font("Arial",15,15));
		g.drawString("Score: " + Game.score, ((Game.WIDTH * Game.SCALE)) - 100, (Game.HEIGHT * Game.SCALE) - 15);

	}
	
}
