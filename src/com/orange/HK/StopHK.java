package com.orange.HK;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

public class StopHK {

	static{   
        //�����ⲿ����Ϊ����������ԣ�dll���Էŵ�jar����  
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
    public static native void Attach();//��������   
    public static native void Detach();//�ر�����
}
