package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.apiModel.request.SignupRequest;
import com.udacity.jwdnd.course1.cloudstorage.apiModel.response.SignupResponse;
import com.udacity.jwdnd.course1.cloudstorage.model.User;


public interface UserService {

    boolean isUserNameAvailable(String userName);

    int CreateUser(User user);

    User getUserByUserName(String user);

    User getUserById(String userId);

    SignupResponse signupUser(SignupRequest data);

}
