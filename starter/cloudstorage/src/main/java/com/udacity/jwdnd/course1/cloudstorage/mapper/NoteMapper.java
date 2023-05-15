package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface NoteMapper {

    @Select("SELECT * FROM NOTES where userId = #{userId}")
    List<Note> getUserNotes(Integer userId);

    @Select("SELECT * FROM NOTES where noteId = #{noteId}")
    Note getNoteById(Integer noteId);

    @Insert("INSERT INTO NOTES ( userId, noteTitle, noteDescription) VALUES ( #{userId}, #{noteTitle}, #{noteDescription})")
    @Options(useGeneratedKeys = true, keyProperty = "noteId")
    int insert(Note note);

    @Insert("UPDATE NOTES SET noteTitle = #{noteTitle}, noteDescription = #{noteDescription} where noteId = #{noteId}")
    @Options(useGeneratedKeys = true, keyProperty = "noteId", keyColumn = "noteId")
    int update(Note note);

    @Delete("DELETE FROM NOTES where noteId = ${noteId}")
    void deleteById(Integer noteId);
}
