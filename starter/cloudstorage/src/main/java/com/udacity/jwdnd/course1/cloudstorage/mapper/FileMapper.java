package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

/*
private String fileName;
    private String contentType;
    private String fileSize;
    private Integer userId;
    private byte[] fileData;
 */

@Mapper
@Component
public interface FileMapper {

    @Select("SELECT * FROM FILES where userId = #{userId}")
    List<File> getUserFiles(Integer userId);

    @Select("SELECT * FROM FILES where fileId = #{fileId}")
    File getFileById(Integer fileId);

    @Insert("INSERT INTO FILES ( fileName, contentType, fileSize, userId, fileData) VALUES ( #{fileName}, #{contentType}, #{fileSize}, #{userId}, #{fileData} )")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    int insert(File file);

    @Delete("DELETE FROM FILES where fileId = ${fileId} and userId = #{userId}")
    void deleteById(Integer fileId, Integer userId);
}
