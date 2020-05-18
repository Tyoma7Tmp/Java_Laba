package com.pnfy.panel;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import com.pnfy.global.Global;
import com.pnfy.tatris.Ground;
import com.pnfy.tatris.Shape;

public class GamePanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Ground ground;
	private Shape shape;

	public void display(Ground ground, Shape shape) {
		System.out.println("GamePanel's display");
		this.ground = ground;
		this.shape = shape;
		
		this.repaint();
		
	}

	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		//super.printComponent(g);
		g.setColor(new Color(0x241c25));
		g.fillRect(0, 0, Global.WIDTH * Global.CELL_SIXE, Global.HEIGHT * Global.CELL_SIXE);
		
		if(shape != null && ground != null) {
			shape.drawMe(g);
			ground.drawMe(g);
		}
	}
	
	public GamePanel() {
		this.setSize(Global.WIDTH * Global.CELL_SIXE, Global.HEIGHT * Global.CELL_SIXE);
	}
}
