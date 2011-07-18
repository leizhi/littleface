package com.mooo.mycoz.tools.test;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.mooo.mycoz.common.StringUtils;

/**
* 功能说明：访问指定的URL并检查返回结果。
* @param strUrl
* @param successFlag 请求成功的标识，比如包含“_SUCCESS”字。
* @return
*/
public class HttpConnection{
	
	public boolean visitURL(String strUrl, String successFlag) {
   
    boolean rs = false;
    
    HttpURLConnection jconn = null;
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

    try {
        URL url = new URL(strUrl);

        jconn = (HttpURLConnection) url.openConnection();
        jconn.setDoOutput(true);
        jconn.setDoInput(true);
        jconn.connect();
       
        InputStream in = jconn.getInputStream();
        byte[] buf = new byte[4096];

        int bytesRead;
        while ((bytesRead = in.read(buf)) != -1) {
            byteArrayOutputStream.write(buf, 0, bytesRead);
        }

        String strRead = new String(byteArrayOutputStream.toByteArray());
       
        strRead = StringUtils.escapeHTMLTags(strRead);
       
        System.out.println("strRead : " + strRead);

        if(strRead.indexOf(successFlag) != -1) {
            System.out.println("Visit URL < " + strUrl + " > success !");
            rs = true;
        }
       
    } catch (MalformedURLException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    } finally {
    	
        jconn.disconnect();

        try {
            byteArrayOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
   
    return rs;
}

}