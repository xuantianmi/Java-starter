package com.phenix.httpcommand;

import java.net.URI;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URLEncodedUtils;

/**
 * @author Qujing
 */
public abstract class HttpGetCommand extends AbstractCommand {
    @Override
    protected void setHttpRequestParams(List<NameValuePair> params){
        String param = URLEncodedUtils.format(params, "UTF-8");
        httpRequest = new HttpGet();
        httpRequest.setURI(URI.create(this.getRequestURL() + "?" + param));
    }

}
