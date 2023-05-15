package com.udacity.jwdnd.course1.cloudstorage.services.Impl;

import com.udacity.jwdnd.course1.cloudstorage.apiModel.request.CredentialRequest;
import com.udacity.jwdnd.course1.cloudstorage.apiModel.response.CredentialResponse;
import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.EncryptionService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CredentialServiceImpl implements CredentialService {
    private final CredentialMapper credentialMapper;
    private final UserService userService;
    private final EncryptionService encryptionService;

    public CredentialServiceImpl(CredentialMapper credentialMapper, UserService userService, EncryptionService encryptionService) {
        this.credentialMapper = credentialMapper;
        this.userService = userService;
        this.encryptionService = encryptionService;
    }

    @Override
    public List<Credential> getUserCredentials(Authentication authentication) {
        User user = userService.getUserByUserName(authentication.getName());
        if(user == null) return new ArrayList<>();
        return credentialMapper.getUserCredentials(user.getUserId())
                    .stream().map(this::addDecryptedPassword)
                    .collect(Collectors.toList());
    }

    public Credential addDecryptedPassword(Credential credential){
        String decryptedPassword = encryptionService.decryptValue(credential.getPassword(), credential.getKey());
        credential.setDecryptedPassword(decryptedPassword);

        return credential;
    }

    @Override
    public CredentialResponse addCredential(Authentication authentication, CredentialRequest credentialRequest) {
        User user = userService.getUserByUserName(authentication.getName());
        if(user == null) return new CredentialResponse("Unable to perform action", null);

        credentialRequest.setUserId(user.getUserId());

        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        String encodedSalt = Base64.getEncoder().encodeToString(salt);

        int row = credentialMapper.insert(new Credential(null, credentialRequest.getUserName(),encodedSalt, encryptionService.encryptValue(credentialRequest.getPassword(), encodedSalt), credentialRequest.getUrl(), credentialRequest.getUserId()));

        if(row < 0) return new CredentialResponse("Unable to complete requees. Please try again", null);

        Credential credential = credentialMapper.getCredentialById(row);

        return new CredentialResponse("Operation successful", credential);
    }

    @Override
    public CredentialResponse updateCredential(Authentication authentication, CredentialRequest credentialRequest) {
        User user = userService.getUserByUserName(authentication.getName());
        if(user == null) return new CredentialResponse("Unable to perform action", null);

        credentialRequest.setUserId(user.getUserId());

        int row = credentialMapper.update(new Credential(credentialRequest.getCredentialId(), credentialRequest.getUserName(), null, credentialRequest.getPassword(), credentialRequest.getUrl(), user.getUserId()));

        if(row < 0) return new CredentialResponse("Unable to complete requees. Please try again", null);

        Credential credential = credentialMapper.getCredentialById(row);

        return new CredentialResponse("Operation successful", credential);
    }

    @Override
    public void deleteCredential(Authentication authentication, Integer credentialId) {
        User user = userService.getUserByUserName(authentication.getName());
        if(user == null) return ;

         credentialMapper.deleteById(credentialId, user.getUserId());
    }

}
