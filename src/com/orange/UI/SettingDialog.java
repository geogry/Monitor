package com.orange.UI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.orange.database.DBHelper;
import com.orange.util.MD5;

public class SettingDialog extends JFrame {
	
	private JPanel contentPane = null;
	private JPanel psdPane = null;
	
	private JLabel email = null;
	private JLabel emailTip = null;
	private JLabel password = null;
	private JLabel pattern = null;
	private JLabel currPsd = null;
	private JLabel conPsd = null;
	private JLabel inputPsd = null;
	
	private ImageIcon image = null;
	
	private JButton savePsd = null;
	private JButton setPsd = null;
	private JButton saveEmail = null;
	private JButton savePattern = null;
	
	private JComboBox<String> patternChoose = null;
	
	private JPasswordField currentPsd = null;
	private JPasswordField psdInput = null;
	private JPasswordField psdConfirm = null;
	private JTextField emailInput = null;
	private JPasswordField emailpwd = null;
	
	private ButtonListener listener = null;
	
	private DBHelper myDBHelper = null;
	
	public SettingDialog(String title){
		super(title);
		
		myDBHelper = new DBHelper();
		myDBHelper.Connect();
		
		image = new ImageIcon(System.getProperty("user.dir") + "/image/about_bg.jpg");
		
		listener = new ButtonListener();
		
		contentPane = new JPanel(){

			@Override
			protected void paintComponent(Graphics g) {
				// TODO Auto-generated method stub
				super.paintComponent(g);
				g.drawImage(image.getImage(), 0, 0, image.getIconWidth(), image.getIconHeight(), null);
			}
			
		};
		contentPane.setLayout(null);
		contentPane.setBounds(0, 0, 640, 400);
		
		email = new JLabel("报警邮箱");
		email.setBounds(10, 10, 70, 30);
		emailInput = new JTextField();
		emailInput.setBounds(20, 40, 300, 30);
		emailpwd = new JPasswordField();
		emailpwd.setBounds(20, 70, 150, 30);
		emailTip = new JLabel("*请把您邮箱的SMTP服务打开并使用QQ邮箱，否则无法接收报警邮件");
		emailTip.setForeground(Color.RED);
		emailTip.setBounds(20, 100, 400, 30);
		saveEmail = new JButton("保存");
		saveEmail.setBounds(330, 70, 70, 30);
		saveEmail.addActionListener(listener);
		contentPane.add(email);
		contentPane.add(emailInput);
		contentPane.add(emailpwd);
		contentPane.add(emailTip);
		contentPane.add(saveEmail);
		
		password = new JLabel("密码安全");
		password.setBounds(10, 140, 70, 30);
		
		currPsd = new JLabel("  旧密码:");
		currPsd.setBounds(0, 0, 70, 30);
		currentPsd = new JPasswordField();
		currentPsd.setBounds(70, 0, 100, 30);
		inputPsd = new JLabel("  新密码:");
		psdInput = new JPasswordField();
		inputPsd.setBounds(0, 35, 70, 30);
		psdInput.setBounds(70, 35, 100, 30);
		conPsd = new JLabel("确认密码:");
		conPsd.setBounds(0, 70, 70, 30);
		psdConfirm = new JPasswordField();
		psdConfirm.setBounds(70, 70, 100, 30);
		savePsd = new JButton("保存");
		savePsd.setBounds(100, 105, 70, 30);
		savePsd.addActionListener(listener);
		psdPane = new JPanel();
		psdPane.setLayout(null);
		psdPane.setOpaque(true);
		psdPane.setBackground(null);
		psdPane.setBounds(10, 170, 170, 135);
		psdPane.add(currPsd);
		psdPane.add(currentPsd);
		psdPane.add(inputPsd);
		psdPane.add(psdInput);
		psdPane.add(conPsd);
		psdPane.add(psdConfirm);
		psdPane.add(savePsd);
		
		setPsd = new JButton("密码设置");
		setPsd.setBounds(20, 170, 100, 30);
		setPsd.addActionListener(listener);
		contentPane.add(password);
		contentPane.add(setPsd);
		
		pattern = new JLabel("模式设置");
		pattern.setBounds(10, 210, 70, 30);
		patternChoose = new JComboBox<String>();
		patternChoose.addItem("无");
		patternChoose.addItem("关闭");
		patternChoose.addItem("休眠");
		patternChoose.addItem("注销");
		patternChoose.setBounds(20, 240, 100, 30);
		savePattern = new JButton("保存");
		savePattern.setBounds(130, 240, 70, 30);
		savePattern.addActionListener(listener);
		
		//设置初始显示值
		emailInput.setText(myDBHelper.queryEM());
		patternChoose.setSelectedItem(myDBHelper.queryOP());
		
		contentPane.add(pattern);
		contentPane.add(patternChoose);
		contentPane.add(savePattern);
		
		int x = (this.getToolkit().getScreenSize().width - 640) / 2;
		int y = (this.getToolkit().getScreenSize().height - 400) / 2;
		
		this.pack();
		this.setContentPane(contentPane);
		this.setBounds(x, y, 650, 430);
		this.setLocation(x, y);
		this.setResizable(false);
		try {
			this.setIconImage(ImageIO.read(new File(System.getProperty("user.dir") + "/image/takephoto.png")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	
	class ButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource() == saveEmail){
				myDBHelper.updateEM(emailInput.getText());
				myDBHelper.updateEMPWD(emailpwd.getText());
			}
			if(e.getSource() == savePattern){
				myDBHelper.updateOP(patternChoose.getSelectedItem().toString());
			}
			if(e.getSource() == setPsd){
				contentPane.remove(setPsd);
				contentPane.add(psdPane);
				pattern.setBounds(10, 315, 70, 30);
				patternChoose.setBounds(20, 345, 100, 30);
				savePattern.setBounds(130, 345, 70, 30);
				contentPane.repaint();
			}
			if(e.getSource() == savePsd){
				String oldPwd = currentPsd.getText();
				String oldPwd_db = myDBHelper.queryPWD();
				String newPwd = psdInput.getText();
				String confirm = psdConfirm.getText();
				if(MD5.md5(oldPwd).equals(oldPwd_db)){
					if(newPwd.equals(confirm)){
						myDBHelper.updatePWD(MD5.md5(newPwd));
						contentPane.remove(psdPane);
						contentPane.add(setPsd);
						pattern.setBounds(10, 210, 70, 30);
						patternChoose.setBounds(20, 240, 100, 30);
						savePattern.setBounds(130, 240, 70, 30);
						contentPane.repaint();
					} else {
						JOptionPane.showMessageDialog(SettingDialog.this, "两次输入的密码不同，请重新输入", "提示", JOptionPane.OK_OPTION);
					}
				} else {
					JOptionPane.showMessageDialog(SettingDialog.this, "密码不正确，请重新输入", "提示", JOptionPane.OK_OPTION);
				}
				currentPsd.setText("");
				psdInput.setText("");
				psdConfirm.setText("");
			}
		}
		
	}
}
