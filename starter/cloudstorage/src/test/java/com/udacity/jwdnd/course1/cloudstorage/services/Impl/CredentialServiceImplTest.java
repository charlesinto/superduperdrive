package com.udacity.jwdnd.course1.cloudstorage.services.Impl;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.core.Authentication;

import static org.junit.jupiter.api.Assertions.*;

class CredentialServiceImplTest {

    @Mock
    CredentialMapper credentialMapper;

    @InjectMocks
    UserService userService;

    @InjectMocks
    CredentialService credentialService;

    @Mock
    Authentication authentication;

    @Test
    void getUserCredentials() {
    }

    @Test
    void addDecryptedPassword() {
    }

    @Test
    void addCredential() {
    }

    @Test
    void updateCredential() {
    }

    @Test
    void deleteCredential() {
    }
}