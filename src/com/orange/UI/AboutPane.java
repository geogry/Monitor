package com.orange.UI;

import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class AboutPane extends JPanel {

	private ImageIcon image = null;
	
	public AboutPane(){
		super();
		image = new ImageIcon(System.getProperty("user.dir") + "/image/about_bg.jpg");
	}

	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		g.drawImage(image.getImage(), 0, 0, 640, 400, null);
	}
	
}
