package com.ninjendo.rave.config;

import javax.validation.constraints.NotNull;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="upload")
public class UploadConfig {

    @NotNull
    private String uploadPath;
    
    @NotNull
    private String hudNetBidFile;
    
    public String getUploadPath() {
        return uploadPath;
    }

    public void setUploadPath(String uploadPath) {
        this.uploadPath = uploadPath;
    }

	public String getHudNetBidFile() {
		return hudNetBidFile;
	}

	public void setHudNetBidFile(String hudNetBidFile) {
		this.hudNetBidFile = hudNetBidFile;
	}
    
    
}
