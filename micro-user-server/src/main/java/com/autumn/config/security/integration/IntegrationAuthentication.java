package com.autumn.config.security.integration;


import lombok.Data;

import java.util.Map;

@Data
public class IntegrationAuthentication {

    private String authType;
    private String username;
    private Map<String,String[]> authParameters;
    private String errorMsg;

    public String getAuthParameter(String paramter){
        String[] values = this.authParameters.get(paramter);
        if(values != null && values.length > 0){
            return values[0];
        }
        return null;
    }

    public String getAuthType() {
        return authType;
    }

    public void setAuthType(String authType) {
        this.authType = authType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Map<String, String[]> getAuthParameters() {
        return authParameters;
    }

    public void setAuthParameters(Map<String, String[]> authParameters) {
        this.authParameters = authParameters;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
