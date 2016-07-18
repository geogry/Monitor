package com.orange.UI;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.media.MediaLocator;
import javax.media.bean.playerbean.MediaPlayer;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.Timer;

import com.orange.camera.Camera;
import com.orange.database.DBHelper;
import com.orange.imgcmp.Comp;
import com.orange.mail.SendMail;
import com.orange.util.Operation;

public class MyFrame extends JFrame{
	
	private Component cavas = null;
	
	private Camera camera = null;
	
	private MyPanel rootPane = null;
	
	private String path = System.getProperty("user.dir");
	
	private Image image = null;
	
	private JMenuBar menubar = null;
	private JMenu startMenu = null;
	private JMenuItem takephoto = null;
	private JMenuItem startMonite = null;
	private JMenuItem stopMonite = null;
	private JMenuItem exit = null;
	private JMenu safe = null;
	private JMenuItem set = null;
	private JMenu helpMenu = null;
	private JMenuItem about = null;
	private JMenuItem illustrate = null;
	private JMenuItem closeAlter = null;
	
	private JButton takephoto_btn = null;
	private JButton startMonitor_btn = null;
	private JButton stopMonitor_btn = null;
	
	private ImageIcon startmonitor = null;

	private MenuListener myMenuListener = null;
	
	private Timer timer_takephoto = null;
	private Timer timer_imgcmp = null;
	private Timer timer_img = null;
	
	private Comp imgCmp = null;
	private boolean isChange = false;
	
	private MediaPlayer playMusic = null;
	
	private MyImageDialog imageDialog = null;
	private AboutDialog aboutDialog = null;
	private IllustrateDialog illustrateDialog = null;
	
	private DBHelper myDBHelper = null;

	public MyFrame(){
		startmonitor = new ImageIcon(path + "/image/startmonitor.png");
		
		myDBHelper = new DBHelper();
		myDBHelper.Connect();
		
		rootPane = new MyPanel();
		rootPane.setLayout(null);
		
		camera = new Camera();
		cavas = camera.startCamera();
		cavas.setSize(750, 500);
		cavas.setBounds(25, 50, 750, 500);
		rootPane.add(cavas);
		
		aboutDialog = new AboutDialog("����");
		illustrateDialog = new IllustrateDialog("˵��");
		
		imgCmp = new Comp();
		
		this.setMenu();
		this.setButton();
		this.initTimer();
		
		//����Frame����
		try {
			this.setIconImage(ImageIO.read(new File(path + "/image/takephoto.png")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.setTitle("orange���");
		this.pack();
		this.setContentPane(rootPane);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setPreferredSize(new Dimension(900, 660));
		int x = (this.getToolkit().getScreenSize().width - 900)/2;
		int y = (this.getToolkit().getScreenSize().height - 660)/2;
		this.setLocation(x, y);
		this.setVisible(true);
		this.setBounds(x, y, 900, 660);
		this.setResizable(false);
		this.addWindowListener(new WindowAdapter(){

			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				super.windowClosed(e);
				new PasswordDialog("exit");
			}
			
		});
	}
	
	private void setMenu(){
		//��ʼ�˵�������
		startMenu = new JMenu("��ʼ");
		takephoto = new JMenuItem("����");
		startMonite = new JMenuItem("��������");
		stopMonite = new JMenuItem("ֹͣ����");
		exit = new JMenuItem("�˳�");
		closeAlter = new JMenuItem("�رձ���");
		myMenuListener = new MenuListener();
		closeAlter.addActionListener(myMenuListener);
		closeAlter.setEnabled(false);
		takephoto.addActionListener(myMenuListener);
		startMonite.addActionListener(myMenuListener);
		stopMonite.addActionListener(myMenuListener);
		stopMonite.setEnabled(false);
		exit.addActionListener(myMenuListener);
		startMenu.add(takephoto);
		startMenu.addSeparator();
		startMenu.add(startMonite);
		startMenu.addSeparator();
		startMenu.add(stopMonite);
		startMenu.addSeparator();
		startMenu.add(closeAlter);
		startMenu.addSeparator();
		startMenu.add(exit);
		//���ò˵�������
		safe = new JMenu("��ȫ");
		set = new JMenuItem("����");
		set.addActionListener(myMenuListener);
		safe.add(set);
		//���ð����˵�
		helpMenu = new JMenu("����");
		about = new JMenuItem("����");
		illustrate = new JMenuItem("����˵��");
		illustrate.addActionListener(myMenuListener);
		about.addActionListener(myMenuListener);
		helpMenu.add(about);
		helpMenu.addSeparator();
		helpMenu.add(illustrate);
		
		menubar = new JMenuBar();
		menubar.add(startMenu);
		menubar.add(safe);
		menubar.add(helpMenu);
		this.setJMenuBar(menubar);
	}
	
	private void setButton(){
		takephoto_btn = new JButton();
		takephoto_btn.setIcon(new ImageIcon(path + "/image/takephoto.png"));
		takephoto_btn.setToolTipText("����");
		takephoto_btn.setBounds(new Rectangle(800, 150, 72, 72));
		takephoto_btn.addActionListener(myMenuListener);
		rootPane.add(takephoto_btn);
		
		startMonitor_btn = new JButton();
		startMonitor_btn.setIcon(startmonitor);
		startMonitor_btn.setBounds(800, 240, 72, 72);
		startMonitor_btn.setToolTipText("��������");
		startMonitor_btn.addActionListener(myMenuListener);
		startMonitor_btn.setEnabled(true);
		rootPane.add(startMonitor_btn);
		
		stopMonitor_btn = new JButton();
		stopMonitor_btn.setIcon(new ImageIcon(path + "/image/stopmonitor.png"));
		stopMonitor_btn.setToolTipText("ֹͣ����");
		stopMonitor_btn.setBounds(800, 330, 72, 72);
		stopMonitor_btn.addActionListener(myMenuListener);
		stopMonitor_btn.setEnabled(false);
		rootPane.add(stopMonitor_btn);
	}
	
	private void initTimer(){
		timer_takephoto = new Timer(60000, new ActionListener(){	//��ȡ��ǰͼ��ÿ1���ӽ�ȡһ��

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				image = camera.takePhoto();
				camera.savaImage(image, path + "/temp/2.jpg");	//����һ�ų�ʼͼ��
			}
			
		});
		
		timer_imgcmp = new Timer(60000, new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				File image1 = new File(path + "/temp/1.jpg");
				File image2 = new File(path + "/temp/2.jpg");
				if(image1.exists() && image2.exists()){
					isChange = imgCmp.comp("1.jpg", "2.jpg");
				}
				if(isChange){
					timer_imgcmp.stop();
					timer_takephoto.stop();
					alert();
					System.out.println("ͼ��ı�");
				}
			}
			
		});
		
