package com.phenix.httpcommand;

import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;

/**
 * @author Qujing
 */
public abstract class HttpPostCommand extends AbstractCommand {
    abstract void setAdditionalHeaders();

    @Override
    protected void setHttpRequestParams(List<NameValuePair> urlParameters){
        try {
            HttpEntity postParams = new UrlEncodedFormEntity(urlParameters, "UTF-8");

            HttpPost httpPost = new HttpPost(this.getRequestURL());
            httpPost.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
            httpPost.setEntity(postParams);
            this.httpRequest = httpPost;
            this.setAdditionalHeaders();
        } catch (Exception ex) {

        }
    }

}
