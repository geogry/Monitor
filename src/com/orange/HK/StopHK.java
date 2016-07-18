package com.orange.HK;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

public class StopHK {

	static{   
        //下面这部分是为了增加灵活性，dll可以放到jar包中  
        try  
        {  
            File file = File.createTempFile("StopHK", ".dll");  
            FileOutputStream fout = new FileOutputStream(file);  
            InputStream in = StopHK.class.getResourceAsStream("StopHK.dll");  
              
            byte[] b = new byte[1024];  
            int len = 0;   
            while((len = in.read(b)) != -1){  
                fout.write(b, 0, len);  
            }  
              
            fout.flush();  
            in.close();  
            fout.close();  
              
            System.load(file.getAbsolutePath());  
        }   
        catch (Exception e) {}  
          
//        System.load("D:/shieldHK.dll");
    }   
    public static native void Attach();//启动屏蔽   
    public static native void Detach();//关闭屏蔽
}
