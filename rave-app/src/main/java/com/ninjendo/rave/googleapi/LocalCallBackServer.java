package com.ninjendo.rave.googleapi;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.api.client.extensions.java6.auth.oauth2.VerificationCodeReceiver;
import com.google.api.client.http.GenericUrl;

public class LocalCallBackServer implements VerificationCodeReceiver {
    volatile String code;
    private final int LOCAL_SERVER_PORT = 10006;
    private String localServerUri = null; 
    private String callBackPath = null;

    private static final Logger LOGGER = LoggerFactory.getLogger(LocalCallBackServer.class);
    
    public LocalCallBackServer(String requestUrl, String callbackPath){
    	//this.redirectUri = req.getRequestURL().toString();
    	this.localServerUri = requestUrl;
    	this.callBackPath = callbackPath;
    }
    @Override
    public synchronized String waitForCode() {

        try {
            this.wait();
        } catch (Exception ex) {
        }
        System.out.println("returning code is -> " + code);
        return code;

    }

    @Override
    public String getRedirectUri() {

    	GenericUrl url = new GenericUrl(this.localServerUri);
        url.setRawPath("/" + callBackPath);
        LOGGER.info("callback URL -> " + url.build());
        return url.build();
    }
	@Override
	public void stop() throws IOException {
		// TODO Auto-generated method stub
		
	}


}
