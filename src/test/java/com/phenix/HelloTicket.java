package com.phenix;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import com.phenix.httpcommand.Result;
import com.phenix.httpcommand.TicketUpdateCommand;
import sun.misc.BASE64Encoder;

public class HelloTicket {
    public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        long time1 = Calendar.getInstance().getTimeInMillis();
        System.out.println(time1);
        String timestamp = Long.toHexString(time1);
        String template = "https://video3.hnticai.com/%s/%s/haotianlive/stream2/index.m3u8?k=%s&t=%d";
        String key = "2JQZjb$sKO";
        MessageDigest md5Gen = MessageDigest.getInstance("MD5");
        // 延时（秒），5分钟
        int time = 5 * 60;
        String rawStr = key + "/haotianlive/stream2/index.m3u8" + time1;
        md5Gen.update(rawStr.getBytes("UTF-8"));
        byte[] md5Array = md5Gen.digest();
        //byte[]通常我们会转化为十六进制的32位长度的字符串来使用
        String md5 = toHex(md5Array);
        //md5 = bytesToHex1(md5Array);

        String result = String.format(template, key, timestamp, md5, time);
        System.out.println("Result:" + result);
    }

    private static String toHex(byte[] bytes) {
        final char[] HEX_DIGITS = "0123456789ABCDEF".toCharArray();
        StringBuilder ret = new StringBuilder(bytes.length * 2);
        for (int i=0; i<bytes.length; i++) {
            ret.append(HEX_DIGITS[(bytes[i] >> 4) & 0x0f]);
            ret.append(HEX_DIGITS[bytes[i] & 0x0f]);
        }
        return ret.toString();
    }

    private static String bytesToHex1(byte[] md5Array) {
        StringBuilder strBuilder = new StringBuilder();
        for (int i = 0; i < md5Array.length; i++) {
            int temp = 0xff & md5Array[i];
            String hexString = Integer.toHexString(temp);
            //如果是十六进制的0f，默认只显示f，此时要补上0
            if (hexString.length() == 1) {
                strBuilder.append("0").append(hexString);
            } else {
                strBuilder.append(hexString);
            }
        }
        return strBuilder.toString();
    }

    public void AliyunImage() {
        long start = Calendar.getInstance().getTimeInMillis();
        TicketUpdateCommand cmd = new TicketUpdateCommand();
        Map<String, Object> params = new HashMap<String, Object>();

        String imageURL = "../resources/ticket1.jpg";
        BASE64Encoder encoder = new BASE64Encoder();
        StringBuilder pictureBuffer = new StringBuilder();
        InputStream input = null;
        try {
            input = new FileInputStream(new File(imageURL));

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] temp = new byte[1024];
            for(int len = input.read(temp); len != -1;len = input.read(temp)){
                out.write(temp, 0, len);
                pictureBuffer.append(encoder.encode(out.toByteArray()));
                out.reset();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        params.put("image_base64", pictureBuffer.toString());
        Result result = cmd.execute(params);
        System.out.println(result);
        System.out.println(Calendar.getInstance().getTimeInMillis()-start);
    }
}
