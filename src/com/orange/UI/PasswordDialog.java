package com.orange.UI;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.media.bean.playerbean.MediaPlayer;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.Timer;

import com.orange.HK.StopHK;
import com.orange.database.DBHelper;
import com.orange.util.MD5;

public class PasswordDialog extends JFrame {

	private JLabel tip = null;
	
	private JPanel contentPane = null;
	
	private BufferedImage icon = null;
	private ImageIcon image = null;
	
	private JButton ok_btn = null;
	private JButton cancel_btn = null;
	
	private JPasswordField input = null;
	
	private String password = null;
	private String path = System.getProperty("user.dir") + "/image/";
	
	private String op = null;
	
	private DBHelper myDBHelper = null;
	
	private SettingDialog setDialog = null;
	
	private MediaPlayer player = null;
	private Timer takePhoto = null;
	private Timer imageCmp = null;
	private Timer img = null;
	private JMenuItem start = null;
	private JMenuItem stop = null;
	private JButton start_btn = null;
	private JButton stop_btn = null;
	private boolean isChange = false;
	private Frame fullScreen = null;
	
	public PasswordDialog(String op){
		this.op = op;
		this.init();
	}
	
	public PasswordDialog(String op, MediaPlayer player, Timer takePhoto, Timer imgCmp){
		this.op = op;
		this.player = player;
		this.takePhoto = takePhoto;
		this.imageCmp = imgCmp;
		this.init();
	}
	
	public PasswordDialog(String op, MediaPlayer player, Timer takePhoto, Timer imgCmp, Timer img,
			JMenuItem start, JMenuItem stop, JButton start_btn, JButton stop_btn, boolean ischange){
		this.op = op;
		this.player = player;
		this.takePhoto = takePhoto;
		this.imageCmp = imgCmp;
		this.img = img;
		this.start = start;
		this.stop = stop;
		this.start_btn = start_btn;
		this.stop_btn = stop_btn;
		this.isChange = isChange;
		this.init();
	}
	
	private void init(){
		setDialog = new SettingDialog("设置");
		
		myDBHelper = new DBHelper();
		myDBHelper.Connect();
		
		try {
			icon = ImageIO.read(new File(path + "takephoto.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		image = new ImageIcon(path + "about_bg.jpg");
		
		contentPane = new JPanel(){

			@Override
			protected void paintComponent(Graphics g) {
				// TODO Auto-generated method stub
				super.paintComponent(g);
				g.drawImage(image.getImage(), 0, 0, 300, 150, null);
			}
			
		};
		contentPane.setLayout(null);
		contentPane.setBounds(0, 0, 300, 150);
		
		tip = new JLabel("为保证安全您计算机的安全，请输入密码");
		tip.setForeground(Color.RED);
		tip.setBounds(25, 15, 245, 30);
		contentPane.add(tip);
		
		input = new JPasswordField();
		input.setBounds(40, 55, 220, 30);
		contentPane.add(input);
		
		ButtonListener listener = new ButtonListener();
		
		ok_btn = new JButton("确定");
		ok_btn.addActionListener(listener);
		ok_btn.setBounds(75, 105, 70, 30);
		cancel_btn = new JButton("取消");
		cancel_btn.addActionListener(listener);
		cancel_btn.setBounds(155, 105, 70, 30);
		contentPane.add(ok_btn);
		contentPane.add(cancel_btn);
		
		int x = (this.getToolkit().getScreenSize().width - 300) / 2;
		int y = (this.getToolkit().getScreenSize().height - 150) / 2;
		
		this.setTitle("提示");
		this.setIconImage(icon);
		this.pack();
		this.setResizable(false);
		this.setBounds(x, y, 310, 180);
		this.setContentPane(contentPane);
		this.setLocation(x, y);
		this.setAlwaysOnTop(true);
		this.setVisible(true);
	}
	
	class ButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource() == ok_btn){
				password = input.getText();
				if(MD5.md5(password).equals(myDBHelper.queryPWD())){
					if(op.equals("exit")){
						System.exit(0);
					} else if(op.equals("set")){
						setDialog.show();
					} else if(op.equals("music")){
						player.stop();
						takePhoto.start();
						imageCmp.start();
					} else if(op.equals("monitor")){
						if(player != null){
							player.stop();
							player.close();
						}
						
						if(takePhoto.isRunning()){
							takePhoto.stop();
						}
						
						if(imageCmp.isRunning()){
							imageCmp.stop();
						}
						
						if(img.isRunning()){
							img.stop();
						}
						isChange = false;
						stop.setEnabled(false);
						start.setEnabled(true);
						stop_btn.setEnabled(false);
						start_btn.setEnabled(true);
						start_btn.setIcon(new ImageIcon(path + "startmonitor.png"));
					}
				} else {
					JOptionPane.showMessageDialog(PasswordDialog.this, "密码错误，无法执行此操作", "提示", JOptionPane.OK_OPTION);
				}
				dispose();
			}
			if(e.getSource() == cancel_btn){
				dispose();
			}
		}
		
	}
}
