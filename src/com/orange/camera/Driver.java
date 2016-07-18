package com.orange.camera;

import java.io.IOException;

import javax.media.CannotRealizeException;
import javax.media.CaptureDeviceInfo;
import javax.media.CaptureDeviceManager;
import javax.media.Manager;
import javax.media.MediaLocator;
import javax.media.NoPlayerException;
import javax.media.Player;

public class Driver {
	
	private CaptureDeviceInfo info = null;
	
	private MediaLocator mediaLocator = null;
	
	private Player player = null;
	
	/*
	 * »ñÈ¡Player
	 */
	public Player getDriver(){
		String driverStr = "vfw:Microsoft WDM Image Capture (Win32):0";//Çý¶¯×Ö·û´®
		info = CaptureDeviceManager.getDevice(driverStr);
		mediaLocator = info.getLocator();
		try {
			player = Manager.createRealizedPlayer(mediaLocator);
		} catch (NoPlayerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CannotRealizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return player;
	}
	
	public void closePlayer(Player player){
		this.player = player;
		player.close();
	}
}
