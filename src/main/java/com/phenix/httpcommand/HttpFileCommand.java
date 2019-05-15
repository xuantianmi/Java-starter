package com.phenix.httpcommand;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;

/**
 * @author Qujing
 */
public abstract class HttpFileCommand extends AbstractCommand {

    @Override
    protected void setHttpRequestParams(List<NameValuePair> params) {
        HttpPost httpPost = new HttpPost(this.getRequestURL());

        HttpEntity postParams = null;

        try {
            postParams = new UrlEncodedFormEntity(params, "UTF-8");
        } catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
        }

        File file = null;
        MultipartEntityBuilder mEntityBuilder = MultipartEntityBuilder.create();
        mEntityBuilder.addBinaryBody("file", file);
        for(NameValuePair entry: params) {
            mEntityBuilder.addTextBody(entry.getName(), entry.getValue());
        }

        httpPost.setEntity(mEntityBuilder.build());

        this.httpRequest = httpPost;

    }
}
