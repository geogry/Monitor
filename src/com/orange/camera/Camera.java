package com.orange.camera;

import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;

import javax.media.Buffer;
import javax.media.Player;
import javax.media.control.FrameGrabbingControl;
import javax.media.format.VideoFormat;
import javax.media.util.BufferToImage;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class Camera {
	
	private Player player = null;
	private Driver driver = null;
	private Component cavas = null;
	
	private Image image = null;
	private Buffer buffer = null;
	private BufferToImage bufferToImage = null;
	
	
	public Component startCamera(){
		driver = new Driver();
		player = driver.getDriver();
		player.start();
		cavas = player.getVisualComponent();
		return cavas;
	}
	
	public void stopCamera(){
		driver.closePlayer(player);
	}
	
	//≈ƒ’’
	public Image takePhoto(){
		FrameGrabbingControl fgc = (FrameGrabbingControl) player.
				getControl("javax.media.control.FrameGrabbingControl"); //ªÒ»°æ≤Ã¨ŒÔœÒ
		buffer = fgc.grabFrame();
		bufferToImage = new BufferToImage((VideoFormat) buffer.getFormat());
		image = bufferToImage.createImage(buffer);
		return image;
	}
	//±£¥Ê’’∆¨
	public boolean savaImage(Image image, String path){
		BufferedImage buffer = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_RGB);
		Graphics2D graphic = buffer.createGraphics();
		graphic.drawImage(image, null, null);
		try {
			FileOutputStream file = new FileOutputStream(path);
			JPEGImageEncoder encode = JPEGCodec.createJPEGEncoder(file);
			JPEGEncodeParam param = encode.getDefaultJPEGEncodeParam(buffer);
			param.setQuality(0.5f, false);
			encode.setJPEGEncodeParam(param);
			encode.encode(buffer);
			file.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
