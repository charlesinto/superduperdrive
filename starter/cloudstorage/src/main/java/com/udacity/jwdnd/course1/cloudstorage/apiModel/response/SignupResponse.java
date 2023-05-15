package com.udacity.jwdnd.course1.cloudstorage.apiModel.response;

import com.udacity.jwdnd.course1.cloudstorage.model.User;

public class SignupResponse {
    private String message;
    private User user;

    public SignupResponse(String message, User user) {
        this.message = message;
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
