package com.orange.UI;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class MyPanel extends JPanel {
	
	private ImageIcon bgImage = null;
	private String path = System.getProperty("user.dir") + "/image/background.jpg";

	public MyPanel(){
		super();
		bgImage = new ImageIcon(path);
		
		this.setSize(new Dimension(900,600));
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		g.drawImage(bgImage.getImage(), 0, 0, bgImage.getIconWidth(), bgImage.getIconHeight(), null);
	}
	
	
}
