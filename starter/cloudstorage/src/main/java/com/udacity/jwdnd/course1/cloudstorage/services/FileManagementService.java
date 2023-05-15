package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface FileManagementService {
    List<File> getUserFiles(Authentication authentication);

    File uploadFile(Authentication authentication, MultipartFile file);

    void deleteFile(Authentication authentication,  Integer fileId);
}
