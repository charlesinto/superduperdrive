package com.udacity.jwdnd.course1.cloudstorage.services.Impl;

import com.udacity.jwdnd.course1.cloudstorage.apiModel.request.SignupRequest;
import com.udacity.jwdnd.course1.cloudstorage.apiModel.response.SignupResponse;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.EncryptionService;
import com.udacity.jwdnd.course1.cloudstorage.services.HashService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Base64;

@Service
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
    private final HashService hashService;

    private final EncryptionService encryptionService;

    private final AuthenticationManager authenticationManager;

    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserMapper userMapper, HashService hashService, EncryptionService encryptionService, AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder) {
        this.userMapper = userMapper;
        this.hashService = hashService;
        this.encryptionService = encryptionService;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean isUserNameAvailable(String userName) {
        return userMapper.getUserByUserName(userName) == null;
    }

    @Override
    public int CreateUser(User user) {
        SecureRandom random = new SecureRandom();

        byte[] salt = new byte[16];
        random.nextBytes(salt);
        String encodedSalt = Base64.getEncoder().encodeToString(salt);

        String hashedPassword = hashService.getHashedValue(user.getPassword(), encodedSalt);
        return userMapper.insert(new User(null, user.getUserName(), encodedSalt, passwordEncoder.encode(user.getPassword()), user.getFirstName(), user.getLastName()));
    }



    @Override
    public User getUserByUserName(String user) {
        return userMapper.getUserByUserName(user);
    }

    @Override
    public User getUserById(String userId) {
        return userMapper.getUserById(userId);
    }

    @Override
    public SignupResponse signupUser(SignupRequest data) {
        if(this.isUserNameAvailable(data.getUserName())){
            int rows = this.CreateUser(new User(null, data.getUserName(), null, data.getPassword(), data.getFirstName(), data.getLastName()));
            if(rows < 0)
                return new SignupResponse("There was an error signig you up. Please try again", null);

            User user = this.getUserByUserName(data.getUserName());

            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUserName(), data.getPassword(), new ArrayList<>()));

            if(authentication.isAuthenticated()){
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }


            return new SignupResponse("Login successful", user);
        }

        return new SignupResponse("The username already exists", null);
    }
}
