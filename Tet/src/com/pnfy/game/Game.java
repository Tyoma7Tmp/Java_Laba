package com.pnfy.game;

import javax.swing.*;

import com.pnfy.panel.GamePanel;
import com.pnfy.tatris.Ground;
import com.pnfy.tatris.ShapeFactory;
import com.pnfy.controllar.Controller;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Game {

	public static void main(String[] args) {
		JFrame menu = new JFrame("Menu");
		JButton btn = new JButton(new ImageIcon("images/start.jpg"));
		JButton btn1 = new JButton(new ImageIcon("images/exit.jpg"));

		ImageIcon image = new ImageIcon("images/icon.png");
		menu.setIconImage(image.getImage());
		menu.getContentPane().setBackground(Color.decode("#241c25"));

		btn.setBounds(105, 50, 408, 121);
		menu.add(btn);
		btn1.setBounds(105, 200, 405, 125);
		menu.add(btn1);
		menu.setBounds(470, 150, 626, 417);
		menu.setLayout(null);
		menu.setVisible(true);
		menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		btn1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent end){
				System.exit(0);
			}
		});

		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent start) {
				menu.setVisible(false);

				ShapeFactory shapeFactory = new ShapeFactory();
				Ground ground = new Ground();
				GamePanel gamePanel = new GamePanel();
				Controller controllar = new Controller(shapeFactory, ground, gamePanel);

				JFrame frame = new JFrame("Tetris");
				ImageIcon image = new ImageIcon("images/icon.png");
				frame.setIconImage(image.getImage());

				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				//frame.setSize(gamePanel.getSize().width + 15, gamePanel.getSize().height + 40);
				frame.setBounds(450, 120,gamePanel.getSize().width + 15, gamePanel.getSize().height + 40);

				//frame.setLocation(50,50);

				frame.setVisible(true);
				frame.add(gamePanel);
				gamePanel.addKeyListener(controllar);
				frame.addKeyListener(controllar);
				frame.setVisible(true);
				controllar.newGame();
			}
		});
	}
}
