package com.phenix.httpcommand;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Qujing
 */
public abstract class AbstractCommand implements Command{
    protected HttpRequestBase httpRequest;

    protected final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    protected static final String URL_PRE = "http://open-ai.alibaba.com/api/image/v2/";

    /**
     * 获得请求地址
     * @return
     */
    protected abstract String getRequestURL();

    /**
     * 配置请求参数（Get/Post请求分别由对应的继承类实现）
     * @param params
     */
    protected abstract void setHttpRequestParams(List<NameValuePair> params);

    /**
     * 此方法可以被覆盖，用于配置业务统一的初始request参数
     */
    protected void initGlobalParams(List<NameValuePair> params) {
        //params.add(new BasicNameValuePair("key", "value"));
    }

    @Override
    public Result execute(Map<String, Object> paramMap) {
        Result resultObj = null;

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        this.initGlobalParams(params);

        for(Map.Entry<String, Object> entry: paramMap.entrySet()) {
            params.add(new BasicNameValuePair(entry.getKey(), entry.getValue().toString()));
        }

        this.setHttpRequestParams(params);

        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            CloseableHttpResponse httpResponse = httpClient.execute(this.httpRequest);
            LOGGER.debug("GET Response Status::{} ", httpResponse.getStatusLine().getStatusCode());
            String result = this.getHttpEntityContent(httpResponse);
            resultObj= JSONObject.parseObject(result, Result.class);

            // print result
            LOGGER.info(result);
            httpClient.close();
        } catch (IOException ex) {
            ex.printStackTrace();
            LOGGER.error(ex.getMessage());
        }

        return resultObj;
    }

    /**
     * 获得响应HTTP实体内容
     * @param response
     * @return
     * @throws IOException
     */
    private String getHttpEntityContent(HttpResponse response) throws IOException {
        //通过HttpResponse 的getEntity()方法获取返回信息
        org.apache.http.HttpEntity entity = response.getEntity();
        if (entity != null) {
            StringBuffer sb = new StringBuffer();

            InputStream is = entity.getContent();
            BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));

            String line = br.readLine();
            while (line != null) {
                sb.append(line);
                line = br.readLine();
            }

            br.close();
            is.close();
            return sb.toString();
        }
        return "";
    }

}
