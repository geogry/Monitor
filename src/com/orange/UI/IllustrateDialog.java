package com.orange.UI;

import java.awt.Graphics;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class IllustrateDialog extends JFrame {
	
	private JPanel contentPane = null;
	private JLabel op1 = null;
	private JLabel op1_1 = null;
	private JLabel op2 = null;
	private JLabel op2_1 = null;
	private JLabel op3 = null;
	private JLabel op3_1 = null;
	private JLabel op4 = null;
	private JLabel op5 = null;
	
	private ImageIcon image = null;
	
	public IllustrateDialog(String title){
		super(title);
		
		op1 = new JLabel("1.第一次安装的用户需要在设置中设置报警邮箱以及安全");
		op1.setBounds(10, 20, 350, 20);
		op1_1 = new JLabel("密码，用于通知报警以及保证软件安全.");
		op1_1.setBounds(10, 40, 390, 20);
		op2 = new JLabel("2.启动软件后，软件自动启动摄像头，请用户不要使用其它需");
		op2.setBounds(10, 70, 390, 20);
		op2_1 = new JLabel("要占用摄像头的软件，以免发生冲突.");
		op2_1.setBounds(10, 90, 390, 20);
		op3 = new JLabel("3.在需要启动监视时，用户需要在摄像头完全静止且没有任何");
		op3.setBounds(10, 120, 390, 20);
		op3_1 = new JLabel("需要环境变化的状态下启动，否则始终会报警.");
		op3_1.setBounds(10, 140, 390, 20);
		op4 = new JLabel("4.如果你无法接收到报警或密码邮件，请将你邮箱的SMTP服务打开.");
		op4.setBounds(10, 170, 390, 20);
		op5 = new JLabel("5.软件初始使用密码为123456，请用户及时修改.");
		op5.setBounds(10, 200, 390, 20);
		
		image = new ImageIcon(System.getProperty("user.dir") + "/image/about_bg.jpg");
		
		contentPane = new JPanel(){

			@Override
			protected void paintComponent(Graphics g) {
				// TODO Auto-generated method stub
				super.paintComponent(g);
				g.drawImage(image.getImage(), 0, 0, 400, 250, null);
			}
			
		};
		contentPane.setLayout(null);
		contentPane.setBounds(0, 0, 400, 250);
		
		contentPane.add(op1);
		contentPane.add(op1_1);
		contentPane.add(op2);
		contentPane.add(op2_1);
		contentPane.add(op3);
		contentPane.add(op3_1);
		contentPane.add(op4);
		contentPane.add(op5);
		
		int x = (this.getToolkit().getScreenSize().width - 400) / 2;
		int y = (this.getToolkit().getScreenSize().height - 250) / 2;
		
		try {
			this.setIconImage(ImageIO.read(new File(System.getProperty("user.dir") + "/image/takephoto.png")));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		this.pack();
		this.setContentPane(contentPane);
		this.setLocation(x, y);
		this.setResizable(false);
		this.setBounds(x, y, 400, 250);
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
