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
		
		op1 = new JLabel("1.��һ�ΰ�װ���û���Ҫ�����������ñ��������Լ���ȫ");
		op1.setBounds(10, 20, 350, 20);
		op1_1 = new JLabel("���룬����֪ͨ�����Լ���֤�����ȫ.");
		op1_1.setBounds(10, 40, 390, 20);
		op2 = new JLabel("2.�������������Զ���������ͷ�����û���Ҫʹ��������");
		op2.setBounds(10, 70, 390, 20);
		op2_1 = new JLabel("Ҫռ������ͷ����������ⷢ����ͻ.");
		op2_1.setBounds(10, 90, 390, 20);
		op3 = new JLabel("3.����Ҫ��������ʱ���û���Ҫ������ͷ��ȫ��ֹ��û���κ�");
		op3.setBounds(10, 120, 390, 20);
		op3_1 = new JLabel("��Ҫ�����仯��״̬������������ʼ�ջᱨ��.");
		op3_1.setBounds(10, 140, 390, 20);
		op4 = new JLabel("4.������޷����յ������������ʼ����뽫�������SMTP�����.");
		op4.setBounds(10, 170, 390, 20);
		op5 = new JLabel("5.�����ʼʹ������Ϊ123456�����û���ʱ�޸�.");
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
