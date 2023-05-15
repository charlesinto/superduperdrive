package com.udacity.jwdnd.course1.cloudstorage.services.Impl;

import com.udacity.jwdnd.course1.cloudstorage.apiModel.request.NoteRequest;
import com.udacity.jwdnd.course1.cloudstorage.apiModel.response.NoteResponse;
import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NoteServiceImpl implements NoteService {

    private final NoteMapper noteMapper;
    private final UserService userService;

    public NoteServiceImpl(NoteMapper noteMapper, UserService userService) {
        this.noteMapper = noteMapper;
        this.userService = userService;
    }

    @Override
    public List<Note> getUserNotes(Authentication authentication) {
        User user = userService.getUserByUserName(authentication.getName());
        if(user == null) return new ArrayList<>();
        return noteMapper.getUserNotes(user.getUserId());
    }

    @Override
    public NoteResponse addNote(Authentication authentication, NoteRequest noteRequest) {
        User user = userService.getUserByUserName(authentication.getName());
        if(user == null) return new NoteResponse("Unable to perform action", null);

        noteRequest.setUserId(user.getUserId());
        int row = noteMapper.insert(new Note(null, noteRequest.getNoteTitle(), noteRequest.getNoteDescription(), noteRequest.getUserId()));

        if(row < 0) return new NoteResponse("Unable to complete requees. Please try again", null);

        Note note = noteMapper.getNoteById(row);

        return new NoteResponse("Operation successful", note);

    }

    @Override
    public NoteResponse updateNote(Authentication authentication,NoteRequest noteRequest) {
        User user = userService.getUserByUserName(authentication.getName());
        if(user == null) return new NoteResponse("Unable to perform action", null);
        noteRequest.setUserId(user.getUserId());

        int row = noteMapper.update(new Note(noteRequest.getNoteId(), noteRequest.getNoteTitle(), noteRequest.getNoteDescription(),null));

        if(row < 0) return new NoteResponse("Unable to complete requees. Please try again", null);

        Note note = noteMapper.getNoteById(row);

        return new NoteResponse("Operation successful", note);
    }

    @Override
    public void deleteNote(Authentication authentication, Integer noteId) {
        noteMapper.deleteById(noteId);
    }


}
