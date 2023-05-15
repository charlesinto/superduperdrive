package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.apiModel.request.NoteRequest;
import com.udacity.jwdnd.course1.cloudstorage.apiModel.response.NoteResponse;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface NoteService {

     List<Note> getUserNotes(Authentication authentication);

     NoteResponse addNote(Authentication authentication, NoteRequest noteRequest);

    NoteResponse updateNote(Authentication authentication, NoteRequest noteRequest);

    void deleteNote(Authentication authentication,Integer noteId);

}
