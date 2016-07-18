package com.orange.UI;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

public class MyImagePanel extends JPanel {

	private Image image = null;
	
	public MyImagePanel(Image image){
		super();
		this.image = image;
		this.repaint();
		this.setLayout(null);
	}
	
	public void paint(Graphics g){
		if(image != null){
			g.drawImage(image, 0, 0, image.getWidth(null), image.getHeight(null), null);
		}
	}
}
