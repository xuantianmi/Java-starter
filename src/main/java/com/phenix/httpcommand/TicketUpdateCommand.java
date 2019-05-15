package com.phenix.httpcommand;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import com.alibaba.fastjson.JSONObject;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpHeaders;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;

/**
 * 阿里云图片识别demo
 * @author Qujing
 */
public class TicketUpdateCommand extends HttpPostCommand {
    JSONObject params = new JSONObject();

    @Override
    protected void setAdditionalHeaders() {
        String degist = "";
        try {
            degist = this.signature(
                this.params.getString("appId")
                , this.params.getString("appKey")
                , this.params.getString("screteKey")
                , this.params.getString("timestamp")
                , this.params.getString("nonce"));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        this.httpRequest.addHeader("Authorization", degist);
    }

    private void initParams() {
        String appId = "100401";
        String appKey = "aVQJFSmHHyo_ih1TITPqe4ofKhwNhANrKpzpJXtE-AA";
        String screteKey = "0yrtd_AB-xsV7wkm1L8JfQvqYjA7K450p-rXPKhCARg";
        String timestamp = String.valueOf(Calendar.getInstance().getTimeInMillis());
        Random ranGen = new Random();
        int ran = ranGen.nextInt(99999);
        String nonce = String.format("%05d", ran);

        this.params.put("appId", appId);
        this.params.put("appKey", appKey);
        this.params.put("screteKey", screteKey);
        this.params.put("timestamp", timestamp);
        this.params.put("nonce", nonce);
    }

    @Override
    protected void setHttpRequestParams(List<NameValuePair> urlParameters){
        try {
            this.initParams();
            for(NameValuePair item: urlParameters) {
                this.params.put(item.getName(), item.getValue().toString());
            }
            StringEntity postParams = new StringEntity(this.params.toString());

            HttpPost httpPost = new HttpPost(this.getRequestURL());
            httpPost.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
            httpPost.setEntity(postParams);
            this.httpRequest = httpPost;
            this.setAdditionalHeaders();
        } catch (Exception ex) {

        }
    }


    /**
     * 上传票图片并返回解析字符串的URL
     */
    private static final String NEW_RECORD_URL = URL_PRE + "wordrecognize";

    @Override
    protected String getRequestURL() {
        return NEW_RECORD_URL;
    }

    @Override
    public Result execute(Map<String, Object> paramMap) {
        return super.execute(paramMap);
    }

    /**
     * signature method
     * @return
     */
    private String signature(String appId, String appKey, String screteKey, String timestamp, String nonce) throws NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException {
        String expiredTime = "1800";

        String signStr = appId + "." + appKey + "." + timestamp + "." + expiredTime + "." + nonce;

        Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
        SecretKeySpec secretKey = new SecretKeySpec(screteKey.getBytes("utf-8"), "HmacSHA256");
        sha256_HMAC.init(secretKey);
        byte[] hash = sha256_HMAC.doFinal(signStr.getBytes("utf-8"));
        String encodeStr = Base64.encodeBase64String(hash);

        String str = "v1." + encodeStr + "." + signStr;
        String finalSignature = Base64.encodeBase64String(str.getBytes("utf-8"));

        /*
         * check and print results
         */

        System.out.println("encodeStr: " + encodeStr);

        System.out.println("str: " + str);

        System.out.println("finalSignature: " + finalSignature);
        return finalSignature;
    }
}
