package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface CredentialMapper {
    @Select("SELECT * FROM CREDENTIALS where userId = #{userId}")
    List<Credential> getUserCredentials(Integer userId);

    @Select("SELECT * FROM CREDENTIALS where credentialId = #{credentialId}")
    Credential getCredentialById(Integer credentialId);

    @Insert("INSERT INTO CREDENTIALS ( url, userName, password, key, userId) VALUES ( #{url}, #{userName}, #{password}, #{key}, #{userId} )")
    @Options(useGeneratedKeys = true, keyProperty = "credentialId")
    int insert(Credential credential);

    @Insert("UPDATE CREDENTIALS SET url = #{url}, userName = #{userName} where credentialId = #{credentialId} and userId = #{userId}")
    @Options(useGeneratedKeys = true, keyProperty = "credentialId")
    int update(Credential credential);

    @Delete("DELETE FROM CREDENTIALS where credentialId = ${credentialId} and userId = #{userId}")
    void deleteById(Integer credentialId, Integer userId);
}
