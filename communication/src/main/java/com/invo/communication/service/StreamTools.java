package com.invo.communication.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by INvo on 2019-10-06.
 */
public class StreamTools {
    /**
     * °ÑÊäÈëÁ÷µÄÄÚÈÝ×ª»¯³É×Ö·û´®
     * @param is
     * @return
     * @throws IOException
     */
    public static String readInputStream(InputStream is){
        try {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            int len = 0;
            byte[] buffer = new byte[1024];
            while((len=is.read(buffer))!=-1){
                stream.write(buffer, 0, len);
            }
            is.close();
            stream.close();
            byte[] result = stream.toByteArray();
            String temp = new String(result);
            return temp;
        } catch (Exception e) {
            e.printStackTrace();
            return "»ñÈ¡Ê§°Ü";
        }

    }
}
