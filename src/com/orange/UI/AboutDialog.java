package com.orange.UI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
/**
 * this class is designed for the message of this software that declare 
 * the version message or copyright ect.
 * @author Lenovo
 * 
 */
public class AboutDialog extends JFrame {
	
	private JLabel about = null;
	private JLabel copyright = null;
	private JLabel softwareVersion = null;
	private JLabel developer = null;
	private JLabel about1 = null;
	
	private AboutPane contentPane = null;
	
	public AboutDialog(String title) {
		super(title);
		about = new JLabel("    本软件专为你的计算机开发，保证你的计算");
		about1 = new JLabel("机信息安全，满足你信息保护需要");
		about1.setBounds(90, 80, 440, 30);
		about1.setFont(new Font("微软雅黑", Font.BOLD, 20));
		about1.setForeground(new Color(29, 23, 223));
		about.setBounds(90, 50, 440, 30);
		about.setFont(new Font("微软雅黑", Font.BOLD, 20));
		about.setForeground(new Color(29, 23, 223));
		copyright = new JLabel("CopyRight © 2013 Orange. All Rights reserved.");
		copyright.setBounds(120, 340, 400, 20);
		copyright.setFont(new Font("consolas", Font.PLAIN, 14));
		copyright.setForeground(new Color(29, 23, 223));
		developer = new JLabel("Developer: Orange Software Team");
		developer.setBounds(170, 160, 270, 20);
		developer.setFont(new Font("consolas", Font.PLAIN, 14));
		developer.setForeground(new Color(29, 23, 223));
		softwareVersion = new JLabel("Orange监视 V1.0");
		softwareVersion.setBounds(230, 250, 130, 20);
		softwareVersion.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		softwareVersion.setForeground(new Color(29, 23, 223));
		
		contentPane = new AboutPane();
		contentPane.setLayout(null);
		contentPane.setBounds(0, 0, 640, 400);
		
		contentPane.add(about);
		contentPane.add(about1);
		contentPane.add(developer);
		contentPane.add(softwareVersion);
		contentPane.add(copyright);
		
		int x = (this.getToolkit().getScreenSize().width - 640) / 2;
		int y = (this.getToolkit().getScreenSize().height - 400) / 2;
		
		this.pack();
		this.setLocation(x, y);
		this.setBounds(x, y, 640, 400);
		this.setContentPane(contentPane);
		try {
			this.setIconImage(ImageIO.read(new File(System.getProperty("user.dir") + "/image/takephoto.png")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.setResizable(false);
		this.addWindowListener(new WindowListener(){

			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				dispose();
			}

			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
	}
	
	
}