		timer_img = new Timer(1000, new ActionListener(){
			
			boolean flag = true;

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(flag){
					flag = false;
					startMonitor_btn.setIcon(null);
				} else {
					flag = true;
					startMonitor_btn.setIcon(startmonitor);
				}
			}
			
		});
	}
	
	public void monite(){
		int x = JOptionPane.showConfirmDialog(this, "�Ƿ�����?");
		if(x == JOptionPane.OK_OPTION){
			new FullFrame();
		}
		
		image = camera.takePhoto();
		camera.savaImage(image, path + "/temp/1.jpg");	//����һ�ų�ʼͼ��
		
		timer_takephoto.start();
		
		timer_imgcmp.start();
		
		timer_img.start();
	}
	//����
	public void alert(){
		//��������
		playMusic = new MediaPlayer();
		playMusic.setMediaLocator(new MediaLocator("file:///" + path + "/music/beats.mp3"));
		playMusic.realize();
		playMusic.start();
		
		//�����ʼ�
		SendMail mail = new SendMail();
		String address = myDBHelper.queryEM();
		String emailPwd = myDBHelper.queryEMPWD();
		if(!(address.equals("") || emailPwd.equals(""))){
			String hostDomain = address.substring(address.indexOf("@") + 1, address.length());
			String username = address.substring(0, address.indexOf("@"));
			mail.setHost("smtp." + hostDomain);
			mail.setUsername(username);
			mail.setPassword(emailPwd);
			mail.setReceiveAddress(address);
			mail.setSendAddress(address);
			mail.setTitle("����");
			mail.send("��ļ�������ڱ��Ƿ��û�ʹ�ã��뾡��鿴��ļ����״̬������");
		} else {
			JOptionPane.showMessageDialog(this, "�㻹û�����ñ������䣬�뼴ʹ����");
		}
		
		String op = myDBHelper.queryOP();
		if(op.equals("�ر�")){
			Operation.shutdown();
		}else if(op.equals("ע��")){
			Operation.logoff();
		}else if(op.equals("����")){
			Operation.dormant();
		}
		
		closeAlter.setEnabled(true);
	}
	
	class MenuListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource() == takephoto || e.getSource() == takephoto_btn){
				image = camera.takePhoto();
				imageDialog = new MyImageDialog(image);
				imageDialog.show();
			}
			
			if(e.getSource() == startMonite || e.getSource() == startMonitor_btn){
				monite();
				startMonite.setEnabled(false);
				stopMonite.setEnabled(true);
				startMonitor_btn.setEnabled(false);
				stopMonitor_btn.setEnabled(true);
			}
			
			if(e.getSource() == exit){
				new PasswordDialog("exit");
			}
			
			if(e.getSource() == stopMonite || e.getSource() == stopMonitor_btn){
				new PasswordDialog("monitor", playMusic, timer_takephoto, timer_imgcmp, 
						timer_img, startMonite, stopMonite, startMonitor_btn, stopMonitor_btn, isChange);
			}
			
			if(e.getSource() == set){
				new PasswordDialog("set");
			}
			
			if(e.getSource() == about){
				aboutDialog.show();
			}
			
			if(e.getSource() == illustrate){
				illustrateDialog.show();
			}
			
			if(e.getSource() == closeAlter){
				new PasswordDialog("music", playMusic, timer_takephoto, timer_imgcmp);
			}
		}
		
	}
}
