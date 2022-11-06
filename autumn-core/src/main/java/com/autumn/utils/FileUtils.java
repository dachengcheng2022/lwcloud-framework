package com.autumn.utils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileUtils {




    public static void saveUrlToFile(String fromUrl,String toFilePath,String filename,boolean isRedownload) {
        File fileDir = new File(toFilePath);
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }
        File f = new File(toFilePath + File.separator + filename);
        if (f.exists()) {
            if(isRedownload) {
                f.delete();
            }else{
                return;
            }
        }else{
            f.getParentFile().mkdirs();
        }
        //url 编码
        char[] ch = fromUrl.toCharArray();
        for (int i = 0; i < ch.length; i++) {
            char c = ch[i];
            if(CharUtil.isChinese(c)){
                try {
                    fromUrl = fromUrl.replaceAll(String.valueOf(c), URLEncoder.encode(String.valueOf(c),"utf-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        }

        FileOutputStream fos = null;
        BufferedInputStream bis = null;
        HttpURLConnection httpUrl = null;
        URL url = null;
        int BUFFER_SIZE = 1024;
        byte[] buf = null;
        int size = 0;
        try {
            url = new URL(fromUrl);
            httpUrl = (HttpURLConnection) url.openConnection();
            httpUrl.setConnectTimeout(10*1000);
            httpUrl.setReadTimeout(20*1000);
            httpUrl.connect();
            bis = new BufferedInputStream(httpUrl.getInputStream());
            buf = new byte[httpUrl.getContentLength()];
            fos = new FileOutputStream(f);
            while ((size = bis.read(buf)) != -1) {
                fos.write(buf, 0, size);
            }
            fos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassCastException e) {
            e.printStackTrace();
        } finally {
            try {
                if(fos!=null)fos.flush();
                if(fos!=null)fos.close();
                if(bis!=null)bis.close();
                httpUrl.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }
    }
}
