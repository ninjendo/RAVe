package com.ninjendo.rave.config;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;

@Component
public class SFDataConfig {

    @NotEmpty
    private String hostUrl;

    @NotEmpty
    private String contextPath;

    private String appToken;

    @Min(1)
    @Max(65535)
    private Integer updateInterval;

    @JsonProperty
    public String getHostUrl() {
        return hostUrl;
    }

    @JsonProperty
    public void setHostUrl(String hostUrl) {
        this.hostUrl = hostUrl;
    }

    @JsonProperty
    public String getContextPath() {
        return contextPath;
    }

    @JsonProperty
    public void setContextPath(String contextPath) {
        this.contextPath = contextPath;
    }

    @JsonProperty
    public String getAppToken() {
        return appToken;
    }

    @JsonProperty
    public void setAppToken(String appToken) {
        this.appToken = appToken;
    }

    @JsonProperty
    public Integer getUpdateInterval() {
        return updateInterval;
    }

    @JsonProperty
    public void setUpdateInterval(Integer updateInterval) {
        this.updateInterval = updateInterval;
    }

    public String getFullURL() {
        return hostUrl + contextPath + (!"".equals(appToken) ? "?$$app_token=" + appToken : "");
    }
}
