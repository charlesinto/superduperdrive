package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.apiModel.request.CredentialRequest;
import com.udacity.jwdnd.course1.cloudstorage.apiModel.response.CredentialResponse;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface CredentialService {

    List<Credential> getUserCredentials(Authentication authentication);

    CredentialResponse addCredential(Authentication authentication, CredentialRequest credentialRequest);

    CredentialResponse updateCredential(Authentication authentication, CredentialRequest credentialRequest);

    void deleteCredential(Authentication authentication,Integer credentialId);
}
