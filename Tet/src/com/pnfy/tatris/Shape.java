package com.pnfy.tatris;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import com.pnfy.global.Global;
import com.pnfy.listener.ShapeListener;

public class Shape {
	public static final int ROTSTE = 0;
	public static final int LEFT = 1;
	public static final int RIGHT = 2;
	public static final int DOWN = 3;

	private int[][] body;
	private int status;
	private int top;
	private int left;
	private int speed = 1000;

	private ShapeListener listener;

	public void moveLeft() {
		left--;
	}

	public void moveRight() {
		left++;
	}

	public void moveDown() {
		top++;
	}

	public void rotate() {
		status = (status + 1) % body.length;
	}

	private Color rand_color(){
		Random rand = new Random();

		float r = rand.nextFloat();
		float g = rand.nextFloat() / 2f;
		float b = rand.nextFloat() / 2f;

		Color randomColor = new Color(r, g, b);
		return randomColor;
	}

	public void drawMe(Graphics g) {
		g.setColor(rand_color());
		for (int x = 0; x < 4; x++) {
			for (int y = 0; y < 4; y++) {
				if (getFlagByPoint(x, y)) {
					g.fill3DRect((left + x) * Global.CELL_SIXE, (top + y)
							* Global.CELL_SIXE, Global.CELL_SIXE,
							Global.CELL_SIXE, true);
				}
			}
		}
	}

	private boolean getFlagByPoint(int x, int y) {
		return body[status][y * 4 + x] == 1;
	}

	public boolean isMember(int x, int y, boolean rotate) {
		@SuppressWarnings("unused")
		int tempStatus = status;
		if (rotate) {
			tempStatus = (status + 1) % body.length;
		}

		return body[status][y * 4 + x] == 1;
	}

	public int speedOfGame(int speed) {

			speed -= 100;

			if (speed == 100) {
				return speed;
			}
			return speed;
	}

	private class ShapeDriver implements Runnable {

		@Override
		public void run() {

			while (listener.isShapeMoveDownable(Shape.this)) {
				moveDown();
				listener.shapeMoveDown(Shape.this);
				try {
					Thread.sleep(speedOfGame(speed));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

	}

	public Shape() {
		new Thread(new ShapeDriver()).start();
	}

	public void addShapeListener(ShapeListener listener) {
		if (listener != null) {
			this.listener = listener;
		}
	}

	public void setBody(int[][] body) {
		this.body = body;

	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getTop() {
		return top;
	}

	public int getLeft() {
		return left;
	}
}
