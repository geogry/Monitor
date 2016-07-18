package com.orange.util;

import java.io.IOException;


public class Operation {
	
	public static void shutdown(){
		try {
			Runtime.getRuntime().exec("shutdown -p");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void logoff(){
		try {
			Runtime.getRuntime().exec("shutdown -l");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void dormant(){
		try {
			Runtime.getRuntime().exec("shutdown -h");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
