package com.orange.imgcmp;

import ImgComp.ImgComp;

import com.mathworks.toolbox.javabuilder.MWException;

public class Comp {
	
	private String imgPath = System.getProperty("user.dir") + "\\temp\\";
	
	/**
	 * @param args
	 * @author Guojun
	 * 图像比较，只对比R值，相同图像延时平均七秒，不同图像延时10秒
	 * 同时对比R、G、B值延时30秒
	 */
	public boolean comp(String tempImg, String currentImg) {
		// TODO Auto-generated method stub
		Object[] a = {imgPath + tempImg, imgPath + currentImg};
		Object[] res = null;
		
		try {
			ImgComp imgComp = new ImgComp();
			res = imgComp.comp(1, a);
		} catch (MWException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(Integer.parseInt(res[0].toString()) == 1)
			return false;
		else
			return true;
		
	}

}
