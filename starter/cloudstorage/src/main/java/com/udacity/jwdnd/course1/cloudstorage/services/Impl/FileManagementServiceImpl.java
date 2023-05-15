package com.udacity.jwdnd.course1.cloudstorage.services.Impl;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.FileManagementService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileManagementServiceImpl implements FileManagementService {
   private final UserService userService;
   private final FileMapper fileMapper;
   private final Logger logger = LoggerFactory.getLogger(FileManagementService.class);

    public FileManagementServiceImpl(UserService userService, FileMapper fileMapper) {
        this.userService = userService;
        this.fileMapper = fileMapper;
    }


    @Override
    public List<File> getUserFiles(Authentication authentication) {
        User user = userService.getUserByUserName(authentication.getName());
        if(user == null) return new ArrayList<>();
        return fileMapper.getUserFiles(user.getUserId());
    }

    @Override
    public File uploadFile(Authentication authentication, MultipartFile file) {
       try{
           User user = userService.getUserByUserName(authentication.getName());
           if(user == null) return  null;

           InputStream inputStream = new BufferedInputStream(file.getInputStream());

           int fileId = fileMapper.insert(
                   new File(null, file.getOriginalFilename(), file.getContentType(),
                           file.getSize(), user.getUserId(),
                           inputStream));
           if(fileId < 0) return null;

           return fileMapper.getFileById(fileId);
       }catch (IOException exception){
           logger.error("Unable to upload file: " + exception.getMessage());
           return null;
       }
    }

    @Override
    public void deleteFile(Authentication authentication, Integer fileId) {
        fileMapper.deleteById(fileId);
    }
}
