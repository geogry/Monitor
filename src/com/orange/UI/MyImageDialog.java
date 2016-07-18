package com.orange.UI;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileFilter;

import com.orange.camera.Camera;

public class MyImageDialog extends JFrame {

	private MyImagePanel imagePane = null;
	
	private JFileChooser pathDialog = null;
	
	private Image image = null;
	
	private JMenuBar menubar = null;
	private JMenu menu = null;
	private JMenuItem save = null;
	
	private Camera camera = null;
	
	private String path = null;
	
	public MyImageDialog(Image image){
		this.image = image;
		
		imagePane = new MyImagePanel(image);
		imagePane.setBounds(0, 0, 640, 400);
		
		camera = new Camera();
		
		save = new JMenuItem("保存");
		save.addActionListener(new SaveListener());
		menu = new JMenu("开始");
		menu.add(save);
		menubar = new JMenuBar();
		menubar.add(menu);
		
		pathDialog = new JFileChooser("存储为");
		pathDialog.addChoosableFileFilter(new FileFilter(){

			@Override
			public boolean accept(File f) {
				// TODO Auto-generated method stub
				return f.getName().toLowerCase().endsWith(".jpg") || f.isDirectory();
			}

			@Override
			public String getDescription() {
				// TODO Auto-generated method stub
				return "JPEG(*.jpg)";
			}
			
		});
		
		int x = (this.getToolkit().getScreenSize().width - 650) / 2;
		int y = (this.getToolkit().getScreenSize().height - 430) / 2;
		
		this.pack();
		try {
			this.setIconImage(ImageIO.read(new File(System.getProperty("user.dir") + "/image/takephoto.png")));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		this.setBounds(new Rectangle(x, y, 650, 430));
		this.setTitle("照片");
		this.setContentPane(imagePane);
		this.setResizable(true);
		this.setJMenuBar(menubar);
		this.setLocation(x, y);
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
	
	class SaveListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			int i = pathDialog.showSaveDialog(new JPanel());
			if(i == JFileChooser.CANCEL_OPTION){
				return;
			}
			path = pathDialog.getCurrentDirectory() + "\\" + pathDialog.getSelectedFile().getName();
			File temp = new File(path + ".jpg");
			if(temp.exists()){
				int op = JOptionPane.showConfirmDialog(MyImageDialog.this, "文件已存在，要覆盖它吗？", "确认对话框", JOptionPane.YES_NO_OPTION);
				if(op == JOptionPane.YES_OPTION){
					camera.savaImage(image, path + ".jpg");
				} else {
					return;
				}
			} else {
				camera.savaImage(image, path + ".jpg");
			}
		}
		
	}
}
