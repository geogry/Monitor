package com.orange.UI;

import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import com.orange.HK.StopHK;
import com.orange.database.DBHelper;
import com.orange.util.MD5;

public class FullFrame extends JFrame {
	
	private JPanel contentPane = null;
	private JLabel tip = null;
	private JPasswordField psd_input = null;
	private JButton ok_btn = null;
	private DBHelper myDBHelper = null;
	
	public FullFrame(){
		StopHK.Attach();
		
		myDBHelper = new DBHelper();
		myDBHelper.Connect();
		
		int x = this.getToolkit().getScreenSize().width;
		int y = this.getToolkit().getScreenSize().height;
		
		contentPane = new JPanel();
		contentPane.setLayout(null);
		contentPane.setBackground(Color.BLACK);
		contentPane.setToolTipText("按ESC键退出");
		contentPane.setBounds(0, 0, x, y);
		
		tip = new JLabel("为保证您的计算机安全，请输入密码");
		tip.setBounds(480, 310, 400, 40);
		tip.setForeground(Color.DARK_GRAY);
		tip.setFont(new Font("宋体", Font.BOLD, 24));
		
		psd_input = new JPasswordField();
		psd_input.setBounds(570, 360, 220, 30);
		
		ok_btn = new JButton("确定");
		ok_btn.setBounds(650, 400, 70, 30);
		ok_btn.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(MD5.md5(psd_input.getText()).equals(myDBHelper.queryPWD())){
					StopHK.Detach();
					FullFrame.this.dispose();
				}
			}
			
		});
		
		this.setAlwaysOnTop(true);
		this.setAutoRequestFocus(true);
		this.setUndecorated(true);
		this.setVisible(true);
		this.setContentPane(contentPane);
		GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().setFullScreenWindow(this);
		
		this.addKeyListener(new KeyAdapter(){

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				super.keyPressed(e);
				if(e.getKeyCode() == 27){
					contentPane.add(tip);
					contentPane.add(psd_input);
					contentPane.add(ok_btn);
					contentPane.repaint();
				}
			}
			
		});
	}
}
