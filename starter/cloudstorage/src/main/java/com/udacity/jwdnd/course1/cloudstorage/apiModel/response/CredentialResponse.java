package com.udacity.jwdnd.course1.cloudstorage.apiModel.response;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;

public class CredentialResponse {
    private String message;
    private Credential credential;

    public CredentialResponse(String message, Credential credential) {
        this.message = message;
        this.credential = credential;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Credential getCredential() {
        return credential;
    }

    public void setCredential(Credential credential) {
        this.credential = credential;
    }
}
